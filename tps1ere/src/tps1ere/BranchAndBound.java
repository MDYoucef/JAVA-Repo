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

	ArrayList<Node> branch(Node n,ArrayList<String> ids, int nbe,int j) {

		ArrayList<Node> children = new ArrayList<Node>();
		children = n.addChild(nbe, ids,j);
		return children;

	}

	void gen(ArrayList<String> ids) {
		
		ArrayList<Node> children = new ArrayList<Node>();
		children.add(root);
		
		int k=0;
		int element = ids.size()-1;
		int t=children.size();
		ArrayList<Node> children2= null;

			while(element!=0) {
				
				
				for (int j = 0; j < t; j++) {
					
					children2 = new ArrayList<Node>();
					children2.addAll(branch(children.get(0),ids ,element,j));
					System.out.println("j= "+j);
					
					for(k=0;k<children2.size();k++) {
						System.out.println(children2.get(k).id);
					}
					
					children.remove(0);
					children.addAll(children2);
					
				}
				t=children2.size();
				ids.remove(0);
				element--;
				
				System.out.println("taille de children= "+children.size());
			}
			
			/*for(int i=0;i<children.size();i++) {
				System.out.println(children.get(i).id);

		}*/

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
