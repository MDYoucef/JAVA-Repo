package chap16;

import java.text.NumberFormat;

public class Hanoi {

	static void hanoi(int n, String depart, String inter, String arrivee) {
		int i = 0;
		
		
		if (n == 1) {
			System.out.println("ici n= " + n);
			System.out.println("depart= " + depart);
			System.out.println("inter= " + inter);
			System.out.println("arrivee= " + arrivee);
			System.out.println("Disk 1 from " + depart + " to " + arrivee);

		} else {
			System.out.println("ici n= " + n);
			System.out.println("depart= " + depart);
			System.out.println("inter= " + inter);
			System.out.println("arrivee= " + arrivee);
			hanoi(n - 1, depart, arrivee, inter);
			System.out.println("coucou");
			System.out.println("depart= " + depart);
			System.out.println("inter= " + inter);
			System.out.println("arrivee= " + arrivee);
			System.out.println("45 disk " + n + " from " + depart + " to " + arrivee);

			hanoi(n - 1, inter, depart, arrivee);

		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Runtime runtime = Runtime.getRuntime(); 
		System.out.println("max memory: " + runtime.maxMemory() / 1024);
		System.out.println("total memory: " + runtime.totalMemory() / 1024);
		System.out.println("free memory: " + runtime.freeMemory() / 1024);

		String depart = "a", inter = "b", arrivee = "c";
		hanoi(3, depart, inter, arrivee);

	}

}
