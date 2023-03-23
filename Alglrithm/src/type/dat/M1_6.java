/*
초콜릿 선물 20230315

알파벳 모양의 초콜릿들을 선물 받았습니다.
어떤 알파벳들이 존재하는지 순서대로 나열하고자 합니다.
대문자로 구성된 장문의 한 문자열을 입력 받습니다.
이 문자열에서 중복을 제외하고 존재하는 알파벳을 순서대로 정렬하여 출력해 주세요.

입력
알파벳 모양의 초콜릿들을 표현한 문자열 S가 입력됩니다.
S의 길이는 최대 100,000입니다.

출력
존재하는 알파벳을 중복 없이 순서대로 출력합니다.

입력 예시 1 
FDAAAAFDA

출력 예시 1
ADF

입력 예시 2 
BBQCOOKIE

출력 예시 2
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