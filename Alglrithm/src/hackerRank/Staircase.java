/*주어진 정수크기 만큼 # 탑 프린트 하기*/
package hackerRank;

import java.io.IOException;
import java.util.Scanner;

public class Staircase {



    public static void main(String[] args) throws IOException {
    	
    	Scanner sc=new Scanner(System.in);
    
    	
    	int n=sc.nextInt();
    	int check=0;
    	
    	for(int j=n;j>0;j--) {//6 5 4 3 2 1
    	
    	for(int i=1;i<=n;i++) { // 1 2 3 4 5 6
    		
    		check=j;
    		String answer="";
    		
    		if(check<=i) {
    			answer=answer+"#";
    		}else {
    			answer=answer+" ";
    		}
    		
    		System.out.print(answer);
    	}
    	System.out.println();
    	}
    	
    }

}
