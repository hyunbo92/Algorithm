package kko;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		int[] p= new int[100]; // 소수 저장
		int pn=0; //소수의 개수
		boolean[] c=new boolean[101]; //지워졌으면 true
		int n=100; //100까지 소수
		for(int i=2;i<=n;i++) {
			if(c[i]==false) {
				p[pn++] = i;
				for(int j= i*i;j<=n; j+=i) {
					c[j]=true;
				}
			}
			
		}
		
	}
	

}
