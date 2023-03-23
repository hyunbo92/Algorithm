/*
구간의 합

N개의 정수로 된 수열 A가 있습니다.
이 수열의 i 부터 j번째 수의 구간의 합(A[i] + A[i+1] ... A[j-1] + A[j])이 M이 되는 경우의 수를 구하는 프로그램을 작성하시오.
[예시]
[1, 2, 3, 4, 2, 5, 3, 1, 1, 2] 라는 수열이 주어질 때, 구간의 합이 10이 되는 경우는 3개가 존재합니다.

image.png

입력
첫번째 줄에 N(1 ≤ N ≤ 10,000), M(1 ≤ M ≤ 300,000,000)이 주어집니다.
다음 줄에는 A[1], A[2], …, A[N]이 공백으로 분리되어 주어집니다. 각각의 A[x]는 30,000을 넘지 않는 자연수입니다.

출력
M을 만들 수 있는 구간의 합의 경우의 수를 출력합니다.

입력 예시 1 
4 2
1 1 1 1

출력 예시 1
3

입력 예시 2 
10 5
1 2 3 4 2 5 3 1 1 2

출력 예시 2
3
 */
package type.TwoPointer;


import java.io.*;
import java.util.*; 

public class M1_4 {


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // ** 마지막 구간까지 확인하기위해 
    // 배열의 크기 = N + 1
    // arr[n+1]; 
    static int[] arr; 
    static int n, target; 

    public static void main(String[] args) throws IOException {
        // input
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        // init -> 배열의 크기는 크기 + 1 (n+1)
        arr = new int[n+1]; 

        st = new StringTokenizer(br.readLine()); 
        for(int i = 0; i < n; i++) 
            arr[i] = Integer.parseInt(st.nextToken());

        // 1. left, right 포인터 설정
        int left = 0;
        int right = 0; 
        int sum = 0; 
        int cnt = 0; 

        // 2. 반복 범위 설정
        while(right <= n) {
            // 가능성에 따라 포인터의 이동 방향을 설정
            // 1. sum > target
            if(sum > target) {
                sum -= arr[left]; 
                left++; 
            }

            // 2. sum < target 
            else if(sum < target) {
                sum += arr[right]; 
                right++;
            }

            // 3. sum == target 
            else {
                // 수행
                cnt++; 
                // 종료를 시킬수 있는 방향으로 포인터를 이동
                sum += arr[right]; 
                right++;
            }
        }
        System.out.println(cnt);
    }
}


/*내가짠코드
package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n,target;
	
    static int[] arr;
	public static void main(String[] args) throws IOException	{
		
		st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		target=Integer.parseInt(st.nextToken());

		
		st=new StringTokenizer(br.readLine());
		arr= new int [n+1];
		
		for(int i =0;i<n;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		
		int left=0;
		int right=0;
		int sum=0;
		int ans=0;
		
		while(right<=n) {
			if(sum>target) {
				sum-=arr[left];
				left++;
			}
			if(sum<target) {
				sum+=arr[right];
				right++;
			}
			if(sum==target) {
				ans++;
				sum+=arr[right];
				right++;
			}
		}
		System.out.println(ans);
	}
}


*/