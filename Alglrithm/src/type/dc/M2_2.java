/*
영화 DVD 수집가 20230314

한 영화 DVD 수집가는, 수 많은 영화 중,Best 영화를 선정하여
1 ~ N 번까지 번호를 붙여 쌓아두었습니다.
그리고 본인이 보고 싶은 영화가 있으면,
DVD를 조심스럽게 빼내어, 영화 감상 후, 가장 위로 올려두곤 합니다.
아래 이미지는 6번 영화를 감상 후,다시 맨 위로 올려둔 예시입니다.

ggtt.png

이렇게 영화 DVD를 관리하다보니,
내가 보고 싶은 영화가, 위에서 몇 번째에 존재하는지 찾기가 어려워졌습니다.
영화 감상을 하려는 영화번호들을 입력 받고,
위에서 몇 번째에 놓여져 있는지 알려주는 프로그램을 제작해주세요.
예를들어,
8 개 영화 중,6 3 1을 입력 받는 경우, 다음과 같이 동작합니다.
초기 영화가 쌓여진 상태는 1 2 3 4 5 6 7 8 입니다.
6번 영화는 위에서 6번째 있고, 영화 감상 후 맨 위로 올림 (1 2 3 4 567 8 -->61 2 3 4 5 7 8)
3번 영화는 위에서 4번째 있고, 영화 감상 후 맨 위로 올림 (61 234 5 7 8 -->36 1 2 4 5 7 8)
1번 영화는 위에서 3번째 있고, 영화 감상 후 맨 위로 올림 (3612 4 5 7 8 -->13 6 2 4 5 7 8)
따라서 출력결과는 6 4 3 입니다.

입력
첫 줄에는 Test Case 개수가 주어집니다. (1 <= Test Case <= 100)
각 테스트 케이스의 첫 줄에는, 영화개수 N과 보려고 하는 영화의 수 M가 주어집니다. (1 <= N, M <= 100,000)
각 테스트 케이스의 두 번째 줄에는, M개의 영화번호가 주어집니다.

출력
영화를 선택할 때 마다, 위에서 몇 번째 있는지 출력해주세요.

입력 예시 1 
2
8 3
6 3 1
5 4
2 4 3 1

출력 예시 1
6 4 3
2 4 4 4
 */
package type.dc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class M2_2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int tree[];
	static int arr[]; // index : 영화번호, value : 위치(leaf node 번호)
	
	static int query(int node, int left, int right, int start, int end) {
		if(right < start || end < left)
			return 0;
		if(start <= left && right <= end)
			return tree[node];
		int mid = (left + right) / 2;
		int leftValue = query(node * 2, left, mid, start, end);
		int rightValue = query(node * 2 + 1, mid + 1, right, start, end);
		return leftValue + rightValue;
	}
	
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
	

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		int T = Integer.parseInt(br.readLine());
		for(int tc = 0; tc < T; tc++)
		{
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int nodeCnt = N + M;
			tree = new int[(nodeCnt + 1) * 4];
			arr = new int[N + 1];
			for(int i = 1; i <= N; i++)
			{   //초기값 세팅 query 에서 합산할때 생각해서 거꾸로 넣음
				update(1, 1, nodeCnt, N - i + 1, 1);
				arr[i] = N - i + 1; // leaf node번호
			}
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < M; i++)
			{
				int num = Integer.parseInt(st.nextToken());
				int pos = arr[num]; //num 영화의 현재 leaf 노드번호
				System.out.print(query(1,1,nodeCnt, pos,nodeCnt) + " "); 
				// pos~nodeCnt까지(pos이후로 전체구역에서) 몇 개의 영화가 있는가? <- pos위에 있는 영화 개수
				update(1,1,nodeCnt, pos, 0); // pos위치에서 삭제
				update(1,1,nodeCnt, N + 1 + i, 1); // 맨 윗 위치에 이번 영화를 추가
				arr[num] = N + 1 + i; // num이라는 영화가 어디에 있는가를 기록
			}
            System.out.println();
		}
	}

}