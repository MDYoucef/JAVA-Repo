package ri_2;

import javax.swing.UIManager;

public class Mdr {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		try {

			UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
			
			Gui g = new Gui();
			//Run base=new Run();
			//base.moteur("/home/skyolia/Documents/tp/corpus","/home/skyolia/Documents/tp/test.arff");
			g.setVisible(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
