/*
Range Minimum Query

N���� ���� �����Ǿ� �ֽ��ϴ�.
�׸��� �� �� s, e�� �Է� ������,
s��° ���� e��°���� �������� ���� ���� ���� ����մϴ�.
���� ���4,2, 3, 5,1����s = 2�̰�, e = 4 �϶�, ���� ���� ���� 2 �Դϴ�.

�Է�
ù ��° �ٿ� ������ ������ ���� N�� query�� �� M�� �־����ϴ�.(1 �� N�� 1,000,000 , 1�� M�� 20,000)
�� ��° �ٿ� N���� ���ڰ� ������ �������� �־����ϴ�.�־����� ���ڴ� int ũ�⸦ ����� �ʴ� �����Դϴ�.
�̾����� M���� �ٿ� ���� �� �ٿ� �ϳ��� query ��Ʈ (s, e) �� �Է����� �־����ϴ�.

���
�� query�� ���� ����� ����մϴ�.
�� �ٿ� �ϳ��� ������ ����մϴ�.

�Է� ���� 1 
10 10
48 -17 31 -8 16 46 29 -34 -6 -25 
5 9
1 7
4 8
2 3
5 10
3 5
6 8
3 6
1 6
2 7

��� ���� 1
-34
-17
-34
-17
-34
-8
-34
-8
-17
-17
 */
package type.dc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class M1_5 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int arr[] = {1,2,3,4,5,6,7,8,9,10}; // ���� �迭 ��
	static int tree[]; // segment tree�� ���� ������ �迭
	
	static int init(int node, int left, int right) {
		// tree�� node��° ��ġ���� �迭�� left~right�� �ش��ϴ� ����� �����ִ�.
		// �ش� ����� ��ȯ
		
		// ���ϰ��� ���� node
		if(left == right)
		{
			tree[node] = arr[left]; // arr[right]
			return tree[node];
		}
		
		int mid = (left + right) / 2;
		// ���� �ڽĿ��� ���� ���� ��(left~mid)
		int leftValue = init(node * 2, left, mid); 
		// ������ �ڽĿ��� ���� ���� ��(mid+1~right)
		int rightValue = init(node * 2 + 1, mid + 1, right); 
		
		tree[node] = Math.min(leftValue, rightValue);
		return tree[node];
	}
	// node, left, right <- 3���� ���� : tree��� ������ Ž���Ҷ� �ʿ��� ����
	static int query(int node, int left, int right, int start, int end) {
		// node��°, left~right��� ������ ���� ���� ���� �������
		// start~end��� �������� ���� ���� ��
		
		// 1. ������ ������ ����� ��� 
		if(right < start || end < left) // ���ϴ� data�� ���Ե��� �ʴ´�.
			return 2134567890; // <- *�ʿ� ���� data(����� ������ ���� ���� data)*
		
		// 2. ������ ������ ���ԵǴ� ���(���� ���ϴ� ���� �� ���� �ִ� ���)
		if(start <= left && right <= end)
			return tree[node]; // left~right������ ���� ������ ��°�� �������� ���
		
		// 3. ��ġ�� ���(�ָ��ϰ� �����ִ� ���)
		int mid = (left + right) / 2;
		// <- left~mid���� ������ start~end�� ���ԵǴ� data�� ���� ���
		int leftValue = query(node * 2, left, mid, start, end); 
		// <- mid+1~right���� ������ start~end�� ���ԵǴ� data�� ���� ���
		int rightValue = query(node * 2 + 1, mid + 1, right, start, end);
		
		return Math.min(leftValue, rightValue);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		arr = new int[N];
		tree = new int[N * 4];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		init(1, 0, N - 1);
		for(int i = 0; i < Q; i++)
		{
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			s -= 1; e -= 1;
			System.out.println(query(1,0,N-1,  s, e));
		}
		
	}
}