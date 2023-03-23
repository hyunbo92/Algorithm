/*
�ΰ��� ��

2���� ��鿡 �� N���� ������ �����ֽ��ϴ�.N���� ���� �߿� �� ���� �����Ͽ� ������ �ű���� �մϴ�.
�� ���� (Xa,Ya),(Xb,Yb)��� ���� ��, ���� |Xa-Xb| <= K�� �����ϵ��� ���� �ؾ� �մϴ�.
�� ���� ���� �������� ��, Ya + Yb�� �ִ밡 �Ǵ� ���� ���ϴ� ���α׷��� �ۼ����ּ���.
[����]
������ ���� �Է��� �ð�ȭ �� ���Դϴ�.

image.png

K = 2�϶�,|Xa-Xb| <= K�� �����ϴ� ���� ���� ��, Ya + Yb�� �ִ��� ������ ������ �����ϴ�.

image.png

�Է�
ù ��° �ٿ� ���� ���� ���� N�� K�� �Էµ˴ϴ�. (2 �� N��3,000,000, 0�� K�� 2,000,000,000)
���� N���� �ٿ��� ���� ��ǥ X, Y�� �Էµ˴ϴ�.(-1,000,000,000��X, Y��1,000,000,000)
�ߺ��Ǵ� ��ġ�� ���� �־����� �ʽ��ϴ�.

���
ù ��° �ٿ�|Xa-Xb| <= K�� �����ϴ� �� ��, �ִ밡 �Ǵ�Ya + Yb�� ���� ����մϴ�.

�Է� ���� 1 
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

��� ���� 1
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
	
	static int tree[]; // <- segment tree : y��ǥ�� �ִ�
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
	
	// �������� version
	static int ps(int start, int end, int target, int index) {
		// start~end��� ���������� target�̶�� x���� ���� �ִ� index

		if(start > end)
			return index;
		// �߰���
		int mid = (start + end) / 2;
		
		if(coord[mid].col <= target) // target�������� �����Ƿ� ������ ������ Ž���Ͽ� �� ū x�� ã��
		{
			index = mid; // ���ɼ� ���θ� Ȯ���غ��� �� �����ϴ���
			return ps(mid + 1, end, target, index);
		}
		else // target���� mid�� ũ�Ƿ� ���� �������� Ž��
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
		
		Arrays.sort(coord); // <- parametric search�� �� �غ�
		
		init(1,0,N - 1);
		int ans = -213457890;
		for(int i = 0; i < N; i++)
		{
			Coord now = coord[i]; // ������ �� ��
			
			// ������ �� ������ ���� x�Ÿ��� K������ ���� ã��
			int target = now.col + K;
			// -1�� �ʱⰪ���� �� ���� : �ش� data�� index�� �ǹ̷� ���, ã�� ���ϸ� index�� -1�� ��������
			// ã�����ߴ����� �Ǻ��� �� �ֵ���
			int index = ps(i + 1, N-1, target, -1); // i���� �����̴� ������ �����ϰ�
			if(index == -1) continue; // ã�� ���ߴٶ�� ����
			
			
			// now�κ��� x��ǥ �Ÿ��� K�̳��� ���� : i��~index��
			// i+1��~index�� ���̿��� �ִ� y��ǥ //<- segment tree�� ��û
			int maxRow = query(1, 0, N-1, i+1, index);
			ans = Math.max(ans, now.row + maxRow);
		}
		System.out.println(ans);
	}
}