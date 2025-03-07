package model;

/**
 * Classe che rappresenta un eccezione che viene lanciata quando una partita termina.
 * @author michele marchetti
 *
 */
public class PartitaTerminataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Metodo costruttore
	 */
	public PartitaTerminataException() {
		super("Partita terminata!!!");
	}
}
