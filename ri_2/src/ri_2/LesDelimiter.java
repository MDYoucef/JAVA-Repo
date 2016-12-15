package ri_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LesDelimiter {

	String delimiters="";

	String mesdelimiter(String del) throws FileNotFoundException {
		
		//del="/home/skyolia/Documents/tp/delimiteurs";
		Scanner s = new Scanner(new File(del));
		while (s.hasNext()) {
			delimiters = delimiters + s.next();
		}
		s.close();
		return delimiters;

	}

}
