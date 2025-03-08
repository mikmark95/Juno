package controller;

import java.io.IOException;

import javax.swing.SwingUtilities;

import model.*;
import model.GridaUnoExceprtion;
import view.*;
/**
 * Classe contennte il main dell'applicazione
 * @author michelemarchetti
 *
 */
public class JUno {
	
	/**
	 * Metodo Main
	 * @param args
	 * @throws IOException
	 * @throws GridaUnoExceprtion
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws IOException, GridaUnoExceprtion, InterruptedException {
		

		SwingUtilities.invokeLater(new Runnable() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				AudioManager.getInstance().playInLoop("res/COLONNA_SONORA.wav");
				CampoDiGioco campoDiGioco = CampoDiGioco.instance();
				PannelloSfondo pannelloSfondo = new PannelloSfondo();
				/**
				 * -----------
				 */
				PannelloGiocatoreUtente pannelloCarteGiocatoreUtente = new PannelloGiocatoreUtente();
				PannelloCentraleGioco pannelloCentraleGioco= new PannelloCentraleGioco();

				PannelloAvversarioCentrale pannelloCarteAvversarioUP = new PannelloAvversarioCentrale(campoDiGioco.getListaAvversari().get(1));

				PannelloAvversarioVerticale pannelloCarteAvversarioDX = new PannelloAvversarioVerticale(campoDiGioco.getListaAvversari().get(2));

				PannelloAvversarioVerticale pannelloCarteAvversarioSX= new PannelloAvversarioVerticale(campoDiGioco.getListaAvversari().get(0));
				
		
				/**
				 * ----------------------------
				 */
				
				System.out.println(campoDiGioco.getPilaScarti().getPila());
				System.out.println(campoDiGioco.getMazzoCarte().getMazzo());
				
				
				FinestraPrincipale f = new FinestraPrincipale( pannelloSfondo, pannelloCarteGiocatoreUtente, pannelloCentraleGioco, pannelloCarteAvversarioUP, pannelloCarteAvversarioDX, pannelloCarteAvversarioSX);
				campoDiGioco.addObserver(f);
				
				
				new FinestraIntroduzione(f);
			
				System.out.println(campoDiGioco.getPilaScarti().getPila());
				System.out.println(campoDiGioco.getMazzoCarte().getMazzo());
				
				
			}
		});
		
	}
}
