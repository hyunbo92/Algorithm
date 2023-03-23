/*
예식장 서빙

민희는 VIP 손님들을 대접하는 예식장의 서빙 아르바이트를 합니다.
이 예식장에는 원형탁자에 앉은 N명의 손님들에게, N개의 맛있는 요리를 제공합니다.
N개의 요리 이름은 숫자로 되어있으며,
같은 숫자는 같은 음식을 뜻합니다.

t1.png

아래 예시는 7명이 원형 탁자에 앉아있고,
고객이 서빙 받은 음식들의 이름이 적혀있습니다.
(12시 방향부터 순서대로 65 - 65 - 81 - 86 - 65 - 71 - 69)

image.png

자리에 앉아있는 사람들은 쉐프의 음식을 종류별로 음식을 먹어보고 싶어합니다.
앉아있는 사람은 거리 R 만큼 떨어진 곳에 있는 음식을 섭취할 수 있습니다.
예를들어 R = 2 일때, 86 음식이 있는 곳에 앉아있는 사람은 71 65 86 81 65 음식을 맛볼 수 있습니다.
이 경우, 중복된 음식이 없기에 서빙에 성공한 것입니다.

image.png

위와 같은 예시에서는
R = 2 일때, 66 자리에서 먹을 수 있는 음식은 65 65 66 81 65 입니다.
중복된 음식은 2개 까지는 손님들의 불만이 없지만, 3개가 있다면 불만을 토로합니다.
따라서 서빙에 성공하지 못한 케이스입니다.
테이블에 앉은 모든 사람들 기준으로
앉아서 먹을 수 있는 음식들이 2개 까지만 중복되게끔 서빙 되있더다면 YES 를,
그렇지 않다면 NO 를 출력 해 주세요.

입력
첫 번째 줄에 테스트 케이스의 수 T 가 주어집니다. (1 <= T <= 100)
각 테스트 케이스의 첫 번째 줄에는 두 정수 N, R 이 주어집니다. (1 <= N <= 1,000,000)
(1 <= R <= 50,000)
각 테스트 케이스의 두 번째 줄에는 N 개의 음식이 숫자로 띄어쓰기로 구분되어 주어집니다. (숫자의 범위는 0 ~ 200 입니다)

출력
각 테스트 케이스마다 만족스러운 세팅이면 YES,
불만족스러운 세팅이면 NO 를 출력합니다.

입력 예시 1 
2
7 2
65 66 81 86 65 71 82
8 2
65 65 81 66 65 65 69 69

출력 예시 1
#1 YES
#2 NO
 */
package type.SlidingWindow;


import java.io.*;
import java.util.*; 

public class M1_3 {

	   static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    static StringTokenizer st;

	    static int test;
	    static int n,r ; 
	    static int[] arr; 

	    public static void main(String[] args) throws IOException {
	        test = Integer.parseInt(br.readLine());
	        for(int t = 1; t <= test; t++) {
	            st = new StringTokenizer(br.readLine());
	            n = Integer.parseInt(st.nextToken());
	            r = Integer.parseInt(st.nextToken());

	            // init -> 배열의 크기를 초기화
	            arr = new int[n * 2];

	            // 배열 input 
	            st = new StringTokenizer(br.readLine());
	            for(int i = 0; i < n; i++) {
	                arr[i] = Integer.parseInt(st.nextToken());
	                // [ .., ] [N ... n * 2 -1] 
	                arr[i+n] = arr[i]; 
	            }

	            // solve
	            // 1\. 공통 구간 
	            int start = 0; 
	            int end = r*2; 
	            int flag = 0; 

	            // ** DAT를 활용할때 항상 "어디 [까지] 저장해야하는가"? 
	            // 음식 번호 200 [까지] = 200을 저장할 수 있도록 -> 201 
	            // ** 팁 : 배열을 생성할떄 항상 그냥 +1 크기만큼으로 만들어 주세요. (+10)
	            // index : 음식번호 : value : 개수 
	            int[] dat = new int[201];

	            for(int i = start; i < end; i++) {
	                // arr에 저장된 음식의 번호 개수를 누적
	                dat[arr[i]]++; 
	                // 공통구간의 중복체크 (수행)
	                // 지금 기록된 음식이 3개 이상 존재한다 -> FAIL 
	                if(dat[arr[i]] > 2) {
	                    flag = 1;
	                    break; 
	                }
	            }

	            // 2\. sliding window 
	            while(end < n*2 && flag == 0) {
	                // 1\. 구간 완성 
	                dat[ arr[end] ]++;

	                // 2\. 수행 
	                if(dat[arr[end]] > 2) {
	                    flag = 1;
	                    break; 
	                }

	                // 3\. 구간 이동
	                // 맨 앞 음식 제외 
	                dat[ arr[start] ]--; 
	                start++;
	                end++; 
	            }

	            if(flag == 0) 
	                System.out.println("#" + t + " YES");
	            else
	                System.out.println("#" + t + " NO");

	        }

	    }
	}


/* 내가 짠 코드
package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int t,n,r;
	
	static int[] food;
	static int[] dat; // inedex :음식, value :개수
	
	public static void main(String[] args) throws IOException	{
		t=Integer.parseInt(br.readLine());
		for(int test=1;test<=t;test++) {
			
			st= new StringTokenizer(br.readLine());
			n=Integer.parseInt(st.nextToken());// n개의 음식
			r=Integer.parseInt(st.nextToken());// +-r 의거리만큼 먹을수있음 --> 배열복사해서+r 만체크
			
			food=new int[2*n];
			st=new StringTokenizer(br.readLine());
			for(int i=0;i<n;i++) {
				food[i]=Integer.parseInt(st.nextToken());
				food[i+n]=food[i];
			}
			
			dat=new int[201];
			
			int start=0;
			int end =2*r;
			int flag=0;
			for(int i=start;i<end;i++) {
				dat[food[i]]++;
				
				if(dat[food[i]]>2) {
					flag=1;
				}
			}
			
			while(end<2*n && flag==0 ) {
				dat[food[end]]++;
				if(dat[food[end]]>2) {
					flag=1;
					break;
				}
				dat[food[start]]--;
				start++;
				end++;
			}
			
			if(flag==1) {
				System.out.println("#"+test+" NO");
			}else {
				System.out.println("#"+test+" YES");
			}
			
			
			
			
			

			
			

		
		}
	}
	
}


 */

