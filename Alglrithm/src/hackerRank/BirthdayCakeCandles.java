/*주어진 배열에서 가장 높은 숫자의 갯수 세기*/
package hackerRank;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class BirthdayCakeCandles {



    public static void main(String[] args) throws IOException {


    	Scanner sc=new Scanner(System.in);
    	
    	int n=sc.nextInt();
    	int[] candle=new int[n];
    	
    	for(int i=0;i<n;i++) {
    		
    		candle[i]=sc.nextInt();
    		
    	}    	
    	
    	 Arrays.sort(candle);
    	 
    	 int max=candle[candle.length-1];
    	 int answer=0;
    	 
    	 
    	for(int i=0;i<n;i++) {
    		
    		if( max==candle[i]) {
    			answer++;
    		}
    		
    	}
    	System.out.println(answer);
    	 
 
    	 
    }

}
