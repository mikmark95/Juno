package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * Classe che contiene il Timer che gestisce l'evento UNO del giocatore
 * @author michele marchetti
 *
 */
public class TimerUno {
	
	/**
	 * Costante stativa che raprpesenta il tempo in millisecondi
	 */
	private final static int DEELAY = 3000; //millis
	
	/**
	 * Variaible che contiene il riferimetno ad un timer
	 */
	private Timer timer;
	
	/**
	 * Metodo costruttore
	 */
	public TimerUno() {
		ActionListener task  = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CampoDiGioco.instance().getTurnoPartita().giocatoreAttuale().pesca(2);// se non viene disattivato dopo 3 secondi il giocatore pesca 2 carte
				System.out.println("pesca 2 carte");
				timer.stop();
			}
		};
		
		this.timer = new Timer(DEELAY, task);
	}
	
	/**
	 * Metodo getter che ritorna il timer
	 * @return Timer
	 */
	public Timer getTimer() {
		return timer;
	}

	

}
