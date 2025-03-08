package model;

/**
 * Classe che rappresenta l'instanza di un eccezione che si scatena quando un giocatore rimane con una carta in mano
 * @author michele marchetti
 *
 */
@SuppressWarnings("serial")
public class GridaUnoExceprtion extends Exception {
	
	/**
	 * Metodo costruttore
	 */
	public GridaUnoExceprtion() {
		super("UNO!!!!");
	}

}
