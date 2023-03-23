/*
�ܰ躰 Merge Sort

Merge Sort�� O(nlogn) �ӵ��� ������ ������ ���� Sort�Դϴ�.
�� Sort�� ũ�� �� ���� �ڵ�� ���� �Ǿ� �ֽ��ϴ�.
���� : ��͸� Ÿ�� ������ �����ϴ� �ڵ�
���� : �� ���ĵ� �迭�� �ϳ��� ��ġ�� �ڵ�
�Ʒ� �׸��� ���� ���ÿ�����, 1�� ~ 5������ ���������� ������ �̷�����ϴ�.
������ �� �� ����, ������ �Ϸ�� ���� ������ִ� ���α׷��� �ۼ� �� �ּ���.

�׸�1.png

�Է�
������ ������ ���� N�� �Է� �޽��ϴ�. (3 <= N <= 100,000)
�� ���� �ٿ��� N���Ǽ��ڸ� �Է� �޽��ϴ�.

���
Merge(����)�� ������ ������
�� �ٿ� ������ �Ϸ�� �迭 ������� ����ϼ���.

�Է� ���� 1 
6
4 7 2 1 8 5

��� ���� 1
4 7 
2 4 7 
1 8 
1 5 8 
1 2 4 5 7 8 
 */
package type.dc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class M1_3 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int arr[];
	
	static void merge(int left, int right) {
		// left ~ right������ ��ħ
		int mid = (left + right) / 2;
		
		int l = left; // ���� ������ ���� ���� ��
		int r = mid + 1; // ������ ������ ���� ���� ��
		
		int temp[] = new int[right - left + 1];
		int index = 0;
		while(l <= mid && r <= right) // <- ���� ������ ������ ������ ���Ͽ� ���� ������ ä���
			if(arr[l] < arr[r]) 
				temp[index++] = arr[l++];
			else
				temp[index++] = arr[r++];
		while(l <= mid) // ���� ������ ���Ҵٸ�
			temp[index++] = arr[l++]; // ���� ������ data�� ���� ä��
		while(r <= right) // ������ ������ ���Ҵٸ�
			temp[index++] = arr[r++]; // ������ ������ data�� ���� ä��
		
		for(int i = left; i <= right; i++)
			arr[i] = temp[i - left];
		
	}
	
	static void mergesort(int left, int right) {
		
		// data�� 1���ۿ� ������ ���� �ɰ��ų� ������ �ʿ䰡 X
		if(left >= right)
			return;
		
		int mid = (left + right) / 2;
		mergesort(left, mid); //<- ���� ���� ����
		mergesort(mid + 1, right); // <- ������ ���� ����
		
		merge(left, right);
		
		for(int i = left; i <= right; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		int N = Integer.parseInt(br.readLine());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		mergesort(0, N-1);
	}
}

