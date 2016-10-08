package chap16;

import java.util.ArrayList;
import java.util.Scanner;

public class PermutationExec {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Runtime runtime = Runtime.getRuntime();
		/*
		 * System.out.println("max memory: " + runtime.maxMemory() / 1024);
		 * System.out.println("total memory: " + runtime.totalMemory() / 1024);
		 * System.out.println("free memory: " + runtime.freeMemory() / 1024);
		 * System.out.println("Used Memory:" + (runtime.totalMemory() -
		 * runtime.freeMemory()) / 1024);
		 */

		/*StringBuffer mot = new StringBuffer();
		
		Scanner input = new Scanner(System.in);

		mot.append(input.nextLine());*/
		Permutation p = new Permutation();
		ArrayList<StringBuffer> t = new ArrayList<StringBuffer>();
		ArrayList<StringBuffer> perm = new ArrayList<StringBuffer>();
		/*if (mot.length() == 1) {
			System.out.println("les permutations possibles :");
			t.add(mot);
			System.out.println(t);
		} else {
			t = p.liaison(mot);
			System.out.println("les permutations possibles :");
			System.out.println(t);
			System.out.println(t.size());
		}*/
		
		perm=p.permutation(new StringBuffer("1234"),t);
		System.out.println(perm);
		System.out.println(perm.size());
		
		//System.out.println(p.move(new StringBuffer("24"), 1, 0));

	}

}
