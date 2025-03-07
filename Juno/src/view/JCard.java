package view;

import javax.swing.*;

import model.Carta;

import java.awt.*;
import java.awt.geom.AffineTransform;

/*
 * Classe che rappresenta la GUI di una carta da gioco
 */
public class JCard extends JPanel  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1885955691319901433L;
	/**
	 * Variabile che contiene il riferimento al modello della carta
	 */
	private Carta carta;
	
	/**
	 * Metodo costruttore
	 * @param Carta carta
	 */
	public JCard(Carta carta) {
		this.carta = carta;
		this.setPreferredSize(new Dimension(50	, 70));
		this.setOpaque(false);
	}
	
	/**
	 * Metodo che disegna l'oggetto
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D)g;
		
		AffineTransform identity = new AffineTransform();
		
		g2d.drawImage(carta.getImage(), identity, this);
	}

	
	
	/**
	 * Metodo getter che ritorna il riferimento alla carta
	 * @return Carta
	 */
	public Carta getCarta() {
		return this.carta;
	}

	

}
