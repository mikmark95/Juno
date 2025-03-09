package controller;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Classe che serve per riprodurre un audio
 * @author michele marchetti
 */
public class AudioManager {

	/**
	 * Variabile statica che contiene l'unica istanza possibile della classe
	 */
	private static AudioManager instance;

	/**
	 * Metodo che restituisce l'istanza della classe
	 * @return l'istanza di AudioManager
	 */
	public static AudioManager getInstance() {
		if (instance == null)
			instance = new AudioManager();
		return instance;
	}

	/**
	 * Metodo costruttore privato
	 */
	private AudioManager() {
	}

	/**
	 * Metodo che riproduce in loop il file audio relativo al percorso passato come argomento
	 * @param filename il percorso del file audio
	 */
	public void playInLoop(String filename) {
		try {
			InputStream in = getClass().getClassLoader().getResourceAsStream(filename);
			if (in == null) {
				System.err.println("File audio non trovato: " + filename);
				return;
			}

			BufferedInputStream bufferedIn = new BufferedInputStream(in);
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedIn);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo che riproduce un file audio una sola volta
	 * @param filename il percorso del file audio
	 */
	public void play(String filename) {
		try {
			InputStream in = getClass().getClassLoader().getResourceAsStream(filename);
			if (in == null) {
				System.err.println("File audio non trovato: " + filename);
				return;
			}

			BufferedInputStream bufferedIn = new BufferedInputStream(in);
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedIn);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
		} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}
}
