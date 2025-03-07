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
public class PannelloAvatarAvversarioCentrale extends PannelloAvatarAstratto{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4597913301660858598L;

	/*
	 * Metodo costruttore
	 */
	public PannelloAvatarAvversarioCentrale(Giocatore giocatore) {
		super(giocatore);
		this.setPreferredSize(new Dimension(150	, 112));
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
		affineTransform.translate(40, 12);
		
		if (getCampoDiGioco().getTurnoPartita().giocatoreAttuale().equals(getGiocatore())) //se e' il turno del giocatore evidenzio i dati
		{
			g2d.setFont(new Font(Font.SERIF, getFont().BOLD, 18));
			g2d.drawImage(getGiocatore().getAvatar(), affineTransform, this);
			g2d.setColor(new Color(186, 101, 4));
			g2d.drawString(getGiocatore().getNickname(), 60, 12);
			g2d.setColor(Color.yellow);
			g2d.setFont(new Font(Font.SERIF, getFont().BOLD, 15));
			g2d.drawString("Punteggio: "+String.valueOf(getGiocatore().getPunteggio()), 40, 100);
		}
		else 
		{

			g2d.setFont(new Font(Font.SERIF, getFont().BOLD, 15));
			g2d.drawImage(getGiocatore().getAvatar(), affineTransform, this);
			
			g2d.drawString(getGiocatore().getNickname(), 60, 10);
			g2d.drawString("Punteggio: "+String.valueOf(getGiocatore().getPunteggio()), 40, 100);
			
		}

	}

}
