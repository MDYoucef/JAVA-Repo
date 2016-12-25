package jade_project;

import java.util.ArrayList;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.MessageTemplate;

public class FederateurBehaviour extends Behaviour {

	private AID[] agents;
	int n;
	int j = 0;
	boolean nsend = false;
	boolean dsend = false;
	boolean fin = false;
	ArrayList<Integer> domaines;
	int i = 0;

	FederateurBehaviour(int n, ArrayList<Integer> domaines, AID[] agents) {

		this.n = n;
		this.domaines = domaines;
		this.agents = agents;

	}

	public void action() {
		
		jade.lang.acl.ACLMessage reception = myAgent.receive();
		jade.lang.acl.ACLMessage d = new jade.lang.acl.ACLMessage(jade.lang.acl.ACLMessage.INFORM);

		if (reception != null && !nsend) {

			System.out.println(myAgent.getAID().getName()+" : envoie du chiffre de teste");
			d.addReceiver(reception.getSender());
			d.setContent("" + n);
			myAgent.send(d);
			nsend = true;
			reception = null;
			// block();
		}

		if (reception != null && !dsend) {

			if (domaines.size() == 1) {

				d.addReceiver(reception.getSender());
				d.setContent("fin");
				myAgent.send(d);
				block();
				System.out.println(myAgent.getAID().getName()+" : "+reception.getContent());
				fin=true;
				//reception = null;

			} else {
				System.out.println(myAgent.getAID().getName()+" : envoie du domaine " + "["+domaines.get(0) + ", " + domaines.get(1)+"]");
				d.addReceiver(reception.getSender());
				d.setContent(domaines.get(0) + " " + domaines.get(1));

				myAgent.send(d);
				reception = null;
				domaines.remove(0);

				j++;

			}

		}

		if (reception == null) {

			//System.out.println("federateur block");
			block();

		}

	}

	@Override
	public boolean done() {

		//System.out.println("j= " + j);
		return fin;
	}

}
