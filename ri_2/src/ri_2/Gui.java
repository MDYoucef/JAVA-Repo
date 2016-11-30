package ri_2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import weka.core.Instances;

public class Gui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextArea jta = new JTextArea("");
	JLabel label = new JLabel();
	JButton b1 = new JButton("ok");
	BackG bg = new BackG();
	Color c = new Color(255, 255, 255);
	Font police = new Font("Times New Roman", Font.PLAIN, 12);
	Border border = BorderFactory.createLineBorder(Color.gray);

	public Gui() {

		this.setTitle("mon moteur de rechereche");
		this.setSize(640, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		mainPan();
		this.setContentPane(bg);
	}

	void mainPan() {

		bg.setLayout(null);
		jta.setBounds(40, 200, 560, 30);
		jta.setBackground(c);
		jta.setBorder(border);
		jta.setFont(police);
		b1.setBounds(290, 250, 60, 30);
		bg.add(jta);
		bg.add(b1);
		b1.addActionListener(new B1Action());
	}

	public class B1Action implements ActionListener {

		String chaine;
		Run run = new Run();
		Instances mabase;
		Instances mareq;
		ArrayList<Instance_Ps> ps = new ArrayList<Instance_Ps>();
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			chaine = jta.getText();
			System.out.println("la requette : " + chaine);

			try (PrintWriter out = new PrintWriter("/home/skyolia/Documents/tp/requette/requette/requette.txt")) {
				out.println(chaine);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			try {
				mabase=run.chargement("/home/skyolia/Documents/tp/test.arff");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				mareq=run.moteur("/home/skyolia/Documents/tp/requette/","/home/skyolia/Documents/tp/requette.arff");
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			try {
				ps=run.PS(mabase, mareq);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
