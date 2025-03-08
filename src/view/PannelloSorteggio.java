package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.CampoDiGioco;
import model.Carta;
import model.Giocatore;
import model.MazzoCarte;
import model.MazzoEsauritoException;
import model.ValoreCartaSpeciale;

/**
 * Classe che raprpesenta il pannello sorteggio mazziere
 * @author michele marchetti
 *
 */
@SuppressWarnings("deprecation")
public class PannelloSorteggio extends JPanel implements Observer{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6251915418224817815L;

	/**
	 * Variabile che contiene il riferimento al modello
	 */
	private CampoDiGioco campoDiGioco;
	
	/**
	 * Variaible che contiene l'indice dei giocatori
	 */
	private int indice;
	
	/**
	 * Variabile che contiene il riferimento al mazzo di carte
	 */
	private MazzoCarte mazzoCarte;
	
	/**
	 * Variabile che contiene la lista degli indici dei giocatori
	 */
	private List<Integer> lstIndici;

	/**
	 * MEtodo costruttore
	 */
	public PannelloSorteggio() {
		
		this.campoDiGioco = CampoDiGioco.instance();
		this.campoDiGioco.addObserver(this);
		this.lstIndici = new ArrayList<Integer>();

		this.setOpaque(false);
		this.setLayout(new FlowLayout(FlowLayout.CENTER,70,150));
		
		this.mazzoCarte = new MazzoCarte();
		
		if (!(campoDiGioco.getTurnoPartita().isSensoOrario()))
		{
			Collections.reverse(campoDiGioco.getListaGiocatoriTotali());
			campoDiGioco.getTurnoPartita().setSensoOrario(true);
		}
		
		for (indice=0; indice <4 ; indice++)
		{
			Carta carta = null;
			try {
				carta = mazzoCarte.pesca();
			} catch (MazzoEsauritoException e) {
				e.printStackTrace();
			}
			Giocatore giocatore =campoDiGioco.getListaGiocatoriTotali().get(indice);
			JCardSorteggio cardSorteggio = new JCardSorteggio(carta, giocatore);
			this.add(cardSorteggio);
			int val = 0;
			if (!(carta.getValore() instanceof ValoreCartaSpeciale))
			{
				val = carta.getValore().getValue();
			}
			lstIndici.add(val);
		}
		
		
		JLabel jLabel = new JLabel(new ImageIcon("res/INIZIA_PARTITA.png"));
		jLabel.addMouseListener(new MouseListener() {
			
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
				JLabel lbl = (JLabel) e.getSource();
				lbl.setBorder(BorderFactory.createEmptyBorder());
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				JLabel lbl = (JLabel) e.getSource();
				lbl.setBorder(BorderFactory.createLineBorder(Color.yellow, 5));
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				JLabel lbl = (JLabel) e.getSource();
				FinestraSorteggioMazziere f = (FinestraSorteggioMazziere) lbl.getRootPane().getParent();
				f.getFinestraPrincipale().setVisible(true);
				f.setVisible(false);
			}
		});
		jLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		jLabel.setForeground(Color.ORANGE);;
		jLabel.setBorder(BorderFactory.createEmptyBorder());
		this.add(jLabel);	
		
		campoDiGioco.getTurnoPartita().setIndice(getValoreMax(lstIndici));

	}

	/**
	 * Metodo che serve per disegnare le componenti
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		AffineTransform affineTransform = new AffineTransform();
		
		g2d.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		g2d.drawString("Sorteggio per decidere chi sara' il mazziere iniziale", 210, 40);
		g2d.drawString("Il giocatore che fara' il mazziere e': "+ campoDiGioco.getListaGiocatoriTotali().get(getValoreMax(lstIndici)).getNickname(), 230, 460);
	}
	
	/**
	 * Metodo che aggiorna l'istanza
	 */
	@Override
	public void update(Observable o, Object arg) {
		this.repaint();
		
	}
	
	/**
	 * Metodo che ritorna il lavore massimo in una lista di interi
	 * @param list<Integer> lst
	 * @return int valore massimo
	 */
	public int getValoreMax(List<Integer> lst) {
		int max = Collections.max(lst);
		int indice = lst.indexOf(max);
		return indice;
	}
	
}
