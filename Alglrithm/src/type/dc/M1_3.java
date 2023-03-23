/*
단계별 Merge Sort

Merge Sort는 O(nlogn) 속도로 정렬이 가능한 빠른 Sort입니다.
이 Sort는 크게 두 가지 코드로 구성 되어 있습니다.
분할 : 재귀를 타며 영역을 분할하는 코드
병합 : 두 정렬된 배열을 하나로 합치는 코드
아래 그림과 같은 예시에서는, 1번 ~ 5번까지 순차적으로 병합이 이루어집니다.
병합이 될 때 마다, 병합이 완료된 값을 출력해주는 프로그램을 작성 해 주세요.

그림1.png

입력
정렬할 숫자의 개수 N을 입력 받습니다. (3 <= N <= 100,000)
그 다음 줄에는 N개의숫자를 입력 받습니다.

출력
Merge(병합)를 진행할 때마다
각 줄에 병합이 완료된 배열 값을모두 출력하세요.

입력 예시 1 
6
4 7 2 1 8 5

출력 예시 1
4 7 
2 4 7 
1 8 
1 5 8 
1 2 4 5 7 8 
 */
package type.dc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class M1_3 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int arr[];
	
	static void merge(int left, int right) {
		// left ~ right구간을 합침
		int mid = (left + right) / 2;
		
		int l = left; // 왼쪽 구역의 가장 작은 값
		int r = mid + 1; // 오른쪽 구역의 가장 작은 값
		
		int temp[] = new int[right - left + 1];
		int index = 0;
		while(l <= mid && r <= right) // <- 왼쪽 구역과 오른쪽 구역을 비교하여 작은 값부터 채우기
			if(arr[l] < arr[r]) 
				temp[index++] = arr[l++];
			else
				temp[index++] = arr[r++];
		while(l <= mid) // 왼쪽 구역이 남았다면
			temp[index++] = arr[l++]; // 왼쪽 구역의 data를 마저 채움
		while(r <= right) // 오른쪽 구역이 남았다면
			temp[index++] = arr[r++]; // 오른쪽 구역의 data를 마저 채움
		
		for(int i = left; i <= right; i++)
			arr[i] = temp[i - left];
		
	}
	
	static void mergesort(int left, int right) {
		
		// data가 1개밖에 없으면 굳이 쪼개거나 정렬할 필요가 X
		if(left >= right)
			return;
		
		int mid = (left + right) / 2;
		mergesort(left, mid); //<- 왼쪽 구역 정렬
		mergesort(mid + 1, right); // <- 오른쪽 구역 정렬
		
		merge(left, right);
		
		for(int i = left; i <= right; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		int N = Integer.parseInt(br.readLine());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		mergesort(0, N-1);
	}
}

