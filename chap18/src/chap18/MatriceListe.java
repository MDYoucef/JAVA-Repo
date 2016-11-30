package chap18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MatriceListe {

	public int ligne, colonne;
	public ArrayList<ArrayList<Double>> matrice = new ArrayList<ArrayList<Double>>();
	public Scanner lire = new Scanner(System.in);

	public MatriceListe(int n, int m) throws TypeDElementInvalide {
		if (n == 0 || m == 0 || n < 0 || m < 0) {
			throw new TypeDElementInvalide();
		}

		ligne = n;
		colonne = m;

	}

	MatriceListe creation(MatriceListe mat) {

		for (int i = 0; i < mat.ligne; i++) {
			ArrayList<Double> row = new ArrayList<Double>();

			System.out.println("ligne " + i);
			for (int j = 0; j < mat.colonne; j++) {
				System.out.println("element: " + "[" + i + "] [" + j + "]");
				row.add(lire.nextDouble());
				System.out.println("fooooooo " + isFull(row));
			}

			matrice.add(i, row);

		}
		return mat;
	}

	boolean isCarre(MatriceListe mat) throws DifferentNombreDElement {

		if (mat.matrice.size() != mat.matrice.get(0).size())
			throw new DifferentNombreDElement();
		else
			return true;

	}

	void affichage(MatriceListe mat) {
		int i;
		for (i = 0; i < mat.matrice.size(); i++) {
			System.out.println(mat.matrice.get(i));

		}
	}

	MatriceListe inverse(MatriceListe mat) throws TypeDElementInvalide {

		MatriceListe mat2 = new MatriceListe(colonne, ligne);

		for (int k = 0; k < mat.matrice.get(0).size(); k++) {
			ArrayList<Double> row = new ArrayList<Double>();

			for (int j = 0; j < mat.matrice.size(); j++) {

				row.add(mat.matrice.get(j).get(k));
			}
			mat2.matrice.add(k, row);

		}
		return mat2;

	}

	MatriceListe getDiag(MatriceListe m) throws TypeDElementInvalide {

		ArrayList<Double> diag1 = new ArrayList<Double>();
		ArrayList<Double> diag2 = new ArrayList<Double>();
		MatriceListe diagonale = new MatriceListe(m.matrice.size(), m.matrice.get(0).size());
		int i = 0;
		for (int j = 0; j < m.matrice.size(); j++) {

			diag1.add(m.matrice.get(j).get(i));
			i++;

		}

		i = m.matrice.get(0).size() - 1;
		for (int j = 0; j < m.matrice.size(); j++) {

			diag2.add(m.matrice.get(j).get(i));
			i--;

		}
		diagonale.matrice.add(diag1);
		diagonale.matrice.add(diag2);
		return diagonale;
	}

	MatriceListe suppColonne_Ligne(MatriceListe m, int c, int l) throws TypeDElementInvalide {

		if (m.matrice.isEmpty())
			return null;

		m = inverse(m);
		m.matrice.remove(c);
		m = inverse(m);
		m.matrice.remove(l);

		return m;
	}

	MatriceListe suppColonne(MatriceListe m, int c) throws TypeDElementInvalide {

		if (m.matrice.isEmpty())
			return null;

		m = inverse(m);
		m.matrice.remove(c);
		m = inverse(m);

		return m;
	}

	MatriceListe suppligne(MatriceListe m, int c) throws TypeDElementInvalide {

		if (m.matrice.isEmpty())
			return null;
		m.matrice.remove(c);

		return m;
	}

	void verifieur(ArrayList<Double> row) throws SameElementException {

		for (int i = 0; i < row.size(); i++) {
			for (int j = 0; j < row.size(); j++) {

				if (i == j)
					continue;
				else {
					if (row.get(i) == row.get(j)) {
						throw new SameElementException(row.get(i));
					} else
						continue;
				}

			}
			// System.out.println("valide");

		}
		System.out.println("valide");
	}

	double somme(ArrayList<Double> row) {
		double s = 0;
		for (Double i : row) {
			s = s + i;
		}
		return s;
	}

	double magiquePart_1(MatriceListe m) throws SameElementException, TypeDElementInvalide, DifferentSommeException {

		double bi;
		double s1 = somme(m.matrice.get(0)), s2 = 0;

		for (int i = 0; i < m.matrice.size(); i++) {

			s2 = somme(m.matrice.get(i));
			System.out.println("la somme de la ligne " + m.matrice.get(i) + " " + s2);
			verifieur(m.matrice.get(i));
			if (s1 != s2)
				throw new DifferentSommeException();
		}

		bi = s2;
		return bi;

	}

	boolean magique(MatriceListe m) throws SameElementException, TypeDElementInvalide, DifferentSommeException {

		double bi_ligne;
		double bi_colonne;
		double bi_diagonale;

		bi_ligne = magiquePart_1(m);
		System.out.println('\n');
		bi_colonne = magiquePart_1(inverse(m));
		System.out.println('\n');
		bi_diagonale = magiquePart_1(getDiag(m));

		if (bi_ligne == bi_colonne && bi_ligne == bi_diagonale)
			return true;
		else
			return false;
	}

	int isFull(ArrayList<Double> m) {

		int s = 0;

		s = s + m.size();

		return s;
	}

	MatriceListe init0(int n) throws TypeDElementInvalide {
		MatriceListe m = new MatriceListe(n, n);

		for (int i = 0; i < n; i++) {
			ArrayList<Double> row = new ArrayList<Double>(Collections.nCopies(n, 0.0));
			m.matrice.add(row);
		}
		return m;
	}

	MatriceListe BuildMagique(int n, double x) throws TypeDElementInvalide {

		int total = 0;
		MatriceListe m;
		m = init0(n);
		int ligneC = m.matrice.size() / 2 + 1;

		ArrayList<Double> c = m.matrice.get(ligneC);
		int colonneC = c.size() / 2;
		c.set(colonneC, x);
		total = 1;
		/*
		 * System.out.println("ligne " + ligneC); System.out.println("colonne "
		 * + colonneC); System.out.println("x " + x); System.out.println(
		 * "ma ligne " + ligneC + " " + c);
		 */
		do {

			ligneC++;
			if (ligneC == n)
				ligneC = 0;
			c = m.matrice.get(ligneC);

			colonneC++;

			if (colonneC == n)
				colonneC = 0;

			if ((double) c.get(colonneC) != 0) {
				ligneC++;
				if (ligneC == n)
					ligneC = 0;
				c = m.matrice.get(ligneC);

				colonneC--;
				if (colonneC < 0)
					colonneC = n - 1;
			}

			x++;
			c.set(colonneC, x);
			// System.out.println("ma ligne " + ligneC + " " + c);
			total++;

			/*
			 * System.out.println("ligne " + ligneC); System.out.println(
			 * "colonne " + colonneC); System.out.println("x " + x);
			 * System.out.println("total " + total);
			 */
			if (c.size() == n)
				m.matrice.set(ligneC, c);

		} while (total != n * n);
		return m;

	}

	MatriceListe commuteur(MatriceListe m, int dep, int arr) throws TypeDElementInvalide {

		MatriceListe mat = inverse(m);

		if (dep > arr) {

			mat.matrice.add(arr, mat.matrice.get(dep));

			mat.matrice.remove(dep + 1);
		} else {

			mat.matrice.add(arr + 1, mat.matrice.get(dep));

			mat.matrice.remove(dep);

		}

		mat = inverse(mat);

		return mat;

	}

	double determinant(MatriceListe m, double d) throws TypeDElementInvalide, DifferentNombreDElement {

		ArrayList<Double> det = new ArrayList<Double>();
		det.add(0.0);
		MatriceListe m2 = new MatriceListe(m.matrice.size(), m.matrice.get(0).size());
		m2.matrice.addAll(m.matrice);

		if (m.matrice.size() == 2) {
			// affichage(m);
			d = m.matrice.get(0).get(0) * m.matrice.get(1).get(1) - m.matrice.get(1).get(0) * m.matrice.get(0).get(1);

			return d;

		} else {

			for (int i = 0; i < m.matrice.get(0).size(); i++) {

				m = m2;
				m = commuteur(m, i, 0);
				// affichage(m);
				d = determinant(m.suppColonne_Ligne(m, 0, 0), d);
				// affichage(m);
				d = d * m.matrice.get(0).get(0);
				if (i % 2 != 0)
					d = -d;
				d = det.get(0) + d;
				det.add(d);
				det.remove(0);

			}
			return d;
		}

	}

}
