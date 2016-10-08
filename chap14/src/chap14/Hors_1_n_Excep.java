package chap14;

public class Hors_1_n_Excep extends Exception {
	
	Hors_1_n_Excep(int n) {
		System.out.println("entrez une valeur entre 1 et " + n);
	}

}