package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Classe che rappresenta le carte di una mano
 * @author michelemarchetti
 *
 */
public class ManoCarte {

	/**
	 * Variabile che contiene la lista delle cartein mano
	 */
	private List<Carta> carteMano;
	/**
	 * Variabile che contiene il riferimento al mazzo di carte
	 */
	private MazzoCarte mazzoCarte;
	/**
	 * Variabile che contiene il riferimento alla pila degli scarti
	 */
	private PilaScarti pilaScarti;
	
	/**
	 * Metodo costruttore
	 * @param mazzo
	 */
	public ManoCarte(MazzoCarte mazzo, PilaScarti pila) {
		this.mazzoCarte = mazzo;
		this.pilaScarti = pila;
		carteMano = new ArrayList<>();
		distribuisciCarte(mazzoCarte);
	}
	
	/**
	 * Metodo che distribuisce sette carte dal mazzo alla mano
	 * @param mazzo MazzoCarte
	 */
	private void distribuisciCarte(MazzoCarte mazzo) {
		for (int k=0 ; k < 7; k++) 
		{
			Carta carta = mazzo.getMazzo().pop();
			carteMano.add(carta);
		}
	}
	
	/**
	 * Metodo che inizializza la mano delle carte
	 */
	public void inizializzaMano() {
		carteMano.clear();
		distribuisciCarte(mazzoCarte);
	}
	
	
	/**
	 * Metodo getter che restiuisce la lista delel carte in mano
	 * @return List<Carta>
	 */
	public List<Carta> getMano() {
		return carteMano;
	}
	
	

	/**
	 * Metodo che scarta una carta dalla mano e la aggiunge alla pila degli scarti
	 * @param carta
	 * @throws GridaUnoExceprtion 
	 * @throws ManoVuotaException 
	 */
	public void scartaCarta(Carta carta) throws GridaUnoExceprtion, ManoVuotaException {
		
		if (carteMano.contains(carta))
		{
		Carta card = cercaCarta(carta);
		pilaScarti.aggiungiCarta(card);
		carteMano.remove(card);
		}
		if (carteMano.size()==1) throw new GridaUnoExceprtion();
		
		if (carteMano.size() == 0) throw new ManoVuotaException();
		
	}
	
	/**
	 * Metodo che utilizza gli stream per fare una ricerca di una carta specifica 
	 * sulla mano e la ritorna se trovata
	 * @param carta Carta
	 * @return Carta
	 */
	private Carta cercaCarta( Carta carta ) {
		Optional<Carta> c = 
				carteMano.stream()
					.filter(x -> x.equals(carta))
					.findAny();
		
		Carta card = c.orElseThrow();
		return card;
	}
	
	/**
	 * Metodo getter che ritorna il mazzo di carte
	 * @return MazzoCarte
	 */
	public MazzoCarte getMazzoCarte() {
		return mazzoCarte;
	}

	
	/**
	 * Metodo getter che ritorna la Pila degli scarti
	 * @return PilaScarti
	 */
	public PilaScarti getPilaScarti() {
		return pilaScarti;
	}

}
