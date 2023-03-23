package type.dc;

import java.io.*;
import java.util.*;

public class M1_SegmentTree {
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
		// ���� �ڽ� ��(left~mid������ ��)
		int leftValue = init(node * 2, left, mid); 
		// ������ �ڽ��� ��(mid+1~right)
		int rightValue = init(node * 2 + 1, mid + 1, right); 
		
		tree[node] = leftValue + rightValue;
		return tree[node];
	}
	// node, left, right <- 3���� ���� : tree��� ������ Ž���Ҷ� �ʿ��� ����
	static int query(int node, int left, int right, int start, int end) {
		// node��°, left~right��� ������ ���� �������
		// start~end��� ������ ���� ��
		
		// 1. ������ ������ ����� ��� 
		if(right < start || end < left) // ���ϴ� data�� ���Ե��� �ʴ´�.
			return 0; // <- *�ʿ� ���� data(����� ������ ���� ���� data)*
		
		// 2. ������ ������ ���ԵǴ� ���(���� ���ϴ� ���� �� ���� �ִ� ���)
		if(start <= left && right <= end)
			return tree[node]; // left~right������ ���� ������ ��°�� �������� ���
		
		// 3. ��ġ�� ���(�ָ��ϰ� �����ִ� ���)
		int mid = (left + right) / 2;
		// <- left~mid���� ������ start~end�� ���ԵǴ� data�� ���� ���
		int leftValue = query(node * 2, left, mid, start, end); 
		// <- mid+1~right���� ������ start~end�� ���ԵǴ� data�� ���� ���
		int rightValue = query(node * 2 + 1, mid + 1, right, start, end);
		
		return leftValue + rightValue;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		int N = 10; // data ����
		tree = new int[N * 4]; 
		// segment tree�� ũ�� : data ���� * 4�� ������
		init(1, 0, N-1);
		int de = 1;
		//{1,2,3,4,5,6,7,8,9,10}; 
		System.out.println(query(1,0,N-1,  2, 5));
		System.out.println(query(1,0,N-1,  3, 8));
		System.out.println(query(1,0,N-1,  4, 5));
		System.out.println(query(1,0,N-1,  1, 6));
		
	}

}