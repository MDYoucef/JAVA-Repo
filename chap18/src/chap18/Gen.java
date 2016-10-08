package chap18;

import java.util.*;

public class Gen {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner lire = new Scanner(System.in);

		int n, m;

		MatriceListe m2 = null;

		do {
			try {

				
				 System.out.println("nombre de ligne et de colonne:"); 
				 n =lire.nextInt(); 
				 m = lire.nextInt(); 
				 m2 = new MatriceListe(n,m); 
				 m2.affichage(m2.creation(m2));
				 
				 

				/*m2 = new MatriceListe(1, 1);
				n = lire.nextInt();
				m = lire.nextInt();
				m2 = m2.BuildMagique(n, m);
				m2.affichage(m2);
				System.out.println("_______________________________________");*/
				
				
				if (m2.isCarre(m2)) {
					//m2.affichage(m2);
				System.out.println("le determinant = " + m2.determinant(m2, 0));
				System.out.println(m2.magique(m2));
				System.out.println('\t');
				m2=m2.inverse(m2);
				m2.affichage(m2);
				System.out.println("le determinant = " + m2.determinant(m2, 0));
				System.out.println(m2.magique(m2));
				}
				

				

			} catch (Exception e) {
				m2 = null;
				e.printStackTrace();
				

			}
		} while (m2 == null);

	}

}
