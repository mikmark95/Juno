package model;

import java.util.List;
import java.util.Stack;

/**
 * Classe che rappresenta una Pila degli scarti
 * @author michelemarchetti
 *
 */
public class PilaScarti {
	
	/**
	 * Varialibe che contiene la pila delle carte scartate
	 */
	private Stack<Carta> pila;
	
	/**
	 * Metodo costruttore
	 */
	public PilaScarti() {
		pila = new Stack<>();
		}
	
	/**
	 * Metodo che aggiunge la carta in cima alla pila degli scarti
	 * @param carta
	 */
	public void aggiungiCarta(Carta carta) {
		pila.push(carta);
	}
	
	/**
	 * Metodo che prende tutti gli elementi della pila e li rimette nel mazzo
	 */
	public void rigeneraMazzo(MazzoCarte mazzo) {
		Carta c = pila.pop();// prendo l'ultima carta della pila e la tengo da parte
		pila.forEach(x -> x.setEffettoUtilizzato(false));
		pila.forEach(x -> mazzo.getMazzo().add(x));
		pila.removeAllElements();
		mazzo.mescolaMazzo();
		pila.add(c);// aggiungo la carta alla pila
	}
	
	/**
	 * Metodo che guarda la cima della pila e ritorna il riferimento della carta
	 * @return Carta
	 */
	public Carta guardaCima() {
		if (pila.size()>0)
		{
			return pila.peek();
		}
		else
		{
			return new Carta(ValoreCartaNormale.ZERO, ColoreCartaSpeciale.DORSO);
		}
		
	}

	/**
	 * Metodo getter che ritorna la pila degli scarti
	 * @return
	 */
	public List<Carta> getPila() {
		return pila;
	}
	
	/**
	 * Metodo che inizializza la pila degli scarti
	 */
	public void inizializzaPila() {
		pila.clear();
	}
	

}
