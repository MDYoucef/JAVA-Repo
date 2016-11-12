package tps1ere;

import java.util.ArrayList;

public class BranchAndBound {

	public ArrayList<ArrayList<Double>> costs = new ArrayList<ArrayList<Double>>();
	ArrayList<String> ids = new ArrayList<String>();
	double lb;
	Node root;

	BranchAndBound(ArrayList<ArrayList<Double>> cout, ArrayList<String> id) {

		costs = cout;
		ids = id;
		root = new Node(cout.get(0).size() - 1, id.get(0), lbroot(cout));
	}

	double lbroot(ArrayList<ArrayList<Double>> matrice) {
		double s = 0;
		ArrayList<Double> ligne = new ArrayList<Double>();

		for (int i = 0; i < matrice.size(); i++) {

			ligne.clear();
			ligne = nMin(matrice.get(i), ligne, 2);

			for (int j = 0; j < ligne.size(); j++) {

				s = s + ligne.get(j);

			}

		}
		return s;
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

	ArrayList<Double> nMin(ArrayList<Double> tableau, ArrayList<Double> lesMin, int n) {

		for (int i = 0; i < tableau.size(); i++) {
			if (tableau.get(i).equals(Double.NaN))
				tableau.remove(i);
		}

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

	ArrayList<Node> branch(Node n, ArrayList<String> ids, int nbe) {

		ArrayList<Node> children = new ArrayList<Node>();
		children = n.addChild(nbe, ids);
		return children;

	}

	/*
	 * double bound(Node n) {
	 * 
	 * }
	 */

	void gen(ArrayList<String> ids) {

		ArrayList<String> ref2 = new ArrayList<String>();
		ArrayList<String> ref = new ArrayList<String>();
		ref2.addAll(ids);
		ArrayList<Node> children = new ArrayList<Node>();
		ArrayList<Node> children2 = new ArrayList<Node>();
		children.add(root);
		int element = ids.size() - 1;
		int t1 = 1, t2 = 1;

		for (int i = 0; i < ids.size(); i++) {

			for (int j = 0; j < t2; j++) {

				System.out.println("j avant= " + j);

				ref2.clear();

				if (i == ids.size() - 1) {
					ref.add(ids.get(0));
					element = 1;
				} else {
					ref2.addAll(ids);
					ref = nommage(children.get(j + t1 - t2).id, ref2);
				}
				System.out.println("<<<<mot courant>>>> " + children.get(j + t1 - t2).id);
				children2.addAll(branch(children.get(j + t1 - t2), ref, element));

			}
			children.addAll(children2);
			
			t2 = children2.size();
			t1 = children.size();
			System.out.println("taille children2 avant= " + t2);
			System.out.println("taille children avant= " + t1);
			children2.clear();
			System.out.println("taille childre2 apres= " + t2);
			element--;

		}

		for (int k = 0; k < children.size(); k++) {
			System.out.println("dans children = " + children.get(k).id);
		}

	}

}
