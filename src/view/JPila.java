package view;

import javax.imageio.ImageIO;
import javax.swing.*;

import model.PilaScarti;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;

/**
 * Classe che rappresenta la GUI di una pila degli scarti
 * @author michele marchetti
 */
@SuppressWarnings("deprecation")
public class JPila extends JPanel implements Observer {

	private static final long serialVersionUID = -3139520284755195353L;
	/**
	 * Variabile che contiene il riferimento alla pila degli scarti
	 */
	private PilaScarti pilaScarti;

	/**
	 * Metodo costruttore
	 * @param pilaScarti
	 */
	public JPila(PilaScarti pilaScarti) {
		this.pilaScarti = pilaScarti;
		this.setPreferredSize(new Dimension(100, 120));
	}

	/**
	 * Metodo che serve per disegnare il componente
	 */
	@Override
	public void paintComponent(Graphics g) {

		Image image = null;
		String imagePath = "res/" + pilaScarti.guardaCima().getValore() + "_" + pilaScarti.guardaCima().getColore() + "_ZOOM.png";

		// Caricamento immagine dal JAR
		InputStream imgStream = getClass().getClassLoader().getResourceAsStream(imagePath);
		if (imgStream == null) {
			System.err.println("Immagine non trovata nel JAR: " + imagePath);
		} else {
			try {
				image = ImageIO.read(imgStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Graphics2D g2d = (Graphics2D) g;
		AffineTransform affineTransform = new AffineTransform();

		if (image != null) {
			g2d.drawImage(image, affineTransform, this);
		}
	}

	/**
	 * Metodo che serve per aggiornare l'istanza
	 */
	@Override
	public void update(Observable o, Object arg) {
		this.repaint();
	}
}
