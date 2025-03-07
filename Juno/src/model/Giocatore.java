package model;

import java.awt.Image;

/**
 * Classe che rappresenta un astrazione di un giocatore
 * @author michelemarchetti
 *
 */
public abstract class Giocatore {

	/**
	 * Variabile che contiene il nickname del giocatore
	 */
	private String nickname;
	
	/**
	 * Variabile che contiene l'immagine del avatar del giocatore
	 */
	private Image avatar;
	
	/**
	 * Variabile che contiene la mano delle carte del giocatore
	 */
	private ManoCarte manoCarte;
	
	/**
	 * Variabile che contiene il punteggio accumulato dal giocatore
	 */
	private int punteggio;
	
	/**
	 * Variabile che indica se il giocatore puo' giocare il turno
	 */
	private boolean turnoBloccato;
	
	
	
	/**
	 * Metodo costruttore
	 * @param nickname String
	 * @param avatar Image
	 * @param manoCarte ManoCarte
	 */
	public Giocatore(String nickname, Image avatar, ManoCarte manoCarte) {
		this.nickname = nickname;
		this.avatar = avatar;
		this.manoCarte = manoCarte;
	}

	/**
	 * Metodo getter che ritorna il nickname
	 * @return nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * Metodo getter che ritorna l'immagine dell avatar
	 * @return immagine avatar
	 */
	public Image getAvatar() {
		return avatar;
	}

	/**
	 * Metodo getter che ritorna la mano delle carte del giocatore
	 * @return carte in mano
	 */
	public ManoCarte getManoCarte() {
		return manoCarte;
	}
	
	/**
	 * Metodo che permette al giocatore di scartare una carta ed aggiungerla in cima 
	 * alla pila degli scarti
	 * @param carta
	 * @throws GridaUnoExceprtion 
	 * @throws ManoVuotaException 
	 */
	public void scartaCarta( Carta carta) throws GridaUnoExceprtion, ManoVuotaException {
		manoCarte.scartaCarta(carta);
	}
	
	/**
	 * Metodo che permetta la giocatore di pescare una carta e gestisce il caso
	 * in cui il mazzo sia esaurito
	 */
	public void pesca() {
		Carta c=null;
		MazzoCarte mazzo = manoCarte.getMazzoCarte();
		try {
			c = mazzo.pesca();
			manoCarte.getMano().add(c);
		} catch (MazzoEsauritoException e) {
			System.out.println(mazzo.getMazzo().size());
			manoCarte.getPilaScarti().rigeneraMazzo(mazzo);
		}
		
	}
	
	/**
	 * Metodo che permette al giocatore di pescare un numero di carte pari
	 * all argomento
	 * @param numeroCarte da pescare
	 */
	public void pesca(int numeroCarte) {
		for (int k = 0; k<numeroCarte; k++)
		{
			pesca();
		}
	}

	/**
	 * Metodo getter che resituisce il putenggio del giocatore
	 * @return  punteggio
	 */
	public int getPunteggio() {
		return punteggio;
	}

	/**
	 * Metodo che serve per calcolare il valore del punteggio dato dalle carte in mano
	 */
	public int calcolaPuntiCarte() {
		int risultato=0;
			for (Carta c : manoCarte.getMano())
				{
					risultato += c.getValore().getValue();
				}
		return risultato;
	}
	
	
	/**
	 * Metodo  che aggiunge i punti al punteggio del giocatore
	 * @param punti da aggiungere
	 * @throws PartitaTerminataException 
	 */
	public void aggiungiPunti(int punti) throws PartitaTerminataException {
		int parziale = punteggio+punti;
		if (parziale >= 500 )
		{
			throw new PartitaTerminataException();
		}
		this.punteggio += punti;
	}

	/**
	 * Metodo che azzera il punteggio del giocatore
	 */
	public void azzeraPunti() {
		this.punteggio = 0;
	}

	/**
	 * Metodo getter che ritorna un booleano che indica se il giocatore puo' giocare il turno
	 * @return boolean turnoBloccato
	 */
	public boolean isTurnoBloccato() {
		return turnoBloccato;
	}

	/**
	 * Metodo setter che riceve un booleano che indica se il giocatore puo' giocare il turno
	 * @param boolean turnoBloccato to set
	 */
	public void setTurnoBloccato(boolean turnoBloccato) {
		this.turnoBloccato = turnoBloccato;
	}
	
	
}
