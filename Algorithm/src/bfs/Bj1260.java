package bfs;

import java.util.*;
public class Bj1260{
	
	static int Nv,Ne;
	static int[][] ad;
	static boolean[] visit;
	
	
	public static void main(String[] args) {
	
		Scanner sc=new Scanner(System.in);
		Nv=sc.nextInt();
		Ne=sc.nextInt();
		ad=new int[Nv][Nv];
		visit=new boolean[Nv];
		
		for(int i=0;i<Ne;i++) {
			int t1=sc.nextInt();
			int t2=sc.nextInt();
			
			ad[t1][t2]=ad[t2][t1]=1;
			
		}
		
		dfs(1);
		
	}
	
	
	public static void dfs(int i) {
		
		visit[i]=true;
		System.out.println(i+ " ");
		
		for(int j=1;j<=Nv;j++) {
			if(ad[i][j]==1&&visit[j]==false) {
				dfs(j);
			}
		}
		
		
		
	}
	
}