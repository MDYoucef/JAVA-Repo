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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import weka.core.Instances;

public class Gui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JTextArea jta = new JTextArea("");
	JTextArea corpus_path = new JTextArea("");
	JTextArea delimiter_path = new JTextArea("");
	JTextArea sw_path = new JTextArea("");

	JLabel corpus_comm = new JLabel("choisir un corpus");
	JLabel delim_comm = new JLabel("choisir le fichier des delimiters");
	JLabel sw_comm = new JLabel("choisir le fichier des stop words");

	JLabel init = new JLabel();
	JLabel q = new JLabel();

	JButton b1 = new JButton("ok");
	JButton par_corpus = new JButton("parcourir");
	JButton par_delim = new JButton("parcourir");
	JButton par_sw = new JButton("parcourir");
	JButton confirmer = new JButton("confirmer");

	BackG bg = new BackG();
	Color c = new Color(255, 255, 255);
	Font police = new Font("Times New Roman", Font.PLAIN, 12);
	Font lab_police = new Font("Times New Roman", Font.PLAIN, 10);
	Border border = BorderFactory.createLineBorder(Color.gray);

	String cpath;
	String dpath;
	String spath;

	public Gui() {

		this.setTitle("mon moteur de rechereche");
		this.setSize(1040, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		mainPan();
		this.setContentPane(bg);
	}

	void mainPan() {

		bg.setLayout(null);
		initpan0();
		initpan1();
		bg.add(init);
		bg.add(q);
	}

	void initpan1() {

		// bg.setLayout(null);//
		q.setLayout(null);
		q.setBackground(Color.red);
		q.setBounds(400, 0, 640, 500);
		q.setBorder(border);
		jta.setBounds(40, 200, 560, 30);
		jta.setBackground(c);
		jta.setBorder(border);
		jta.setFont(police);
		b1.setBounds(290, 250, 60, 30);
		q.add(jta);
		q.add(b1);
		b1.addActionListener(new B1Action());
	}

	void initpan0() {

		init.setLayout(null);
		init.setBackground(Color.white);
		init.setBounds(0, 0, 400, 500);
		init.setBorder(border);
		
		corpus_comm.setFont(lab_police);
		corpus_comm.setBounds(10, 30, 380, 10);
		corpus_path.setBounds(10, 50, 380, 30);
		
		par_corpus.setBounds(155, 100, 90, 30);
		corpus_path.setBorder(border);
		init.add(corpus_comm);
		init.add(corpus_path);
		init.add(par_corpus);
		
		delim_comm.setFont(lab_police);
		delim_comm.setBounds(10, 140, 380, 10);
		delimiter_path.setBounds(10, 160, 380, 30);
		par_delim.setBounds(155, 210, 90, 30);
		delimiter_path.setBorder(border);
		init.add(delim_comm);
		init.add(delimiter_path);
		init.add(par_delim);
		
		sw_comm.setFont(lab_police);
		sw_comm.setBounds(10, 250, 380, 10);
		sw_path.setBounds(10, 270, 380, 30);
		par_sw.setBounds(155, 320, 90, 30);
		sw_path.setBorder(border);
		init.add(sw_comm);
		init.add(sw_path);
		init.add(par_sw);
		
		confirmer.setBounds(155, 400, 90, 30);
		init.add(confirmer);
		
		par_corpus.addActionListener(new Folder_choose());
		par_delim.addActionListener(new File_choose());
		par_sw.addActionListener(new File_choose1());
		confirmer.addActionListener(new Confirmer());
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
				mabase = run.chargement("/home/skyolia/Documents/tp/test.arff");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				mareq = run.moteur("/home/skyolia/Documents/tp/requette/",dpath,spath, "/home/skyolia/Documents/tp/requette.arff");
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			try {
				ps = run.PS(mabase, mareq);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public class Folder_choose implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File("/home/skyolia"));
			chooser.setDialogTitle("parcourir");
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			// chooser.setAcceptAllFileFilterUsed(false);

			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				cpath = chooser.getSelectedFile().getAbsolutePath();
				System.out.println("cpath " + cpath);
			} else {
				System.out.println("No Selection ");
			}
			corpus_path.setText(cpath);

		}

	}

	public class File_choose implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser dialogue = new JFileChooser();

			// affichage
			dialogue.showOpenDialog(null);

			// récupération du fichier sélectionné
			dpath = dialogue.getSelectedFile().getAbsolutePath();
			System.out.println("qpath : " + dpath);
			delimiter_path.setText(dpath);
		}

	}

	public class File_choose1 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser dialogue = new JFileChooser();

			// affichage
			dialogue.showOpenDialog(null);

			// récupération du fichier sélectionné
			spath = dialogue.getSelectedFile().getAbsolutePath();
			System.out.println("qpath : " + spath);
			sw_path.setText(spath);

		}

	}
	
	public class Confirmer implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Run base=new Run();
			try {
				base.moteur(cpath,dpath,spath,"/home/skyolia/Documents/tp/test.arff");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}
}
