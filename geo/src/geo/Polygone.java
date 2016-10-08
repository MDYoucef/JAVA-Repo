package geo;

import java.math.*;
import java.util.Scanner;

public class Polygone extends Figure {
	
	Point[]sommet=new Point[100];
	int nbs;

	public Polygone() {
		// TODO Auto-generated constructor stub
		nbs=0;
	}
	
	public Polygone(Point[]m,int n) {
		
		//super(m[0]);
		nbs=n;
		
		for(int i=0;i<n;i++) {
			sommet[i]=m[i];
		}
	}
	
	/*public double peri() {
		
		double p=0;
		//p1=Point.distance(sommet[0], sommet[1]);
		
		for(int i=0;i<nbs;i++) {
			
		
			
			p=p+Point.distance(sommet[i%nbs], sommet[(i+1)%nbs]);
			
			
		}
		return p;
			
		
			}*/
	
	public void peri() {
		
		double[]tabDeDistance=new double[100];
		
		BigDecimal[] bigTabDistance = new BigDecimal[tabDeDistance.length];
		
		int p=0; double sommeD=0; 
		BigDecimal pe=new BigDecimal(0);
		BigDecimal bigSommeD=new BigDecimal(0);
		for(int j=0;j<nbs;j++) {
			
			bigTabDistance[j%nbs]=Point.distance(sommet[j%nbs], sommet[(j+1)%nbs]);
			bigSommeD=bigSommeD.add(bigTabDistance[j%nbs]);
			
			//BigDecimal bigSommeD=new BigDecimal(sommeD);
			System.out.println("la distance "+j%nbs+" entre ("+j%nbs+","+((j+1)%nbs)+") = "
					+bigTabDistance[j%nbs]);
		}
		//System.out.println("sommeD= "+sommeD);
		
		
		for(int i=0;i<nbs;i++) {
			switch(p) {
			
			case 0: 
				
				
				
				if(bigTabDistance[i%nbs].compareTo
						(bigSommeD.subtract(bigTabDistance[i%nbs]))<0)
					{
					if(i==nbs-1)
					{
						System.out.println("fette menna");
					System.out.println("c'est un Polygone");
					pe=pe.add(bigTabDistance[i%nbs]);
					System.out.println("le perimetre du polygone= "+pe);
					break;
					}
					
					else {
						System.out.println("fette mennaaaaaaaaaaaaaaaaaaaaaaaa"+i);
						pe=pe.add(bigTabDistance[i%nbs]);
						p=0;
						
						}
					continue;
					}
				
				else p=1;
			case 1:
				
				System.out.println("ce n'est pas un Polygone");
				BigDecimal merde=new BigDecimal(2);
				System.out.println("le perimetre du non polygone= "+bigSommeD.divide(merde));
			
				
		}
		break;
			}
		//System.out.println("le perimetre= "+pe);
		//return pe;
	}
	
	public void affiche() {
		
		//System.out.println("polygone");
		
		for(int i=0;i<nbs;i++) {
		double s=0;
		
		System.out.println("le sommet "+i+": ("+sommet[i].ab+" , "+sommet[i].or+
				")");
		
		
		}
		
		
	}
		
	

}
