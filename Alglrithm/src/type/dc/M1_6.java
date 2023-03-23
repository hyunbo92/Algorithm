/*
Range Sum Query + Update

N���� ���� �־����ϴ�.
�׸��� a��° ���� b�� �ٲپ� �޶�� ��û(��ɾ�1)��,
s ���� e ����, Ư�������� ���� ����� �޶�� ��û(��ɾ�2)�� �Է����� �־����ϴ�.
�ش� ��û�� ó�����ִ� �ý����� �������ּ���.

�Է�
[Query ����]
1 a b : ��ɾ� 1, a��° ���� b�� ���� (��� ��� ����)
2 s e : ��ɾ� 2, s��° ~ e��° ������ ���� ���
[����]
1, 2, 3, 4, 5 �����־��� ��Ȳ����,
1 3 6�� ����� �����ϸ� 3��° ���ڸ� 6���� �ٲ� 1, 2, 6, 4, 5 �� �˴ϴ�.
�̾ 2 2 5�� ����� �����ϸ� 2 ~ 5��° ������ ���ڵ��� ���� 17�� ��� �˴ϴ�.

[���λ���]
ù ��° �ٿ� ������ ������ ���� N�� query�� �� M�� �־����ϴ�.(1 �� N�� 1,000,000 , 1�� M�� 20,000)
�� ��° �ٿ� N���� ���ڰ� ������ �������� �־����ϴ�. �־����� ���ڴ� int ũ�⸦ ����� �ʴ� �����Դϴ�.
�̾����� M���� �ٿ� ���� �� �ٿ� �ϳ��� query�� �־����ϴ�.

���
Query �� ���� ����� ����մϴ�.

�Է� ���� 1 
5 4
1 2 3 4 5
1 3 6
2 2 5
1 5 2
2 3 5

��� ���� 1
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
	static int arr[] = {1,2,3,4,5,6,7,8,9,10}; // ���� �迭 ��
	static long tree[]; // segment tree�� ���� ������ �迭
	
	static long init(int node, int left, int right) {
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
		long leftValue = init(node * 2, left, mid); 
		// ������ �ڽĿ��� ���� ���� ��(mid+1~right)
		long rightValue = init(node * 2 + 1, mid + 1, right); 
		
		tree[node] = leftValue + rightValue;
		return tree[node];
	}
	// node, left, right <- 3���� ���� : tree��� ������ Ž���Ҷ� �ʿ��� ����
	static long query(int node, int left, int right, int start, int end) {
		// node��°, left~right��� ������ ���� ���� ���� �������
		// start~end��� �������� ���� ���� ��
		
		// 1. ������ ������ ����� ��� 
		if(right < start || end < left) // ���ϴ� data�� ���Ե��� �ʴ´�.
			return 0; // <- *�ʿ� ���� data(����� ������ ���� ���� data)*
		
		// 2. ������ ������ ���ԵǴ� ���(���� ���ϴ� ���� �� ���� �ִ� ���)
		if(start <= left && right <= end)
			return tree[node]; // left~right������ ���� ������ ��°�� �������� ���
		
		// 3. ��ġ�� ���(�ָ��ϰ� �����ִ� ���)
		int mid = (left + right) / 2;
		// <- left~mid���� ������ start~end�� ���ԵǴ� data�� ���� ���
		long leftValue = query(node * 2, left, mid, start, end); 
		// <- mid+1~right���� ������ start~end�� ���ԵǴ� data�� ���� ���
		long rightValue = query(node * 2 + 1, mid + 1, right, start, end);
		
		return leftValue + rightValue;
	}
	
	static long update(int node, int left, int right, int idx, int val) {
		// ���� node��°(left~right)�� �ְ�,
		// idx��° �迭�� ���� val�� ����
		
		// 1. left~right������ idx�� ���Ե��� �ʴ� ���
		if(idx < left || right < idx)
			return tree[node]; // ���ݱ��� �������� ����� �����.(������ ���� ����)
		
		// 2. ��Ȯ�ϰ� idx��° data�� ���� node
		if(left == right) // ������ ���ϰ��� ���� node���� �Դٸ� <- idx��° data�� ���� node
		{
			tree[node] = arr[idx] = val;
			return tree[node];
		}
		
		int mid = (left + right) / 2;
		long leftValue = update(node * 2, left, mid, idx, val); // ���� �ڽĹ���
		long rightValue = update(node * 2 + 1, mid + 1, right, idx, val); // ������ �ڽ� ����
		
		tree[node] = leftValue + rightValue; // ����(�� ���� �� �ϳ��� idx�� �����ؼ� ��� ������ ����)
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
				// ���� �۾�
				update(1, 0, N-1, a - 1, b);
			else
				System.out.println(query(1, 0, N - 1, a - 1, b - 1));
			
			
			
		}
		
	}
}