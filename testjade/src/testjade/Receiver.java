package testjade;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.introspection.ACLMessage;

public class Receiver extends Agent {
	int x = 0, y = 0;

	protected void setup() {

		System.out.println("Hello! Buyer-agent " + getAID().getName() + " is ready.");
		addBehaviour(new TickerBehaviour(this, 500) {

			@Override
			protected void onTick() {

				jade.lang.acl.ACLMessage partie1 = receive();
				
				jade.lang.acl.ACLMessage attente = new jade.lang.acl.ACLMessage(jade.lang.acl.ACLMessage.REQUEST);
				jade.lang.acl.ACLMessage reponse = new jade.lang.acl.ACLMessage(jade.lang.acl.ACLMessage.INFORM);

				if (partie1 != null) {
					
					if (partie1.getPerformative() == jade.lang.acl.ACLMessage.REQUEST) {
						System.out.println("coucou");
						System.out.println(partie1.getContent());
						x = Integer.parseInt(partie1.getContent());
						attente.setContent("j'ai reçu le 1er chiffre");
						attente.addReceiver(partie1.getSender());
						partie1=null;
						send(attente);

					}

					else if (partie1.getPerformative() == jade.lang.acl.ACLMessage.INFORM) {
						System.out.println("bouh");
						System.out.println(partie1.getContent());
						y = Integer.parseInt(partie1.getContent());
						int somme = x + y;
						reponse.setContent("" + somme);
						reponse.addReceiver(partie1.getSender());
						send(reponse);

					}
				} else {
					block();
				}

				// }

			}
		});

	}

}
