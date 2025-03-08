  package model;

import java.util.Optional;
import java.util.Random;

/**
 * Classe che modella una Strategia di gioco semplice, che osserva la pila degli scarti
 * e da la priorita' alle carte dello stesso colore
 * @author michelemarchetti
 *
 */
public class StrategiaGiocoSemplice  implements StrategiaGioco{

	/**
	 * Variabile che contiene il riferimento al giocatore al 
	 * quale applicare la strategia
	 */
	private ManoCarte manoCarte;
	
	/**
	 * Metodo costruttore
	 * @param giocatoreAvversario riferimento al giocatoree al quale applicare la strategia
	 */
	public StrategiaGiocoSemplice(ManoCarte manoCarte) {
		this.manoCarte = manoCarte;
	}
	
	/**
	 * Metodo che fa eseguire una mossa al giocatore
	 * @throws GridaUnoExceprtion 
	 * @throws ManoVuotaException 
	 */
	@Override
	public void faiMossa() throws GridaUnoExceprtion, ManoVuotaException {
		
		Carta cartaDaGiocare = null;
		PilaScarti scarti =  manoCarte.getPilaScarti();
		Carta cartaInCampo = scarti.guardaCima();
		
		cartaDaGiocare = cercaPerColore(manoCarte, scarti, cartaInCampo);
		
		if (cartaDaGiocare!=null) //se trovo la carta per colore
			{
				manoCarte.scartaCarta(cartaDaGiocare);
				CampoDiGioco.instance().setColoreCampo(cartaDaGiocare.getColore());
			}
		else if ( (cartaDaGiocare=cercaPerValore(manoCarte, scarti, cartaInCampo) ) != null) //altrimetni cerco la carta per valore
			{
				cartaDaGiocare = cercaPerValore(manoCarte, scarti, cartaInCampo);
				
				if (cartaDaGiocare.getValore().equals(ValoreCartaSpeciale.JOLLY) )  //se viene scelta una carta jolly NERA scgli un colore casuale
				{
					manoCarte.scartaCarta(cartaDaGiocare);
					CampoDiGioco.instance().setColoreCampo(cercaColoreCasuale(manoCarte));	
				}
				else if (cartaDaGiocare.getValore().equals(ValoreCartaSpeciale.JOLLY_PESCA_4) ) //se viene scelta una carta pesca 4 NERA, verifico se posso giorcarla e scgli un colore casuale
				{
					if (cercaPerColore(manoCarte, scarti, cartaInCampo)==null)
					{
						manoCarte.scartaCarta(cartaDaGiocare);
						CampoDiGioco.instance().setColoreCampo(cercaColoreCasuale(manoCarte));	
					}
				}
				else //altrimenti imposta il colore della carta
				{					
					manoCarte.scartaCarta(cartaDaGiocare);
					CampoDiGioco.instance().setColoreCampo(cartaDaGiocare.getColore());
				}
				
			}
		
		else if ( (cartaDaGiocare=cercaJolly(manoCarte, scarti, cartaInCampo)) != null) //altrimenti cerco una carta jolly
			{
			CampoDiGioco.instance().setColoreCampo(cercaColoreCasuale(manoCarte));
			manoCarte.scartaCarta(cartaDaGiocare);
			}
		
		else if ( scarti.guardaCima().getColore().equals(ColoreCartaSpeciale.DORSO)) //altrimenti se il campo e' vuoto
			{
				cartaDaGiocare = cercaCartaCasuale(manoCarte);
				manoCarte.scartaCarta(cartaDaGiocare);
				if ( cartaDaGiocare.getColore().equals(ColoreCartaSpeciale.NERO))
				{
					CampoDiGioco.instance().setColoreCampo(cercaColoreCasuale(manoCarte));
				}
				else
				{
					CampoDiGioco.instance().setColoreCampo(cartaDaGiocare.getColore());
				}
			}
		
		
		else //altrimenti pesca
			{
				try {
					Carta c = manoCarte.getMazzoCarte().pesca();
					if (c.getColore().equals(CampoDiGioco.instance().getColoreCampo()) ) //se la carta che pesco e' del colore del campo la gioco
					{
						manoCarte.scartaCarta(c);
						CampoDiGioco.instance().setColoreCampo(c.getColore());
					}
					else
					{						
						manoCarte.getMano().add(c);
					}
				} catch (MazzoEsauritoException e) {
					manoCarte.getPilaScarti().rigeneraMazzo(manoCarte.getMazzoCarte());
				}
			}
		
		
	}

	/**
	 * Metodo privato che controlla la cima della pila degli scarti e seleziona
	 * dalla mano una carta dello stesso colore
	 * @param manoCarte
	 * @param scarti
	 * @param cartaInCampo
	 * @return
	 */
	private Carta cercaPerColore(ManoCarte manoCarte, PilaScarti scarti, Carta cartaInCampo) {
		Optional<Carta> cartaDaTrovare = manoCarte.getMano().stream()
				.filter(x-> x.getColore().equals(CampoDiGioco.instance().getColoreCampo()))
				.findAny();
		return cartaDaTrovare.orElse(null);
	}
	
	/**
	 * Metodo privato che controlla la cima della pila degli scarti e seleziona
	 * dalla mano una carta dello stesso valore
	 * @param manoCarte
	 * @param scarti
	 * @param cartaInCampo
	 * @return
	 */
	private Carta cercaPerValore(ManoCarte manoCarte, PilaScarti scarti, Carta cartaInCampo) {
		Optional<Carta> cartaDaTrovare = manoCarte.getMano().stream()
				.filter(x-> x.getValore().equals(cartaInCampo.getValore()))
				.findAny();
		return cartaDaTrovare.orElse(null);
	
	}
	
	/**
	 * Metodo che controlla la mano in cerca di una carta jolly e se trovata la ritorna
	 * @param manoCarte
	 * @param scarti
	 * @param cartaInCampo
	 * @return
	 */
	private Carta cercaJolly(ManoCarte manoCarte, PilaScarti scarti, Carta cartaInCampo) {
		Optional<Carta> cartaDaTrovare = manoCarte.getMano().stream()
				.filter(x-> x.getColore().equals(ColoreCartaSpeciale.NERO))
				.findAny();
		return cartaDaTrovare.orElse(null);
	}
	
	/**
	 * Metodo privato che restituisce un colore casuale fra le carte che il giocatore ha in mano
	 * @param manoCarte
	 * @return Colore carta
	 */
	private ColoreCarta cercaColoreCasuale(ManoCarte manoCarte) {
	
			Optional<Carta> c = manoCarte.getMano().stream()
				.findAny();
			ColoreCarta colore = c.orElse(null).getColore();
			if (colore.equals(ColoreCartaSpeciale.NERO))
			{
				Random random = new Random();
				int intRandom = random.nextInt(0, 4);
				switch (intRandom) {
				case 0:
					colore = ColoreCartaNormale.BLU;
					return colore;
				case 1:
					colore = ColoreCartaNormale.GIALLO;
					return colore;
				case 2:
					colore = ColoreCartaNormale.ROSSO;
					return colore;
				case 3:
					colore = ColoreCartaNormale.VERDE;
					return colore;
				default:
					break;
				}
			}
			return colore;
	}
		
		
	
	/**
	 * Metodo privato che restituisce una carta casuale fra le carte che il giocatore ha in mano
	 * @param manoCarte
	 * @return Carta carta
	 */
	private Carta cercaCartaCasuale(ManoCarte manoCarte) {
		Optional<Carta> c = manoCarte.getMano().stream()
				.findAny();
		return  c.orElse(null);
	}
	

	

}
