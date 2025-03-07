package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import model.CampoDiGioco;
import model.Carta;
import model.ColoreCartaSpeciale;
import model.GridaUnoExceprtion;
import model.ManoVuotaException;

/**
 * Classe che rappresenta un pannello che serve per decidere se giocare o meno la carta appena pescata
 * @author michele marchetti
 *
 */
public class PannelloGiocaPesca extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1268891379811108545L;

	/**
	 * Variaible che contiene il riferimento al modello
	 */
	private CampoDiGioco campoDiGioco;
	
	/**
	 * Variabile che contiene il riferimento alla carta
	 */
	@SuppressWarnings("unused")
	private Carta carta;

	/**
	 * Metodo costruttore
	 * @param carta
	 */
	public PannelloGiocaPesca(Carta carta) {
		this.setTitle("Scegli cosa fare");
		this.campoDiGioco = CampoDiGioco.instance();
		this.carta = carta;
		this.setAlwaysOnTop(true);
		this.setLayout(new BorderLayout());
		
		JCard jCard = new JCard(carta);
		JPanel jPanelCarta = new  JPanel(new FlowLayout(FlowLayout.CENTER));
		jPanelCarta.add(jCard);
		this.add(jPanelCarta, BorderLayout.NORTH);
		
		
		JPanel jPanelBottoni =  new  JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton btnGiocaCarta = new JButton("GIOCA CARTA");
		jPanelBottoni.add(btnGiocaCarta);
		btnGiocaCarta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					campoDiGioco.getGiocatoreUtente().scartaCarta(carta);
					campoDiGioco.getPilaScarti().aggiungiCarta(carta);
					campoDiGioco.setColoreCampo(carta.getColore());
					
					campoDiGioco.getGiocatoreUtente().setTurnoInCorso(false);
					campoDiGioco.getTurnoPartita().prossimoTurno();
					campoDiGioco.aggiorna();
					
				} catch (GridaUnoExceprtion e1) {
					if ( carta.getColore().equals(ColoreCartaSpeciale.NERO))
					{
						SceltaColore s = new SceltaColore();
					}
					
					//-------------
					campoDiGioco.getPilaScarti().aggiungiCarta(carta);
					campoDiGioco.setColoreCampo(carta.getColore());
					//----------------
					
					campoDiGioco.getTurnoPartita().getTimerUno().getTimer().start();
					new PannelloUno(campoDiGioco.getGiocatoreUtente().getNickname());
					campoDiGioco.aggiorna();
				} catch (ManoVuotaException e1) {
					e1.printStackTrace();
				}
				finally {
					campoDiGioco.aggiorna();
					
					PannelloGiocaPesca.this.setVisible(false);

				}
			}
		});
		
		
		
		JButton btnPassaTUrno = new JButton("PASSA TURNO");
		jPanelBottoni.add(btnPassaTUrno);
		btnPassaTUrno.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				campoDiGioco.getGiocatoreUtente().setTurnoInCorso(false);
				campoDiGioco.getGiocatoreUtente().getManoCarte().getMano().add(carta);
				campoDiGioco.getTurnoPartita().prossimoTurno();
				campoDiGioco.aggiorna();	
				PannelloGiocaPesca.this.setVisible(false);
			}
		});
		
		this.add(jPanelBottoni, BorderLayout.CENTER);
		
		this.setVisible(true);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

	}
}
