package view;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Classe che rappresente la finestra introduttiva
 * @author michele marchetti
 *
 */
public class FinestraIntroduzione extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Variabile che contiene il pannello dello sfondo
	 */
	private PannelloSfondoIntroduzione pannelloSfondoIntroduzione;
	/**
	 * Variabile che contiene il riferimento alla finestra principale
	 */
	private FinestraPrincipale finestraPrincipale;
	
	/**
	 * Variabile che contiene il riferiemnt oalal finestra sorteggio mazziere
	 */
	private FinestraSorteggioMazziere finestraSorteggioMazziere;
	
	/**
	 * Metodo costruttore
	 */
	public FinestraIntroduzione(FinestraPrincipale finestraPrincipale) {
		super("Juno");
		this.finestraPrincipale = finestraPrincipale;
		this.pannelloSfondoIntroduzione = new PannelloSfondoIntroduzione();
		this.add(pannelloSfondoIntroduzione);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setSize(new Dimension(1000, 780));
		this.setResizable(false);
		this.setVisible(true);
		this.finestraSorteggioMazziere =  new FinestraSorteggioMazziere(finestraPrincipale);
		
	}

	/**
	 * Metodo getter che ritorna il riferimento alla finestra principale
	 * @return FinestraPrincipale la finestraPrincipale
	 */
	public FinestraPrincipale getFinestraPrincipale() {
		return finestraPrincipale;
	}
	
	/**
	 * Metodo getter che ritorna il riferimento alal finestra sorteggio mazziere 
	 * @return FinestraSorteggioMazziere
	 */
	public FinestraSorteggioMazziere getFinestraSorteggioMazziere(){
		return finestraSorteggioMazziere;
	}
}
