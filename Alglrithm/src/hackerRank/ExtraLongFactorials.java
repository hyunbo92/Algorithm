package hackerRank;


//���丮�� ��Ÿ���� -->�ſ� ū ���ڶ� BigInteger ���

import java.math.BigInteger;
import java.util.Scanner;

public class ExtraLongFactorials {
	
	
	public static void main(String[] args) {
		
	 Scanner sc=new Scanner(System.in);
	 
	 int n =sc.nextInt();
	 
	 
	 
	sc.close();
	           


	
	 BigInteger answer =BigInteger.ONE;
	 
	 for(int i=1;i<=n;i++) {
		 
		 answer=answer.multiply(BigInteger.valueOf(i));

	 }
	 
	 System.out.println(answer);
	 
	}
	
}