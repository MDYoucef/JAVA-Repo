package ri_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LesDelimiter {

	String delimiters;

	String mesdelimiter() throws FileNotFoundException {

		Scanner s = new Scanner(new File("/home/skyolia/Documents/tp/delimiteurs"));
		while (s.hasNext()) {
			delimiters = delimiters + s.next();
		}
		s.close();
		return delimiters;

	}

}
