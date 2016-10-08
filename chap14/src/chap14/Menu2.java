package chap14;

import java.util.Scanner;

//import chap11.Matrice;

public class Menu2 {

	Scanner lirec = new Scanner(System.in);
	Scanner lire = new Scanner(System.in);
	int nbrtab;
	char car[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	String[] miniMenu(int size) {
		Scanner lirec = new Scanner(System.in);
		Scanner lire = new Scanner(System.in);

		String q[] = new String[size];
		for (int i = 0; i < size; i++) {

			q[i] = lire.nextLine();

		}

		return q;
	}

	int recuperation(String[] questionnaire) {

		int d = 0;
		int j, i;

		for (i = 0; i < questionnaire.length; i++) {
			if (questionnaire[i] != null)
				d++;
			else
				break;
		}

		return d;
	}

	void affichahe(String[] questionnaire) {

		
		// for (int i = 0; i < dimension; i++) {
		for (int j = 0; j < questionnaire.length; j++) {
			System.out.print(questionnaire[j] + '\t');
		}

		System.out.print("\n");

		// }
	}

	int convertion(char c) throws RienExcep {

		int i;
		for (i = 0; i <= car.length; i++) {

			if (i < car.length && c == car[i])

				return i;

			else if (i == car.length)
				throw new RienExcep();

			else
				continue;
		}
		return i;

	}

	boolean horsType(String[] questionnaire, int i) 
			throws Hors_1_n_Excep, Inf_a_1_Excep, RienExcep {
		
		int k;

		bigloop: for (k = 0; k < questionnaire.length; k++) {

			if (i > questionnaire.length) {
				
				throw new Hors_1_n_Excep(questionnaire.length);

			} else if (i < 1) {

				throw new Inf_a_1_Excep();

			} else
				return true;

		}
		return false;
	}

}
