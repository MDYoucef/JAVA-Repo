package ri_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import weka.core.stopwords.StopwordsHandler;

public class LesStopWord implements StopwordsHandler{

	
	ArrayList<String>mes_stop_word=new ArrayList<String>();
	
	LesStopWord (String sw) throws FileNotFoundException {
		
		Scanner s = new Scanner(new File(sw));
		while (s.hasNext()){
			mes_stop_word.add(s.next());
		}
		s.close();
		int l=mes_stop_word.size();
		for(int i=0;i<l;i++) {
			
			String chaine=mes_stop_word.get(i).substring(0, 1).toUpperCase();
			String nameCapitalized = chaine + mes_stop_word.get(i).substring(1);
			mes_stop_word.add(nameCapitalized);
		}
		System.out.println("les mots vide = "+sw);
	}
	
	public boolean isStopword(String ex) {
		// TODO Auto-generated method stub
		return mes_stop_word.contains(ex);
	}

}
