package view;

import javax.swing.*;

import model.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Classe astratta che rappresenta un pannello Avatar generico
 * @author michele marchetti
 *
 */
@SuppressWarnings("deprecation")
public abstract class PannelloAvatarAstratto extends JPanel implements Observer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1398892836041197342L;

	/**
	 * Variabile che contiene il riferimento del modello
	 */
	private CampoDiGioco campoDiGioco;
	
	/**
	 * Variabile che contiene il riferimento al giocatore
	 */
	private Giocatore giocatore;
	
	/*
	 * Metodo costruttore
	 */
	public PannelloAvatarAstratto(Giocatore giocatore) {
		this.campoDiGioco = CampoDiGioco.instance();
		this.giocatore = giocatore;
		
		// ------------------------- MouseListener che serve per permettere all utente di guardare tramite console le carte in mano agli avversari
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(giocatore.getManoCarte().getMano().toString());
				
			}
		});
		//--------------------------
		
	}
	
	/**
	 * Metodo getter che restuisce il riferimento del modello
	 * @return CampoDiGioco campoDiGioco
	 */
	public CampoDiGioco getCampoDiGioco() {
		return campoDiGioco;
	}
	
	/**
	 * Metodo getter che restiuisce il riferimento del giocatore
	 * @return Giocatore giocatore
	 */
	public Giocatore getGiocatore() {
		return giocatore;
	}

	/**
	 * Metodo che consente di disegnare le componenti
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
	}

	/**
	 * Metodo che aggiorna l'istanza
	 */
	@Override
	public void update(Observable o, Object arg) {
		this.repaint();
		
	}

	
}

