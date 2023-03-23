package type.BinarySearch;

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
            bsearch(0, n-1, target); 
        }

    }
}