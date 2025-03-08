package view;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

import model.CampoDiGioco;
import model.ColoreCartaSpeciale;
import model.MazzoCarte;

/**
 * Classe che rappresenta l'interfacia grafica di un mazzo di carte
 * @author michelemarchetti
 *
 */
public class JMazzo extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5714356135112311146L;

	/**
	 * Varibile che contiene il riferimento del modello
	 */
	private CampoDiGioco campoDiGioco;
	
	/**
	 * Varibile che contiene il riferimento al mazo di carte
	 */
	private MazzoCarte mazzoCarte;
	/**
	 * Campo statico che contiene il riferimento al percorso del immagine
	 */
	private static final String PERCORSO_IMMAGINE = "res/DORSO_MAZZO_ZOOM1.png";
	
	/**
	 * Varibile che contiene il riferimento dell immagine
	 */
	private Image image;
	
	/**
	 * Metodo costrutore 
	 * @param mazzoCarte MazzoCarte
	 */
	public JMazzo(MazzoCarte mazzoCarte) {
		this.campoDiGioco = CampoDiGioco.instance();
		this.mazzoCarte = (campoDiGioco.getMazzoCarte());
		this.setPreferredSize(new Dimension(100, 165));
		this.setOpaque(false);
		try {
			image = ImageIO.read(new File(PERCORSO_IMMAGINE));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo che serve per disegnare l'immagine del componente
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D )g;
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.translate(0, 20);
		
		g2d.drawImage(image, affineTransform,this); 
		g2d.drawString("Carte: "+String.valueOf(campoDiGioco.getMazzoCarte().getMazzo().size()), 15, 15);
		
		if (campoDiGioco.getPilaScarti().guardaCima().getColore().equals(ColoreCartaSpeciale.DORSO))
		{
			g2d.setFont(new Font(Font.SERIF, Font.BOLD, 12));
			g2d.drawString("Clicca per iniziare", 0, 158);
		}
		
	}

	/**
	 * Metodo getter che ritorna il mazzo dei carte
	 * @return MazzoCarte
	 */
	public MazzoCarte getMazzoCarte() {
		return mazzoCarte;
	}

}
