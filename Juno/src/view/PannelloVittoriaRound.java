package view;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.Timer;

import controller.AudioManager;
import model.CampoDiGioco;
import model.Carta;
import model.MazzoEsauritoException;
import model.ValoreCartaSpeciale;

/**
 * Classe che rappresenta la finestra che viene visualizzata appena un round termina
 * @author michele marchetti
 *
 */
public class PannelloVittoriaRound extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6735629888140860242L;
	/**
	 * Variabile che contiene il riferimento ad un timer
	 */
	private Timer timer;
	
	/**
	 * Metodo costruttore
	 * @param String nomeGiocatore
	 */
	public PannelloVittoriaRound(String nomeGiocatore) {
		this.setTitle("Ruond Terminato");
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel jLabel = new JLabel("Il round e' stato vinto da: "+ nomeGiocatore+" e per questo giochera' per primo al prossimo round");
		jLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		
		this.add(jLabel);
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
		
		int delay = 3500; //millis
		
		ActionListener task = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				chiudiFinestra();
				
			}

		};
		AudioManager.getInstance().play("res/round.wav");
		
		CampoDiGioco.instance().setRoundTerminato(false);
		this.timer = new Timer(delay, task);
		this.timer.start();

	}
	
	/**
	 * Metodo che viene chiamato dal timer per chiudere la finestra ed avanzare nel gioco
	 */
	private void chiudiFinestra() {
		this.setVisible(false);
		this.timer.stop();
		CampoDiGioco.instance().ricomincia();
		Carta c=null;
		try {
			
			c = CampoDiGioco.instance().getMazzoCarte().pesca();
			if (!(c.getValore() instanceof ValoreCartaSpeciale))
			{
				CampoDiGioco.instance().getPilaScarti().aggiungiCarta(c);
				CampoDiGioco.instance().setColoreCampo(c.getColore());
			}
			else
			{
				c = CampoDiGioco.instance().getMazzoCarte().pesca();
				CampoDiGioco.instance().getPilaScarti().aggiungiCarta(c);
				CampoDiGioco.instance().setColoreCampo(c.getColore());
			}
		} catch (MazzoEsauritoException e) {
			e.printStackTrace();
		}
		
		CampoDiGioco.instance().aggiorna();			
		CampoDiGioco.instance().getTurnoPartita().turnoAttuale();
	}
}
