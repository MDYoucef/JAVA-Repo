package testjade;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.introspection.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class HelloAgent extends Agent {
	private String targetBookTitle;
	// The list of known seller agents
	private AID[] lesAgents; // { new AID("seller1", AID.ISLOCALNAME), new
								// AID("seller2", AID.ISLOCALNAME) };

	// Put agent initializations here
	protected void setup() {
		// Printout a welcome message
		System.out.println("Hello! Buyer-agent " + getAID().getName() + " is ready.");

		// Get the title of the book to buy as a start-up argument
		Object[] args = getArguments();
		/*
		 * args[1] = "da vinci code"; args[2] = "ange et demon";
		 */

		if (args != null && args.length > 0) {
			// targetBookTitle = (String) args[0];
			System.out.println("je veux des calculs ");

			addBehaviour(new TickerBehaviour(this, 500) {

				@Override
				protected void onTick() {
					MessageTemplate partie1 = null;
					MessageTemplate partie2 = null;
					int x = 0;
					jade.lang.acl.ACLMessage m = new jade.lang.acl.ACLMessage(jade.lang.acl.ACLMessage.REQUEST);
					

					m.addReceiver(new AID("vendeur", AID.ISLOCALNAME));

					int chiffre = (int) (Math.random() * 10);
					m.setContent(chiffre + "");

					send(m);

					jade.lang.acl.ACLMessage reception = receive();

					if (reception != null) {
						if (reception.getPerformative() == jade.lang.acl.ACLMessage.REQUEST) {
							jade.lang.acl.ACLMessage m1 = new jade.lang.acl.ACLMessage(jade.lang.acl.ACLMessage.INFORM);
							System.out.println("envoie du 2eme chiffre");
							m1.addReceiver(reception.getSender());

							int chiffre2 = (int) (Math.random() * 10);
							m1.setContent(chiffre2 + "");

							send(m1);
						}
						
						else if (reception.getPerformative()==jade.lang.acl.ACLMessage.INFORM) {
							System.out.println("j'ai reçu la reponse qui est "+reception.getContent());
							reception = null;

						}
						
					} else {
						block();
					}
				}
			});

		} else {
			// Make the agent terminate immediately
			System.out.println("No book title specified");
			doDelete();
		}

	}

	// Put agent clean-up operations here
	protected void takeDown() {
		// Printout a dismissal message
		System.out.println("oooooooooooooooooooooooooooooooooooooo" + lesAgents.length);
		System.out.println("Buyer-agent " + getAID().getName() + " terminating.");
	}
}