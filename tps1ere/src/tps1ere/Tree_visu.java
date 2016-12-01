package tps1ere;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Tree_visu extends JFrame{
	
	JLabel pan = new JLabel();
	JTextArea print = new JTextArea();
	JScrollPane sp = new JScrollPane(print, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	Color c = new Color(255, 255, 255);
	Font police = new Font("Times New Roman", Font.BOLD, 15);
	public Tree_visu() {

		this.setTitle("TSP Solver");
		this.setSize(getMaximumSize());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		initpan1();
		this.setContentPane(sp);
	}
	
	void initpan1() {

		pan.setLayout(null);
		
		pan.setSize(getMaximumSize());
		print.setBackground(c);
		print.setForeground(Color.BLACK);
		print.setFont(police);
		print.setBounds(0, 0, pan.getWidth(), pan.getHeight());
		sp.setBounds(90, 50, 120, 90);
		sp.setViewportView(print);
		pan.add(sp);
		

	}

}
