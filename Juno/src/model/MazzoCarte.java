package model;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Classe che rappresenta un mazzo di carte
 * @author michelemarchetti
 *
 */
public class MazzoCarte {
	
	/**
	 * Variabile che contiene la pila delel carte del mazzo
	 */
	private Stack<Carta> mazzo;
	

	/**
	 * Metodo costruttore
	 */
	public MazzoCarte() {
		mazzo = new Stack<>();
		generaMazzo(mazzo);
		mescolaMazzo();
		
	}

	
	/**
	 * Metodo privato di utilita'  che gestisce la logica di costruzione del mazzo
	 * @param l
	 */
	private void generaMazzo(List<Carta> l) {
		ValoreCartaNormale[] vcn = ValoreCartaNormale.values();
		ColoreCartaNormale[] ccn = ColoreCartaNormale.values();
		ValoreCartaSpeciale[] vcs = ValoreCartaSpeciale.values();
		
		//Aggiungo al mazzo 2 copie di ogni colore delle carte da 1 a 9
		for (int j = 0; j<2 ; j++) 
		{
			for (ColoreCartaNormale coloreCartaNormale: ccn) 
			{
				for (int k = 1; k< vcn.length; k++)
				{
					l.add(new Carta(vcn[k], coloreCartaNormale));
				}
			}
		}
		
		//Aggiungo al mazzo 1 copia di ogni colore delle carte 0
		for (ColoreCartaNormale coloreCartaNormale: ccn) 
		{	
				l.add(new Carta(vcn[0], coloreCartaNormale));
		}
		
		
		//Aggiungo al mazzo 2 copie di ogni colore delle carte speciali NON JOLLY
		for (int j = 0; j<2 ; j++) 
		{
			for (ColoreCartaNormale coloreCartaNormale: ccn) 
			{
				for (int k = 0; k< 3; k++)
				{
					l.add(new Carta(vcs[k], coloreCartaNormale));
				}
			}
		}
		
		//Aggiungo al mazzo 4 copie delle carte JOLLY
		for (int y = 0; y < 4; y++)
		{
			for (int u = 3 ; u< vcs.length; u++ ) 
			{
				l.add(new Carta(vcs[u], ColoreCartaSpeciale.NERO));
			}
		}
		
	}
	
	/**
	 * Metodo per mescolare in modo casuale le carte del mazzo
	 */
	public void mescolaMazzo() {
		Collections.shuffle(mazzo);
	}
	
	/**
	 * Metodo che ritorna il mazzo
	 * @return Mazzo
	 */
	public Stack<Carta> getMazzo() {
		return mazzo;
	}
	
	/**
	 * Metodo che inizializza il mazzo
	 */
	public void inizializzaMazzo() {
		mazzo.clear();
		generaMazzo(mazzo);
		mescolaMazzo();
	}
	/**
	 * Metodo che rimouve la carta in cima al mazzo e la ritorna
	 * @return Carta
	 * @throws MazzoEsauritoException 
	 */
	public Carta pesca() throws MazzoEsauritoException {
		if (mazzo.size()>1)
		{
			return mazzo.pop();
		}
		else
			{
			throw new MazzoEsauritoException();
			}
	}
	
	
}
