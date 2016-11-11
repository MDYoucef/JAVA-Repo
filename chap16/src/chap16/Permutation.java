package chap16;

import java.util.ArrayList;

public class Permutation {
	StringBuffer mot;

	public StringBuffer move(StringBuffer mot, int depart, int arrivee) {

		StringBuffer mot2 = new StringBuffer();

		int i = 0;

		while (i != mot.length()) {
			if (arrivee > depart) {
				if (i < depart) {
					mot2.append(mot.charAt(i));

				} else {
					if (i < arrivee)
						mot2.append(mot.charAt(i + 1));
					else if (i == arrivee)
						mot2.append(mot.charAt(depart));
					else if (i > arrivee)
						mot2.append(mot.charAt(i));

				}
			} else {
				if (i < arrivee) {
					mot2.append(mot.charAt(i));

				} else {
					if (i > depart)
						mot2.append(mot.charAt(i));
					else if (i == arrivee)
						mot2.append(mot.charAt(depart));
					else if (i <= depart)
						mot2.append(mot.charAt(i - 1));

				}
			}
			i++;

		}
		return mot2;

	}

	ArrayList<StringBuffer> supDoublon(ArrayList<StringBuffer> t) {

		for (int x = 0; x < t.size(); x++) {
			min: for (int y = 0; y < t.size(); y++) {

				if (x == y) {
					continue min;
				}

				else if (t.get(x).toString().equals(t.get(y).toString())) {

					t.remove(y);

				}

			}
		}

		return t;

	}

	ArrayList<StringBuffer> combinaison(StringBuffer m) {

		ArrayList<StringBuffer> t = new ArrayList<StringBuffer>();

		StringBuffer chaine = new StringBuffer();

		for (int i = 0; i < m.length(); i++) {
			chaine = new StringBuffer(String.valueOf(m.charAt(i)));
			for (int j = 0; j < m.length(); j++) {

				if (i == j)
					continue;
				else {
					chaine = chaine.append(new StringBuffer(String.valueOf(m.charAt(j))));
					t.add(chaine);
					chaine = new StringBuffer(String.valueOf(m.charAt(i)));
				}
			}

		}

		if (m.length() % 2 != 0) {
			ArrayList<StringBuffer> imp = new ArrayList<StringBuffer>();
			for (int i = 0; i < m.length(); i++) {
				imp.add(new StringBuffer(String.valueOf(m.charAt(i))));

			}
			t.addAll(imp);
		}

		return t;
	}

	ArrayList<StringBuffer> tabDePermutation(StringBuffer m, StringBuffer sc) {

		ArrayList<StringBuffer> permut = new ArrayList<StringBuffer>();

		ArrayList<StringBuffer> t = combinaison(m);

		loop1: for (int j = 0; j < t.size(); j++) {

			if (sc.length() % 2 != 0 && t.get(j).length() % 2 != 0)
				continue loop1;

			for (int x = 0; x < sc.length(); x++) {

				loop3: for (int y = 0; y < t.get(j).length(); y++) {

					if (sc.charAt(x) != t.get(j).charAt(y)) {
						if (x == sc.length() - 1 && y == t.get(j).length() - 1) {
							permut.add(t.get(j));
						} else
							continue loop3;
					} else
						continue loop1;

				}
			}

		}

		return permut;
	}

	ArrayList<StringBuffer> liaison(StringBuffer mot) {

		ArrayList<StringBuffer> combi = combinaison(mot);
		ArrayList<StringBuffer> ultime = new ArrayList<StringBuffer>();
		max: for (int i = 0; i < combi.size(); i++) {
			StringBuffer tmp = combi.get(i);
			String chaine;
			ArrayList<StringBuffer> res = new ArrayList<StringBuffer>();
			big: while (mot.length() >= tmp.length()) {

				ArrayList<StringBuffer> diff = tabDePermutation(mot, tmp);

				if (diff.isEmpty()) {
					ultime.addAll(combi);

					break max;
				} else {
					for (int j = 0; j < diff.size(); j++) {

						chaine = tmp.toString() + diff.get(j).toString();
						res.add(new StringBuffer(chaine));

						if (j == diff.size() - 1) {

							tmp = res.get(0);
							if (res.get(0).length() != mot.length()) {

								res.remove(0);

							} else
								break big;

						}
					}

				}
			}
			ultime.addAll(res);

		}
		return supDoublon(ultime);
	}

	ArrayList<StringBuffer> permutation(StringBuffer mot, ArrayList<StringBuffer> perm) {

		StringBuffer mot2 = new StringBuffer();
		mot2.append(mot);
		if (mot.length() == 2) {
			perm.add(mot);
			perm.add(move(mot, 1, 0));
			
			return perm;
		} else {
			for (int i = 0; i < mot.length(); i++) {

				mot = move(mot, i, 0);
				
				perm = permutation(new StringBuffer(mot.substring(1)), perm);
				
				for (int j = 0; j < perm.size(); j++) {
					if (perm.get(j).length() < mot.length()) {
						
						perm.set(j, new StringBuffer(String.valueOf(mot.charAt(0)))
								.append(perm.get(j)));

						

						

						if (j != perm.size() - 1)
							continue;
					}
						
					
				}
			}

			return perm;
		}
	}
}
