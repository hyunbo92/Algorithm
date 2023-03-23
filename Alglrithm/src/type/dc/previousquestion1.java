/*
민코 팩토리

민코 팩토리에서는 쭉 일렬로 나열된 N개의 제품에 대해 정상적인 수치를 지니고 있는지 검사하려고 합니다.
이에 공장장 민코씨는 검사를 위해 한 가지 프로그램을 만들기로 하였습니다.
프로그램은 특정 구간과 수치 p를 입력해서 "해당 구간에 p보다 큰 수치를 가지고 있는 제품들의 개수가 몇개인가?" 질문에 대해 답해주는 프로그램입니다.
예를 들어, N = 10 의 제품들이 나열되어 있고 , 각 제품들의 수치는 왼쪽부터 차례로
[1750, 1820, 1780, 1790, 1700, 1790, 1750, 1850, 1850, 1810] 라고 합니다. 이때, [3,7] 구간에 있는 제품들은 [1780, 1790, 1700, 1790, 1750] 이고 이들 중 1750 보다 수치가 높은 제품은 3개입니다.
나열되있는 N개의 제품의 수치가 순서대로 주어지고, 공장장 민코씨가 Q개의 질문을 입력하였을 때, 질문에 답을 해주는 프로그램을 작성해 주세요.

입력
첫번째 줄에는 테스트 케이스의 수 T 가 입력됩니다. ( 1 <= T <= 10 )
각 테스트케이스별로 ,
첫째 줄에는 일렬로 나열된 제품의 수 N 과 입력할 질문의 수 Q 가 주어집니다.
둘째 줄에는 N 개의 자연수가 공백으로 주어지게 되는데, 왼쪽부터 나열되있는 제품의 수치가 주어집니다.
셋째 줄부터 총 Q 개의 질문 입력이 한줄씩 입력됩니다.
각 줄에는 “s e p” 이렇게 세개의 수가 주어지며, 이는 “s 부터 e 구간에는 p 수치보다 높은 제품이 몇개 있는가?” 라는 질문을 의미합니다. 단, s <= e를 항상 만족합니다.

[제한조건]
나열된 제품의 수 N 은 1 <= N <= 300,000 의 자연수입니다.
질문의 수 Q는 1 <= Q <= 300,000 의 자연수입니다.
제품의 가능한 수치와 질문으로 입력하는 수치 p 는 int 범위의 자연수 입니다.

출력
T줄에 걸쳐 테스트케이스 별로 한줄씩
"#(테스트케이스 번호)"  을 출력후,  입력된첫번째 질문부터 Q 번째 질문까지의 답을 차례대로 출력해주세요.

입력 예시 1 
3
10 3
1750 1820 1780 1790 1700 1790 1710 1850 1850 1810
3 7 1750
1 10 1800
1 10 1790
7 5
1830 1760 1750 1830 1740 1820 1860
1 4 1760
2 6 1770
1 7 1800
5 7 1800
3 5 1740
2 2
1610 1680
1 2 1750
1 2 1880

출력 예시 1
#1 3 4 4 
#2 2 2 4 2 2

 */
package type.dc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class previousquestion1 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static class Node implements Comparable<Node>{
		int val; // 수치값
		int idx; // index
		Node(int val, int idx){
			this.val = val;
			this.idx = idx;
		}
		@Override
		public int compareTo(Node right) {
			// 큰 수치부터 우선
			
			// TODO Auto-generated method stub
			if(val > right.val) return -1;
			if(val < right.val) return 1;
			return 0;
		}
	}
	static class Query implements Comparable<Query>{
		int start, end, val, idx;
		Query(int start, int end, int val, int idx){
			this.start = start;
			this.end= end;
			this.val = val;
			this.idx = idx;
		}
		@Override
		public int compareTo(Query right) {
			// TODO Auto-generated method stub
			
			// 수치값이 큰 것 우선
			if(val > right.val) return -1;
			if(val < right.val) return 1;
			return 0;
		}
		
	}
	
	
	static int tree[];
	
	static int update(int node, int left, int right, int idx, int val) {
		if(idx < left || right < idx)
			return tree[node];
		if(left == right)
			return tree[node] += val; // val만큼 '추가'적으로 등록
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
		
		int t = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= t; tc++)
		{
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int Q = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			Node node[] = new Node[N];
			Query q[] = new Query[Q];
			tree = new int[(N + 1) * 4];
			for(int i = 0; i < N; i++)
			{
				int num = Integer.parseInt(st.nextToken());
				node[i] = new Node(num, i + 1);
			}
			for(int i = 0; i < Q; i++)
			{
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int val = Integer.parseInt(st.nextToken());
				q[i] = new Query(start,end,val, i);
			}
			
			Arrays.sort(node); // 제품의 수치를 기반으로 정렬
			Arrays.sort(q); // 제품의 수치를 기반으로 정렬
			int index = 0;
			int ans[] = new int[Q];
			for(int i = 0; i < Q; i++)
			{
				Query now = q[i];
				// q.start~q.end까지 q.val을 넘기는 제품이 몇 개 있는가?
				//   -> q.val을 넘기는 제품의 '개수' => q.val을 넘기는 제품에 대한 정보를 세팅

				// idx번째의 물품이 now.val의 수치를 넘기에 segment tree에 세팅 <- 모든 now.val의 수치를 넘는 제품을 등록
				while(node[index].val > now.val){
					update(1,1,N, node[index].idx, 1); // <- 1개만 등록 X, 1개 '더' 등록
					index++;
				}
				// segment tree에는 now.val의 수치를 넘는 제품들만 등록
				// 그 제품들 중 now.start ~ now.end 구간에 '몇 개'의 제품이 있는가?
				ans[now.idx] = query(1, 1, N, now.start, now.end); 
				// now.idx번째로 입력받았던 질문에 대한 답을 저장
			}
			
			System.out.print("#" + tc + " ");
			for(int i = 0; i < Q; i++)
				System.out.print(ans[i] + " ");
			System.out.println();
		}
		
		
	}}