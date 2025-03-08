package view;

import model.*;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 *Classe che rappresenta l'interfacca grafica del pannello Avatar di un avversario
 * @author michele marchetti
 *
 *
 */
public class PannelloAvatarAvversarioVerticale extends PannelloAvatarAstratto{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6088457381447482373L;

	/*
	 * Metodo costruttore
	 */
	public PannelloAvatarAvversarioVerticale(Giocatore giocatore) {
		super(giocatore);
		this.setPreferredSize(new Dimension(150	, 115));
		this.setOpaque(false);
	}

	/**
	 * Metodo che consente di disegnare le componenti
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.translate(70, 12);
		
		if (getCampoDiGioco().getTurnoPartita().giocatoreAttuale().equals(getGiocatore()))
		{
			g2d.setFont(new Font(Font.SERIF, getFont().BOLD, 18));
			g2d.drawImage(getGiocatore().getAvatar(), affineTransform, this);
			g2d.setColor(new Color(186, 101, 4));
			g2d.drawString(getGiocatore().getNickname(), 80, 12);
			g2d.setColor(Color.yellow);
			g2d.setFont(new Font(Font.SERIF, getFont().BOLD, 15));
			g2d.drawString("Punteggio: "+String.valueOf(getGiocatore().getPunteggio()), 70, 102);
		}
		else 
		{
			g2d.setFont(new Font(Font.SERIF, getFont().BOLD, 15));
			g2d.drawImage(getGiocatore().getAvatar(), affineTransform, this);
			
			g2d.drawString(getGiocatore().getNickname(), 80, 10);
			g2d.drawString("Punteggio: "+String.valueOf(getGiocatore().getPunteggio()), 70, 100);
			
		}

	}
	
}
