/*��Ģ�� ���� ���� round Ȥ�� ����*/
package hackerRank;

import java.io.IOException;
import java.util.Scanner;

public class GradingStudents {



    public static void main(String[] args) throws IOException {
    	
    	Scanner sc=new Scanner(System.in);
    
    	int n=sc.nextInt();
    	
    	int grades[]=new int[n];
    	
    	for(int i=0;i<n;i++) {
    		grades[i]=sc.nextInt();
    	}
    	
    	
    		
    	for(int i=0;i<n;i++) {
    		if(grades[i]>=38) {
    		
	    		if(grades[i]%5==3) {
	    			grades[i]+=2;
	    		}else if (grades[i]%5==4) {
	    			grades[i]+=1;
	    		}else {
	    			//������ 1,2,3 �ǰ��� �״�� 
	    		}
    		}else {
    			//38 ������ ���� �״��
    		}
    		
    		
    	System.out.println(grades[i]);	
    	}
    	
    	
    	
    	
    }

}
