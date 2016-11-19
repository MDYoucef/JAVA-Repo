package tps1ere;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.UIManager;

public class Crea {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme("Default");
            UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		Pblm tsp=new Pblm();
		tsp.setVisible(true);
		}
		catch (Exception ex) {
            ex.printStackTrace();
        }

	}

}
