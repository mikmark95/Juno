package controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;

import model.CampoDiGioco;
import view.BottoneColore;

/**
 * Classe che rappresenta l'evento che scatena il mouse sul bottone colore
 * @author michele marchetti
 *
 */
public class BottoneColoreListener implements MouseListener {

	/**
	 * Metodo che gestisce il click sulla sorgente
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		BottoneColore bc = (BottoneColore) e.getSource();
		CampoDiGioco.instance().setColoreCampo(bc.getColoreBottone());
		
		CampoDiGioco.instance().getGiocatoreUtente().setTurnoInCorso(false);
		
		bc.getParent().getParent().getParent().getParent().setVisible(false);
		CampoDiGioco.instance().aggiorna();
		
		// faccio in modo che una volta scelto i lcolore si faccia la prossima mossa
		CampoDiGioco.instance().getTurnoPartita().prossimoTurno();
		CampoDiGioco.instance().aggiorna();

	}

	/**
	 * Metodo che gestisce il click prolungato sulla sorgente
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Metodo che gestisce il rilascio del click prolungato sulla sorgente
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Metodo che gestisce l'ingresso del cursone sulla sorgente
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		BottoneColore bc = (BottoneColore) e.getSource();
		bc.setBorder(BorderFactory.createLineBorder(Color.BLACK,5));

	}

	/**
	 * Metodo che gestisce l'uscita del cursore dalla sorgente
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		BottoneColore bc = (BottoneColore) e.getSource();
		bc.setBorder(BorderFactory.createEtchedBorder());

	}

}
