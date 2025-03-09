package view;

import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import controller.BottoneColoreListener;
import model.ColoreCarta;
import model.ColoreCartaNormale;

/**
 * Classe che rappresenta un bottone di scelta del colore
 * @author michele marchetti
 */
public class BottoneColore extends JButton {

	private static final long serialVersionUID = 1L;
	/**
	 * Variabile che contiene il valore del colore
	 */
	private ColoreCartaNormale coloreBottone;

	/**
	 * Metodo costruttore
	 */
	public BottoneColore(ColoreCartaNormale coloreCarta) {
		this.coloreBottone = coloreCarta;
		this.setPreferredSize(new Dimension(25, 50));

		// Caricare l'icona dal JAR
		this.setIcon(loadIcon("res/VUOTA_" + coloreCarta.getName() + "_ZOOM.png"));

		this.setBorder(BorderFactory.createEtchedBorder());
		this.addMouseListener(new BottoneColoreListener());
	}

	/**
	 * Metodo per caricare un'icona come risorsa dal JAR
	 */
	private ImageIcon loadIcon(String path) {
		try {
			return new ImageIcon(getClass().getClassLoader().getResource(path));
		} catch (Exception e) {
			System.err.println("Immagine non trovata: " + path);
			return null;
		}
	}

	/**
	 * Metodo getter che ritorna il colore associato al bottone
	 * @return il colore associato al bottone
	 */
	public ColoreCarta getColoreBottone() {
		return coloreBottone;
	}

	@Override
	public String toString() {
		return "Bottone: " + coloreBottone.getName();
	}
}
