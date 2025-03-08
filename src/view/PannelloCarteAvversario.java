package view;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.Observable;
import model.Carta;
import model.ColoreCartaSpeciale;
import model.Giocatore;
import model.ValoreCartaNormale;

/**
 * Classe ceh rappresenta la zona dove andranno inserite le carte del Giocatore Avversario
 * @author michele marchetti
 *
 */
@SuppressWarnings("deprecation")
public class PannelloCarteAvversario extends PannelloCarteAstratto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8494423243810522238L;


	/**
	 * Metodo costruttore
	 * @param giocatore
	 */
	public PannelloCarteAvversario(Giocatore giocatore) {
		super(giocatore);
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER,1,7));
		this.setOpaque(false);
		disponiCarte();
		
		@SuppressWarnings("unused")
		Toolkit toolkit = getToolkit();
		
	}
	
	/**
	 * Metodo che dispone le carte nel pannello
	 */
	@Override
	public void disponiCarte() {
		for (Carta c : getGiocatore().getManoCarte().getMano())
		{
			Carta carta = new Carta(ValoreCartaNormale.UNO	, ColoreCartaSpeciale.DORSO);	
			JCard jCard = new JCard(carta);
			this.add(jCard);
		}
		setToolTipText("Numero Carte:"+getGiocatore().getManoCarte().getMano().size());
	}
	

	/**
	 * Metodo che aggiorna l'istanza
	 */
	@Override
	public void update(Observable o, Object arg) {
		Carta c = (Carta) arg;
		Component[] components = this.getComponents();
		for (int x = 0; x< components.length; x++)
		{
			components[x].setVisible(false);
			this.remove(components[x]);
		}
		getGiocatore().getManoCarte().getMano().remove(cercaCarta(c));
		disponiCarte();
		
	}
	
	
	
}
