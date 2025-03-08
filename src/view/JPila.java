package view;

import javax.imageio.ImageIO;
import javax.swing.*;

import model.PilaScarti;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 * Classe che rappresenta la GUI di una pila degli scarti
 * @author michele marchetti
 *
 */
@SuppressWarnings("deprecation")
public class JPila extends JPanel implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3139520284755195353L;
	/**
	 * Varibile che contiene il riferimento alla pila degli scarti
	 */
	private PilaScarti  pilaScarti;
	
	
	/**
	 * Metodo costruttore
	 * @param pilaScarti
	 */
	public JPila(PilaScarti pilaScarti) {
		this.pilaScarti = pilaScarti;
		this.setPreferredSize(new Dimension(100,120));
		
	}
	
	/**
	 * Metodo che serve per disegnare il componente
	 */
	@Override
	public void paintComponent(Graphics g) {

//		Image image = pilaScarti.guardaCima().getImage();
		Image image= null;
		try {
			image = ImageIO.read(new File("res/"+pilaScarti.guardaCima().getValore()+"_"+pilaScarti.guardaCima().getColore()+"_ZOOM.png"));
		} catch (IOException e) {
			System.out.println(pilaScarti.guardaCima().toString());
			e.printStackTrace();
		}
		
		Graphics2D g2d = (Graphics2D )g;
		AffineTransform affineTransform = new AffineTransform();
		
		g2d.drawImage(image, affineTransform,this);
	}

	/**
	 * Metodo che serve per aggiornale l'istanza
	 */
	@Override
	public void update(Observable o, Object arg) {
		this.repaint();
		
	}
	
	
}
