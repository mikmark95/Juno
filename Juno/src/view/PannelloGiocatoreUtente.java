package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.CampoDiGioco;

@SuppressWarnings("deprecation")
public class PannelloGiocatoreUtente extends JPanel implements Observer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6204903006246828882L;
	private CampoDiGioco campoDiGioco;
	
	public PannelloGiocatoreUtente() {
		this.campoDiGioco = CampoDiGioco.instance();
		campoDiGioco.addObserver(this);

		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(200	,200));
		this.setBackground(null);
		this.setOpaque(false);

		this.add(new PannelloAvatarGiocatore(campoDiGioco.getGiocatoreUtente()), BorderLayout.WEST);
		this.add(new PannelloCarteGiocatore(campoDiGioco.getGiocatoreUtente()), BorderLayout.CENTER);
		
		this.add(new PannelloMazzo(), BorderLayout.EAST);
	}

	
	@Override
	public void update(Observable o, Object arg) {
	}

	
}
