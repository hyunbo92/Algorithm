/*
Range Sum Query + Update

N개의 수가 주어집니다.
그리고 a번째 수를 b로 바꾸어 달라는 요청(명령어1)과,
s 에서 e 까지, 특정구간의 합을 계산해 달라는 요청(명령어2)가 입력으로 주어집니다.
해당 요청을 처리해주는 시스템을 제작해주세요.

입력
[Query 형태]
1 a b : 명령어 1, a번째 수를 b로 변경 (출력 결과 없음)
2 s e : 명령어 2, s번째 ~ e번째 구간의 합을 출력
[예시]
1, 2, 3, 4, 5 수가주어진 상황에서,
1 3 6의 명령을 수행하면 3번째 숫자를 6으로 바꿔 1, 2, 6, 4, 5 가 됩니다.
이어서 2 2 5의 명령을 수행하면 2 ~ 5번째 구간의 숫자들을 합한 17이 출력 됩니다.

[세부사항]
첫 번째 줄에 관리할 숫자의 개수 N과 query의 수 M이 주어집니다.(1 ≤ N≤ 1,000,000 , 1≤ M≤ 20,000)
두 번째 줄에 N개의 숫자가 공백을 구분으로 주어집니다. 주어지는 숫자는 int 크기를 벗어나지 않는 정수입니다.
이어지는 M개의 줄에 걸쳐 각 줄에 하나의 query가 주어집니다.

출력
Query 에 대한 결과를 출력합니다.

입력 예시 1 
5 4
1 2 3 4 5
1 3 6
2 2 5
1 5 2
2 3 5

출력 예시 1
17
12
 */
package type.dc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class M1_6 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int arr[] = {1,2,3,4,5,6,7,8,9,10}; // 실제 배열 값
	static long tree[]; // segment tree의 값을 저장할 배열
	
	static long init(int node, int left, int right) {
		// tree의 node번째 위치에는 배열의 left~right에 해당하는 결과를 갖고있다.
		// 해당 결과를 반환
		
		// 단일값을 갖는 node
		if(left == right)
		{
			tree[node] = arr[left]; // arr[right]
			return tree[node];
		}
		
		int mid = (left + right) / 2;
		// 왼쪽 자식에서 제일 작은 값(left~mid)
		long leftValue = init(node * 2, left, mid); 
		// 오른쪽 자식에서 제일 작은 값(mid+1~right)
		long rightValue = init(node * 2 + 1, mid + 1, right); 
		
		tree[node] = leftValue + rightValue;
		return tree[node];
	}
	// node, left, right <- 3가지 정보 : tree라는 구조를 탐색할때 필요한 정보
	static long query(int node, int left, int right, int start, int end) {
		// node번째, left~right라는 범위의 제일 작은 값을 기반으로
		// start~end라는 구간에서 제일 작은 값
		
		// 1. 구간을 완전히 벗어나는 경우 
		if(right < start || end < left) // 원하는 data가 포함되지 않는다.
			return 0; // <- *필요 없는 data(결과에 영향을 주지 않을 data)*
		
		// 2. 구간에 완전히 포함되는 경우(내가 원하는 값을 다 갖고 있는 경우)
		if(start <= left && right <= end)
			return tree[node]; // left~right구간에 대한 정보를 통째로 가져가서 사용
		
		// 3. 겹치는 경우(애매하게 걸쳐있는 경우)
		int mid = (left + right) / 2;
		// <- left~mid까지 구간에 start~end에 포함되는 data에 대한 결과
		long leftValue = query(node * 2, left, mid, start, end); 
		// <- mid+1~right까지 구간에 start~end에 포함되는 data에 대한 결과
		long rightValue = query(node * 2 + 1, mid + 1, right, start, end);
		
		return leftValue + rightValue;
	}
	
	static long update(int node, int left, int right, int idx, int val) {
		// 현재 node번째(left~right)에 있고,
		// idx번째 배열의 값을 val로 수정
		
		// 1. left~right범위에 idx가 포함되지 않는 경우
		if(idx < left || right < idx)
			return tree[node]; // 지금까지 범위에서 결과가 어떻더라.(변동을 주지 않음)
		
		// 2. 정확하게 idx번째 data만 갖는 node
		if(left == right) // 마지막 단일값을 갖는 node까지 왔다면 <- idx번째 data만 갖는 node
		{
			tree[node] = arr[idx] = val;
			return tree[node];
		}
		
		int mid = (left + right) / 2;
		long leftValue = update(node * 2, left, mid, idx, val); // 왼쪽 자식방향
		long rightValue = update(node * 2 + 1, mid + 1, right, idx, val); // 오른쪽 자식 방향
		
		tree[node] = leftValue + rightValue; // 재계산(두 구간 중 하나는 idx를 포함해서 어떠한 변동이 있음)
		return tree[node];
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		arr = new int[N];
		tree = new long[N * 4];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		init(1, 0, N - 1);
		for(int i = 0; i < Q; i++)
		{
			st = new StringTokenizer(br.readLine());
			int oper = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(oper == 1)
				// 수정 작업
				update(1, 0, N-1, a - 1, b);
			else
				System.out.println(query(1, 0, N - 1, a - 1, b - 1));
			
			
			
		}
		
	}
}