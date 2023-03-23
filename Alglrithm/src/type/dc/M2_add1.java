/*
����

� ���忡�� 2*N���� ��谡 2���� ���� N���� ��ġ�Ǿ� �ֽ��ϴ�.
�� 2���� ���� ���� A���� B ���̶�� �θ��ϴ�.
A���� �ִ� N���� ���� ������ B���� �ִ� N���� ���� �ϳ��� ¦�� �̷�� ���̺�� ����Ǿ� �ֽ��ϴ�.
��, A���� ������ ���� B���� ������ ���� ���̺�� ����Ǿ� �ְ�, B���� ������ ���� A���� ������ ���� ���̺�� ����Ǿ� �ֽ��ϴ�
����, �� ��迡�� �ĺ���ȣ�� �پ�������, ¦�� �ξ��� ��賢���� ���� �ĺ���ȣ�� �پ��ֽ��ϴ�.
��, �� ���� �ִ� N���� ��賢���� ���� �ٸ� �ĺ���ȣ�� ������ ������, �ݴ��� ���� �ִ� ���� �ĺ���ȣ�� ���� ���� ���̺�� �̾��� �ֽ��ϴ�.



���� �۾��� ȿ������ ���� ������ ¦�� ���� ������� ��ġ���� ������, �ʿ信 ���� �� ���� ������ ������ �ٲ� �ٶ��� ���̺��� ���� �����ִ� �����Դϴ�.
�̷��� ���ѹ��� ���̺��� ���� ������ ������ �Ǳ� ������, ����� ��ġ�� �ٲ��� ���� ���¿��� ���̺��� �� ��踦 �մ� ������ ���·� ������ �߽��ϴ�.



���� ���, ���� �׸��� ���� N = 5�̰�, A���� ��ġ�� ����� �ĺ���ȣ�� ������� 132, 392, 311, 351, 231�̰� B���� ��ġ�� ����� �ĺ���ȣ�� ������� 392, 351, 132, 311, 231�̶�� ���̺���� ���� Ƚ�� Ȥ�� ���� �����ϴ� ���̺� ���� ������ 3�� �˴ϴ�.
���� N�� A���� ��ġ�� ���, B���� ��ġ�� ����� �ĺ���ȣ�� ���� ������� �־��� ���� ���� �����ϴ� ���̺� ���� ������ ��Ȯ�ϰ� ���� ����ϴ� ���α׷��� �ۼ��� �ּ���.

�Է�
ù ��° �ٿ��� ���� N�� �־����ϴ�.
�� ��° �ٿ��� A���� ��ġ�� N�� ����� ���� �ٸ� �ĺ���ȣ�� ������� ���鹮�ڷ� ���еǾ� �־����ϴ�.
�� ��° �ٿ��� B���� ��ġ�� N���� ����� �ĺ���ȣ�� ������� ���鹮�ڷ� ���еǾ� �־����ϴ�.
��, 1 �� N �� 500,000�̸�, ����� �ĺ���ȣ�� ��� 0 �̻� 1,000,000 ������ ������ �־����ϴ�.

���
2*N���� ����� ��ġ�κ��� ���� �����ϴ� ���̺� ���� ������ ���� ���·� �� �ٿ� ����ؾ� �մϴ�.

�Է� ���� 1 
5
132 392 311 351 231
392 351 132 311 231

��� ���� 1
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
		// index : ��� �ĺ� ��ȣ, value : A������ ��� index�� ��ġ�ϴ°�?
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
			update(1, 0, N-1, index[num], 1); // A���� index[num]���� B���� i���� ����
			ans += query(1, 0, N-1, index[num] + 1, N-1);
		}
		System.out.println(ans);
	}}