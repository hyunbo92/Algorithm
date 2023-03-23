/*
재귀로 만드는 Binary Search

정렬 되어있는 데이터를 검색할 때,
for문으로 찾는 것보다 Binary Search로 더 빨리 원하는 값을 찾을 수 있습니다.
(Binary Search와 Binary Search Tree는 다릅니다.)

e8d5eff447fb8f5e3aa3411db7833d11.png

n개의 배열 값을 입력 받아주세요.
그리고 찾아야 하는 수를 입력 받고,
배열 내 존재하는 값인지 아닌지 O(log N) 속도로 찾는 프로그램을 작성해 주세요.

입력
첫 줄에는 수 n을 입력 받습니다. (1 <= n <= 100,000)
다음 줄에는 n개의 수가 입력으로 주어집니다. (n = Integer범위)
그리고 수 k가 입력됩니다. ( 1 <= n <= 1,000)
다음줄에는 k 개의 찾아야 하는 수가 입력됩니다.

출력
k개의 수가 배열 안에 존재하는지출력하는 프로그램을 작성해주세요.
각 수마다, 존재한다면 "O"를, 존재하지 않다면 "X"를 출력합니다.

입력 예시 1 
10
20 22 23 24 4 4 5 7 8 10
10
1 2 3 4 5 6 7 8 9 10

출력 예시 1
XXXOOXOOXO

입력 예시 2 
5
-10 -40 -30 -20 50
3
-10 0 10

출력 예시 2
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
            bsearch(0, n, target); 
        }

    }
}