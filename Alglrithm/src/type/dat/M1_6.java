/*
���ݸ� ���� 20230315

���ĺ� ����� ���ݸ����� ���� �޾ҽ��ϴ�.
� ���ĺ����� �����ϴ��� ������� �����ϰ��� �մϴ�.
�빮�ڷ� ������ �幮�� �� ���ڿ��� �Է� �޽��ϴ�.
�� ���ڿ����� �ߺ��� �����ϰ� �����ϴ� ���ĺ��� ������� �����Ͽ� ����� �ּ���.

�Է�
���ĺ� ����� ���ݸ����� ǥ���� ���ڿ� S�� �Էµ˴ϴ�.
S�� ���̴� �ִ� 100,000�Դϴ�.

���
�����ϴ� ���ĺ��� �ߺ� ���� ������� ����մϴ�.

�Է� ���� 1 
FDAAAAFDA

��� ���� 1
ADF

�Է� ���� 2 
BBQCOOKIE

��� ���� 2
BCEIKOQ

 */
package type.dat;

import java.io.*;
import java.util.*; 

public class M1_6 {

	  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	  static StringTokenizer st;
	  static String str="";
	  static int dat[] =new int[128];


	    public static void main(String[] args) throws IOException{
	    	str=br.readLine();
	    	
	    	for(int i=0;i<str.length();i++) {
	    	
	    		char c=str.charAt(i);
	    		dat[c]=1;
	    		
	    	
	    	
	    	}
	    	
    	for(int i='A';i<='Z';i++) {
    		if(dat[i]==1) {
    			System.out.println((char)i);
    		}
    	}
	    	
	    	

	    }
	}