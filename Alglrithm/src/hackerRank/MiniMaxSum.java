/* �־��� 5���� ������ 4������ min, max �� ���ϱ� */
package hackerRank;

import java.io.IOException;
import java.util.Scanner;

public class MiniMaxSum {



    public static void main(String[] args) throws IOException {
    	
    	Scanner sc=new Scanner(System.in);
    	
       
    	int[] arr=new int[5];
    	
    	for(int i=0;i<5;i++) {
    		arr[i]=sc.nextInt();
    	}
    	
    	int min=999999999;
    	int max=0;
    	
    	for(int i=0;i<5;i++) {

    		if(arr[i]<min)
    			min=arr[i];
    		
    		if(arr[i]>max)
    			max=arr[i];
    	}	
    	
    	long sum=0;
    	
    	for(int i=0;i<5;i++) {
    		sum+=arr[i];
    	}
    	
    	System.out.println((sum-max)+" "+(sum-min));
    	
    	
    	
    	
    	
    	
    	
    	
    }

}
