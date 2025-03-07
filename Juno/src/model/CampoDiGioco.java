package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.imageio.ImageIO;

/**
 * Classe che rappresenta l'unica istanza del campo da gioco che si dovra interfacciare con le varie view
 * @author michele marchetti
 *
 */
@SuppressWarnings("deprecation")
public class CampoDiGioco extends Observable {
	
	/**
	 * Variabile che contiene il riferimento ad un mazzo di carte
	 */
	private MazzoCarte mazzoCarte;
	
	/**
	 * Variabile che contiene il riferimento alla pila degli scarti
	 */
	private PilaScarti pilaScarti;
	
	/**
	 * Variabile che contiene il riferimento del giocatore utente
	 */
	private GiocatoreUtente giocatoreUtente;
	
	/**
	 * Variabile che contiene il riferimento alla lista dei giocatori avversati
	 */
	private List<GiocatoreAvversario> listaAvversari;
	
	/**
	 * Variabile che contiene l'unica istanza della classe
	 */
	private static CampoDiGioco instance;
	
	/**
	 * Variabile che contiene il riferimento del colore in campo
	 */
	private ColoreCarta coloreCampo;
	
	/**
	 * Variabile che contine il riferimento all oggetto che gestisce la turnazione dei giocatori
	 */
	private TurnoPartita turnoPartita;

	/**
	 * Varaibile che contiene il booleano che indica che la partita e' termianta o meno
	 */
	private boolean partitaTerminata;
	
	/*
	 * Variabile che contiene il riferimento della lista dei giocatori
	 */
	private List<Giocatore> listaGiocatoriTotali;
	
	/**
	 * Variabile che indica se un giocatore e' rimasto senza carte
	 */
	private boolean uno;
	
	/**
	 * Varaibile che indica se i lround e' terminato
	 */
	private boolean roundTerminato;
	
	
	/**
	 * Metodo costruttore privato
	 */
	private CampoDiGioco() {
		inizializzaPartita();
	}
	
	/**
	 * Metodo statico che ritorna l'istanza dell oggetto, e se necessario la crea.
	 * @return CampoDiGioco
	 */
	public static CampoDiGioco instance() {
		if (instance == null)
		{
			instance = new CampoDiGioco();
			return instance;
		}
		else
		{
			return instance;
		}
	}
	
	
	
