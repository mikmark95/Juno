package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.CampoDiGioco;

/**
 * Classe che rappresenta il pannello centrale 
 * @author michel marchetti
 *
 */
@SuppressWarnings("deprecation")
public class PannelloCentraleGioco extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3747317602918462943L;
	/**
	 * Variabile che contiene il riferimento al modello
	 */
	private CampoDiGioco campoDiGioco;
	
	
	/**
	 * Metodo costruttore
	 */
	public PannelloCentraleGioco() {
		this.campoDiGioco = CampoDiGioco.instance();
		campoDiGioco.addObserver(this);
		this.setLayout(new  FlowLayout(FlowLayout.CENTER, 200,100));
	
		this.setPreferredSize(new Dimension(200,200));
		this.setBackground(Color.cyan);
		this.setOpaque(false);
	}

	/**
	 * Metodo che serve per disegnare le componenti
	 */
	@Override 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D ) g;
		
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.translate(10, 50);
		
		g2d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));

		if (campoDiGioco.getPilaScarti().getPila().size()>0)
		{	
			g2d.drawString("Turno Giocatore: "+ campoDiGioco.getTurnoPartita().nomeGiocatoreAttuale().toString(), 170, 50);
			g2d.drawString("Colore in campo: "+ campoDiGioco.getColoreCampo(), 170, 300);
			
			Image img = null;
			if (campoDiGioco.getTurnoPartita().isSensoOrario()) //se il turno e' orario
			{
				
				try {
					img = ImageIO.read(new File("res/ORARIO.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else //se il turno e' antiorario
			{
				try {
					img = ImageIO.read(new File("res/ANTIORARIO.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			g2d.drawImage(img, affineTransform, this);
		}
		else 
		{
			g2d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

			g2d.drawString("Scopri la prima carta del mazzo", 120, 180);

		}
	}
	
	/**
	 * Metodo che aggiorna l'istanza
	 */
	@Override
	public void update(Observable o, Object arg) {
		this.repaint();
	}
	
}
