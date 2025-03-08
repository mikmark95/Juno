package model;

/**
 * Classe che rappresenza l'eccezione che si scatena quando una mano rimane senza carte
 * @author michele marchetti
 *
 */
public class ManoVuotaException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Metodo costruttore
	 */
	public ManoVuotaException() {
		super("Il giocatore ha terminato le carte in mano");
	}

}
