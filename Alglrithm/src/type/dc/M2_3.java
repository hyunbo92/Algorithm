/*
Inversion Counting

1 ~ N ���� ������ �����Ǿ� �ֽ��ϴ�.
�����Ǿ��ִ� ������ �� ���� �������� ��,
���ʿ� �ִ� �������̵��ʿ� �ִ� ���������� �� ū ���,Inversion�̶�� �մϴ�.
�� �� ���� Inversion�� �����ϴ��� ã�Ƴ��� ���α׷��� �ۼ� ���ּ���.
 

������� 1 2 3 4 5 �� ��� Inversion�� �������� �ʽ��ϴ�.
vvv2.png

������� 4 1 3 5 2 �� ���, �� 5���� Inversion�� ã�� �� �ֽ��ϴ�.
vvv3.png
 

�Է�
ù �ٿ��� N�� �Է����� �־����ϴ�. ( 2 <= N <= 1000000)
�� ��° �������� N���� ���� �ߺ� ���� �־����ϴ�.

���
Inversion�� �� ������ ������ּ���.

�Է� ���� 1 
5
1 2 3 4 5

��� ���� 1
0

�Է� ���� 2 
5
4 1 3 5 2

��� ���� 2
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
			
			ans += query(1,1,N,  arr[i] + 1, N); // arr[i]���� ū data�� �� �� �־��°�?
		}
		System.out.print(ans);
			
	}
}