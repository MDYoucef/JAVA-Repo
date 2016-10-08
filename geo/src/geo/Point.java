package geo;

import java.math.*;

public class Point {
	
	public BigDecimal ab,or;
	
	Point(BigDecimal x, BigDecimal y) {
		ab=x;
		or=y;
	}
	
	Point(Point p) {
		ab=p.ab; 
		or=p.or;
	}
	
	public static BigDecimal sqrt(BigDecimal value) {
	    BigDecimal x = new BigDecimal(Math.sqrt(value.doubleValue()));
	    return x.add(new BigDecimal(value.subtract(x.multiply(x)).doubleValue() / (x.doubleValue() * 2.0)));
	}
	
	
	
	public static BigDecimal distance(Point m, Point n) {
		
		BigDecimal d;
		
		BigDecimal v1=n.ab.subtract(m.ab);
		BigDecimal v2=n.or.subtract(m.or);
		
		BigDecimal dv1=v1.multiply(v1);
		BigDecimal dv2=v1.multiply(v2);
		//d=Math.sqrt((n.ab-m.ab)*(n.ab-m.ab)+(n.or-m.or)*(n.or-m.or));
		d=sqrt((dv1.add(dv2)));
		
		return d;
		
	}
		
		
	

}
