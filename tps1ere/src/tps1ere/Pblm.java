package tps1ere;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Pblm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel container = new JPanel();
	Bg essai = new Bg();

	Font police = new Font("Times New Roman", Font.BOLD, 12);
	JLabel bg = new JLabel();

	JTextField jtf = new JTextField("");
	JLabel label = new JLabel("tapez le nombre de ville à visiter");
	JButton b1 = new JButton("ok");
	JLabel top = new JLabel();

	JLabel label2 = new JLabel("tapez les villes");
	JTextArea lesvilles = new JTextArea(15, 10);
	JButton b2 = new JButton("ok");
	JLabel top2 = new JLabel();
	JScrollPane sp = new JScrollPane(lesvilles, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

	JLabel label3 = new JLabel("tapez les differents couts");
	JButton b3 = new JButton("find");
	JLabel top3 = new JLabel();

	JTable row;
	JTable column;
	JTable couts;
	// JScrollPane sp3 = new JScrollPane(essai,
	// JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
	// JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	int n;

	ArrayList<StringBuffer> v = new ArrayList<StringBuffer>();
	StringBuffer p;

	public Pblm() {

		this.setTitle("TSP Solver");
		this.setSize(400, 768);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		initfen();

		this.setContentPane(essai);
	}

	void mainPan() {

		essai.setLayout(null);
		// sp3.setBounds(0, 0, 1500, 1500);
		// sp3.setViewportView(essai);
		essai.add(top);
		essai.add(top2);
		essai.add(top3);
	}

	void initPan1() {

		top.setLayout(null);
		jtf.setFont(police);
		jtf.setForeground(Color.white);
		top.setBounds(353, 10, 300, 200);
		jtf.setBounds(90, 50, 120, 30);
		label.setFont(police);
		label.setBounds(30, 15, 240, 20);
		b1.setBounds(120, 100, 60, 30);
		top.add(jtf);
		top.add(label);
		top.add(b1);
		b1.addActionListener(new B1Action());

	}

	void initPan2() {

		top2.setLayout(null);

		top2.setBounds(713, 10, 300, 200);
		label2.setBounds(80, 15, 140, 20);
		label2.setFont(police);
		b2.setBounds(120, 160, 60, 30);
		lesvilles.setFont(police);
		lesvilles.setForeground(Color.white);
		lesvilles.setBounds(90, 50, 120, 30);
		sp.setBounds(90, 50, 120, 90);
		sp.setViewportView(lesvilles);
		top2.add(sp);
		top2.add(label2);
		top2.add(b2);
		b2.addActionListener(new B2Action());

	}

	void initfen() {

		mainPan();
		initPan1();
		initPan2();
		// initPan3();
	}

	void initPan3(ArrayList<StringBuffer> v) {

		// top3.setBackground(Color.red);
		top3.setLayout(null);
		int longueur = v.size();

		row = new JTable(1, longueur);
		row.setBackground(Color.gray);

		for (int i = 0; i < row.getColumnCount(); i++) {
			row.setValueAt(v.get(i), 0, i);

		}

		column = new JTable(longueur, 1);
		column.setBackground(Color.gray);
		for (int i = 0; i < column.getRowCount(); i++) {
			column.setValueAt(v.get(i), i, 0);

		}

		row.setRowHeight(20);
		column.setRowHeight(20);
		couts = new JTable(longueur, longueur);
		couts.setRowHeight(20);
		top3.setBounds(683 - 37 * (longueur + 1), 240, (longueur + 1) * 75, longueur * 20 + 130);
		label3.setBounds(50, 15, 200, 20);
		label.setFont(police);
		top3.add(label3);
		row.setBounds(75, 60, longueur * 75, 20);
		column.setBounds(0, 80, 75, longueur * 20);
		couts.setBounds(75, 80, longueur * 75, longueur * 20);
		b3.setBounds((longueur + 1) * 75 / 2 - 40, longueur * 20 + 100, 80, 30);
		row.setEnabled(false);
		column.setEnabled(false);
		row.setForeground(Color.white);
		column.setForeground(Color.white);
		couts.setForeground(Color.white);
		top3.add(row);
		top3.add(column);
		top3.add(couts);

		top3.add(b3);
		b3.addActionListener(new B3Action());

	}

	class B1Action implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			n = Integer.parseInt(jtf.getText());
			System.out.println("taille:" + n);

		}

	}

	class B2Action implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			StringBuffer mot = new StringBuffer();
			p = new StringBuffer((lesvilles.getText()).toString());
			System.out.println(p.length());
			// System.out.println(p.charAt(0));

			for (int i = 0; i < p.length(); i++) {

				if (p.charAt(i) == '\n') {

					v.add(mot);
					mot = new StringBuffer();

				} else {

					mot.append(p.charAt(i));

					if (i == p.length() - 1) {

						v.add(mot);
					}

				}

			}

			for (int i = 0; i < v.size(); i++) {

				if (i >= n) {
					v.remove(i);
					i--;
				}
			}

			initPan3(v);

		}

	}

	class B3Action implements ActionListener {

		public ArrayList<ArrayList<Double>> matrice = new ArrayList<ArrayList<Double>>();

		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			System.out.println(v);
			System.out.println(v.size());

			if (couts.isEditing()) {
				couts.getCellEditor().stopCellEditing();

				for (int i = 0; i < v.size(); i++) {

					ArrayList<Double> cost = new ArrayList<Double>();

					for (int j = 0; j < v.size(); j++) {

						if (couts.getValueAt(i, j) != null)
							cost.add(j, Double.parseDouble(couts.getValueAt(i, j).toString()));
						else {
							double d = Double.NaN;
							cost.add(j, d);
						}

					}
					matrice.add(i, cost);

				}

				System.out.println(matrice);
			}

			ArrayList<String> ref = new ArrayList<String>();
			for (int i = 0; i < v.size(); i++) {

				ref.add(i, v.get(i).toString());
			}

			BranchAndBound bb = new BranchAndBound(matrice, ref);
			System.out.println("id =" + bb.root.getId());
			System.out.println("enfant =" + bb.root.nbE);
			
			System.out.println("lb root =" + bb.root.lb);
			//ref.remove(0);
			/*
			 * children=bb.root.addChild(bb.root.nbE,ref); for(int
			 * i=0;i<children.size();i++){
			 * 
			 * System.out.println(children.get(i).getId()); }
			 */
			bb.gen(ref);
		/*	ref=bb.nommage("a_d_b",ref);
			System.out.println("ici  "+ref);*/

		}

	}
}
