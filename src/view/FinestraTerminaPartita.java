package view;

import java.awt.Dimension;
import javax.swing.JDialog;
import model.CampoDiGioco;
import model.Giocatore;

/**
 * Classe che raprpesenta la finestra che viene visualizzato al termine di una partita
 * @author michele marchetti
 *
 */
public class FinestraTerminaPartita extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Variabile che contiene il riferimento del modello
	 */
	CampoDiGioco campoDiGioco;
	
	/**
	 * Varaibile che contiene il riferimento del giocatore che ha vinto la partita
	 */
	Giocatore giocatore;
	/**
	 * Metodo costruttore
	 */
	public FinestraTerminaPartita(Giocatore giocatore) {
		this.campoDiGioco = CampoDiGioco.instance();
		this.giocatore = giocatore;
		this.add(new PannelloTerminaPartita( giocatore));
		
		
		this.setVisible(true);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setTitle("PARTITA TERMINATA !!!");
		this.setPreferredSize(new Dimension(600, 350));
		this.setLocationRelativeTo(null);
		this.pack();
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		
		
		
	}
	
	
	
	
}
