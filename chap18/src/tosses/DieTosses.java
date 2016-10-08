package tosses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import chap16.Permutation;

public class DieTosses {

	int n;

	ArrayList<Integer> play(int m, int max) {

		ArrayList<Integer> dt = new ArrayList<Integer>();
		Random r = new Random();
		for (int i = 0; i < m; i++) {

			dt.add(r.nextInt(max));
		}

		return dt;

	}

	int occurrence(StringBuffer mot, char c) {
		int s = 0;
		for (int i = 0; i < mot.length(); i++) {

			if (mot.charAt(i) == c)
				s++;

		}
		return s;
	}

	StringBuffer substitution(StringBuffer phrase, StringBuffer nouv, StringBuffer vieu) {
		int x = 0;
		int y = 0;
		int i = 0;
		int j = 0;
		big: while (i < phrase.length()) {

			if (String.valueOf(phrase.charAt(i)).equals(String.valueOf(vieu.charAt(j)))) {
				x = i;

				while (String.valueOf(phrase.charAt(i)).equals(String.valueOf(vieu.charAt(j)))) {

					j++;
					i++;
					if (j == vieu.length()) {
						y = i;

						phrase.replace(x, y, nouv.toString());

						break big;

					}

				}
				j = 0;
			}
			i++;

		}

		return phrase;
	}

	StringBuffer nettoyage(StringBuffer chaine) {

		for (int i = 0; i < chaine.length(); i++) {

			if (String.valueOf(chaine.charAt(i)).equals(",") || String.valueOf(chaine.charAt(i)).equals(" ")) {
				chaine.deleteCharAt(i);
				i--;
			} else
				continue;

		}
		return chaine;

	}

	StringBuffer run(ArrayList<Integer> dt) {

		StringBuffer chaine = nettoyage(new StringBuffer(dt.toString()));
		Permutation p = new Permutation();

		boolean b = true;
		int i = 1;
		while (i < chaine.length()) {

			if (String.valueOf(chaine.charAt(i - 1)).equals(String.valueOf(chaine.charAt(i))) && b) {
				chaine.append("(");
				chaine = p.move(chaine, chaine.length() - 1, i - 1);

				b = false;

				i++;
			}
			if (!b && !String.valueOf(chaine.charAt(i - 1)).equals(String.valueOf(chaine.charAt(i)))) {
				b = true;
				chaine.append(")");
				chaine = p.move(chaine, chaine.length() - 1, i);

			}

			i++;

		}
		return denettoyage(chaine);
	}
	
	StringBuffer denettoyage(StringBuffer chaine) {
		
		Permutation p = new Permutation();
		for (int j = 1; j < chaine.length(); j++) {

			if (String.valueOf(chaine.charAt(j)).equals(")"))
				continue;
			if (!String.valueOf(chaine.charAt(j - 1)).equals("(") || String.valueOf(chaine.charAt(j - 1)).equals(")")) {

				chaine.append(" ");
				chaine = p.move(chaine, chaine.length() - 1, j);
				j++;

			}

		}
		return chaine;
	}


	StringBuffer longuestRun(StringBuffer therun) {

		Permutation p = new Permutation();
		therun = nettoyage(therun);

		StringBuffer mini = new StringBuffer();

		StringBuffer anc = new StringBuffer();
		ArrayList<StringBuffer> conteneur = new ArrayList<StringBuffer>();

		int i = 1;

		while (occurrence(therun, '(') > 1) {

			if (String.valueOf(therun.charAt(i - 1)).equals("(")) {

				while (!String.valueOf(therun.charAt(i)).equals(")")) {
					mini = mini.append(therun.charAt(i));
					// j++;
					i++;
				}

				conteneur.add(mini);
				mini = new StringBuffer("");

				if (conteneur.size() >= 2) {
					Collections.sort(conteneur, new Comparator<StringBuffer>() {
						public int compare(StringBuffer mini1, StringBuffer mini2) {

							return Integer.compare(mini1.length(), mini2.length());
						}
					});

					anc.append(conteneur.get(0));

					anc.append("(");

					anc = p.move(anc, anc.length() - 1, 0);
					anc = anc.append(")");

					substitution(therun, conteneur.get(0), anc);
					conteneur.remove(conteneur.get(0));
					anc.setLength(0);
					i--;
				}

			}
			i++;
		}
		return denettoyage(therun);

	}

}
