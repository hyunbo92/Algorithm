/*
미확인 신호


"M1nCod1n9"
357.482 KHz의 신호로 미지의 신호가 잡혔습니다.
알파벳 대소문자와 0 ~ 9 까지의 숫자로 이뤄져 있는 신호입니다.
이 미지의 신호는 크기가 M 인 구간에서는 중복된 문자가 존재하지 않는다는 것을 알아냈습니다.
슈베르트 알렉산더 박사는 이 외계 신호를 분석하였고, 다시 송신을 할 메시지를 만들어 냈습니다.
박사가 만든 메시지에서 크기가 M인 구간에 중복된 문자가 있는지 체크해주는 프로그램을 만들어 주세요.

입력
첫줄에 테스트케이스의 수(T)가 입력됩니다.
그 다음줄부터 각 테스트 케이스 별로,
구간 M이 입력되며

다음줄에는 박사가 만든 메시지가 입력됩니다.

메시지의 최대길이는 10만이며 , M은 메시지의 길이보다 더 작은값이 입력됩니다.

출력
각 테스트케이스 별로 유효성 검사 결과를 PASS , FAIL 로 출력해주세요.

입력 예시 1 

3
4
1bacDBTAab12D
3
734734734
2
10101010111
출력 예시 1

#1 PASS
#2 PASS
#3 FAIL
 */
package type.SlidingWindow;


import java.io.*;
import java.util.*; 

public class M1_2 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int test;
    static int m;
    static String str; 

    public static void main(String[] args) throws IOException {
        test = Integer.parseInt(br.readLine());
        for(int t = 1; t <= test; t++) {
            // input 
            m = Integer.parseInt(br.readLine());
            str = br.readLine(); 

            // solve -> sliding window 

            // 1. 공통구간 
            int start = 0;
            int end = m - 1; 
            int[] DAT = new int[128];
            int flag = 0; // <-- 중복을 찾았는가? 

            for(int i = start; i < end; i++) {
                // str에서 내가 i번째 문자에 접근했을때, 중복이 있는가? 
                // 접근하는 i번째 문자들 -> DAT에 기록
                char c = str.charAt(i);

                // c가 현재 구간에서 1개가 더 사용되었다! -> 기록 
                DAT[c]++; 

                // 중복체크 -> 지금 이 문자가 2개 이상 사용되었다 -> 중복이 있다!
                // AAABCDEFG
                if(DAT[c] >= 2) {
                    flag = 1; 
                    break; // 중복이 확인되었으면 더이상 수행을 할 필요 없음. 
                }    
            }

            // 2. sliding window 
            // 창문이 다 열릴때까지 확인 (O) -> 중복을 확인헀다면 더이상 수행 X 
            // 중복을 확인하지 못한 경우에만 계속해서 진행
            while(end < str.length() && flag == 0) {

                // 1. 구간 완성 (구간의 요소들의 개수 확인 완성)
                char c = str.charAt(end); 
                DAT[c]++; 

                // 2. 수행 -> 이 구간 내에 중복이 있는가? 
                if(DAT[c] >= 2) {
                    flag = 1; 
                    break; 
                }

                // 3. 구간 이동 
                // 시작 위치에 있는 문자의 개수 누적된것 -> 해제
                DAT[ str.charAt(start) ]--; 

                // 포인터 이동
                start++;
                end++; 
            }
            // 테스트케이스에 대한 결과 출력
            // flag == 0 : 중복이 없이 잘 완수되었다!
            if(flag == 0) 
                System.out.println("#" + t + " PASS");
            else
                System.out.println("#" + t + " FAIL");
        }
    }
}

/*내가짠코드
package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int t,m;
	static char[] msg;
	
	public static void main(String[] args) throws IOException	{
		t=Integer.parseInt(br.readLine());
		for(int test=1;test<=t;test++) {
			m=Integer.parseInt(br.readLine());//구간 
			String message=br.readLine();
			msg=new char[message.length()];
			
			for(int i=0;i<message.length();i++) {
				msg[i]=message.charAt(i);
			}
			int start=0;
			int end=m-1;
			int flag=0;
			int dat[]=new int[100000];//INDEX 문자 , VALUE 개수
			for(int i=start;i<end;i++) {
				dat[msg[i]]++;
				if(dat[msg[end]]>=2) {
						flag=1;
						break;
					}
			}
			while(end<message.length()) {
				dat[msg[end]]++;
					if(dat[msg[end]]>=2) {
						flag=1;
						break;
					}
				dat[msg[start]]--;
				start++;
				end ++;
			}
			if(flag==1) {
				System.out.println("#"+test+" FAIL");
			}else {
				System.out.println("#"+test+" PASS");
			}
		}
	}
	
}


*/