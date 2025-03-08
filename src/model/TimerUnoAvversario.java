package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
/**
 * Classe che contiene il Timer che gestisce l'evento UNO dell'aversario
 * @author michele marchetti
 *
 */
public class TimerUnoAvversario {
	/**
	 * Costante stativa che raprpesenta il tempo in millisecondi
	 */
	private final static int DEELAY = 2000; //millis
	
	/**
	 * Variaible che contiene il riferimetno ad un timer
	 * 
	 */
	private Timer timer;
	
	/**
	 * Metodo costruttore
	 */
	public TimerUnoAvversario() {
		ActionListener task  = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
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
