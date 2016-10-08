package chap16;

import java.util.Scanner;

public class Ex_3 {

	static void romain(int n) {
		int i = 0, x = 0;
		String a = String.valueOf(n);
		String r="";
		if (a.length() == 1) {

			if (n >= 1 && n < 4) {

				System.out.print("I");

				romain(n - 1);

			} else if (n >= 4) {

				if (n >= 4 && n < 5 || n == 9) {

					System.out.print("I");

					romain(n + 1);
				} else if (n >= 5) {
					System.out.print("V");

					romain(n - 5);
				}

			}
		} else if (a.length() == 2) {

			if (n >= 10 && n < 40) {

				System.out.print("X");

				romain(n - 10);

			} else if (n >= 40) {

				if (n >= 40 && n < 50 || n == 90) {

					System.out.print("X");

					romain(n + 10);
				} else if (n >= 50) {
					System.out.print("L");

					romain(n - 50);
				}

			}
		} else if (a.length() == 3) {

			if (n >= 100 && n < 400) {

				System.out.print("C");

				romain(n - 100);

			} else if (n >= 400) {

				if (n >= 400 && n < 500 || n == 900) {

					System.out.print("C");

					romain(n + 100);
				} else if (n >= 500) {
					System.out.print("D");

					romain(n - 500);
				}

			}

		} else if (a.length() == 4) {

			if (n >= 1000 && n < 4000) {

				System.out.print("M");

				romain(n - 1000);

			} else if (n >= 4000) {

				if (n >= 4000 && n < 5000 || n == 9000) {

					System.out.print("M");

					romain(n + 1000);
				} else if (n >= 500) {
					System.out.print("P");

					romain(n - 5000);
				}

			}

		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner lire = new Scanner(System.in);
		int j = 0;
		do {
			int n = lire.nextInt();
			romain(n);
		} while (j == 0);

	}

}
