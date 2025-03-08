package model;

/**
 * Classe che rappresenta l'istanza di una eccezione che viene lanciata quando il mazzo si
 * esaurisce
 * @author michelemarchetti
 *
 */
public class MazzoEsauritoException extends Exception {


	/**
	 * Metodo costruttore
	 */
	public MazzoEsauritoException() {
		super("Mazzo carte esaurito! Mettere la pila degli scarti nel mazzo e mischia!");
	}

}
