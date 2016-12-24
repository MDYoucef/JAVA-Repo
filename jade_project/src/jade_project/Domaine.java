package jade_project;

import java.util.ArrayList;

public class Domaine {

	ArrayList<Integer> lesDomaines(int n, int m) {

		int save = (int) n / 2;
		int n1 = (int) n / 2;
		int sous = 0;
		ArrayList<Integer> borne = new ArrayList<Integer>();
		borne.add(2);
		sous = (int) (n1/ m);
		for (int i = 0; i < m; i++) {

			if (i == m - 1) {
				
				if (save <= borne.get(i - 1))
					borne.add(borne.get(i - 1) + 1);
				
				else
					borne.add(save);
			}

			else
				borne.add(borne.get(i) + sous);

		}
		//borne.set(0, 2);
		return borne;

	}

}
