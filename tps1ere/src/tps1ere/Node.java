package tps1ere;

import java.util.ArrayList;

public class Node {

	ArrayList<Node> children = new ArrayList<>();
	ArrayList<String> ref = new ArrayList<String>();
	Node parent;
	double lb;
	String id;
	int nbE;
	
	Node(int nbE,String id,double lb) {
		this.parent=null;
		this.lb=lb;
		this.id=id;
		this.nbE=nbE;
	}

	void setId(String id) {

		this.id =parent.id+">"+id;
	}
	
	void setLb(double lb) {

		this.lb=lb;
	}

	void setParent(Node p) {
		//p.addChild(nbE);
		this.parent=p;
	}

	Node getParent() {
		return parent;
	}
	
	String getId() {
		return id;
	}
	
	
	
	double getLb() {
		return lb;
	}
	
	ArrayList<Node> getChildren() {
		return children;
	}
	
	ArrayList<Node> addChild(int nbe,ArrayList<String>ref) {
		
		
		
		ArrayList<Node> children = new ArrayList<>();
		for (int i = 0; i < nbe; i++) {
			
			
			
			children.add(i, new Node(0,null,0));
			
			children.get(i).setParent(this);
			//ref.remove(j);
			children.get(i).setId(ref.get(i));
			
			
			
		}
		return children;

	}

	public static void main(String[] args) {
		
		Node n=new Node(3,"a",0);
		ArrayList<Node> children = new ArrayList<>();
		children=n.getChildren();
		
		System.out.println(children.size());
		//for(int i=0;i<children.size();i++)
		//System.out.println(children.get(i).getId());
		//ArrayList<Node> children2 = new ArrayList<>();
		//children2=children.get(1).addChild(4);
		//for(int i=0;i<children.size();i++)
		//System.out.println(children2.get(i).getLb());
		//children.get(1).getChildren();

	}
}
