package view;

import javax.imageio.ImageIO;
import javax.swing.*;

import model.CampoDiGioco;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 * Classe che rappresenta il pannello di sfondo
 * @author michelemarchetti
 *
 */
@SuppressWarnings("deprecation")
public class PannelloSfondo extends JPanel implements Observer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3164132995823574908L;

	/**
	 * Varaibile statica che contiene il percorso del file dello sfondo
	 */
	private static String PERCORSO_SFONDO= "res/SFONDO.PNG";
	
	/**
	 * Variabile che contiene il riferimento al modello
	 */
	private CampoDiGioco campoDiGioco;
	
	/**
	 * Variabile che contiene l'immagine dello sfondo
	 */
	private Image image;
	
	/**
	 * Metodo costruttore
	 */
	public PannelloSfondo() {
		try {
			
			this.campoDiGioco = CampoDiGioco.instance();
			campoDiGioco.addObserver(this);
			
			this.image = ImageIO.read(new File(PERCORSO_SFONDO) );
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setLayout(new FlowLayout());
	}
	
	
	/**
	 * metodo che disegna le componenti
	 */
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D)g;
		
		AffineTransform identity = new AffineTransform();
		
		
		g2d.drawImage(image, identity, this);
		
	}


	/**
	 * Metodo che aggiorna l'istanza
	 */
	@Override
	public void update(Observable o, Object arg) {
		
		repaint();
		
	}
}
