package tosses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import chap16.Permutation;

public class PlayDieTosses {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner lire = new Scanner(System.in);

		System.out.println("nombre de jeu= ");
		int m = lire.nextInt();
		System.out.println("limite = ");
		int max = lire.nextInt();

		DieTosses dt = new DieTosses();

		StringBuffer a;
		ArrayList al = dt.play(m, max);

		a = dt.run(al);
		System.out.println(a);
		a=dt.nettoyage(a);
		System.out.println(a);
		System.out.println(a.length());

		a = dt.longuestRun(a);
		System.out.println(a);
		System.out.println(a.length());
		
		

	}

}
