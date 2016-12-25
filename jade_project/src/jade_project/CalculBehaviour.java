package jade_project;

import java.util.ArrayList;

import jade.core.behaviours.Behaviour;

public class CalculBehaviour extends Behaviour {

	jade.lang.acl.ACLMessage envoie = new jade.lang.acl.ACLMessage(jade.lang.acl.ACLMessage.INFORM);
	boolean nreceive = false;
	boolean dreceive = false;
	boolean fin = false;
	int isImpaire = 0;
	int n;

	ArrayList<Integer> getDomaine(String message) {

		ArrayList<Integer> domaine = new ArrayList<Integer>();
		String borneS = "";
		int borne;

		for (int i = 0; i < message.length(); i++) {

			if (message.charAt(i) == ' ') {

				borne = Integer.parseInt(borneS);
				domaine.add(borne);
				borneS = "";

			} else {

				borneS = borneS + message.charAt(i);

				if (i == message.length() - 1) {

					borne = Integer.parseInt(borneS);
					domaine.add(borne);
				}

			}

		}

		return domaine;

	}

	boolean testDomaine(int n, ArrayList<Integer> domaine) {

		int bInf = domaine.get(0);
		int bSup = domaine.get(1);
		int i = bInf;
		boolean x = false;

		while (i <= bSup) {

			if (n % i == 0) {
				System.out.println(myAgent.getAID().getName() + " : il existe un diviseur dans ce domaine et c'est " + i);
				x = true;
			}

			else {

				if (i == bSup && !x)
					System.out.println(myAgent.getAID().getName() + " : pas de diviseur");
			}

			i++;
		}
		System.out.println('\n');
		if (x)
			return true;
		else
			return false;

	}

	public void action() {

		jade.lang.acl.ACLMessage reception = myAgent.receive();
		jade.lang.acl.ACLMessage envoie = new jade.lang.acl.ACLMessage(jade.lang.acl.ACLMessage.INFORM);

		if (reception != null && !nreceive) {

			n = Integer.parseInt(reception.getContent());
			envoie.addReceiver(reception.getSender());
			envoie.setContent("j'ai reçu le chiffre de teste");
			myAgent.send(envoie);
			nreceive = true;
			reception = null;
			System.out.println(myAgent.getAID().getName() + " : le chiffre du test est reçu est c'est " + n);
			System.out.println('\n');

		}

		if (reception != null && !dreceive) {

			if (reception.getContent().equals("fin")) {

				
				dreceive = true;

			}

			else {

				System.out.println(myAgent.getAID().getName() + " : le domaine est reçu est c'est "+ getDomaine(reception.getContent()));
				envoie.addReceiver(reception.getSender());
				if (testDomaine(n, getDomaine(reception.getContent()))) {
					isImpaire = 1;
				}
				if (isImpaire == 0) {
					//envoie.addReceiver(reception.getSender());
					envoie.setContent(n + " est impaire");
					//myAgent.send(envoie);
					
					
				} else {
					//envoie.addReceiver(reception.getSender());
					envoie.setContent(n + " est paire");
					//myAgent.send(envoie);
					
				}
				
				myAgent.send(envoie);
				reception = null;
			}
			// fin = true;

		}

		if (reception == null) {

			// System.out.println("calcul block");
			block();

		}

	}

	@Override
	public boolean done() {

		// System.out.println("dereceive " + dreceive);
		return dreceive;
	}

}
