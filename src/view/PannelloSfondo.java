package view;

import javax.imageio.ImageIO;
import javax.swing.*;

import model.CampoDiGioco;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;

/**
 * Classe che rappresenta il pannello di sfondo
 * @author michelemarchetti
 */
@SuppressWarnings("deprecation")
public class PannelloSfondo extends JPanel implements Observer {

	private static final long serialVersionUID = -3164132995823574908L;

	/**
	 * Variabile statica che contiene il percorso del file dello sfondo
	 */
	private static final String PERCORSO_SFONDO = "res/SFONDO.PNG";

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

			// Caricamento immagine di sfondo dal JAR
			InputStream imgStream = getClass().getClassLoader().getResourceAsStream(PERCORSO_SFONDO);
			if (imgStream == null) {
				System.err.println("Immagine di sfondo non trovata nel JAR: " + PERCORSO_SFONDO);
			} else {
				this.image = ImageIO.read(imgStream);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setLayout(new FlowLayout());
	}

	/**
	 * Metodo che disegna le componenti
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		AffineTransform identity = new AffineTransform();

		if (image != null) {
			g2d.drawImage(image, identity, this);
		} else {
			System.err.println("Errore: immagine di sfondo non caricata correttamente.");
		}
	}

	/**
	 * Metodo che aggiorna l'istanza
	 */
	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
}
