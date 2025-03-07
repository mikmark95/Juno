package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import model.Giocatore;
/**
 * Classe che rappresenta il pannello GUI di un avversario posizionato verticalmente nel campo di gioco
 * @author michele marchetti
 *
 */
public class PannelloAvversarioVerticale extends PannelloAvversarioAstratto/* JPanel implements Observer*/{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 562283850516976353L;

	/**
	 * Metodo costruttore
	 */
	public PannelloAvversarioVerticale(Giocatore giocatore) {
		super(giocatore);
		this.setLayout(new BorderLayout());
		this.setBackground(null);
		this.setOpaque(false);
		
		this.setPreferredSize(new Dimension(230, 152));
		this.add( new PannelloAvatarAvversarioVerticale(giocatore), BorderLayout.NORTH);
		this.add(new PannelloCarteAvversario(giocatore),BorderLayout.CENTER);
		
	}
	
}
