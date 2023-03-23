/*
��ȭ DVD ������ 20230314

�� ��ȭ DVD ��������, �� ���� ��ȭ ��,Best ��ȭ�� �����Ͽ�
1 ~ N ������ ��ȣ�� �ٿ� �׾Ƶξ����ϴ�.
�׸��� ������ ���� ���� ��ȭ�� ������,
DVD�� ���ɽ����� ������, ��ȭ ���� ��, ���� ���� �÷��ΰ� �մϴ�.
�Ʒ� �̹����� 6�� ��ȭ�� ���� ��,�ٽ� �� ���� �÷��� �����Դϴ�.

ggtt.png

�̷��� ��ȭ DVD�� �����ϴٺ���,
���� ���� ���� ��ȭ��, ������ �� ��°�� �����ϴ��� ã�Ⱑ ����������ϴ�.
��ȭ ������ �Ϸ��� ��ȭ��ȣ���� �Է� �ް�,
������ �� ��°�� ������ �ִ��� �˷��ִ� ���α׷��� �������ּ���.
�������,
8 �� ��ȭ ��,6 3 1�� �Է� �޴� ���, ������ ���� �����մϴ�.
�ʱ� ��ȭ�� �׿��� ���´� 1 2 3 4 5 6 7 8 �Դϴ�.
6�� ��ȭ�� ������ 6��° �ְ�, ��ȭ ���� �� �� ���� �ø� (1 2 3 4 567 8 -->61 2 3 4 5 7 8)
3�� ��ȭ�� ������ 4��° �ְ�, ��ȭ ���� �� �� ���� �ø� (61 234 5 7 8 -->36 1 2 4 5 7 8)
1�� ��ȭ�� ������ 3��° �ְ�, ��ȭ ���� �� �� ���� �ø� (3612 4 5 7 8 -->13 6 2 4 5 7 8)
���� ��°���� 6 4 3 �Դϴ�.

�Է�
ù �ٿ��� Test Case ������ �־����ϴ�. (1 <= Test Case <= 100)
�� �׽�Ʈ ���̽��� ù �ٿ���, ��ȭ���� N�� ������ �ϴ� ��ȭ�� �� M�� �־����ϴ�. (1 <= N, M <= 100,000)
�� �׽�Ʈ ���̽��� �� ��° �ٿ���, M���� ��ȭ��ȣ�� �־����ϴ�.

���
��ȭ�� ������ �� ����, ������ �� ��° �ִ��� ������ּ���.

�Է� ���� 1 
2
8 3
6 3 1
5 4
2 4 3 1

��� ���� 1
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
	static int arr[]; // index : ��ȭ��ȣ, value : ��ġ(leaf node ��ȣ)
	
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
			{   //�ʱⰪ ���� query ���� �ջ��Ҷ� �����ؼ� �Ųٷ� ����
				update(1, 1, nodeCnt, N - i + 1, 1);
				arr[i] = N - i + 1; // leaf node��ȣ
			}
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < M; i++)
			{
				int num = Integer.parseInt(st.nextToken());
				int pos = arr[num]; //num ��ȭ�� ���� leaf ����ȣ
				System.out.print(query(1,1,nodeCnt, pos,nodeCnt) + " "); 
				// pos~nodeCnt����(pos���ķ� ��ü��������) �� ���� ��ȭ�� �ִ°�? <- pos���� �ִ� ��ȭ ����
				update(1,1,nodeCnt, pos, 0); // pos��ġ���� ����
				update(1,1,nodeCnt, N + 1 + i, 1); // �� �� ��ġ�� �̹� ��ȭ�� �߰�
				arr[num] = N + 1 + i; // num�̶�� ��ȭ�� ��� �ִ°��� ���
			}
            System.out.println();
		}
	}

}