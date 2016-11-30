package ri_2;

import weka.core.Instance;

public class Instance_Ps {

	Instance i;
	double ps;
	String classe;

	void setInstance(Instance i) {
		this.i = i;
	}
	
	void setPs(double ps) {
		this.ps = ps;
	}
	
	void setClass(String classe) {
		this.classe = classe;
	}

}
