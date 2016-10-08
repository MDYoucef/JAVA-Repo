package chap16;

import java.util.Scanner;

public class Palindrome {
	static boolean ispalin(String mot) {
		int i = 0;
		if (mot.length() <= 1) {
			
			return true;
		}

		else if (mot.charAt(0) == mot.charAt(mot.length() - 1)) {

			mot = mot.substring(1, mot.length() - 1);

			return ispalin(mot);

		} else
			return false;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner lire = new Scanner(System.in);
		int j = 0;
		do {
			String mot = lire.nextLine();
			boolean b = ispalin(mot);
			System.out.println(b);
		} while (j == 0);

	}

}
