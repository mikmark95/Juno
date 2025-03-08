package controller;

import java.awt.event.MouseEvent;

import java.awt.event.MouseListener;

import model.CampoDiGioco;
import model.Carta;
import model.ColoreCartaSpeciale;
import model.MazzoEsauritoException;
import view.PannelloGiocaPesca;
import view.JMazzo;

/**
 * Classe che contiene gli eventi relativi al click sul mazzo
 * @author michele marchetti
 *
 */
public class PescaMazzoListener implements MouseListener{
	
	/**
	 * Variabile che contiene il riferimento al modello
	 */
	private CampoDiGioco campoDiGioco;
	
	/**
	 * Metodo costruttore
	 */
	public PescaMazzoListener() {
		this.campoDiGioco = CampoDiGioco.instance();
	}
	
	/**
	 * Metodo che esegue le istruzione al verificarsi del click
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		JMazzo jMazzo = (JMazzo) e.getSource();
		
		//----------------------- 
		if (campoDiGioco.getPilaScarti().guardaCima().getColore().equals(ColoreCartaSpeciale.DORSO)) //se siamo all inizio del gioco
		{
			try {
				Carta carta = jMazzo.getMazzoCarte().pesca();
				if (carta.getColore()!= ColoreCartaSpeciale.NERO)
				{
					campoDiGioco.getPilaScarti().aggiungiCarta(carta);
					AudioManager.getInstance().play("res/carta.wav"); //effetto audio
					campoDiGioco.setColoreCampo(carta.getColore());
					campoDiGioco.aggiorna();
					campoDiGioco.getTurnoPartita().prossimoTurno();
					
				}
				else
				{
					carta = jMazzo.getMazzoCarte().pesca();
					campoDiGioco.getPilaScarti().aggiungiCarta(carta);
					AudioManager.getInstance().play("res/carta.wav"); //effetto audio
					campoDiGioco.setColoreCampo(carta.getColore());
					campoDiGioco.aggiorna();
					campoDiGioco.getTurnoPartita().prossimoTurno();
				}
			} catch (MazzoEsauritoException e1) {
				e1.printStackTrace();
			}
		}
		//------------------------
		
		
		if (campoDiGioco.getGiocatoreUtente().isTurnoInCorso()) //se e' il turno del giocatore utente
		{
		try {
			Carta carta = jMazzo.getMazzoCarte().pesca();				
			//------ 
			if (carta.getColore().equals(campoDiGioco.getColoreCampo()) || carta.getValore().equals(campoDiGioco.getPilaScarti().guardaCima().getValore()) && carta.getColore()!= ColoreCartaSpeciale.NERO)
			{
				campoDiGioco.getTurnoPartita().getTimer().stop();
				campoDiGioco.getGiocatoreUtente().setTurnoInCorso(false);//blocco gli eventi sulle carte fino al prossimo turno
				campoDiGioco.aggiorna();
				AudioManager.getInstance().play("res/carta.wav"); //effetto audio
				new view.PannelloGiocaPesca(carta);
			}
			else 
			{				
				campoDiGioco.getGiocatoreUtente().getManoCarte().getMano().add(carta);
				AudioManager.getInstance().play("res/carta.wav"); //effetto audio
				campoDiGioco.getGiocatoreUtente().setTurnoInCorso(false);
				campoDiGioco.aggiorna();
				campoDiGioco.getTurnoPartita().prossimoTurno();
			}
			//-----
			
		} catch (MazzoEsauritoException e1) {
			campoDiGioco.getPilaScarti().rigeneraMazzo(campoDiGioco.getMazzoCarte());
			try {
				Carta carta = jMazzo.getMazzoCarte().pesca();			
				
				//------ 
				if (carta.getColore().equals(campoDiGioco.getColoreCampo()) || carta.getValore().equals(campoDiGioco.getPilaScarti().guardaCima().getValore()))
				{
					campoDiGioco.getTurnoPartita().getTimer().stop();
					campoDiGioco.getGiocatoreUtente().setTurnoInCorso(false); //blocco gli eventi sulle carte fino al prossimo turno
					campoDiGioco.aggiorna();
					AudioManager.getInstance().play("res/carta.wav"); //effetto audio
					new PannelloGiocaPesca(carta);
				}
				else 
				{				
					campoDiGioco.getGiocatoreUtente().getManoCarte().getMano().add(carta);
					AudioManager.getInstance().play("res/carta.wav"); //effetto audio
					campoDiGioco.getGiocatoreUtente().setTurnoInCorso(false);
					campoDiGioco.aggiorna();
					campoDiGioco.getTurnoPartita().prossimoTurno();
				}
				//-----------
			} catch (MazzoEsauritoException e2) {
				e2.printStackTrace();
			}
		}
		

		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
//		System.out.println(campoDiGioco.getGiocatoreUtente().getManoCarte().getMano().toString());
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
