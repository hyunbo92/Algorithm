/*
Binary Search 20230321

정렬 되어있는 데이터를 검색할 때,
for문으로 찾는 것보다 Binary Search로 더 빨리 원하는 값을 찾을 수 있습니다.
(Binary Search와 Binary Search Tree는 다릅니다.)

e8d5eff447fb8f5e3aa3411db7833d11.png

n개의 배열 값을 입력 받아주세요.
그리고 찾아야 하는 수를 입력 받고,
배열 내 존재하는 값인지 아닌지 O(log N) 속도로 찾는 프로그램을 작성해 주세요.

입력
첫 줄에는 수 n을 입력 받습니다. (1 <= n <= 100,000)
다음 줄에는 n개의 수가 입력으로 주어집니다. (n = Integer범위)
그리고 수 k가 입력됩니다. ( 1 <= n <= 1,000)
다음줄에는 k 개의 찾아야 하는 수가 입력됩니다.

출력
k개의 수가 배열 안에 존재하는지출력하는 프로그램을 작성해주세요.
각 수마다, 존재한다면 "O"를, 존재하지 않다면 "X"를 출력합니다.

입력 예시 1 
10
20 22 23 24 4 4 5 7 8 10
10
1 2 3 4 5 6 7 8 9 10

출력 예시 1
XXXOOXOOXO

입력 예시 2 
5
-10 -40 -30 -20 50
3
-10 0 10

출력 예시 2
OXX
 */
package type.BinarySearch;


import java.io.*;
import java.util.*; 

public class M1_7 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int n, k; 
	static int[] arr; 
	
	// binary search
	// 숫자 맞추기 게임 구현
	// num == target
	static void bsearch(int num) {
		// 1. 구간 설정
		int left = 0; 
		int right = n-1; 
		
		// 탐색
		while(left <= right) {
			// 1. binary search 항상 "중간"만을 확인
			int mid = (left + right) / 2; 
			
			// 방향 설정 
			// mid > target 
			if(arr[mid] > num) {
				right = mid -1; 
			}
			
			// mid < target
			else if (arr[mid] < num) {
				left = mid +1; 
			}
			
			// mid == target 
			else {
				System.out.print("O");
				return; 
			}
		}
		// 끝까지 찾아봤는데 없다!
		System.out.print("X");
	}
	
	
	public static void main(String[] args) throws IOException {
		
		// input 
		n = Integer.parseInt(br.readLine());
		// init
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) 
			arr[i] = Integer.parseInt(st.nextToken()); 
		// ** 필수 : 정렬
		Arrays.sort(arr);
		
		k = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < k; i++) {
			int num = Integer.parseInt(st.nextToken());
			bsearch(num); 
		}
	}
}