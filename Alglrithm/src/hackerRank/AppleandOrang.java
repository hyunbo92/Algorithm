/*
 ���� �������� ���, ������ ���� ���� 
 */
package hackerRank;

import java.io.IOException;
import java.util.Scanner;

public class AppleandOrang {



    public static void main(String[] args) throws IOException {
    	
    	Scanner sc=new Scanner(System.in);
    
    	int s=sc.nextInt();//�� ����
    	int t=sc.nextInt();//�� ������
    	int a=sc.nextInt();//������� (����)
    	int b=sc.nextInt();//���������� (������)
    	int m=sc.nextInt();//�������
    	int n=sc.nextInt();//������ ����
    	
    	int countApple=0;
    	int countOrange=0;
    	
    	int[] x=new int[m];//��� �����κ��� ������ ����� �Ÿ�
    	int[] y=new int[n];//�����������κ��� ������ �������� �Ÿ�
    	
    	
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
