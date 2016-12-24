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
	private AID[] lesAgents = { new AID("vendeur", AID.ISLOCALNAME) };

	// Put agent initializations here
	protected void setup() {
		// Printout a welcome message
		System.out.println("Hello! Buyer-agent " + getAID().getName() + " is ready.");

		// Get the title of the book to buy as a start-up argument
		Object[] args = getArguments();
		/*
		 * args[1] = "da vinci code"; args[2] = "ange et demon";
		 */
		long p = 500;

		if (args != null && args.length > 0) {
			// targetBookTitle = (String) args[0];
			System.out.println("je veux des calculs ");

			addBehaviour(new TickerBehaviour(this,1000) {

				@Override
				public void onTick() {

					jade.lang.acl.ACLMessage reception = receive();

					if (reception != null) {

						if (reception.getContent().equals("je suis pret")) {
							jade.lang.acl.ACLMessage m1 = new jade.lang.acl.ACLMessage(jade.lang.acl.ACLMessage.INFORM);
							int chiffre = (int) (Math.random() * 10);
							System.out.println("envoie du 1er chiffre");
							m1.addReceiver(reception.getSender());
							m1.setContent(chiffre + "");
							m1.setConversationId("debut");
							send(m1);

						} else if (reception.getContent().equals("j'ai reçu le 1er chiffre")) {
							jade.lang.acl.ACLMessage m1 = new jade.lang.acl.ACLMessage(jade.lang.acl.ACLMessage.INFORM);
							int chiffre2 = (int) (Math.random() * 10);
							System.out.println("envoie du 2eme chiffre");
							m1.addReceiver(reception.getSender());
							m1.setContent(chiffre2 + "");
							m1.setConversationId("finale");
							send(m1);
						}

						else {
							System.out.println("j'ai reçu la reponse qui est " + reception.getContent());
							jade.lang.acl.ACLMessage m1 = new jade.lang.acl.ACLMessage(jade.lang.acl.ACLMessage.INFORM);
							int chiffre = (int) (Math.random() * 10);
							m1.addReceiver(reception.getSender());
							m1.setContent(chiffre + "");
							m1.setConversationId("debut");
							send(m1);
						}

					} else {
						System.out.println("block acheteur");
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