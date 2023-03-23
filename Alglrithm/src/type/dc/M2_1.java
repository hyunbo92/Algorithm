/*
���� ���� ������ 20230314

Stanvard �������б����� �� �ؿ� �� 100������ ���� �մϴ�.
�̵��� ���� 1 �� ~ 1,000,000 �� ������ ������ �ֽ��ϴ�.
�� �л����� ���� ������ �������� �й��� �߱� �޽��ϴ�.
������� 32���� 32������ ������ �л��Դϴ�.
�� �������б����� ������ �����մϴ�.
�л����� �����Ӱ� �����ϰ�, ���� ��ҵ� �����մϴ�.

image.png

����� Standvard �������б��� �ý��� �������Դϴ�.
�����ؾ� �� �ý��ۿ��� �� ���� Command�� �����մϴ�.
1 [�й�] : Ư�� �й��� ����� ��ȸ�� �����մϴ�. �׸��� ���� ������ ����մϴ�.
2 [�й�] : Ư�� �й��� ����� ��ȸ ������ ����մϴ�. �׸��� ���� ��� ���� ����մϴ�.
����  1, 3, 43�� �л��� ��ȸ�� ������ ����� ��Ȳ����,
7�� �л��� ��ȸ�� ������ ����Ѵٸ�, ���� ������ 3�� �Դϴ�.
�л��� �濬��ȸ�� ���� ��û�� ���� ��, ���� ������ ������ִ� ���α׷��� �������ּ���.

�Է�
ù �ٿ��� Query�� ���� N �� �Էµ˴ϴ�. ( 1 <= N <= 1,000,000)
�� ��° �ٺ��� [Command ��ȣ] [�й�] �� N �ٿ� ���� �Էµ˴ϴ�.
(1 <= �й� <= 1,000,000)
* �߸��� �Է� ���� �־����� �ʽ��ϴ�.

���
��ȸ�� ������ �� ����,���� ������ ����մϴ�.
��ȸ ���� ����� �� ����, ��ȸ ���� ����� ������� ���� ����մϴ�.

�Է� ���� 1 
6
1 1
1 3
1 43
1 7
2 1
1 10

��� ���� 1
1��
2��
3��
3��
3��
3��

�Է� ���� 2 
18
1 15
1 14
1 13
1 12
1 11
2 11
1 11
1 1
1 2
1 3
1 20
2 1
2 2
2 3
1 21
1 1
1 2
1 3

��� ���� 2
1��
1��
1��
1��
1��
4��
1��
1��
2��
3��
9��
8��
7��
6��
7��
1��
2��
3��
 */
package type.dc;

import java.io.*;
import java.util.*;

public class M2_1 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int tree[];
	// init : � �������� data���� �ѹ��� �ʱ�ȭ ex) arr�̶�� �迭�� ���� segment tree�� �����ؾ� �ϴ°��
	static int update(int node, int left, int right, int idx, int val) {
		// 1. idx��� ����� left~right������ ���� ���
		if(idx < left || right < idx)
			return tree[node];
		
		// 2. leaf���� ������ ���(���� ���� ���� node)
		if(left == right) // left == right == idx
			return tree[node] = val; // �� �й��� ����� 1���̱⿡ �ٷ� ����
		
		// 3. left~right '����'�� idx�� ���ԵǴ� ���
		int mid = (left + right) / 2;
		int leftValue = update(node * 2, left, mid, idx, val);
		int rightValue = update(node * 2 + 1, mid+1, right, idx, val);
		return tree[node] = leftValue + rightValue;
	}
	
	static int query(int node, int left, int right, int start, int end) {
		// 1. left~right�� start~end������ ����� ���
		if(right < start || end < left)
			return 0;
		
		// 2. left~right�� start~end���� �ȿ� �Ϻ��� ���ԵǴ� ���
		if(start <= left && right <= end)
			return tree[node];
		
		// 3. left~right�� start~end������ '����'�ִ� ���
		int mid = (left + right) / 2;
		int leftValue = query(node * 2, left, mid, start, end);
		int rightValue = query(node * 2 + 1, mid +1, right, start, end);
		return leftValue + rightValue;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		tree = new int[1000001 * 4];
		
		int Q = Integer.parseInt(br.readLine());
		for(int i = 0; i < Q; i++)
		{
			st = new StringTokenizer(br.readLine());
			int oper = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			if(oper == 1)
			{
				update(1, 1, 1000000, num, 1);
				
				//1~num�̶�� '�� �̻��� �ɷ�'�� ���� ����� '�� ��'�ΰ�?
				System.out.println(query(1,1,1000000, 1, num) + "��");
			}
			else
			{
				update(1, 1, 1000000, num, 0);
				//��ü���
				System.out.println(query(1,1,1000000, 1, 1000000) + "��");
			}
		}
	}

}