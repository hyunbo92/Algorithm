/*
공장

어떤 공장에는 2*N개의 기계가 2열에 걸쳐 N개씩 배치되어 있습니다.
이 2개의 열을 각각 A열과 B 열이라고 부릅니다.
A열에 있는 N개의 기계는 각각이 B열에 있는 N개의 기계와 하나씩 짝을 이루어 케이블로 연결되어 있습니다.
즉, A열의 임의의 기계는 B열의 유일한 기계와 케이블로 연결되어 있고, B열의 임의의 기계는 A열의 유일한 기계와 케이블로 연결되어 있습니다
또한, 각 기계에는 식별번호가 붙어있으며, 짝이 맺어진 기계끼리는 같은 식별번호가 붙어있습니다.
즉, 각 열에 있는 N개의 기계끼리는 서로 다른 식별번호를 가지고 있으며, 반대쪽 열에 있는 같은 식별번호를 가진 기계와 케이블로 이어져 있습니다.



공장 작업의 효율성을 위해 기계들은 짝을 맺은 순서대로 배치되지 않으며, 필요에 따라 각 열의 기계들의 순서를 바꾼 바람에 케이블은 마구 엉켜있는 상태입니다.
이렇게 엉켜버린 케이블은 잦은 고장의 원인이 되기 때문에, 기계의 위치를 바꾸지 않은 상태에서 케이블을 두 기계를 잇는 직선의 형태로 만들기로 했습니다.



예를 들어, 위의 그림과 같이 N = 5이고, A열에 위치한 기계의 식별번호가 순서대로 132, 392, 311, 351, 231이고 B열에 위치한 기계의 식별번호가 순서대로 392, 351, 132, 311, 231이라면 케이블들의 교차 횟수 혹은 서로 교차하는 케이블 쌍의 개수는 3이 됩니다.
정수 N과 A열에 위치한 기계, B열에 위치한 기계의 식별번호가 각각 순서대로 주어질 때에 서로 교차하는 케이블 쌍의 개수를 정확하게 세어 출력하는 프로그램을 작성해 주세요.

입력
첫 번째 줄에는 정수 N이 주어집니다.
두 번째 줄에는 A열에 위치한 N개 기계의 서로 다른 식별번호가 순서대로 공백문자로 구분되어 주어집니다.
세 번째 줄에는 B열에 위치한 N개의 기계의 식별번호가 순서대로 공백문자로 구분되어 주어집니다.
단, 1 ≤ N ≤ 500,000이며, 기계의 식별번호는 모두 0 이상 1,000,000 이하의 정수로 주어집니다.

출력
2*N개의 기계의 배치로부터 서로 교차하는 케이블 쌍의 개수를 정수 형태로 한 줄에 출력해야 합니다.

입력 예시 1 
5
132 392 311 351 231
392 351 132 311 231

출력 예시 1
3
 */

package type.dc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class M2_add1 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int tree[];
	
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
		int leftValue = query(node * 2, left, mid, start, end);
		int rightValue = query(node * 2 + 1 , mid + 1, right, start, end);
		return leftValue + rightValue;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		int N = Integer.parseInt(br.readLine());
		tree = new int[(N + 1) * 4];
		int index[] = new int[1000001];
		// index : 기계 식별 번호, value : A열에서 어느 index에 위치하는가?
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++)
		{
			int num = Integer.parseInt(st.nextToken());
			index[num] = i;
		}
		long ans = 0;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++)
		{
			int num = Integer.parseInt(st.nextToken());
			update(1, 0, N-1, index[num], 1); // A열의 index[num]번과 B열의 i번을 연결
			ans += query(1, 0, N-1, index[num] + 1, N-1);
		}
		System.out.println(ans);
	}}