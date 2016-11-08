package tps1ere;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.UIManager;

public class Crea {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
            // select Look and Feel
            UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
		Pblm tsp=new Pblm();
		tsp.setVisible(true);
		}
		catch (Exception ex) {
            ex.printStackTrace();
        }

	}

}
