package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.AudioManager;
import model.CampoDiGioco;
import model.Giocatore;

/**
 * Classe che rappresenta il pannello di sfondo della finestra termina partita
 * @author michele marchetti
 *
 */
public class PannelloTerminaPartita extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3375712197304545346L;

	/**
	 * Variabile che contiene il riferimento al giocatore che ha vinto
	 */
	private Giocatore giocatore;
	
	/**
	 * Variabile che contiene il riferimento del modello
	 */
	private CampoDiGioco campoDiGioco;
	
	/**
	 * Metodo costruttore
	 * @param giocatore 
	 */
	public PannelloTerminaPartita(Giocatore giocatore) {
		AudioManager.getInstance().play("res/vittoria.wav");
		
		this.giocatore = giocatore;
		this.campoDiGioco = CampoDiGioco.instance();
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER,100,100));
		
		JLabel lblRigioca = new JLabel(new ImageIcon("res/RIGIOCA.png"));
		lblRigioca.addMouseListener(new MouseListener() {
			
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
				FinestraTerminaPartita f = (FinestraTerminaPartita) jLabel.getRootPane().getParent();
				f.setVisible(false);
				campoDiGioco.ricomincia();
				PannelloSfondo pannelloSfondo = new PannelloSfondo();
				/**
				 * -----------
				 */
				PannelloGiocatoreUtente pannelloCarteGiocatoreUtente = new PannelloGiocatoreUtente();
				PannelloCentraleGioco pannelloCentraleGioco= new PannelloCentraleGioco();

				PannelloAvversarioCentrale pannelloCarteAvversarioUP = new PannelloAvversarioCentrale(campoDiGioco.getListaAvversari().get(1));

				PannelloAvversarioVerticale pannelloCarteAvversarioDX = new PannelloAvversarioVerticale(campoDiGioco.getListaAvversari().get(2));

				PannelloAvversarioVerticale pannelloCarteAvversarioSX= new PannelloAvversarioVerticale(campoDiGioco.getListaAvversari().get(0));
				
				FinestraPrincipale finestraPrincipale = new FinestraPrincipale(pannelloSfondo, pannelloCarteGiocatoreUtente, pannelloCentraleGioco, pannelloCarteAvversarioUP, pannelloCarteAvversarioDX, pannelloCarteAvversarioSX);
				
				FinestraSorteggioMazziere fsm =new FinestraSorteggioMazziere(finestraPrincipale);
				fsm.setVisible(true);
			}
		});
		this.add(lblRigioca);
		
		JLabel lblEsci = new JLabel(new ImageIcon("res/ESCI.png"));
		lblEsci.addMouseListener(new MouseListener() {
			
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
		this.add(lblEsci);
	}
	/**
	 * Metodo che serve per disegnare le componenti
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		AffineTransform affineTransform = new AffineTransform();
		Image img = null;
		try {
			img = ImageIO.read(new File("res/SFONDO_RIDOTTO.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g2d.drawImage(img, affineTransform, this);
		
		g2d.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		g2d.drawString("La parita e' stata vinta da "+ giocatore.getNickname()+" !!!", 50, 50);
		
		if(giocatore.equals(campoDiGioco.getGiocatoreUtente()))
		{
			affineTransform.translate(210, 150);
			g2d.drawImage(giocatore.getAvatar(), affineTransform, this);
			
		}
		else 
		{
			affineTransform.translate(260, 150);
			g2d.drawImage(giocatore.getAvatar(), affineTransform, this);
			
		}
	}
}
