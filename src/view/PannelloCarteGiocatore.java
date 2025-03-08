package view;

import java.awt.Component;
import java.awt.FlowLayout;
import java.util.Observable;
import controller.MouseCardListener;
import model.Carta;
import model.Giocatore;

/**
 * Classe che rappresenta la zona dove andranno inserite le carte del Giocatore utente
 * @author michele marchetti
 *
 */
@SuppressWarnings("deprecation")
public class PannelloCarteGiocatore extends PannelloCarteAstratto {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5169011736787089275L;


	/**
	 * Metodo costruttpre
	 * @param giocatore
	 */
	public PannelloCarteGiocatore(Giocatore giocatore) {
		super(giocatore);
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0,25));
		this.setOpaque(false);
		disponiCarte();
		
	}
	
	/**
	 * Metodo che serve per disporre le carte nel pannello
	 */
	@Override
	public void disponiCarte() {
		for (Carta c : getGiocatore().getManoCarte().getMano())
		{
		JCard jCard = new JCard(c);
			
			//------- serve disabilitare le carte quando non e' il turno
			if (this.getCampoDiGioco().getGiocatoreUtente().isTurnoInCorso())
			{
				jCard.addMouseListener(new MouseCardListener());
			}
		this.add(jCard);
			//--------
		}
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
