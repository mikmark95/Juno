package model;

import java.awt.Color;

/**
 * Classe che contiene le costanti enumerative delle classi "speciali"
 * @author michelemarchetti
 *
 */
public enum ColoreCartaSpeciale implements ColoreCarta {

		
		
		
		NERO(Color.BLACK),
		DORSO(Color.WHITE);
		
		
		/**
		 * Varibile che contienre il colore della carta
		 */
		private Color colore;
		
		ColoreCartaSpeciale(Color colore){
			this.colore = colore;
		}

		/**
		 * Metodo getter che ritorna il Colore
		 * @return il colore Color
		 */
		public Color getColore() {
			return colore;
		}

		/**
		 * Metedo getter che ritorna il nome della costante enumerativa
		 */
		@Override
		public String getName() {
			return this.name();
		}
	

}
