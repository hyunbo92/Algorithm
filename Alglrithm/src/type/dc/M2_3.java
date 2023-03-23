/*
Inversion Counting

1 ~ N 까지 정수가 나열되어 있습니다.
나열되어있는 수에서 두 수를 선택했을 때,
앞쪽에 있는 정수값이뒷쪽에 있는 정수값보다 더 큰 경우,Inversion이라고 합니다.
총 몇 개의 Inversion이 존재하는지 찾아내는 프로그램을 작성 해주세요.
 

예를들어 1 2 3 4 5 일 경우 Inversion은 존재하지 않습니다.
vvv2.png

예를들어 4 1 3 5 2 인 경우, 총 5개의 Inversion을 찾을 수 있습니다.
vvv3.png
 

입력
첫 줄에는 N이 입력으로 주어집니다. ( 2 <= N <= 1000000)
두 번째 수에서는 N개의 수가 중복 없이 주어집니다.

출력
Inversion의 총 개수를 출력해주세요.

입력 예시 1 
5
1 2 3 4 5

출력 예시 1
0

입력 예시 2 
5
4 1 3 5 2

출력 예시 2
5
 */
package type.dc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class M2_3 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int tree[];
	static int arr[];
	
	
	static int update(int node, int left, int right, int idx, int val) {
		if(idx < left || right < idx)
			return tree[node];
		if(left == right)
			return tree[node] = val;
		int mid = (left + right) / 2;
		int leftValue = update(node * 2, left, mid, idx, val);
		int rightValue = update(node * 2 + 1, mid + 1, right, idx, val);
		return tree[node] = leftValue + rightValue;
	}
	
	static int query(int node, int left, int right, int start, int end) {
		if(right < start || end < left)
			return 0;
		if(start <= left && right <= end)
			return tree[node];
		int mid = (left + right) / 2;
		int leftValue = query(node * 2, left, mid, start, end );
		int rightValue = query(node * 2 + 1, mid + 1, right, start, end);
		return leftValue + rightValue;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		int N = Integer.parseInt(br.readLine());
		tree = new int[(N + 1) * 4];
		st = new StringTokenizer(br.readLine());
		arr = new int[(N + 1)];
		for(int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		int ans = 0;
		for(int i = 0; i < N; i++)
		{
			update(1, 1, N,  arr[i], 1);
			
			ans += query(1,1,N,  arr[i] + 1, N); // arr[i]보다 큰 data가 몇 개 있었는가?
		}
		System.out.print(ans);
			
	}
}