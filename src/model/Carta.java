package model;

import java.awt.Image;
import java.io.File;
import java.util.Objects;

import javax.imageio.ImageIO;


/**
 * Classe astratta che rappresenta una carta da gioco
 * @author michelemarchetti
 *
 */
public class Carta implements Comparable<Carta> {
	
	/**
	 * Variabile che contiene l'immagine della carta
	 */
	private Image image;
	/**
	 * Varabile che raprpesenta il valroe della carta
	 */
	private ValoreCarta valore;
	/** 
	 * Variabile che rappresenta il colore della carta
	 */
	private ColoreCarta colore;
	/*
	 * Variaible che indica se l'effetto della carta e' stato utilizzato
	 */
	private boolean effettoUtilizzato;

	
	
	/**
	 * Metodo costruttore
	 * @param valore
	 * @param colore
	 */
	public Carta(ValoreCarta valore, ColoreCarta colore) {
		this.valore = valore;
		this.colore = colore;
		assegnaImmagine();
	}
	
	/**
	 * Metodo privato di utilita'  per associare l'immagine alla carta in base 
	 * al valore e colore
	 * 
	 */
	private void assegnaImmagine() {
		try {
			
			String percorso = "res/"+valore.getName()+"_"+colore.getName()+".png";
			Image image = ImageIO.read(new File(percorso));
			this.image = image;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Metodo getter che restituisce l'immagine associata alls carta
	 * @return l'immagine della carta
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Metodo getter che restiuisce il valore della carta
	 * @return valore della carta
	 */
	public ValoreCarta getValore() {
		return valore;
	}

	/**
	 * Metodo getter che restiuisce il colore della carta
	 * @return colore della carta
	 */
	public ColoreCarta getColore() {
		return colore;
	}
	
	/**
	 * Metodo che ritronta una rappresentazione dell oggetto sotto foram di stringa
	 */
	@Override
	public String toString() {
		return "Valore: "+valore.getName()+" Colore: "+colore.getName() /* +" Image: "+ image.toString() */ ;
	}

	/**
	 * Metodo che serve per fare una comparazione fra le carte e dargli un 
	 * ordinamento
	 */
	@Override
	public int compareTo(Carta o) {
		if (o.getValore().getValue() < valore.getValue())
		{
			return 1;
		}
		else if (o.getValore().getValue() == valore.getValue())
		{
			return (o.getValore().getName()).compareTo(valore.getName());
		}
		return -1;
	}

	/**
	 * Metodo che genera un hascode
	 */
	@Override
	public int hashCode() {
		return Objects.hash(colore, image, valore);
	}

	
	/**
	 * Metodo che serve per confrontare due Carte e verificare se sono uguali
	 */
	@Override
	public boolean equals(Object o) {
		
		if (o == null) 
			{ 
				return false;
			}
		if (this == o) 
			{
				return true;
			}
		if (getClass() != o.getClass())
			{ 
				return false;
			}
		Carta obj = (Carta) o;
		if (this.valore.equals(obj.valore) && this.colore.equals(obj.colore) && this.image.equals(obj.image))
			{
				return true;
			}
		return false;
	}

	/**
	 * Metodo getter che ritorna il booleano che indica se la carta e' stat utilizzata
	 * @return the effettoUtilizzato
	 */
	public boolean isEffettoUtilizzato() {
		return effettoUtilizzato;
	}

	/**
	 * Metodo setter che imposta il valore booleano della carta
	 * @param effettoUtilizzato the effettoUtilizzato to set
	 */
	public void setEffettoUtilizzato(boolean effettoUtilizzato) {
		this.effettoUtilizzato = effettoUtilizzato;
	}
	
	
	
	
}
