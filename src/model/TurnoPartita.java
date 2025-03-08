package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.Timer;

/**
 * Classe che rappresenta la turnazione dei 4 giocatori
 * @author michele marchetti	
 *
 */
public class TurnoPartita {
	
	/*
	 * variabile  Indice che indica il giocatore in turno
	 */
	private int indice;
	

	/**
	 * variabile che rappresenta la Lista che contiene tutti i giocatori 
	 */
	private List<Giocatore> listaGiocatori;
	
	/**
	 * Variaible che contiene il riferimento alla Pila degli scarti
	 */
	private PilaScarti pilaScarti;
	
	
	/**
	 * Variabile che contiene il timer che gestisce i turni
	 */
	private Timer timer;
	
	
	/**
	 *  Variabile che indica se la turnazione e' in senso orario
	 */
	private boolean sensoOrario;
	
	/**
	 *Variabile che contiene il timer UNO
	 */
	private TimerUno timerUno;
	
	/**
	 * Variabile che contiene il timer UNO avversario
	 *
	 */
	private TimerUnoAvversario timerUnoAvversario;

	/*
	 * Metodo costruttore
	 */
	public TurnoPartita(PilaScarti pilaScarti) {
		this.pilaScarti = pilaScarti;
		this.listaGiocatori = new ArrayList<Giocatore>();
		this.sensoOrario = true;
		this.timerUno = new TimerUno();
		this.timerUnoAvversario = new TimerUnoAvversario();
		
		// timer che gestisce la turnazione --------------------------------
		
		int delay = 2500; //milliseconds
		ActionListener task =  new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		    	  controllaCarta();
		    	  incrementaIndice();
		    	  turnoAttuale();
		    	  CampoDiGioco.instance().aggiorna();
		    	  System.out.println("STO ESEGUENDO I TURNI INTERVALLATI");
		      }
		  };
		this.timer = new Timer(delay, task);
	}
		//------------------------------------------------------------------

	/**
	 * Metodo che serve per gestire il turno attuale
	 */
	public void turnoAttuale() {
		
		Giocatore g = listaGiocatori.get(indice); //controllo il giocatore corrispondente all indice
		
		if (g instanceof GiocatoreAvversario) // se il giocatore e' un avversario
		{
			try {
				GiocatoreAvversario gAvv = (GiocatoreAvversario) g;
				
				if (gAvv.isTurnoBloccato() == false) //se il giocatore non ha il turno bloccato
					{
					
						gAvv.giocaTurno(); //eseguo il metodo per giocare il turno
						prossimoTurno(); // e poi invoco il prossimo turno
					}
				else
					{
	
						gAvv.setTurnoBloccato(false); //altrimenti rimuovo il vincolo del turno bloccato 
						incrementaIndice();
						turnoAttuale();
						
					}
			} catch (GridaUnoExceprtion e) { //se rimane con una carta in mano
				timer.stop();
				timerUnoAvversario.getTimer().start();
				CampoDiGioco.instance().setUno(true); 
				CampoDiGioco.instance().aggiorna(); 
				
			} catch (ManoVuotaException e) { //se rimane senza carte in mano
				timer.stop();
				
				List<Giocatore> listaGiocatoriRimanenti = new ArrayList<Giocatore>();
				
				for (Giocatore giocatore: listaGiocatori)
				{
					listaGiocatoriRimanenti.add(giocatore);
				}
				listaGiocatoriRimanenti.remove(indice);
				
				int risultato = 0; 
				
				for (Giocatore giocatoreRimanente: listaGiocatoriRimanenti)
				{
					int punti = giocatoreRimanente.calcolaPuntiCarte();
					risultato += punti;
				}
				
				try {
					g.aggiungiPunti(risultato);
					CampoDiGioco.instance().setRoundTerminato(true);
					CampoDiGioco.instance().aggiorna();
				} catch (PartitaTerminataException e1) {
					System.out.println("Ha vinto il giocatore: "+ g.getNickname());
					CampoDiGioco.instance().getGiocatoreUtente().sconfitta();
					CampoDiGioco.instance().setPartitaTerminata(true);
				}
				
		

				}
			}
			
			else if ( g instanceof GiocatoreUtente) //altrimenti se il giocatore e' l'utente
			{
				GiocatoreUtente gUtente = (GiocatoreUtente) g;
				
				if (gUtente.isTurnoBloccato() == false)//se il giocatore non ha il turno bloccato
					{
						System.out.println("turno del giocatore utente"); //aspetto che il giocatore faccia una mossa	
						
						timer.stop();
						gUtente.setTurnoInCorso(true);
					}
				else
					{
					gUtente.setTurnoBloccato(false); //altrimenti rimuovo il vincolo del turno bloccato
					incrementaIndice();
					turnoAttuale();
					}
			}
		
	
		
	}
	
	/**
	 * Metodo privato che controlla se la carta giocata dal giocatore sia speciale o normale
	 */
	private void controllaCarta() {
		
		Carta carta = pilaScarti.guardaCima();
		if (carta.getValore() instanceof ValoreCartaSpeciale && carta.isEffettoUtilizzato()==false)
			{
				ValoreCartaSpeciale vs = (ValoreCartaSpeciale) carta.getValore();
				switch ( vs) {
				case PESCA_2:
					giocatoreSuccessivo().pesca(2);
					giocatoreSuccessivo().setTurnoBloccato(true);
					carta.setEffettoUtilizzato(true);
					break;
				case SALTA_TURNO:
					giocatoreSuccessivo().setTurnoBloccato(true);;
					carta.setEffettoUtilizzato(true);
					break;
				case INVERTI:
					invertiSenso();
					carta.setEffettoUtilizzato(true);
					break;
				case JOLLY:
					carta.setEffettoUtilizzato(true);
					break;
				case JOLLY_PESCA_4:
					giocatoreSuccessivo().pesca(4);
					giocatoreSuccessivo().setTurnoBloccato(true);
					carta.setEffettoUtilizzato(true);
					break;
				default:
					break;
				}
			}
		CampoDiGioco.instance().aggiorna();
		
	}
	
	
	/**
	 * Metodo privato che fe eseguire il turno successivo
	 */
	public void prossimoTurno() {
		timer.start();

	}
	
	/**
	 * Metodo che ritorna il giocatore successivo rispetto a quello in turno
	 * @return Giocatore
	 */
	public Giocatore giocatoreSuccessivo() {
		int indiceX=0;
		if (indice == listaGiocatori.size()-1) 
		{
			 indiceX = 0; 
		}
	else
		{
			indiceX = indice+1; 
		}
		return listaGiocatori.get(indiceX);
	}
	
	/**
	 * Metodo che ritorna il giocatore precedente rispetto a quello in turno
	 * @return Giocatore
	 */
	public Giocatore giocatorePrecedente() {
		
		int indiceX=0;
		
		if (indice== 0)
		{
			indiceX = listaGiocatori.size()-1; 
		}
	else
		{
			indiceX = indice-1; 
		}
		return listaGiocatori.get(indiceX);
	}
	
	/**
	 * Metodo che serve per spostarsi avanti di una posizione nella lista dei giocatori
	 */
	public void incrementaIndice(){
		if (indice== listaGiocatori.size()-1) //se l'indice e' all utima posizione
			{
				indice = 0; // lo riporto al inizio
			}
		else
			{
				indice++; // altrimenti lo incremento
			}
	}
	
	/**
	 * Metodo che serve per spostarsi indietro di una posizione nella lista dei giocatori
	 */
	public void decrementaIndice() {
		if (indice== 0) //se l'indice e' all prima posizione
			{
				indice = listaGiocatori.size()-1; // lo riporto alla fine
			}
		else
			{
				indice--; //altrimenti lo decremento
			}
	}
	
	/**
	 * Metodo che restituisce il nome del giocatore attuale
	 */
	public String nomeGiocatoreAttuale() {
		return listaGiocatori.get(indice).getNickname();
	}
	
	/**
	 * Metodo che restituisce il giocatore attuale
	 */
	public Giocatore giocatoreAttuale() {
		return listaGiocatori.get(indice);
	}
	
	/**
	 * Metodo getter che ritorna la lista dei giocatori
	 * @return List<Giocaotre> the listaGiocatori
	 */
	public List<Giocatore> getListaGiocatori() {
		return listaGiocatori;
	}

	/**
	 * Metodo setter che imposta la lista dei giocatori
	 * @param List<Giocatore> la listaGiocatori to set
	 */
	public void setListaGiocatori(List<Giocatore> listaGiocatori) {
		this.listaGiocatori = listaGiocatori;
	}

	/**
	 * Metodo getter che ritorna il riferimento al timer
	 * @return Timer il timer
	 */
	public Timer getTimer() {
		return timer;
	}
	
	/**
	 *Metodo che inverte l'ordine dei turni
	 * 
	 */
	public void invertiSenso() {
		if (isSensoOrario()) //da senso orario a senso antiorario
		{
			if (indice == 0)
			{
				decrementaIndice();
			}
			else if (indice == 1)
			{
				incrementaIndice();
			}
			else if (indice == 2)
			{
				decrementaIndice();
			}
			else if (indice == 3)
			{
				incrementaIndice();
			}
			Collections.reverse(listaGiocatori);
			setSensoOrario(false);
		}
		else //da senso antiorario a senso orario
		{
			if (indice == 0)
			{
				decrementaIndice();
			}
			else if (indice == 1)
			{
				incrementaIndice();
			}
			else if (indice == 2)
			{
				decrementaIndice();
			}
			else if (indice == 3)
			{
				incrementaIndice();
			}
			Collections.reverse(listaGiocatori);
			setSensoOrario(true);
		}
	
	}
	
	/**
	 * Metodo getter che ritorna un booleano 
	 * @return boolean il sensoOrario
	 */
	public boolean isSensoOrario() {
		return sensoOrario;
	}

	/**
	 * Metodo setter che imposta se la turnazione e' in senso orario
	 * @param boolean il sensoOrario to set
	 */
	public void setSensoOrario(boolean sensoOrario) {
		this.sensoOrario = sensoOrario;
	}

	/**
	 * Metodo getter che ritorna il timer uno
	 * @return TimerUno il timerUno
	 */
	public TimerUno getTimerUno() {
		return timerUno;
	}

	/**
	 * Metodo getter che ritorna il timer uno avversario
	 * @return TimerUnoAvversario il timerUnoAvversario
	 */
	public TimerUnoAvversario getTimerUnoAvversario() {
		return timerUnoAvversario;
	}
	
	/**
	 * Metodo getter che ritorna l'indice del turno
	 * @return int l'indice
	 */
	public int getIndice() {
		return indice;
	}

	/**
	 * Metodo setter che imposta l'indice del turno
	 * @param int l'indice to set
	 */
	public void setIndice(int indice) {
		this.indice = indice;
	}
}
