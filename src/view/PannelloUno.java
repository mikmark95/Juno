package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.Timer;
import controller.GridaUnoListener;
import model.CampoDiGioco;

/**
 * Classe che rappresenta la finestra di dichiarazione UNO
 * @author michele marchetti
 */
public class PannelloUno extends JDialog {

	private static final long serialVersionUID = 8913994049305836912L;
	/**
	 * Variabile che contiene il riferimento a un timer
	 */
	private Timer timer;

	/**
	 * Metodo costruttore
	 * @param String nomeGiocatore
	 */
	public PannelloUno(String nomeGiocatore) {
		this.setTitle(nomeGiocatore);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));

		JButton button = new JButton();

		// Caricamento immagine dal JAR
		button.setIcon(new ImageIcon(getClass().getClassLoader().getResource("res/GRIDA_UNO.png")));

		button.addMouseListener(new GridaUnoListener());

		this.add(button);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setAlwaysOnTop(true);

		int delay = 5000; //millis
		ActionListener task = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chiudiFinestra();
			}
		};

		this.timer = new Timer(delay, task);
		this.timer.start();
	}

	/**
	 * Metodo che viene chiamato dal timer per chiudere la finestra ed avanzare nel gioco
	 */
	private void chiudiFinestra() {
		this.setVisible(false);
		this.timer.stop();
		CampoDiGioco.instance().aggiorna();
		CampoDiGioco.instance().getTurnoPartita().prossimoTurno();
	}
}
