package kko;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		int[] p= new int[100]; // �Ҽ� ����
		int pn=0; //�Ҽ��� ����
		boolean[] c=new boolean[101]; //���������� true
		int n=100; //100���� �Ҽ�
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
