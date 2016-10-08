package geo;

import java.math.*;
import java.util.Scanner;

public class Geometrie {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Scanner lire=new Scanner(System.in);
		double d=0;
		System.out.print("le nombre de sommet= ");
		Integer n=lire.nextInt();
		//Double rayon=lire.nextDouble();
		
		Point []sommet=new Point[n];
		
		
		
		for(int i=0;i<n;i++) {
			 
			System.out.println("entrer les coord des points ");
			BigDecimal ab=lire.nextBigDecimal();
			BigDecimal or=lire.nextBigDecimal();
			sommet[i]=new Point(ab,or);
			 
			}
		Polygone forme=new Polygone(sommet,n);
		forme.peri();
		forme.affiche();
		
		
		System.out.println("fin ");
		
		//System.out.println("le perimetre de cette forme= "+d);
		

	}

}
