package view;

import java.awt.*;
import javax.swing.*;

import model.ColoreCartaNormale;

/**
 * Classe che rappresenta un finestra che consente di scegliere il co9lore del campo
 * @author michelemarchetti
 *
 */
public class SceltaColore extends JDialog {
	
	  

	/**
	 * 
	 */
	private static final long serialVersionUID = -8019070009106960473L;

	/**
	 * Metodo costruttore
	 */
	public SceltaColore() {
		this.setTitle("SCEGLI IL COLORE!!!");
		this.setPreferredSize(new Dimension(400, 400));
		this.setLayout(new GridLayout(2, 2));
		
		
		
		
		BottoneColore btnRosso = new BottoneColore(ColoreCartaNormale.ROSSO);
		this.add(btnRosso);
		BottoneColore btnGiallo = new BottoneColore(ColoreCartaNormale.GIALLO);
		this.add(btnGiallo);
		BottoneColore btnBlu = new BottoneColore(ColoreCartaNormale.BLU);
		this.add(btnBlu);
		BottoneColore btnVerde= new BottoneColore(ColoreCartaNormale.VERDE);
		this.add(btnVerde);
		
		
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); //per evitare che si esca senza scegliere il colore
		this.setResizable(false);
		this.setAlwaysOnTop(true);
	}




	
}
