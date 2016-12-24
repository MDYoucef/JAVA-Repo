package jade_project;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class Creation {

	public static void main(String[] args) throws StaleProxyException {
		
		
		/*Domaine d=new Domaine();
		int n = (int) (Math.random() * 40);
		System.out.println("mon chiffre = "+n);
		System.out.println("le tableau = "+d.lesDomaines(n, 4));*/
		
		/*CalculBehaviour test=new CalculBehaviour();
		
		String t="9 10";
		
		System.out.println(test.getDomaine(t));
		test.testDomaine(20,test.getDomaine(t) );
		
		/*boolean nsend=false;
		boolean dsend=false;
		boolean reception = true;

		if (reception) {
			
			if(!nsend) {
				
				System.out.println("aaaaaaaaaaaaaa");
				nsend=true;
				reception=false;
			}

			if (!dsend) {
				
				System.out.println("bbbbbbbbbbbbbb");
				dsend=true;

			}

		} else
			System.out.println("block");*/
		
		Runtime rt = Runtime.instance();
	      Profile p = new ProfileImpl();
	      AgentContainer ac = rt.createMainContainer(p);
	      AgentController agent1 = ac.createNewAgent("federateur", "jade_project.Federateur", new Object[0]);
	      
	     
	      AgentController agent2 = ac.createNewAgent("calcultaur", "jade_project.Calcul", new Object[0]);
	      agent2.start();
	      agent1.start();
	     
	      
	      AgentController RMAagent =ac.createNewAgent("rma", "jade.tools.rma.rma", new Object[0]);
	      RMAagent.start();
		
	}

}
