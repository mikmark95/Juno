package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import model.CampoDiGioco;
/**
 * Classe che rappresenta l'intefaccia della sorteggi odel mazziere
 * @author michele marchetti
 *
 */
@SuppressWarnings("deprecation")
public class FinestraSorteggioMazziere extends JFrame implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Variaible che contiene il riferimento al modello
	 */
	CampoDiGioco campoDiGioco;
	
	/**
	 * Variaible che contiene il riferimento al pannello sfondo
	 */
	PannelloSfondo pannelloSfondo;
	
	/**
	 * Variabile che contiene il pannello sorteggio
	 */
	PannelloSorteggio pannelloSorteggio;
	
	/**
	 * Variabile che contiene il riferimento alla finestra principale
	 */
	FinestraPrincipale finestraPrincipale;
	
	/**
	 * Mtodo costruttore
	 */
	public  FinestraSorteggioMazziere (FinestraPrincipale finestraPrincipale) {
		super("Juno");
		this.campoDiGioco = CampoDiGioco.instance();
		this.pannelloSfondo = new PannelloSfondo();
		this.pannelloSorteggio = new PannelloSorteggio();
		this.finestraPrincipale = finestraPrincipale;
		
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
		this.add(pannelloSorteggio);
		
		campoDiGioco.addObserver(this);

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
	
	
	/*
	 * Metodo che serve per agigornare l'istanza
	 */
	@Override
	public void update(Observable o, Object arg) {
		repaint();
		
	}

	/**
	 * Metodo getter che ritorna il pannello sorteggio
	 * @return PannelloSorteggio
	 */
	public PannelloSorteggio getPannelloSorteggio() {
		return pannelloSorteggio;
	}
	
	/**
	 * Metodo getter che ritorna la finestra principale
	 * @return FinestraPrincipale
	 */
	public FinestraPrincipale getFinestraPrincipale() {
		return finestraPrincipale;
	}
	
}
