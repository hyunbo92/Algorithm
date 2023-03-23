/*
 집에 떨어지는 사과, 오랜지 개수 세기 
 */
package hackerRank;

import java.io.IOException;
import java.util.Scanner;

public class AppleandOrang {



    public static void main(String[] args) throws IOException {
    	
    	Scanner sc=new Scanner(System.in);
    
    	int s=sc.nextInt();//집 왼쪽
    	int t=sc.nextInt();//집 오른쪽
    	int a=sc.nextInt();//사과나무 (왼쪽)
    	int b=sc.nextInt();//오렌지나무 (오른쪽)
    	int m=sc.nextInt();//사과개수
    	int n=sc.nextInt();//오렌지 개수
    	
    	int countApple=0;
    	int countOrange=0;
    	
    	int[] x=new int[m];//사과 나무로부터 떨어진 사과의 거리
    	int[] y=new int[n];//오렌지나무로부터 떨어진 오렌지의 거리
    	
    	
    	for(int i=0;i<m;i++) {
    		x[i]=sc.nextInt();
    		x[i]+=a;
    		if(x[i]>=s&&x[i]<=t) {
    			countApple++;
    		}
    	}
    	for(int i=0;i<n;i++) {
    		y[i]=sc.nextInt();
    		y[i]+=b;
    		if(y[i]>=s&&y[i]<=t) {
    			countOrange++;
    		}
    	}
    	
    	System.out.println(countApple);
    	System.out.println(countOrange);
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    }

}
