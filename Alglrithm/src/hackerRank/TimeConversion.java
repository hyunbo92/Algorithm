/*12 �ð��� �ð��� �־����� 24 �ð����� �ٲٱ�*/
package hackerRank;



//substring, split -->���ڿ� �ڸ���
//����<->���� �����ϱ�

import java.io.IOException;
import java.util.Scanner;

public class TimeConversion {



    public static void main(String[] args) throws IOException {


    	Scanner sc=new Scanner(System.in);
    	
    	String time=sc.nextLine();
    	
//    	System.out.println(time);
    	
    	
    	String gubun=time.substring(8,10);
//    	System.out.println(gubun);
    	
    	
    	int si=0;
    	
    	if(gubun.equals("PM")) {
    		if(time.substring(0,2).equals("12")){ 			
    			si=12;
    		}else {
    			si=Integer.parseInt(time.substring(0,2))+12;
    		}	
    	}else if (gubun.equals("AM")) {
    		if(time.substring(0,2).equals("12")){ 			
    			si=0;
    		}else {
    			si=Integer.parseInt(time.substring(0,2));
    		}	
    	}
    	
    	
    	if(gubun.equals("AM")) {
    		time="0"+si+time.substring(2,8);
    	}else {
    		time=Integer.toString(si)+time.substring(2,8);
    	}
    	
    	System.out.println(time);
    	
    	
    	 
    }

}
