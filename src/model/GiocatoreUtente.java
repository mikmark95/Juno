package model;

import java.awt.Image;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Classe che rappresenta un oggetto giocatore utente
 * @author michelemarchetti
 *
 */
public class GiocatoreUtente extends Giocatore {
	
	/**
	 * Variabile statica che contiene la stringa del percorso del file di conifgurazione
	 */
	private static final String FILE_CONFIG = "res/config.txt";
	
	/**
	 * Variabile che rappresenta il livello del giocatore
	 */
	private int livello;
	
	/**
	 * Variabile che rappresenta il numero di partite vinte
	 */
	private int partiteVinte;
	
	/**
	 * Variabile che rappresenta il numero di partite perse
	 */
	private int partitePerse;
	
	/**
	 * Variabile che indica se il giocatore sta eseguendo il turno
	 */
	private boolean turnoInCorso;

	/**
	 * Metodo costruttore
	 * @param nickname nickname
	 * @param avatar immagine dell avatar
	 * @param manoCarte carte in mano
	 */
	public GiocatoreUtente(String nickname, Image avatar, ManoCarte manoCarte) {
		super(nickname, avatar, manoCarte);
		this.setTurnoInCorso(false);
		estraiVariabili();
	}
	
	
	/**
	 * Metodo privato che legge il file di configurazione e ritorna una 
	 * mappa delle informazioni del giocatore
	 * @return mappa chiave-valore
	 */
	private Map<String, Integer> getConfigurazione(){
		Map<String, Integer> map = null;
		try {
			map = Files.lines(Paths.get(FILE_CONFIG))
					.map(s->s.split("="))
					.collect(Collectors.toMap(x->x[0], t ->  Integer.parseInt(t[1])))
				
					;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}


	/**
	 * Metodo privato che modifica il file di configurazione con i nuovi paramentri
	 * @param livello
	 * @param vinte
	 * @param perse
	 */
	private void setConfigurazione(int livello, int vinte, int perse) {
		try {
			List<String> l = Files.readAllLines(Paths.get(FILE_CONFIG));
			String sLivello = l.get(0);
			sLivello = sLivello.substring(0, sLivello.length()-1).concat(String.valueOf(livello));
			
			String sVinte = l.get(1);
			sVinte = sVinte.substring(0, sVinte.length()-1).concat(String.valueOf(vinte));
			
			String sPerse = l.get(2);
			sPerse = sPerse.substring(0, sPerse.length()-1).concat(String.valueOf(perse));
			
			List<String> lNuova = new ArrayList<>();
			lNuova.add(sLivello);
			lNuova.add(sVinte);
			lNuova.add(sPerse);
			
			Files.write(Paths.get(FILE_CONFIG), lNuova, StandardOpenOption.WRITE);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo che legge i dati dal file di configurazione e le associa alle relative variabili
	 */
	private void estraiVariabili() {
		Map<String, Integer> mappa = getConfigurazione();
		this.livello = mappa.get("livello");
		this.partitePerse= mappa.get("perse");
		this.partiteVinte=mappa.get("vinte");
	}
	
	/**
	 * Metodo getter che ritorna il livello del giocatore
	 * @return  livello del giocatore
	 */
	public int getLivello() {
		return livello;
	}

	/**
	 * Mettodo setter che imposta il livello del giocatore
	 * @param livello del giocatore da impostare
	 */
	private void setLivello(int livello) {
		if (livello>0)
			{
				this.livello = livello;
			}
		else
			{
				return;
			}
	}

	/**
	 * Metodo getter che ritorna il numero di partite vinte
	 * @return numero partite vinte
	 */
	public int getPartiteVinte() {
		return partiteVinte;
	}

	/**
	 * Metodo setter che imposta il numero di partie vinte
	 * @param il numero di partite vinte da impostare
	 */
	private void setPartiteVinte(int partiteVinte) {
		if (this.partiteVinte < 3)
		{
			this.partiteVinte = partiteVinte;
		}
		else
			{
				this.partiteVinte = 0;
				setLivello(livello+1);
			}
	}

	/**
	 * Metodo getter che ritorna il numero di partite perse
	 * @return ritorna il numero di partite perse
	 */
	public int getPartitePerse() {
		return partitePerse;
	}

	/**
	 * Metodo setter che imposta il numero di partite perse
	 * @param numero di partite per se da impostare
	 */
	private void setPartitePerse(int partitePerse) {
		if (this.partitePerse<4)
		{
			this.partitePerse = partitePerse;
		}
		else
			{
				this.partitePerse=0;
				setLivello(livello-1);
			}
	}
	
	/**
	 * Metodo che aggiunge una vittoria allo sotirco del giocatore
	 */
	public void vittoria() {
		setPartiteVinte(partiteVinte+1);
		setPartitePerse(0);
		setConfigurazione(livello, partiteVinte, partitePerse);
	}
	
	/**
	 * Metodo che aggiunge una sconfitta allo storico del giocatore
	 */
	public void sconfitta() {
		setPartitePerse(partitePerse+1);
		setPartiteVinte(0);
		setConfigurazione(livello, partiteVinte, partitePerse);
	}
	
	/**
	 * Metodo che aggiorna il file di configurazione e azzera i parametri del giocatore
	 */
	public void azzeraParametri() {
		setConfigurazione(1, 0, 0);
		estraiVariabili();
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
		sb.append("Livello: "+livello+"\n");
		sb.append("Partite Vinte: "+partiteVinte+"\n");
		sb.append("Partite Perse:" +partitePerse+"\n");
		return sb.toString();
	}


	/**
	 * Metodo getter che ritorna un booleano che iondica se il giocatore sta eseguendo il turno attuale
	 * @return  boolean turnoInCorso
	 */
	public boolean isTurnoInCorso() {
		return turnoInCorso;
	}


	/**
	 * Metodo setter che assegna un booleano che indica se il giocatore sta eseguendo il turno attuale
	 * @param boolean turnoInCorso 
	 */
	public void setTurnoInCorso(boolean turnoInCorso) {
		this.turnoInCorso = turnoInCorso;
	}

}
