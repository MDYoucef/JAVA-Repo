package testjade;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.introspection.ACLMessage;

public class Receiver extends Agent {

	protected void setup() {

		addBehaviour(new OneShotBehaviour() {

			@Override
			public void action() {

				jade.lang.acl.ACLMessage reception = receive();
				jade.lang.acl.ACLMessage envoie = new jade.lang.acl.ACLMessage(jade.lang.acl.ACLMessage.INFORM);
				//envoie.addReceiver(reception.getSender());
				System.out.println("Hello! Buyer-agent " + getAID().getName() + " is ready.");
				
				if (reception != null) {
					System.out.println("le message de reception " + reception.getContent());
					envoie.setContent("il existe");
					send(envoie);

				} 
				else{
                    block();
                }

			}
		});

	}

}
