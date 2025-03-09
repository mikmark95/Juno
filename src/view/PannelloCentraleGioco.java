package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.CampoDiGioco;

/**
 * Classe che rappresenta il pannello centrale
 * @author michele marchetti
 */
@SuppressWarnings("deprecation")
public class PannelloCentraleGioco extends JPanel implements Observer {

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
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 100));
		this.setPreferredSize(new Dimension(200, 200));
		this.setBackground(Color.cyan);
		this.setOpaque(false);
	}

	/**
	 * Metodo che serve per disegnare le componenti
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		AffineTransform affineTransform = new AffineTransform();
		affineTransform.translate(10, 50);

		g2d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));

		if (campoDiGioco.getPilaScarti().getPila().size() > 0) {
			g2d.drawString("Turno Giocatore: " + campoDiGioco.getTurnoPartita().nomeGiocatoreAttuale(), 170, 50);
			g2d.drawString("Colore in campo: " + campoDiGioco.getColoreCampo(), 170, 300);

			// Caricamento immagine dal JAR
			Image img = null;
			String imagePath = campoDiGioco.getTurnoPartita().isSensoOrario() ? "res/ORARIO.png" : "res/ANTIORARIO.png";

			InputStream imgStream = getClass().getClassLoader().getResourceAsStream(imagePath);
			if (imgStream == null) {
				System.err.println("Immagine non trovata nel JAR: " + imagePath);
			} else {
				try {
					img = ImageIO.read(imgStream);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (img != null) {
				g2d.drawImage(img, affineTransform, this);
			}
		} else {
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
