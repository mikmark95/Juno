package view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.CampoDiGioco;
import model.Giocatore;

/**
 * Classe che rappresenta una istanza astratta del pannello avversario
 * @author michele marchetti
 *
 */

@SuppressWarnings("deprecation")
public abstract class PannelloAvversarioAstratto extends JPanel implements Observer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8400653695889097090L;

	/**
	 * Variabile che contiene il riferimento al modello
	 */
	private CampoDiGioco campoDiGioco;
	
	/**
	 * Variabile che contiene il riferimento al giocatore
	 */
	@SuppressWarnings("unused")
	private Giocatore giocatore;
	
	/**
	 * Metodo costruttore che prende come argomento un giocatore
	 * @param giocatore Giocatore
	 */
	public PannelloAvversarioAstratto(Giocatore giocatore) {
		this.giocatore = giocatore;
		this.campoDiGioco = CampoDiGioco.instance();
		campoDiGioco.addObserver(this);
		
	}

	
	/**
	 * Metodo che serve per aggiornare l'istanza
	 */
	@Override
	public void update(Observable o, Object arg) {
		this.repaint();
	}

	/**
	 * Metodo getter che ritorna il riferimento del modello
	 */
	public CampoDiGioco getCampoDiGioco() {
		return campoDiGioco;
	}
}
