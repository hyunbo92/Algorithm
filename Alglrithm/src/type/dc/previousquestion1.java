/*
���� ���丮

���� ���丮������ �� �Ϸķ� ������ N���� ��ǰ�� ���� �������� ��ġ�� ���ϰ� �ִ��� �˻��Ϸ��� �մϴ�.
�̿� ������ ���ھ��� �˻縦 ���� �� ���� ���α׷��� ������ �Ͽ����ϴ�.
���α׷��� Ư�� ������ ��ġ p�� �Է��ؼ� "�ش� ������ p���� ū ��ġ�� ������ �ִ� ��ǰ���� ������ ��ΰ�?" ������ ���� �����ִ� ���α׷��Դϴ�.
���� ���, N = 10 �� ��ǰ���� �����Ǿ� �ְ� , �� ��ǰ���� ��ġ�� ���ʺ��� ���ʷ�
[1750, 1820, 1780, 1790, 1700, 1790, 1750, 1850, 1850, 1810] ��� �մϴ�. �̶�, [3,7] ������ �ִ� ��ǰ���� [1780, 1790, 1700, 1790, 1750] �̰� �̵� �� 1750 ���� ��ġ�� ���� ��ǰ�� 3���Դϴ�.
�������ִ� N���� ��ǰ�� ��ġ�� ������� �־�����, ������ ���ھ��� Q���� ������ �Է��Ͽ��� ��, ������ ���� ���ִ� ���α׷��� �ۼ��� �ּ���.

�Է�
ù��° �ٿ��� �׽�Ʈ ���̽��� �� T �� �Էµ˴ϴ�. ( 1 <= T <= 10 )
�� �׽�Ʈ���̽����� ,
ù° �ٿ��� �Ϸķ� ������ ��ǰ�� �� N �� �Է��� ������ �� Q �� �־����ϴ�.
��° �ٿ��� N ���� �ڿ����� �������� �־����� �Ǵµ�, ���ʺ��� �������ִ� ��ǰ�� ��ġ�� �־����ϴ�.
��° �ٺ��� �� Q ���� ���� �Է��� ���پ� �Էµ˴ϴ�.
�� �ٿ��� ��s e p�� �̷��� ������ ���� �־�����, �̴� ��s ���� e �������� p ��ġ���� ���� ��ǰ�� � �ִ°�?�� ��� ������ �ǹ��մϴ�. ��, s <= e�� �׻� �����մϴ�.

[��������]
������ ��ǰ�� �� N �� 1 <= N <= 300,000 �� �ڿ����Դϴ�.
������ �� Q�� 1 <= Q <= 300,000 �� �ڿ����Դϴ�.
��ǰ�� ������ ��ġ�� �������� �Է��ϴ� ��ġ p �� int ������ �ڿ��� �Դϴ�.

���
T�ٿ� ���� �׽�Ʈ���̽� ���� ���پ�
"#(�׽�Ʈ���̽� ��ȣ)"  �� �����,  �Էµ�ù��° �������� Q ��° ���������� ���� ���ʴ�� ������ּ���.

�Է� ���� 1 
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

��� ���� 1
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
		int val; // ��ġ��
		int idx; // index
		Node(int val, int idx){
			this.val = val;
			this.idx = idx;
		}
		@Override
		public int compareTo(Node right) {
			// ū ��ġ���� �켱
			
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
			
			// ��ġ���� ū �� �켱
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
			return tree[node] += val; // val��ŭ '�߰�'������ ���
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
			
			Arrays.sort(node); // ��ǰ�� ��ġ�� ������� ����
			Arrays.sort(q); // ��ǰ�� ��ġ�� ������� ����
			int index = 0;
			int ans[] = new int[Q];
			for(int i = 0; i < Q; i++)
			{
				Query now = q[i];
				// q.start~q.end���� q.val�� �ѱ�� ��ǰ�� �� �� �ִ°�?
				//   -> q.val�� �ѱ�� ��ǰ�� '����' => q.val�� �ѱ�� ��ǰ�� ���� ������ ����

				// idx��°�� ��ǰ�� now.val�� ��ġ�� �ѱ⿡ segment tree�� ���� <- ��� now.val�� ��ġ�� �Ѵ� ��ǰ�� ���
				while(node[index].val > now.val){
					update(1,1,N, node[index].idx, 1); // <- 1���� ��� X, 1�� '��' ���
					index++;
				}
				// segment tree���� now.val�� ��ġ�� �Ѵ� ��ǰ�鸸 ���
				// �� ��ǰ�� �� now.start ~ now.end ������ '�� ��'�� ��ǰ�� �ִ°�?
				ans[now.idx] = query(1, 1, N, now.start, now.end); 
				// now.idx��°�� �Է¹޾Ҵ� ������ ���� ���� ����
			}
			
			System.out.print("#" + tc + " ");
			for(int i = 0; i < Q; i++)
				System.out.print(ans[i] + " ");
			System.out.println();
		}
		
		
	}}