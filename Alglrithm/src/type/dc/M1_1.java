/*
��ͷ� ����� Binary Search

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
package type.dc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class M1_1 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, k; 
    static int[] arr; 

    static int bsearch(int start, int end, int num) {

        // 2\. �������� -> ���� start�� end������ ��ġ�� (������)
        if(start >= end) {
            //��ã�Ҵ�!
            System.out.print("X");
            return -1; 
        }

        // 1\. ��ͱ��� 
        // binary search : 1\. ���� (�ʼ�) 2\. mid�� Ȱ���ؼ� üũ
        int mid = (start + end) / 2; 

        // �������� �ɰ����ų�
        // ���� �߰����� ���� ã������ �ϴ� ������ ũ��
        if(arr[mid] > num)
            // �� ���� �������� �ɰ��� ã�ƺ�
            return bsearch(start, mid, num);

        // ���������� �ɰ����ų�
        // ���� �߰����� ���� ã������ �ϴ� ������ ������ 
        else if (arr[mid] < num)
            // �� ū �������� �ɰ��� ã�ƺ�
            return bsearch(mid+1, end, num);

        // ã�Ҵ�!
        else {
            System.out.print("O");
            return mid;
        }
    }

    public static void main(String[] args) throws IOException {

        // n input 
        n = Integer.parseInt(br.readLine());

        // n���� ��� �迭 input
        arr = new int[n]; // ���� �ؾ��Ҷ����� ũ�� ����
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) 
            arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        k  = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < k; i++) {
            int target = Integer.parseInt(st.nextToken());
            bsearch(0, n, target); 
        }

    }
}