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

        // 2\. 기저조건 -> 만약 start와 end구간이 겹치면 (만나면)
        if(start >= end) {
            //못찾았다!
            System.out.print("X");
            return -1; 
        }

        // 1\. 재귀구성 
        // binary search : 1\. 정렬 (필수) 2\. mid를 활용해서 체크
        int mid = (start + end) / 2; 

        // 왼쪽으로 쪼개지거나
        // 만약 중간값이 내가 찾으려고 하는 값보다 크면
        if(arr[mid] > num)
            // 더 작은 영역으로 쪼개서 찾아봄
            return bsearch(start, mid, num);

        // 오른쪽으로 쪼개지거나
        // 만약 중간값이 내가 찾으려고 하는 값보다 작으면 
        else if (arr[mid] < num)
            // 더 큰 영역으로 쪼개서 찾아봄
            return bsearch(mid+1, end, num);

        // 찾았다!
        else {
            System.out.print("O");
            return mid;
        }
    }

    public static void main(String[] args) throws IOException {

        // n input 
        n = Integer.parseInt(br.readLine());

        // n개의 요소 배열 input
        arr = new int[n]; // 정렬 해야할때에는 크기 주의
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