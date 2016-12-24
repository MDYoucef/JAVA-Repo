package ri_2;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import weka.attributeSelection.*;
import weka.filters.supervised.attribute.AttributeSelection;
import weka.core.Attribute;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.TextDirectoryLoader;
import weka.core.converters.ArffLoader.ArffReader;
import weka.core.stemmers.LovinsStemmer;
import weka.core.tokenizers.WordTokenizer;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class Run {

	Instances chargement(String path) throws Exception {

		BufferedReader reader = new BufferedReader(new FileReader(path));
		ArffReader arff = new ArffReader(reader);
		Instances data = arff.getData();
		data.setClassIndex(0);
		/*AttributeSelection as=new AttributeSelection();
		CfsSubsetEval e=new CfsSubsetEval();
		GreedyStepwise s=new GreedyStepwise();
		
		//s.setSearchBackwards(true);
		
		as.setEvaluator(e);
		as.setSearch(s);
		
		as.setInputFormat(data);
		

		Instances i3= Filter.useFilter(data, as);
		System.out.println("nombre attribut data = "+data.numAttributes());*/
		return data;
	}

	Instances moteur(String entree,String del,String sw, String sortie) throws Exception {

		TextDirectoryLoader txl = new TextDirectoryLoader();
		File base = new File(entree);
		txl.setDirectory(base);
		Instances i = txl.getDataSet();
		txl.setOutputFilename(true);

		// System.out.println("contenu" +i.instance(0).stringValue(0));

		StringToWordVector filtre = new StringToWordVector();

		LovinsStemmer les_stemmer = new LovinsStemmer();
		filtre.setStemmer(les_stemmer);// lemmatisation

		LesStopWord les_stopword = new LesStopWord(sw);
		filtre.setStopwordsHandler(les_stopword);// elimination des mots vides

		WordTokenizer wt = new WordTokenizer();
		LesDelimiter delimiter=new LesDelimiter();
		String delimiters = " "+delimiter.mesdelimiter(del);
		wt.setDelimiters(delimiters);
		filtre.setTokenizer(wt);// les delimiters

		filtre.setOutputWordCounts(true);
		filtre.setInputFormat(i);

		Instances i2 = Filter.useFilter(i, filtre);
		System.out.println("nombre attribut i2= "+i2.numAttributes());
		
		/*OneRAttributeEval eva=new OneRAttributeEval();
		Map<Attribute, Double> infogainscores = new HashMap<Attribute, Double>();
		for (int x = 1; x < i2.numAttributes(); x++) {
		    Attribute t_attr = i2.attribute(x);
		    double infogain  = eva.evaluateAttribute(x);
		    System.out.println("eval= "+infogain);
		    infogainscores.put(t_attr, infogain);
		}*/
		
		i.renameAttribute(0, "contenu");
		
		i2.insertAttributeAt(i.attribute(0), i2.numAttributes());

		//i2.del
		for (int x = 0; x < i2.numInstances(); x++) {

			// last.add(i.instance(x).stringValue(0));
			i2.instance(x).setValue(i2.numAttributes() - 1, i.instance(x).stringValue(0));
		}

		ArffSaver saver2 = new ArffSaver();
		saver2.setInstances(i2);
		saver2.setFile(new File(sortie));
		saver2.writeBatch();
		
		//System.out.println("le corpus = "+i2);
		//System.out.println("les delimiter = "+delimiters);
		
		return i2;
	}

	ArrayList<Instance_Ps> PS(Instances base, Instances requette) throws Exception {

		ArrayList<Instance_Ps> ps = new ArrayList<Instance_Ps>();
		PrintWriter out = new PrintWriter("/home/skyolia/Documents/tp/res.txt");
		PrintWriter out2 = new PrintWriter("/home/skyolia/Documents/tp/fin.txt");
		double v = 0;

		maxloop: for (int i = 0; i < base.numInstances(); i++) {
			Instance_Ps ips = new Instance_Ps();
			minloop: for (int k = 1; k < requette.numAttributes() - 1; k++) {
				int pos = 0;
				String c = requette.attribute(k).name();
				try {
					pos = base.attribute(c).index();
				} catch (Exception e1) {
					requette.deleteAttributeAt(k);
					if(requette.numAttributes()==2) {
						String sortie="aucun resultat trouvé";
						out2.println(sortie);
						out2.close();
						if (Desktop.isDesktopSupported()) {
							try {
								File myFile = new File("/home/skyolia/Documents/tp/fin.txt");
								//File myFile2 = new File("/home/skyolia/Documents/tp/test.arff");
								Desktop.getDesktop().open(myFile);
								//Desktop.getDesktop().open(myFile2);
							} catch (IOException ex) {
								// no application registered for PDFs
							}
						}
						System.exit(0);
					}
					else continue minloop;
				}
				// System.out.println("instance courante "+base.instance(i));
				// System.out.println("instance courante "+i);
				// System.out.println("valeur attribut "+c+"dans l'instance
				// "+i+" = "+base.instance(i).value(pos));

				v = v + base.instance(i).value(pos) * requette.instance(0).value(k);

			}
			ips.setInstance(base.instance(i));
			ips.setPs(v);
			String chaine = "le ps= " + v + "         " + base.instance(i).toString();
			out.println(chaine);
			ps.add(ips);
			v = 0;
		}
		Collections.sort(ps, new Comparator<Instance_Ps>() {
			public int compare(Instance_Ps n1, Instance_Ps n2) {

				return Double.compare(n1.ps, n2.ps);
			}
		});
		Collections.reverse(ps);
		for (int y = 0; y < ps.size(); y++) {
			if (ps.get(y).ps == 0) {
				ps.remove(y);
				y = 0;
			}
		}

		for (int y = 0; y < ps.size(); y++) {
			String mot = ps.get(y).i.stringValue(base.numAttributes() - 1) + "      le ps " + ps.get(y).ps
					+ "       dans le dossier : " + ps.get(y).i.value(0);
			String sep = "\n" + "\n" + "\n";
			// System.out.println("le contenu
			// "+ps.get(y).i.stringValue(base.numAttributes()-1));
			// System.out.println("le ps "+ps.get(y).ps);
			out2.println(mot);
			out2.println(sep);
		}
		out.close();
		out2.close();

		if (Desktop.isDesktopSupported()) {
			try {
				File myFile = new File("/home/skyolia/Documents/tp/fin.txt");
				//File myFile2 = new File("/home/skyolia/Documents/tp/test.arff");
				Desktop.getDesktop().open(myFile);
				//Desktop.getDesktop().open(myFile2);
			} catch (IOException ex) {
				// no application registered for PDFs
			}
		}
		System.out.println(ps.size());
		return ps;
	}

}
