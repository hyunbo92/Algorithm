/*�־��� �迭���� ������ ����, ����ǰ���, 0 �ǰ����� ������ �����ϴ� ����*/
package hackerRank;

import java.io.IOException;
import java.util.Scanner;

public class PlusMinus {



    public static void main(String[] args) throws IOException {
    	
    	Scanner sc=new Scanner(System.in);
    	
    	int total=sc.nextInt();   	
    	int arr[]=new int[total];
    	
    	float plus=0;
    	float minus=0;
    	float zero=0;
    	
    	for(int i=0;i<total;i++) {
    		arr[i]=sc.nextInt();
    	}
    	
    	for(int i=0;i<total;i++) {
    		
    		if (arr[i]<0) {
    			minus++;
    		}else if(arr[i]>0) {
    			plus++;
    		}else {
    			zero++;
    		}
    			
    	}
    	
    	System.out.println(plus/total);
    	System.out.println(minus/total);
    	System.out.println(zero/total);
    	
    	
    	
    }

}
