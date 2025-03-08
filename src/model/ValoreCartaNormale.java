package model;

/**
 * La classe rappresta le costanti dei valori delle carte "normali"
 * @author michelemarchetti
 *
 */
public enum ValoreCartaNormale implements ValoreCarta{

	ZERO(0),
	UNO(1),
	DUE(2),
	TRE(3),
	QUATTRO(4),
	CINQUE(5),
	SEI(6),
	SETTE(7),
	OTTO(8),
	NOVE(9);
	
	/**
	 * Metodo costruttore
	 * @param i il valore numerico della costante
	 */
	ValoreCartaNormale(int i) {
		this.valoreNumerico = i;
	}
	
	/**
	 * Varaibile che contiente il valore intero delle carte
	 */
	private int valoreNumerico;

	/**
	 * Metodo getter
	 * @return int
	 */
	

	/**
	 * Metodo che restiuisce la stringa del nome della costante enumerativa
	 */
	@Override
	public String getName() {
		return this.name();
	}

	/**
	 * Metodo che restituisce l'intero del valroe delal costante
	 */
	@Override
	public int getValue() {
		return valoreNumerico;
	}

}
