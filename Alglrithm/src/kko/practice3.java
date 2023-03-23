/*
7 1 2 3 4 5 6 7
8 1 2 3 5 8 13 21 34
0  
 */


package kko;

import java.util.Scanner;

public class practice3 {
	static int N;
	static int[] lotto;
	static int[] result;

	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		while(true) {
			N=sc.nextInt();
			if(N==0)break;
			lotto=new int[N+1];
			result=new int[N+1];
			
			for(int i=1;i<=N;i++) {
				lotto[i]=sc.nextInt();
			}
			
			DFS(1,1);
			System.out.println();
			
		}
	}
	
	
	public static void DFS(int x, int depth) {
		if(depth==7) {
			for(int i=1;i<=N;i++) {
				if(result[i]==1) {
					System.out.println(lotto[i]+" ");
				}
				System.out.println();
			}
		}
		
		for(int i=x;i<=N;i++) {
			result[i]=1;
			DFS(i+1,depth+1);
			result[i]=0;
		}
		
		
	}
	
	
	
	
	
	
}