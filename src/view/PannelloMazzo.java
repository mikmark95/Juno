package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import controller.PescaMazzoListener;
import model.CampoDiGioco;

/**
 * Classe che rappresenta il pannello che contiente il mazzo
 * @author michele marchetti
 *
 */
@SuppressWarnings("deprecation")
public class PannelloMazzo extends JPanel implements Observer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3444963347393084669L;
	/**
	 * Variabile che contiene il riferimento del modello
	 */
	private CampoDiGioco campoDiGioco;

	/**
	 * Metodo costruttore
	 */
	public PannelloMazzo() {
		this.campoDiGioco = CampoDiGioco.instance();
		this.campoDiGioco.addObserver(this);
		
		JMazzo jMazzo = new JMazzo(campoDiGioco.getMazzoCarte());
		jMazzo.addMouseListener(new PescaMazzoListener());
		this.add(jMazzo);
		
		this.setOpaque(false);
		this.setLayout(new FlowLayout(FlowLayout.CENTER,0,30));
		this.setPreferredSize(new Dimension(200	, 200));
		
		
		
		
	}
	
	/**
	 * Metodo che disegna le componenti grafiche
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
			
	}

	/**
	 * MEtodo che aggiorna l'istanza
	 */
	@Override
	public void update(Observable o, Object arg) {
		this.repaint();
		
	}

}
