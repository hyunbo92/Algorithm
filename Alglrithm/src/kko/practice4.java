package kko;

import java.util.Scanner;

public class practice4 {
	static int N;
	static int[] lotto;
	static int[] result;
	
	public static void main(String args[]) throws Exception{
		Scanner sc= new Scanner(System.in);
		
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
	
	
	public static void DFS(int x,int depth) {
		
		if(depth==7) {
			for(int i=1;i<=N;i++) {
				if(result[i]==1)
					System.out.print(lotto[i]+" ");
			}
			System.out.println();
		}
		
		for(int i=x;i<=N;i++) {
//			System.out.println("i: "+i+" ~ N: "+N + ", depth: "+depth+" result["+i+"]"+"=1");			
			result[i]=1;
//			System.out.println("DFS("+(i+1)+","+(depth+1)+")");
 			DFS(i+1,depth+1);
//			System.out.println("i:"+i+","+"deppth"+depth);
			result[i]=0;
//			System.out.println("result["+i+"]"+"=0");
			
		}
		
	}
}




