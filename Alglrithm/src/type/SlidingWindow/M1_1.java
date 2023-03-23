/*
슬라이딩 윈도우

멋진 풍경 사진을 찍으려 합니다.
일직선 상에 있는 피사체들을 점수로 표현하였습니다.

image.png

사진기로 모든 피사체들을 찍고 싶지만, 사진기 안에 담을 수 있는 폭은 한정되어 있습니다.
예를들어 위 그림은 피사체의 각 점수가 2 5 2 6 5 -3 9 4 2 -7 이며, 사진기가 찍을 수 있는 폭이 5칸인 경우 입니다.
만약 0 번 인덱스에서 4 번인덱스까지 촬영 시, 얻을 수 있는 예술 점수는 2 + 5 + 2 + 6 + 5 = 20 입니다.
만약 1 번 인덱스에서 5 번 인덱스까지 촬영 시, 얻을 수 있는 예술 점수는 5 + 2 + 6 + 5 + -3 = 15 입니다.
이렇게 폭이 5인 경우 위 풍경에서 얻을 수 있는 가장 높은 점수는 6 + 5 + -3 + 9 + 4 = 21 입니다.
피사체들의 정보를 입력받고, 가장 예술 점수가 높은 사진을 찍기 위해서 몇번 인덱스부터 몇번 인덱스까지 촬영을 해야되는지를 출력해주세요

입력
첫 번째 줄에는 테스트케이스의 수(T)가 입력됩니다. (1 <= T <= 50)
두 번째 줄부터 각 테스트케이스별로,
첫번째 줄에는피사체의 갯수(N), 사진기의 폭(W) 을 입력받고
그 다음 줄에는 피사체의 수만큼 점수를 입력받습니다.
(1 <= W < N <= 100,000)

출력
각 테스트케이스 별로
최대예술 점수가 나오는 곳의 시작 인덱스, 끝 인덱스, 최대 예술점수를 출력합니다.
인덱스 번호는 0번 인덱스부터 시작합니다.
만약, 최대 예술 점수가 나오는 구간이 여러 곳인 경우 그중 가장 왼쪽에 있는 곳으로 출력합니다.

입력 예시 1 
2
10 5
2 5 2 6 5 -3 9 4 2 -7 
5 2
-1 -2 -3 -4 -5

출력 예시 1
#1 3 7 21
#2 0 1 -3
 */
package type.SlidingWindow;

import java.util.*;
import java.io.*;

public class M1_1 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int test; 
    static int n, w;
    static int[] arr; 

    public static void main(String[] args) throws IOException {

        test = Integer.parseInt(br.readLine());
        for(int t = 1; t <= test; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            // init
            arr = new int[n]; 

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) 
                arr[i] = Integer.parseInt(st.nextToken());

            // sliding window

            // 1. 공통 구간 세팅 
            // 포인터 두개 세팅 
            int start = 0; 
            int end = w - 1; 
            int sum = 0;
            for(int i = start; i < end; i++) 
                sum += arr[i]; 

            int startidx = 0; 
            int endidx = 0; 
            int maxval = Integer.MIN_VALUE; 

            // 2. 슬라이딩 윈도우 
            while(end < n) {
                // 1. 구간 완성 
                sum += arr[end]; 

                // 2. 수행 
                // 가장 합이 큰 구간이 어디서부터 어딘가? 
                // 지금 내가 접근하고 있는 구간의 합 > 지금까지 기록된 최대값 -> 갱신 
                if(sum > maxval) {
                    maxval = sum; 
                    // 이 구간의 index도 기록 -> 포인터를 기록
                    startidx = start; 
                    endidx = end; 
                }

                // 3. 구간 이동 (포인터 이동)
                // 첫번째 위치는 제외 
                sum -= arr[start];

                // 포인터 이동
                start++;
                end++; 
            }
            System.out.println("#" + t + " " + startidx + " " + endidx + " " + maxval);
        }    
    }
}


/* 내가짠코드
package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int t;
	static int n,w;
	static int[] score;
	
	public static void main(String[] args) throws IOException	{
		
		t=Integer.parseInt(br.readLine());
		
		for(int test=1;test<=t;test++) {
			int maxvalue=Integer.MIN_VALUE ;
			int left=0,right=0;
			
			st=new StringTokenizer(br.readLine());
			n=Integer.parseInt(st.nextToken());//피사채의 개수
			w=Integer.parseInt(st.nextToken());//사진기의 폭
			score=new int[n];
			
			st=new StringTokenizer(br.readLine());
			for(int i=0;i<n;i++) {
				score[i]=Integer.parseInt(st.nextToken());
			}
			
			//최대예술점수가 나오는 곳의 시작인덱스,끝인덱스,최대 예술점수 출력
			
			int start=0;
			int end=w-1;
			int sum=0;
			
			for(int i=start;i<end;i++) {
				sum+=score[i];
			}
			
			while(end<n) {
				sum+=score[end];
				if(sum>maxvalue) {
					maxvalue=sum;
					
					right=end;
					left=start;
				}
				sum-=score[start];
				
			
				start++;
				end++;
			}
			System.out.println("#"+test+" "+left+" "+ right+" "+ maxvalue);
		}
	
	
	}
	
}

*/
