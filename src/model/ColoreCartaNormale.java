package model;

import java.awt.Color;

/**
 * La classe rappresenta le costatni enumerative dei colori delle carte "normali"
 * @author michelemarchetti
 *
 */

public enum ColoreCartaNormale implements ColoreCarta {

	ROSSO(Color.RED),
	BLU(Color.BLUE),
	GIALLO(Color.YELLOW),
	VERDE(Color.GREEN);
	
	

	
	
	/**
	 * Varibile che contienre il colore della carta
	 */
	private Color colore;
	
	/**
	 * Metodo costruttore
	 * @param colore
	 */
	ColoreCartaNormale(Color colore){
		this.colore = colore;
	}

	/**
	 * Metodo getter che restituisce il colore
	 * @return il colore Color
	 */
	public Color getColore() {
		return colore;
	}

	/**
	 * Metodo che restituisce il nome sotto forma di stringa della costatnte
	 * enumerativa
	 */
	@Override
	public String getName() {
		return this.name();
	}
}
