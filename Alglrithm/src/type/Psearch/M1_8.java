/*
연료 게이지

주유시, 기름이 어느정도 채워졌는지 알려주는 장치를 만드려고합니다.
고객들이 많아 O(N) 으로 성능을 측정하면, 손님을 놓칠 수 있기 때문에
보다 빠른성능으로 동작하는 프로그램을 작성해야합니다.

20190723111923_93068.png

기름의 상태를 문자열로 입력 받아주세요.
총 몇 % 연료가 채워졌는지출력해 주세요.

입력
기름의 상태를 나타내는 N개의 문자열을 입력 받습니다.
(1 <= N <= 10,000 / 문자열 길이는 최대 10만)
빈칸은 언더바 ("_") 로 표기하며, 기름이 채워진 곳은 샵 ("#") 으로 표기됩니다.

출력
각 줄마다 연료가 몇 % 남았는지를 출력합니다.
정답은 (연료칸수 x 100 / 전체칸수 ) 공식을 정수형으로 계산합니다.

입력 예시 1 
4
######____
#___
____
#######

출력 예시 1
60%
25%
0%
100%
 */
package type.Psearch;


import java.io.*;
import java.util.*; 

public class M1_8 {
	  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    static StringTokenizer st;

	    static int n ;
	    static String str; 

	    static void psearch() {
	        // 기본 구성 == binary search
	        // 1. 구간 설정 
	        int left = 0;
	        int right = str.length() - 1;
	        int ans = -1;

	        // 탐색
	        while(left <= right) {
	            int mid = (left + right) / 2; 

	            // psearch -> 가능성에 따라 정답을 갱신

	            // 중간을 확인 ->  땅이다 (연료가 차있는 곳이다)
	            // --> 이 전 구간은 모두 차있을 것이다 (가정)
	            // --> 어? 그럼 더 오른쪽도 차있을 가능성이 있을까? -> 구간을 넓혀봄
	            if(str.charAt(mid) == '#') {
	                // 가능성을 보았다 -> 여기는 일단 정답이 될수 있는데, 이후로도 될수 있는 가능성이 있는 방향
	                // 가능성이 있는 방향에서 정답 갱신
	                ans = mid; 
	                // 구간을 더 넓혀서 더 멀리 갈 수 있는지 (연료가 더 차있는지)를 확인해보자!
	                left = mid + 1; 
	            }
	            // 여기가 지금 비어있는 연료 게이지
	            // 더 왼쪽 구간 어딘가에 차있을 것
	            else {
	                right = mid - 1; 
	            }
	        }

	        System.out.println(((ans+1) * 100) / str.length() + "%");
	    }


	    public static void main(String[] args) throws IOException {
	        n = Integer.parseInt(br.readLine());
	        for(int i = 0; i < n; i++) {
	            str = br.readLine(); 
	            psearch(); // parametric search 
	        }

	    }
	}