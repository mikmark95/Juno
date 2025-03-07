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
 *
 */
public class BottoneColore extends JButton{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Variabile che contiene il valore del colore
	 */
	private ColoreCartaNormale coloreBottone;
	
	
	/**
	 * Metodo costruttore
	 */
	public BottoneColore(ColoreCartaNormale coloreCarta) {
//		super("Colore "+ coloreCarta.getName());
		this.coloreBottone = coloreCarta;
		this.setPreferredSize(new Dimension(25, 50));
		this.setIcon(new ImageIcon("res/"+"VUOTA_"+coloreCarta.getName()+"_ZOOM.png"));
		this.setBorder(BorderFactory.createEtchedBorder());
		this.addMouseListener(new BottoneColoreListener());
//		this.setBackground(coloreBottone.getColore());
		
	}

	/**
	 * Metodo getter che ritorna il colore associato al bottone
	 * @return il colore associato al bottone
	 */
	public ColoreCarta getColoreBottone() {
		return coloreBottone;
	}
	
	/**
	 * Metodo che ritorna la rappresentazione dell oggetto sotto forma di stringa
	 * @return String 
	 */
	@Override
	public String toString() {
		return "Bottone: "+coloreBottone.getName();
	}
	
}
 