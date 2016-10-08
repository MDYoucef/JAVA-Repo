package geo;
import java.math.*;
public class Cercle extends Figure {
	
	double rayon;

	public Cercle(Point c,double r) {
		// TODO Auto-generated constructor stub
		super(c);
		rayon=r;
		
	}
	
	public void peri() {
		double p=0;
		final double pi=3.14;
		
		p=2*pi*rayon;
		//return p;
	}
	
	public void affiche() {
		
		System.out.println("un cerle de rayon "+rayon+"a un perimetre= ");
	}

}
