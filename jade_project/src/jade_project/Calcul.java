package jade_project;

import jade.core.AID;
import jade.core.Agent;

public class Calcul extends Agent {
	
protected void setup() {
		
		System.out.println("je suis " + getAID().getName() + " et je suis pret");
		
		jade.lang.acl.ACLMessage init = new jade.lang.acl.ACLMessage(jade.lang.acl.ACLMessage.REQUEST);
		init.setContent("ok");
		init.addReceiver(new AID("federateur", AID.ISLOCALNAME));
		send(init);
		
		addBehaviour(new CalculBehaviour());
		
	}

}
