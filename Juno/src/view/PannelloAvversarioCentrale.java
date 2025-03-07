package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;

import model.Giocatore;

/**
 * Classe che rappresenta il pannello GUI di un avversario posizionato centralmente nel campo di gioco
 * @author michele marchetti
 *
 */
public class PannelloAvversarioCentrale extends PannelloAvversarioAstratto{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 443804917862421051L;

	/**
	 * Metodo costruttore
	 * @param giocatore
	 */
	public PannelloAvversarioCentrale(Giocatore giocatore) {
		super(giocatore);

		this.setLayout(new BorderLayout());
		this.setBackground(null);
		this.setOpaque(false);
		
		
		this.setPreferredSize(new Dimension(200	,200));
		// Pannello di appoggio per inserire il pannello avatar al centro
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout());
		p.setOpaque(false);
		p.setPreferredSize(new Dimension(100, 110));
		p.add(new PannelloAvatarAvversarioCentrale(giocatore));
		//----------------------------------------------------------------
		
		this.add(p, BorderLayout.NORTH);
		this.add(new PannelloCarteAvversario(giocatore), BorderLayout.SOUTH);
		
	}
	

	
}
