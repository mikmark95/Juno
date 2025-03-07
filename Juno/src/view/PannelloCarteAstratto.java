package view;

import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import javax.swing.JPanel;

import model.CampoDiGioco;
import model.Carta;
import model.Giocatore;

/**
 * Classe astratta che rappresenta la zona dove andranno inserite le carte
 * @author michele marchetti
 *
 */
@SuppressWarnings("deprecation")
public abstract class PannelloCarteAstratto extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9084057908992461744L;

	/**
	 * Variabile che contiene il riferimento al modello
	 */
	private CampoDiGioco campoDiGioco;

	/**
	 * Variabile che contiene il riferimento al giocatore dal quale usare le carte
	 */
	private Giocatore giocatore;
	
	/**
	 * Metodo costruttore
	 * @param giocatore
	 */
	public PannelloCarteAstratto(Giocatore giocatore) {
		this.campoDiGioco = CampoDiGioco.instance();

		campoDiGioco.addObserver(this);
		this.giocatore = giocatore;
	}
	
	
	/**
	 * Metodo  che utilizza gli stream per cercare una specifica carta nella mano
	 * @return Carta
	 */
	public Carta cercaCarta(Carta c) {
		Optional<Carta> carta = giocatore.getManoCarte().getMano().stream()
			.filter(x -> x.equals(c) )
			.findAny();
		return carta.orElse(null);
	}
	
	
	/**
	 * Metodo che aggiorna l'istanza
	 */
	@Override
	public void update(Observable o, Object arg) {
		
	}
	
	/**
	 * Metodo getter che ritorna il campo da gioco
	 * @return  campoDiGioco
	 */
	public CampoDiGioco getCampoDiGioco() {
		return campoDiGioco;
	}


	/**
	 * Metodo getter che ritorna il giocatore
	 * @return  giocatore
	 */
	public Giocatore getGiocatore() {
		return giocatore;
	}
	
	/**
	 * Metodo che dispone le carte sul pannello
	 */
	public abstract void disponiCarte();

}