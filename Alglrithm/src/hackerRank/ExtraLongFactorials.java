package hackerRank;


//팩토리얼 나타내기 -->매우 큰 숫자라 BigInteger 사용

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