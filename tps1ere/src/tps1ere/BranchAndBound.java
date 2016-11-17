package tps1ere;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BranchAndBound {

	public ArrayList<ArrayList<Double>> costs = new ArrayList<ArrayList<Double>>();
	ArrayList<String> ids = new ArrayList<String>();
	double lb;
	Node root;

	BranchAndBound(ArrayList<ArrayList<Double>> cout, ArrayList<String> id) {

		costs = cout;
		ids = id;
		root = new Node(cout.get(0).size() - 1, id.get(0), 0);
	}

	ArrayList<String> nommage(String m1, ArrayList<String> ids) {

		ArrayList<StringBuffer> ref = new ArrayList<StringBuffer>();
		StringBuffer p = new StringBuffer(m1);
		StringBuffer mot = new StringBuffer();

		for (int i = 0; i < p.length(); i++) {

			if (p.charAt(i) == '>') {

				ref.add(mot);
				mot = new StringBuffer();

			} else {

				mot.append(p.charAt(i));

				if (i == p.length() - 1) {

					ref.add(mot);
				}

			}

		}

		for (int j = 0; j < ids.size(); j++) {
			for (int i = 0; i < ref.size(); i++) {

				if (ids.get(j).equals(ref.get(i).toString())) {
					ids.remove(j);
					j = 0;
					i = 0;

				}
			}

		}
		return ids;

	}

	ArrayList<Double> valnan(ArrayList<Double> tableau2) {

		ArrayList<Double> tableau = new ArrayList<Double>();
		tableau.addAll(tableau2);

		int j = 0;
		while (j < tableau.size()) {

			if (tableau.get(j).equals(Double.NaN)) {
				tableau.remove(j);
				j = 0;

			} else
				j++;

		}
		return tableau;
	}

	ArrayList<Double> nMin(ArrayList<Double> tableau2, ArrayList<Double> lesMin, int n) {

		ArrayList<Double> tableau = new ArrayList<Double>();
		tableau.addAll(tableau2);
		int r = 0;
		if (n == 1) {

			double min = tableau.get(0);

			for (int i = 0; i < tableau.size(); i++) {
				if (tableau.get(i) < min) {
					min = tableau.get(i);
					r = i;
				}
			}
			tableau.remove(r);
			lesMin.add(min);
		}

		else {

			double min = tableau.get(0);

			for (int i = 0; i < tableau.size(); i++) {
				if (tableau.get(i) < min) {
					min = tableau.get(i);
					r = i;
				}

			}
			tableau.remove(r);
			lesMin.add(min);
			n--;
			nMin(tableau, lesMin, n);
		}
		return lesMin;

	}

	ArrayList<Double> nMax(ArrayList<Double> tableau2, ArrayList<Double> lesMax, int n) {

		ArrayList<Double> tableau = new ArrayList<Double>();
		tableau.addAll(tableau2);
		int r = 0;
		if (n == 1) {

			double max = tableau.get(0);

			for (int i = 0; i < tableau.size(); i++) {
				if (tableau.get(i) > max) {
					max = tableau.get(i);
					r = i;
				}
			}
			tableau.remove(r);
			lesMax.add(max);
		}

		else {

			double max = tableau.get(0);

			for (int i = 0; i < tableau.size(); i++) {
				if (tableau.get(i) > max) {
					max = tableau.get(i);
					r = i;
				}

			}
			tableau.remove(r);
			lesMax.add(max);
			n--;
			nMax(tableau, lesMax, n);
		}
		return lesMax;

	}

	double maxv(ArrayList<ArrayList<Double>> matrice) {

		ArrayList<Double> maxligne = new ArrayList<Double>();
		ArrayList<Double> maxmatrice = new ArrayList<Double>();

		for (int i = 0; i < matrice.size(); i++) {

			maxligne = nMax(valnan(matrice.get(i)), maxligne, 1);

			maxmatrice.add(maxligne.get(0));
			maxligne.clear();

		}

		maxligne = nMax(maxmatrice, maxligne, 1);
		return maxligne.get(0);

	}

	ArrayList<ArrayList<Double>> completion(ArrayList<ArrayList<Double>> matrice) {

		for (int i = 0; i < matrice.size(); i++) {
			for (int j = 0; j < matrice.get(i).size(); j++) {

				if (matrice.get(i).get(j).equals(Double.NaN))

					matrice.get(i).set(j, maxv(matrice) + 1);
			}
		}

		return matrice;

	}

	ArrayList<Integer> indice(StringBuffer p) {

		ArrayList<StringBuffer> ref = new ArrayList<StringBuffer>();
		ArrayList<Integer> refint = new ArrayList<Integer>();
		StringBuffer mot = new StringBuffer();

		for (int i = 0; i < p.length(); i++) {

			if (p.charAt(i) == '>') {

				ref.add(mot);
				mot = new StringBuffer();

			} else {

				mot.append(p.charAt(i));

				if (i == p.length() - 1) {

					ref.add(mot);
				}

			}

		}

		for (int j = 0; j < ref.size(); j++) {
			for (int i = 0; i < ids.size(); i++) {

				if (ids.get(i).equals(ref.get(j).toString()))
					refint.add(j, i);

			}
		}
		return refint;

	}

	ArrayList<ArrayList<Double>> copie(ArrayList<ArrayList<Double>> origin) {

		ArrayList<ArrayList<Double>> copie = new ArrayList<ArrayList<Double>>();

		for (int i = 0; i < origin.size(); i++) {

			ArrayList<Double> ligne = new ArrayList<Double>();

			for (int j = 0; j < origin.get(0).size(); j++) {

				ligne.add(origin.get(i).get(j));

			}
			copie.add(ligne);
		}
		return copie;

	}

	ArrayList<ArrayList<Double>> supp(ArrayList<ArrayList<Double>> matrice, int n, int m) {

		ArrayList<Double> inter = new ArrayList<Double>();

		inter = matrice.get(n);
		inter.remove(m);
		matrice.set(n, inter);
		return matrice;
	}

	double bound(Node n) {

		ArrayList<ArrayList<Double>> inter = new ArrayList<ArrayList<Double>>(completion(costs));
		ArrayList<Integer> refint = new ArrayList<Integer>();
		refint = indice(new StringBuffer(n.getId()));

		ArrayList<Double> lesMin = new ArrayList<Double>();
		ArrayList<Double> sansnan = new ArrayList<Double>();
		double x = 0, y = 0, s = 0;
		System.out.println("refint= " + refint);

		ArrayList<ArrayList<Double>> inter2 = new ArrayList<ArrayList<Double>>();
		inter2 = copie(inter);
		if (refint.size() > ids.size()) {

			refint.remove(refint.size() - 1);
			for (int i = 0; i < refint.size(); i++) {

				if (i == 0) {

					y = inter2.get(refint.get(i)).get(refint.get(i + 1));
					System.out.println("y= " + y);
					x = inter2.get(refint.get(i)).get(refint.get(refint.size() - 1));
					System.out.println("x= " + x);
					s = s + x + y;

					System.out.println("s= " + s);
				} else if (i == refint.size() - 1) {

					x = inter2.get(refint.get(i)).get(refint.get(i - 1));
					y = inter2.get(refint.get(i)).get(refint.get(0));
					System.out.println("y= " + y);
					System.out.println("x= " + x);
					s = s + x + y;

					System.out.println("s= " + s);

				} else {

					x = inter2.get(refint.get(i - 1)).get(refint.get(i));
					y = inter2.get(refint.get(i)).get(refint.get(i + 1));
					System.out.println("y= " + y);
					System.out.println("x= " + x);
					s = s + x + y;
					// System.out.println("i= " + i);
					System.out.println("s= " + s);
				}

				inter2 = copie(inter);
			}
			System.out.println("lower bound= " + s / 2);
			return s / 2;
		}

		else {

			for (int i = 0; i < refint.size(); i++) {
				lesMin.clear();
				sansnan.clear();

				if (i == 0) {

					y = inter2.get(refint.get(i)).get(refint.get(i + 1));
					sansnan.addAll(valnan(supp(inter2, refint.get(i), refint.get(i + 1)).get(refint.get(i))));
					lesMin = nMin(sansnan, lesMin, 1);

					x = lesMin.get(0);
					s = s + x + y;

					System.out.println("s= " + s);

				} else if (i == refint.size() - 1) {

					x = inter2.get(refint.get(i)).get(refint.get(i - 1));
					sansnan.addAll(valnan(supp(inter2, refint.get(i), refint.get(i - 1)).get(refint.get(i))));
					lesMin = nMin(sansnan, lesMin, 1);
					y = lesMin.get(0);
					s = s + x + y;

					System.out.println("s= " + s);

				} else {

					x = inter2.get(refint.get(i - 1)).get(refint.get(i));
					y = inter2.get(refint.get(i)).get(refint.get(i + 1));
					s = s + x + y;
					// System.out.println("i= " + i);
					System.out.println("s= " + s);
				}
				inter2 = copie(inter);
			}
		}

		StringBuffer u = new StringBuffer();
		for (int i = 0; i < ids.size(); i++) {
			u = u.append(new StringBuffer(ids.get(i))).append(">");
		}
		ArrayList<Integer> ref = new ArrayList<Integer>();

		ref = indice(u);
		// System.out.println("ref avant = " + ref);
		for (int j = 0; j < refint.size(); j++) {
			for (int i = 0; i < ref.size(); i++) {

				if (refint.get(j).equals(ref.get(i))) {
					ref.remove(i);
					j = 0;
					i = 0;

				}
			}

		}

		for (int i = 0; i < ref.size(); i++) {
			lesMin.clear();
			sansnan.clear();
			sansnan.addAll(valnan(inter2.get(ref.get(i))));
			lesMin = nMin(sansnan, lesMin, 2);
			// System.out.println("les mins= " + lesMin);
			for (int j = 0; j < lesMin.size(); j++) {
				s = s + lesMin.get(j);

			}
		}
		System.out.println("lower bound= " + s / 2);
		return s / 2;
	}

	ArrayList<Node> branch(Node n, ArrayList<String> ids, int nbe) {

		ArrayList<Node> children = new ArrayList<Node>();
		children = n.addChild(nbe, ids);
		return children;

	}

	void gen(ArrayList<String> ids, ArrayList<ArrayList<Double>> inter) {

		ArrayList<String> ref2 = new ArrayList<String>();
		ArrayList<String> ref = new ArrayList<String>();
		ref2.addAll(ids);
		ArrayList<Node> children = new ArrayList<Node>();
		ArrayList<Double> values = new ArrayList<Double>();
		ArrayList<Double> vsorted = new ArrayList<Double>();
		ArrayList<Node> children2 = new ArrayList<Node>();
		children.add(root);
		int element = ids.size() - 1;
		int t1 = 1, t2 = 1;
		double min = 0;

		for (int i = 0; i < ids.size(); i++) {

			minloop: for (int j = 0; j < t2; j++) {

				ref2.clear();

				if (i == ids.size() - 1) {
					ref.add(ids.get(0));
					element = 1;
				} else {
					ref2.addAll(ids);
					ref = nommage(children.get(j + t1 - t2).id, ref2);
				}

				if (children.get(j + t1 - t2).lb <= min) {

					System.out.println("min= " + min);
					System.out.println("<<<<noeud courant>>>> " + children.get(j + t1 - t2).id);
					children2.addAll(branch(children.get(j + t1 - t2), ref, element));

					int x = children2.size();

					for (int k = x - element; k < children2.size(); k++) {

						System.out.println("######ses fils###### " + children2.get(k).id);

						children2.get(k).setLb(bound(children2.get(k)));
						double v = children2.get(k).lb;
						values.add(v);
					}

				} else
					continue minloop;

			}
			vsorted = nMin(values, vsorted, values.size());
			min = vsorted.get(0);
			vsorted.remove(0);
			children.addAll(children2);

			t2 = children2.size();
			t1 = children.size();
			children2.clear();
			element--;

		}
		int rid = children.get(children.size() - 1).id.length();

		for (int k = 0; k < children.size(); k++) {

			if (children.get(k).id.length() == rid) {
				children2.add(children.get(k));
			}
		}

		Collections.sort(children2, new Comparator<Node>() {
			public int compare(Node n1, Node n2) {

				return Double.compare(n1.lb, n2.lb);
			}
		});

		System.out.println("le plus court chemin " + children2.get(0).id);
		System.out.println("son cout " + children2.get(0).lb);
		System.out.println("nombre total de noeud generer " + t1);

	}

}
