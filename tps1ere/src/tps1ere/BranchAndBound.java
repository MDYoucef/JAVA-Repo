package tps1ere;

import java.util.ArrayList;

public class BranchAndBound {

	public ArrayList<ArrayList<Double>> costs = new ArrayList<ArrayList<Double>>();
	ArrayList<String> ids = new ArrayList<String>();
	Node root;

	BranchAndBound(ArrayList<ArrayList<Double>> cout, ArrayList<String> id) {

		costs = cout;
		ids = id;
		root = new Node(cout.get(0).size() - 1, id.get(0));
	}

	ArrayList<String> nommage(String m1, ArrayList<String> ids) {

		ArrayList<StringBuffer> ref = new ArrayList<StringBuffer>();
		StringBuffer p = new StringBuffer(m1);
		StringBuffer mot = new StringBuffer();
		ArrayList<String> fin = new ArrayList<String>();

		// System.out.println(p.length());
		// System.out.println(p.charAt(0));

		for (int i = 0; i < p.length(); i++) {

			if (p.charAt(i) == '_') {

				ref.add(mot);
				mot = new StringBuffer();

			} else {

				mot.append(p.charAt(i));

				if (i == p.length() - 1) {

					ref.add(mot);
				}

			}

		}
		fin=ids;
		System.out.println("ref =" + ref);
		for (int j = 0; j < ids.size(); j++) {
			for (int i = 0; i < ref.size(); i++) {

				if (ids.get(j).equals(ref.get(i).toString())) {
					fin.remove(j);
					System.out.println("fin =" + fin);
				}
			}

		}
		return fin;

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

	ArrayList<Node> branch(Node n, ArrayList<String> ids, int nbe, int j) {

		ArrayList<Node> children = new ArrayList<Node>();
		children = n.addChild(nbe, ids, j);
		return children;

	}

	void gen(ArrayList<String> ids) {
		
		ArrayList<String>ref2=new ArrayList<String>();
		ref2.addAll(ids);
		ArrayList<Node> children = new ArrayList<Node>();
		children.add(root);

		int c = 0;
		int k = 0;
		int element = ids.size() - 1;
		int t = children.size();
		ArrayList<Node> children2 = null;

		for (int i = 0; i < ids.size() - 1; i++) {

			children2 = new ArrayList<Node>();

			for (int j = 0; j < t; j++) {
				
				System.out.println("mot courant "+children.get(0).id);
				ref2.clear();
				ref2.addAll(ids);
				System.out.println("ids "+ids);
				System.out.println("ref2 "+ref2);
				children2.addAll(branch(children.get(0), nommage(children.get(0).id, ref2), element, j));

				System.out.println("j= " + j);

				for (k = 0; k < children2.size(); k++) {
					System.out.println(children2.get(k).id);
				}
				children.addAll(children2);
				children.remove(0);

				children2.clear();

			}
			t = children.size();

			// children2.clear();
			element--;

			System.out.println("taille de children= " + children.size());
		}

		/*
		 * for(int i=0;i<children.size();i++) {
		 * System.out.println(children.get(i).id);
		 * 
		 * }
		 */

	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * BranchAndBound bb = new BranchAndBound();
	 * System.out.println("enfant ="+bb.root.nbE);
	 * 
	 * }
	 */

}
