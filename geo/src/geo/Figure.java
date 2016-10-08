package geo;

import java.math.*;

public abstract class Figure {
	
	static BigDecimal z1=new BigDecimal(0);
	private static final Point zero =new Point(z1,z1);
	Point origine;
	
	Figure() {
		origine=zero;
	}
	
	Figure(Point p) {
		origine=new Point(p);
	}
	
	abstract void peri();
	
	abstract void affiche();
	

}
