package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.io.InputStream;

/**
 * Classe che rappresenta il pannello di sfondo
 * @author michelemarchetti
 */
public class PannelloSfondoIntroduzione extends JPanel {

	private static final long serialVersionUID = -6163235051813831493L;

	/**
	 * Variabile statica che contiene il percorso del file dello sfondo
	 */
	private static final String PERCORSO_SFONDO = "res/SFONDO_INTRODUZIONE.PNG";

	/**
	 * Variabile che contiene l'immagine dello sfondo
	 */
	private Image image;

	/**
	 * Metodo costruttore
	 */
	public PannelloSfondoIntroduzione() {
		// Caricamento dell'immagine di sfondo dal JAR
		InputStream imgStream = getClass().getClassLoader().getResourceAsStream(PERCORSO_SFONDO);
		if (imgStream == null) {
			System.err.println("Immagine di sfondo non trovata nel JAR: " + PERCORSO_SFONDO);
		} else {
			try {
				this.image = ImageIO.read(imgStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		this.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 450));
		this.setOpaque(false);

		// Caricamento immagini per i bottoni dal JAR
		JLabel labelGioca = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("res/GIOCA.png")));
		JLabel labelEsci = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("res/ESCI.png")));

		labelGioca.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {
				JLabel jLabel = (JLabel) e.getSource();
				jLabel.setBorder(BorderFactory.createEmptyBorder());
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				JLabel jLabel = (JLabel) e.getSource();
				jLabel.setBorder(BorderFactory.createLineBorder(Color.yellow, 5));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				JLabel jLabel = (JLabel) e.getSource();
				FinestraIntroduzione f = (FinestraIntroduzione) jLabel.getRootPane().getParent();
				f.getFinestraSorteggioMazziere().setVisible(true);
				f.setVisible(false);
			}
		});

		labelEsci.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {
				JLabel jLabel = (JLabel) e.getSource();
				jLabel.setBorder(BorderFactory.createEmptyBorder());
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				JLabel jLabel = (JLabel) e.getSource();
				jLabel.setBorder(BorderFactory.createLineBorder(Color.yellow, 5));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});

		this.add(labelGioca);
		this.add(labelEsci);
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

		Font f = new Font(Font.MONOSPACED, Font.BOLD, 15);
		g2d.setFont(f);
		g2d.drawString("Autore: Marchetti Michele", 700, 700);
	}
}