	/**
	 * Metodo privato che inizzializza la partita
	 */
	private void inizializzaPartita() {
		this.mazzoCarte = new MazzoCarte();
		this.pilaScarti = new PilaScarti();
		this.coloreCampo = pilaScarti.guardaCima().getColore();
		
		
		this.listaGiocatoriTotali = new ArrayList<>();
		
		ManoCarte manoGiocatore = new ManoCarte(mazzoCarte, pilaScarti);
		ManoCarte manoAvversario1 = new ManoCarte(mazzoCarte, pilaScarti);
		ManoCarte manoAvversario2 = new ManoCarte(mazzoCarte, pilaScarti);
		ManoCarte manoAvversario3 = new ManoCarte(mazzoCarte, pilaScarti);
		
		try {
			
			this.giocatoreUtente = new GiocatoreUtente("MikMark95", ImageIO.read(new File("res/avatarGiocatore.png")), manoGiocatore );
			listaGiocatoriTotali.add(giocatoreUtente);
			
			this.listaAvversari = new ArrayList<>();
			
			GiocatoreAvversario avversario1 = new GiocatoreAvversario("Chang", ImageIO.read(new File("res/avversario1.png")), manoAvversario1, new StrategiaGiocoSemplice(manoAvversario1));
			listaAvversari.add(avversario1);
			listaGiocatoriTotali.add(avversario1);
			
			GiocatoreAvversario avversario2 = new GiocatoreAvversario("Jonnhy", ImageIO.read(new File("res/avversario2.png")), manoAvversario2, new StrategiaGiocoSemplice(manoAvversario2));
			listaAvversari.add(avversario2);
			listaGiocatoriTotali.add(avversario2);
			
			GiocatoreAvversario avversario3 = new GiocatoreAvversario("Pablo", ImageIO.read(new File("res/avversario3.png")), manoAvversario3, new StrategiaGiocoSemplice(manoAvversario3));
			listaAvversari.add(avversario3);
			listaGiocatoriTotali.add(avversario3);
			
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		this.turnoPartita = new TurnoPartita(pilaScarti);
		turnoPartita.setListaGiocatori(listaGiocatoriTotali);
		
	}
	
	
	/**
	 * Metodo che fa iniziare una nuova partita
	 */
	public void  ricomincia() {
		
		pilaScarti.inizializzaPila();
		
		mazzoCarte.inizializzaMazzo();
		
		setPartitaTerminata(false);;
		giocatoreUtente.getManoCarte().inizializzaMano();
		for (GiocatoreAvversario gAvv: listaAvversari)
		{
			gAvv.getManoCarte().inizializzaMano();
		}
	}
	
	
	/**
	 * Metodo getter che ritorna il giocatore utente
	 * @return GiocatoreUtente
	 */
	public GiocatoreUtente getGiocatoreUtente() {
		return giocatoreUtente;
	}

	/**
	 * Metodo getter che ritorna il mazzo di carte
	 * @return MazzoCarte
	 */
	public MazzoCarte getMazzoCarte() {
		return mazzoCarte;
	}

	/**
	 * Metodo getter che ritorna la pila degli scarti
	 * @return PilaScarti
	 */
	public PilaScarti getPilaScarti() {
		return pilaScarti;
	}

	/**
	 * Metodo getter che ritorna la lista degli avversari
	 * @return List<GiocatoriAvversari>
	 */
	public List<GiocatoreAvversario> getListaAvversari() {
		return listaAvversari;
	}
	
	
	/**
	 * Meteodo che notifica tutti gli osservatori di un cambiamento del modello
	 * 
	 */
	public void aggiorna() {
		this.setChanged();
		this.notifyObservers();
		
	}

	/**
	 * Meteodo che notifica tutti gli osservatori di un cambiamento del modello
	 * @param carta 
	 */
	public void aggiorna(Carta carta) {
		this.setChanged();
		this.notifyObservers(carta);
		
	}
	
	/**
	 * Meteodo che notifica tutti gli osservatori di un cambiamento del modello
	 * @param stringa
	 */
	public void aggiorna(String stringa) {
		this.setChanged();
		this.notifyObservers(stringa);
	}

	/**
	 * Metodo getter che ritorna il colore in campo
	 * @return ColoreCarta the coloreCampo
	 */
	public ColoreCarta getColoreCampo() {
		return coloreCampo;
	}

	/**
	 * Metodo setter per impostare il colore del campo
	 * @param ColoreCarta the coloreCampo to set
	 */
	public void setColoreCampo(ColoreCarta coloreCampo) {
		this.coloreCampo = coloreCampo;
	}


	

	/**
	 * Metodo gette che ritorna l'istanza del turno partita
	 * @return  TurnoPartita  il turnoPartita
	 */
	public TurnoPartita getTurnoPartita() {
		return turnoPartita;
	}

	

	/**
	 * Metodo getter che ritorna la lista dei giocatori totali
	 * @return List<Giocatore> the listaGiocatoriTotali
	 */
	public List<Giocatore> getListaGiocatoriTotali() {
		return listaGiocatoriTotali;
	}

	/**
	 * Metodo getter che ritorna se la partiaa e' terminata
	 * @return boolean the partitaTerminata
	 */
	public boolean isPartitaTerminata() {
		return partitaTerminata;
	}
	
	/**
	 * Metodo setter che imposta se la partita e' terminata
	 * @param boolean the partitaTerminata to set
	 */
	public void setPartitaTerminata(boolean partitaTerminata) {
		this.partitaTerminata = partitaTerminata;
	}

	/**
	 * Metodo getter che ritorna se si e' verificato "UNO"
	 * @return boolean the uno
	 */
	public boolean isUno() {
		return uno;
	}

	/**
	 * Metodo setter che imposta se si e' verificato "UNO"
	 * @param boolean  uno to set
	 */
	public void setUno(boolean uno) {
		this.uno = uno;
	}

	/**
	 * MEtodo geeter che ritorna se il round e' terminato
	 * @return boolean il roundTerminato
	 */
	public boolean isRoundTerminato() {
		return roundTerminato;
	}

	/**
	 * Metodo setter che imposta se il raound e' terminato
	 * @param boolean il roundTerminato to set
	 */
	public void setRoundTerminato(boolean roundTerminato) {
		this.roundTerminato = roundTerminato;
	}
}

