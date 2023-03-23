/*
오염수 정화 1

화학연구소에서는 오염수를 깨끗한 물로 정화시키는 솔루션을 개발하고 있다.
해당 솔루션의 정화 수치는 1부터 1,000,000,000까지의 양의 정수로 표기하고,오염수의 오염도는  -1부터 -1,000,000,000까지의 음의 정수로 표기하고 있다.
깨끗한 물은 0에 가까운 오염도를 의미한다. 정화 수치가 너무 높으면 인체에 해가 될 수 있기 때문에, 최대한 오염도가 0에 가깝게 정화시켜야 한다.
화학연구소에서 가진 샘플 중 오염수와 솔루션을 하나씩 섞어, 최대한 깨끗한 물에 가깝게 정화하려고 한다.

image

예를 들어, [-2, 4, -99, -1, 98] 의 샘플이 있다면, -2, -99, -1의 오염도를 지닌 오염수가 있고, 4, 98의 정화 수치를 가진 솔루션이 있다는 것이다.
여기서 -99의 오염수와 98의 솔루션을 섞으면 -1의 오염도를 가진, 가장 깨끗한 물에 가깝게 정화시킬 수 있다.
샘플의 상황에 따라, 오염수 또는 솔루션이 부족할 경우, 오염수끼리, 또는 솔루션끼리 섞어 깨끗한 물에 가깝게 정화시켜야 할 수도 있다.
오염수와 솔루션의 샘플이 주어졌을 때, 두 개의 샘플을 혼합하여 오염도가 0인 깨끗한 물에 가장 가깝게 정화시키는 두 샘플을 찾아내는 프로그램을 작성하시오.

입력
첫번째 줄에는 샘플의 수 N이 입력된다. (2 <= N <= 100,000)두번째 줄에는 각 샘플의 오염도와 정화 수치를 나타내는 N개의 정수가 빈칸을 사이에 두고 주어진다.
이 수들은 모두 -1,000,000,000 이상 1,000,000,000 이하이다.
N개의 샘플의 값들은 모두 다르고, 오염수 또는 솔루션만으로 입력이 주어질 수도 있다.

출력
오염도가 0에 가장 가까운 깨끗한 물을 만들어내는 두 샘플의 오염도 또는 정화 수치를 오름차순으로 출력한다.
오염도를 0으로 정화할 수 있는 오염수와 솔루션의 쌍이 여러개라면, 두 샘플의 수치의 절대값의 합이 가장 큰 것을 출력한다.

입력 예시 1 
5
-2 4 -99 -1 98

출력 예시 1
-99 98
 */

package type.TwoPointer;


import java.io.*;
import java.util.*; 

public class M1_5 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n;
    static int[] arr; 

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        // init
        arr = new int[n]; 
        // input 
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // solve

        // ** 정렬 
        Arrays.sort(arr);

        // two pointer 

        // 왼쪽 포인터 : 가장 작은 값
        // 오른쪽 포인터 : 가장 큰 값 
        int left = 0; 
        int right = n-1;

        // 0이 아닐때 제대로 된 방향으로 갈수 있도록
        // 지금 조합의 0부터의 거리 (절대값)의 최저값을 저장을 하고 있어야 합니다. 
        // -1 -> 0 = 1, 1 -> 0 -> 1
        int diff = Integer.MAX_VALUE;
        // 이 위치가 어디였는지 
        int minans = 0;
        int maxans = 0; 

        // 언제까지 반복? 
        // [] [] [] [] 
        //     ^ ^
        // 요 두개의 포인터가 겹칠때까지 반복 
        while(left < right) {

            // 이전 문제와 달리 계속해서 누적된 합이 아닌, left, right 포인터의 합만 보면 되는것. 
            int sum = arr[left] + arr[right]; 

            // 방향성을 설정하는 조건 
            // 1. sum == 0 
            if(sum == 0) {
                System.out.println(arr[left] + " " + arr[right]);
                return; 
            }

            // 0이라는 답이 안나올시 0에서 가장 가까운 합을 만들던 두개의 솔루션을 출력
            // 가장 가까운 답을 계속해서 기록 / 갱신 
            // 두 포인터의 합의 절대값 = 0부터의 거리
            if(Math.abs(sum) < diff) {
                // 갱신
                diff = Math.abs(sum);
                minans = arr[left];
                maxans = arr[right]; 
            }

            // 2. sum > 0
            // 지금 left + right 포인터의 합이 너무 크다면 -> 좀더 작은것을 더해봐야 함
            // left --> 더 커질것 (합이 커짐)
            // right <-- 값이 더 작아짐 (합이 작아짐) 
            if(sum > 0) {
                right--; 
            }
            // 3. sum < 0
            else {
                left++; 
            }
        }
        System.out.println(minans + " " + maxans);
    }
}