package model;

import java.awt.Image;

/**
 * Classe che rappresenta un giocatore avversario
 * @author michelemarchetti
 *
 */
public class GiocatoreAvversario extends Giocatore {
	
	/**
	 * Variaible che contiene la strategia di gioco
	 */
	private StrategiaGioco strategiaGioco;
	

	/**
	 * Metodo costruttore
	 * @param nickname nickname del giocatore
	 * @param avatar immagine del giocatore
	 * @param manoCarte carte in mano al giocatore
	 * @param strategiaGioco strategia di gioco del giocatore
	 */
	public GiocatoreAvversario(String nickname, Image avatar, ManoCarte manoCarte, StrategiaGioco strategiaGioco) {
		super(nickname, avatar, manoCarte);
		this.strategiaGioco = strategiaGioco;
	}

	/**
	 * Metodo getter che ritorna la strategia di gioco del giocatore
	 * @return StrategiaGioco la strategia di gioco
	 */
	public StrategiaGioco getStrategia() {
		return strategiaGioco;
	}
	
	/**
	 * Metodo che fa eseguire la mossa al giocatore in base alla strategia
	 * che ha impostato
	 * @throws GridaUnoExceprtion 
	 * @throws ManoVuotaException 
	 */
	public void giocaTurno() throws GridaUnoExceprtion, ManoVuotaException {
		strategiaGioco.faiMossa();
	}
	
	/**
	 * Metodo setter che imposta la strategia di gioco dle giocatore
	 * @param strategiaGioco StrategiaGioco
	 */
	public void setStrategia(StrategiaGioco strategiaGioco) {
		this.strategiaGioco = strategiaGioco;
	}
	
	/**
	 * Metodo che restiusce una stringa che rappresenta il giocatore
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("NickName: "+getNickname()+"\n");
		sb.append("Avatar: "+getAvatar().toString()+"\n");
		sb.append("Carte in mano: "+ getManoCarte().getMano().toString()+"\n");
		sb.append("Numero carte in mano: "+ getManoCarte().getMano().size()+"\n");
		sb.append("Punteggio: "+ getPunteggio()+"\n");
		sb.append("Strategia di gioco: "+ strategiaGioco.toString()+"\n");
		return sb.toString();
	}
}
