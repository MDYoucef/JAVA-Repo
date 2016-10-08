package chap14;

import java.io.IOException;
import java.util.Scanner;

public class Test2 {

	public static void main(String[] args) throws RienExcep, IOException {
		// TODO Auto-generated method stub

		int n;
		char c;
		int i, t = 0, p;
		Menu2 m = new Menu2();
		Scanner lire = new Scanner(System.in);
		Scanner lirec = new Scanner(System.in);

		System.out.println("nombre de question: ");
		int l = lire.nextInt();
		String[][] mega = new String[l][];

		Menu2[] a = new Menu2[l];
		for (i = 0; i < l; i++) {
			a[i] = new Menu2();
			System.out.println("entrer la taille du tableau " + (i + 1));
			n = lire.nextInt();
			System.out.println("entrer les elements du tableau " + (i + 1));
			mega[i] = a[i].miniMenu(n);

		}
		System.out.println("voici le questionnaire : ");
		for (i = 0; i < l; i++) {
			a[i].affichahe(mega[i]);
		}
		i = 0;
		
		do {

			try {

				for (int j = 0; j < mega[i].length; j++) {
					System.out.println("tapez "+(j+1)+" pour "+mega[i][j]);
				}
				
				String chaine = lirec.nextLine();
				System.out.println("votre choix : ");
				c = chaine.charAt(0);
				p = m.convertion(c);
				if (m.horsType(mega[i], p)) {
					System.out.println("vous avez choisis : " + mega[i][p - 1]);
				}

				i++;
				t++;
			} catch (Exception e1) {
				t = 0;

			}
		} while (i < l && t < l);

	}

}
