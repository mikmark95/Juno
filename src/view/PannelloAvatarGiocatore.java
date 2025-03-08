package view;

import model.*;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Classe che rappresenta l'interfacca grafica del pannello Avatar del giocatore
 * @author michele marchetti
 *
 */
public class PannelloAvatarGiocatore extends PannelloAvatarAstratto{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -71162315107009564L;

	/*
	 * Metodo costruttore
	 */
	public PannelloAvatarGiocatore(Giocatore giocatore) {
		super(giocatore);
		this.setPreferredSize(new Dimension(200	, 202));
		this.setOpaque(false);
		
	}

	/**
	 * Metodo che consente di disegnare le componenti
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		StringBuilder sb = new StringBuilder();
		sb.append("Liv:"+getCampoDiGioco().getGiocatoreUtente().getLivello()+" ");
		sb.append("V:"+getCampoDiGioco().getGiocatoreUtente().getPartiteVinte()+" ");
		sb.append("P:"+getCampoDiGioco().getGiocatoreUtente().getPartitePerse()+" ");
		
		
	
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.translate(0, 45);
		
		if (getCampoDiGioco().getTurnoPartita().giocatoreAttuale().equals(getGiocatore()))
		{
			
			g2d.drawImage(getGiocatore().getAvatar(), affineTransform, this);
			g2d.setFont(new Font(Font.SERIF, getFont().BOLD, 18));
			g2d.setColor(new Color(186, 101, 4));
			g2d.drawString(getGiocatore().getNickname(), 10, 14);
			g2d.setColor(Color.yellow);
			g2d.setFont(new Font(Font.SERIF, getFont().BOLD, 15));
			g2d.drawString("Punteggio: "+String.valueOf(getGiocatore().getPunteggio()), 10, 26);
			g2d.drawString(sb.toString(), 10, 42);
		}
		else
		{			
			g2d.drawImage(getGiocatore().getAvatar(), affineTransform, this);
			g2d.setFont(new Font(Font.SERIF, getFont().BOLD, 15));
			g2d.drawString(getGiocatore().getNickname(), 10, 12);
			g2d.drawString("Punteggio: "+String.valueOf(getGiocatore().getPunteggio()), 10, 26);
			g2d.drawString(sb.toString(), 10, 42);
		}
		
	}

	
	
}
