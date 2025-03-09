package controller;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ResourceLoader {

    public static BufferedImage loadImage(String path) {
        try {
            InputStream stream = ResourceLoader.class.getClassLoader().getResourceAsStream(path);
            if (stream == null) {
                System.err.println("Immagine non trovata: " + path);
                return null;
            }
            return ImageIO.read(stream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Clip loadSound(String path) {
        try {
            InputStream stream = ResourceLoader.class.getClassLoader().getResourceAsStream(path);
            if (stream == null) {
                System.err.println("File audio non trovato: " + path);
                return null;
            }
            BufferedInputStream bufferedIn = new BufferedInputStream(stream);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedIn);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            return clip;
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
            return null;
        }
    }
}
