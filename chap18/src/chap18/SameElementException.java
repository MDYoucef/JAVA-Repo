package chap18;

public class SameElementException extends Exception { 
	
	SameElementException(double n) {
		System.out.println("l'element "+n+" se repete");
	}

}
