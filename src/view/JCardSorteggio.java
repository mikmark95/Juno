package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

import model.Carta;
import model.Giocatore;

/**
 * Classe che raprpesenta un pannello della carta di sorteggio
 * @author michele marchetti
 *
 */
public class JCardSorteggio extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3456246260071801085L;

	/**
	 * Variabile che contiene il riferimento al modello della carta
	 */
	private Carta carta;
	
	/**
	 * Variabile che contiene il riferimento al giocatore
	 */
	private Giocatore giocatore;
	
	
	/**
	 * MEtodo costruttore
	 * @param carta
	 * @param giocatore
	 */
	public JCardSorteggio(Carta carta, Giocatore giocatore) {
		this.carta = carta;
		this.giocatore = giocatore;
		
		this.setPreferredSize(new Dimension(150, 250));
		this.setOpaque(false);
	}
	
	/**
	 * Metodo che disegno l'oggetto
	 */
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		AffineTransform identity = new AffineTransform();
		g2d.drawImage(giocatore.getAvatar(), identity, this);
		identity.translate(10, 170);
		g2d.drawString(giocatore.getNickname(), 10, 160);
		g2d.drawImage(carta.getImage(), identity, this);
	}
	
	/**
	 * Metodo getter che ritorna la carta
	 */
	public Carta getCarta() {
		return carta;
	}
	
	
	/**
	 * Metodo getter che ritorna il giocatore
	 */
	public Giocatore getGiocatore() {
		return giocatore;
	}
	
	
	
	
	
}
