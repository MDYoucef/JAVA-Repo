package chap16;

import java.util.Scanner;

public class Ex_1 {
	
	static double fibonacci(int n) {
		if(n==0) return 1;
		else if (n==1) return 1;
		else return fibonacci(n-1)+fibonacci(n-2);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner lire=new Scanner(System.in);
		int n=lire.nextInt();
		double m=0;
		double fib[]={0,1};
		
		for(int i=1;i<n;i++) {
			m=fib[0]+fib[1];
			fib[0]=fib[1];
			fib[1]=m;
			System.out.println("fib("+i+")= "+m);
			
		}
			
	}

}
