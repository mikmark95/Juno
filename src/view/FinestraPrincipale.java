package view;

import javax.swing.*;

import model.CampoDiGioco;
import model.MazzoCarte;
import model.PilaScarti;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Classe che rappresenta la view principale del programma che raccoglie tutte le component
 * @author michelemarchetti
 *
 */
@SuppressWarnings({ "serial", "deprecation" })
public class FinestraPrincipale extends JFrame implements Observer{
	
	/**
	 * Variabile che contiene il riferimento del modello
	 */
	CampoDiGioco campoDiGioco;
	/**
	 * Varaibile che contiene il riferimento del pannello di sfondo
	 */
	PannelloSfondo pannelloSfondo;
	/**
	 * Variabile che contiene il riferimento del pannello giocatore utente
	 */
	PannelloGiocatoreUtente pannelloCarteGiocatoreUtente;
	/**
	 * Varaibile che contiene il riferimento al pannello centrale del gioco
	 */
	PannelloCentraleGioco pannelloCentraleGioco;
	/**
	 * Variabile che contiene il riferimento al pannello avversario centrale
	 */
	PannelloAvversarioCentrale pannelloCarteAvversarioUP;
	/**
	 * Variabile che contiene il riferimento al pannel;lo avversario verticale
	 */
	PannelloAvversarioVerticale pannelloCarteAvversarioDX;
	/**
	 * Variabile che contiene il riferimento al pannel;lo avversario verticale
	 */
	PannelloAvversarioVerticale pannelloCarteAvversarioSX;
	
	
	
	/**
	 * MEeodo costruttore
	 * @param pannelloSfondo
	 * @param pannelloCarteGiocatoreUtente
	 * @param pannelloCentraleGioco
	 * @param pannelloCarteAvversarioUP
	 * @param pannelloCarteAvversarioDX
	 * @param pannelloCarteAvversarioSX
	 * @throws HeadlessException
	 */
	public FinestraPrincipale( PannelloSfondo pannelloSfondo,
			PannelloGiocatoreUtente pannelloCarteGiocatoreUtente, PannelloCentraleGioco pannelloCentraleGioco,
			PannelloAvversarioCentrale pannelloCarteAvversarioUP, PannelloAvversarioVerticale pannelloCarteAvversarioDX,
			PannelloAvversarioVerticale pannelloCarteAvversarioSX) throws HeadlessException {
		super("Juno");
	
		this.campoDiGioco = CampoDiGioco.instance();
		this.pannelloSfondo = pannelloSfondo;
		this.pannelloCarteGiocatoreUtente = pannelloCarteGiocatoreUtente;
		this.pannelloCentraleGioco = pannelloCentraleGioco;
		this.pannelloCarteAvversarioUP = pannelloCarteAvversarioUP;
		this.pannelloCarteAvversarioDX = pannelloCarteAvversarioDX;
		this.pannelloCarteAvversarioSX = pannelloCarteAvversarioSX;
		setup();
		addDetails();
	}


	/**
	 * Metodo che Crea il setup della finestra, inizializzato gli oggetti. 
	 */
	private void setup() {	
		/**
		 * Imposta il pannello dove andranno inseriti i contenuti
		 */
		this.setContentPane(pannelloSfondo);
		
		
		this.setLayout(new BorderLayout());
		this.add(pannelloCentraleGioco, BorderLayout.CENTER);
		this.add(pannelloCarteAvversarioUP, BorderLayout.NORTH);
		this.add(pannelloCarteAvversarioDX, BorderLayout.EAST);
		this.add(pannelloCarteAvversarioSX, BorderLayout.WEST);
		this.add(pannelloCarteGiocatoreUtente, BorderLayout.SOUTH);
		
		
		campoDiGioco.addObserver(this);
		
		MazzoCarte mazzoCarte = campoDiGioco.getMazzoCarte();
		JMazzo jMazzo = new JMazzo(mazzoCarte);
		 
		
		PilaScarti pilaScarti = campoDiGioco.getPilaScarti();
		JPila jPila = new JPila(pilaScarti);
		campoDiGioco.addObserver(jPila);
		pannelloCentraleGioco.add(jPila);
		
	}
	
	
	
	/**
	 *Metodo che serve ad aggiungere i dettagli fondamentali della finestra.
	 */
	private void addDetails() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setSize(new Dimension(1000, 780));
		this.setResizable(false);
	}
	
	/**
	 * Metodo getter che ritorna il Pannello centrale
	 * @return PannelloCentraleGioco
	 */
	public PannelloCentraleGioco getCentrale() {
		return pannelloCentraleGioco;
	}


	/**
	 * Metodo che serve per aggiornare l'istanza
	 */
	@Override
	public void update(Observable o, Object arg) {
		//---------- prova
		if (campoDiGioco.isUno())
		{
			String nome = campoDiGioco.getTurnoPartita().nomeGiocatoreAttuale();
			new PannelloUnoAvversario(nome);
		}
		//-----------
		
		boolean b = campoDiGioco.isPartitaTerminata();
		if (b == true)
		{			
			this.setVisible(false);
			campoDiGioco.deleteObserver(this);
			new FinestraTerminaPartita(campoDiGioco.getTurnoPartita().giocatoreAttuale());
		}
		else
		{
			this.setVisible(true);
		}
		
		if (campoDiGioco.isRoundTerminato())
		{
			new PannelloVittoriaRound(campoDiGioco.getTurnoPartita().nomeGiocatoreAttuale());
		}
	
	}
	
	
}
