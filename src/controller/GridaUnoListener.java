package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import model.CampoDiGioco;
import model.TimerUno;

/**
 * Classe che gestisce il click sul pulsante grida uno
 * @author michele marchetti
 *
 */
public class GridaUnoListener implements MouseListener {
	
	/**
	 * Variabile che contiene il riferimento del modello
	 */
	CampoDiGioco campoDiGioco;
	
	/**
	 * Metodo costruttore
	 */
	public GridaUnoListener() {
		this.campoDiGioco = CampoDiGioco.instance();
	}

	/**
	 * Metodo che esegue le istruzione al verificarsi del click
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		JButton jb = (JButton) e.getSource();
		TimerUno tUno= campoDiGioco.getTurnoPartita().getTimerUno();
		if ( tUno.getTimer().isRunning())
		{
			tUno.getTimer().stop();
			jb.getParent().getParent().getParent().getParent().setVisible(false);
			campoDiGioco.aggiorna();
			campoDiGioco.getTurnoPartita().prossimoTurno();
			AudioManager.getInstance().play("res/uno.wav"); //effetto audio
		}
		else
		{
			jb.getParent().getParent().getParent().getParent().setVisible(false);
			campoDiGioco.aggiorna();
			campoDiGioco.getTurnoPartita().prossimoTurno();

		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
