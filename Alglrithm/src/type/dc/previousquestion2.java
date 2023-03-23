/*
두개의 점

2차원 평면에 총 N개의 점들이 찍혀있습니다.N개의 점들 중에 두 점을 선택하여 점수를 매기려고 합니다.
두 점을 (Xa,Ya),(Xb,Yb)라고 했을 때, 점은 |Xa-Xb| <= K를 만족하도록 선택 해야 합니다.
두 개의 점을 선정했을 때, Ya + Yb가 최대가 되는 값을 구하는 프로그램을 작성해주세요.
[예시]
다음은 예제 입력을 시각화 한 것입니다.

image.png

K = 2일때,|Xa-Xb| <= K를 만족하는 여러 점들 중, Ya + Yb가 최댓값인 점들은 다음과 같습니다.

image.png

입력
첫 번째 줄에 점의 점의 개수 N과 K가 입력됩니다. (2 ≤ N≤3,000,000, 0≤ K≤ 2,000,000,000)
다음 N개의 줄에는 점의 좌표 X, Y가 입력됩니다.(-1,000,000,000≤X, Y≤1,000,000,000)
중복되는 위치의 점은 주어지지 않습니다.

출력
첫 번째 줄에|Xa-Xb| <= K를 만족하는 점 중, 최대가 되는Ya + Yb의 값을 출력합니다.

입력 예시 1 
10 2
-2 0
2 -2
3 -2
-1 -4
2 3
1 -4
-1 -1
2 5
2 -5
3 5

출력 예시 1
10

 */
package type.dc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class previousquestion2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static class Coord implements Comparable<Coord>{
		int row, col;
		Coord(int row, int col){
			this.row = row;
			this.col = col;
		}
		@Override
		public int compareTo(Coord right) {
			// TODO Auto-generated method stub
			if(col < right.col) return -1;
			if(col > right.col) return 1;
			return 0;
		}
	}
	
	static int tree[]; // <- segment tree : y좌표의 최댓값
	static Coord coord[];
	
	static int init(int node, int left, int right) {
		
		if(left == right)
			return tree[node] = coord[left].row;
		
		int mid = (left + right) / 2;
		int leftValue = init(node * 2 , left, mid);
		int rightValue = init(node * 2 + 1, mid + 1, right);
		return tree[node] = Math.max(leftValue, rightValue);
	}
	
	static int query(int node, int left, int right, int start, int end) {
		if(right < start || end < left)
			return -2134567890;
		if(start <= left && right <= end)
			return tree[node];
		
		int mid = (left + right) / 2;
		int leftValue = query(node * 2, left, mid, start, end);
		int rightValue = query(node * 2 + 1, mid + 1, right, start, end);
		return Math.max(leftValue, rightValue);
	}
	
	// 분할정복 version
	static int ps(int start, int end, int target, int index) {
		// start~end라는 구간내에서 target이라는 x범위 내에 최대 index

		if(start > end)
			return index;
		// 중간점
		int mid = (start + end) / 2;
		
		if(coord[mid].col <= target) // target범위내에 들어오므로 오른쪽 구간을 탐색하여 더 큰 x값 찾기
		{
			index = mid; // 가능성 여부를 확인해보니 더 가능하더라
			return ps(mid + 1, end, target, index);
		}
		else // target보다 mid가 크므로 왼쪽 구간으로 탐색
		{
			return ps(start, mid - 1, target, index);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		coord = new Coord[N];
		tree = new int[N * 4];
		for(int i = 0 ; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int col = Integer.parseInt(st.nextToken());
			int row = Integer.parseInt(st.nextToken());
			coord[i] = new Coord(row, col);
		}
		
		Arrays.sort(coord); // <- parametric search를 할 준비
		
		init(1,0,N - 1);
		int ans = -213457890;
		for(int i = 0; i < N; i++)
		{
			Coord now = coord[i]; // 기준이 될 점
			
			// 기준이 될 점에서 부터 x거리가 K이하인 구간 찾기
			int target = now.col + K;
			// -1을 초기값으로 준 이유 : 해당 data는 index의 의미로 사용, 찾지 못하면 index가 -1로 나오도록
			// 찾지못했는지도 판별할 수 있도록
			int index = ps(i + 1, N-1, target, -1); // i번은 본인이니 본인을 제외하고
			if(index == -1) continue; // 찾지 못했다라면 무시
			
			
			// now로부터 x좌표 거리가 K이내인 구간 : i번~index번
			// i+1번~index번 사이에서 최대 y좌표 //<- segment tree에 요청
			int maxRow = query(1, 0, N-1, i+1, index);
			ans = Math.max(ans, now.row + maxRow);
		}
		System.out.println(ans);
	}
}