package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.Timer;
import controller.GridaUnoAvversarioListener;
import model.CampoDiGioco;

/**
 * Classe che rappresenta la finestra di dichiarazione UNO avversario
 * @author michele marchetti
 */
public class PannelloUnoAvversario extends JDialog {

	private static final long serialVersionUID = 3893693776347908523L;
	/**
	 * Variabile che contiene il riferimento a un timer
	 */
	private Timer timer;

	/**
	 * Metodo costruttore
	 * @param String nomeGiocatore
	 */
	public PannelloUnoAvversario(String nomeGiocatore) {
		this.setTitle(nomeGiocatore + " grida");
		this.setLayout(new FlowLayout(FlowLayout.CENTER));

		JButton button = new JButton();

		// Caricamento immagine dal JAR
		button.setIcon(new ImageIcon(getClass().getClassLoader().getResource("res/GRIDA_UNO.png")));

		button.addMouseListener(new GridaUnoAvversarioListener());

		this.add(button);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		this.setAlwaysOnTop(true);

		int delay = 3000; //millis
		ActionListener task = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chiudiFinestra();
			}
		};

		CampoDiGioco.instance().setUno(false); //prova

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
