/*
Binary Search 20230321

���� �Ǿ��ִ� �����͸� �˻��� ��,
for������ ã�� �ͺ��� Binary Search�� �� ���� ���ϴ� ���� ã�� �� �ֽ��ϴ�.
(Binary Search�� Binary Search Tree�� �ٸ��ϴ�.)

e8d5eff447fb8f5e3aa3411db7833d11.png

n���� �迭 ���� �Է� �޾��ּ���.
�׸��� ã�ƾ� �ϴ� ���� �Է� �ް�,
�迭 �� �����ϴ� ������ �ƴ��� O(log N) �ӵ��� ã�� ���α׷��� �ۼ��� �ּ���.

�Է�
ù �ٿ��� �� n�� �Է� �޽��ϴ�. (1 <= n <= 100,000)
���� �ٿ��� n���� ���� �Է����� �־����ϴ�. (n = Integer����)
�׸��� �� k�� �Էµ˴ϴ�. ( 1 <= n <= 1,000)
�����ٿ��� k ���� ã�ƾ� �ϴ� ���� �Էµ˴ϴ�.

���
k���� ���� �迭 �ȿ� �����ϴ�������ϴ� ���α׷��� �ۼ����ּ���.
�� ������, �����Ѵٸ� "O"��, �������� �ʴٸ� "X"�� ����մϴ�.

�Է� ���� 1 
10
20 22 23 24 4 4 5 7 8 10
10
1 2 3 4 5 6 7 8 9 10

��� ���� 1
XXXOOXOOXO

�Է� ���� 2 
5
-10 -40 -30 -20 50
3
-10 0 10

��� ���� 2
OXX
 */
package type.BinarySearch;


import java.io.*;
import java.util.*; 

public class M1_7 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int n, k; 
	static int[] arr; 
	
	// binary search
	// ���� ���߱� ���� ����
	// num == target
	static void bsearch(int num) {
		// 1. ���� ����
		int left = 0; 
		int right = n-1; 
		
		// Ž��
		while(left <= right) {
			// 1. binary search �׻� "�߰�"���� Ȯ��
			int mid = (left + right) / 2; 
			
			// ���� ���� 
			// mid > target 
			if(arr[mid] > num) {
				right = mid -1; 
			}
			
			// mid < target
			else if (arr[mid] < num) {
				left = mid +1; 
			}
			
			// mid == target 
			else {
				System.out.print("O");
				return; 
			}
		}
		// ������ ã�ƺôµ� ����!
		System.out.print("X");
	}
	
	
	public static void main(String[] args) throws IOException {
		
		// input 
		n = Integer.parseInt(br.readLine());
		// init
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) 
			arr[i] = Integer.parseInt(st.nextToken()); 
		// ** �ʼ� : ����
		Arrays.sort(arr);
		
		k = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < k; i++) {
			int num = Integer.parseInt(st.nextToken());
			bsearch(num); 
		}
	}
}