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
			targetBookTitle = (String) args[0];
			System.out.println("Trying to buy " + targetBookTitle);

			addBehaviour(new OneShotBehaviour() {
				
				@Override
				public void action() {
					
					//MessageTemplate resultTemp=null;
					int x=0;
					jade.lang.acl.ACLMessage m=new jade.lang.acl.ACLMessage(jade.lang.acl.ACLMessage.REQUEST);
					
					
	                    m.addReceiver(new AID("vendeur",AID.ISLOCALNAME));
	                
					
					m.setContent("harry potter");
					
					//m.setConversationId("calcul-requete") ;
					send(m);
					System.out.println("le message qui a ete envoyé" +m.getContent());
					//resultTemp =MessageTemplate.MatchConversationId("calcul-requet");

					System.out.println((String) args[0]+x);
					//System.out.println((String) args[1]+x);
					
				}
			}
			);
			
			/*addBehaviour(new TickerBehaviour(this, 500) {

				int x = 0;

				@Override
				protected void onTick() {
					x++;

					System.out.println((String) args[1]+x);
				}
			});*/

		} else {
			// Make the agent terminate immediately
			System.out.println("No book title specified");
			doDelete();
		}

	}

	// Put agent clean-up operations here
	protected void takeDown() {
		// Printout a dismissal message
		System.out.println("oooooooooooooooooooooooooooooooooooooo"+lesAgents.length);
		System.out.println("Buyer-agent " + getAID().getName() + " terminating.");
	}
}