package model;

/**
 * La classe rappresta le costanti dei valori delle carte "speciali"
 * @author michelemarchetti
 *
 */
public enum ValoreCartaSpeciale implements ValoreCarta{

	
	
	PESCA_2(20),
	SALTA_TURNO(20),
	INVERTI(20),
	JOLLY(20),
	JOLLY_PESCA_4(50);
	
	/**
	 * Metodo costruttore
	 * @param i
	 */
	ValoreCartaSpeciale(int i) {
		this.valoreNumerico = i;
	}
	
	/**
	 * Varaibile che contiente il valore intero delle carte
	 */
	private int valoreNumerico;

	/**
	 * Metodo che restituisce il nome della costante enumerativa
	 */
	@Override
	public String getName() {
		return this.name();
	}

	/**
	 * Metodo che restiuisce il valroe della costante enumerativa
	 */
	@Override
	public int getValue() {
		return valoreNumerico;
	}


}
