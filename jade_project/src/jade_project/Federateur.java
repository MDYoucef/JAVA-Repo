package jade_project;

import java.util.ArrayList;

import jade.core.AID;
import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.core.behaviours.TickerBehaviour;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class Federateur extends Agent {
	
	private  AID [] agents ;

	protected void setup() {

		System.out.println("je suis " + getAID().getName() + " et je suis pret");

		Domaine d = new Domaine();
		int n = (int) (Math.random() * 4000);
		ArrayList<Integer> domaines = d.lesDomaines(n, 36);
		System.out.println("le chiffre a tester = " + n);
		System.out.println("les domaines du testes = " + domaines);

		/*jade.lang.acl.ACLMessage init = new jade.lang.acl.ACLMessage(jade.lang.acl.ACLMessage.REQUEST);
		init.setContent("prepare toi");
		init.addReceiver(new AID("calculateur", AID.ISLOCALNAME));
		send(init);*/

		addBehaviour(new FederateurBehaviour(n, domaines, agents));
		

	}

}
