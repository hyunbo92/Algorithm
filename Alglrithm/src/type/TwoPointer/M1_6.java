/*
반 대표

민코딩고등학교에는 N개의 반이 있으며, 반마다 M명의 학생이 있다. 모든 학생들은 저마다의  코딩 실력을 가지고 있고, 이 실력은 학생마다 전부 다르다.
민코딩 고등학교에서 진행하는 코딩콘테스트는 각 반마다 한 명의 반 대표를 선발하여 진행한다.
대회의 긴장감과 재미, 그리고 형평성을 위하여 각 반에서 선발된 대표들의 실력을 최대한 공평하게 하려고 한다.
그렇게 하기 위해 선정된 반 대표들의 최대 실력과 최소 실력의 차이가 최소가 되어야 한다.
예를 들어, N=3, M=4일때, 다음과 같은 반이 있다고 가정해보자.
1반 : [12, 16, 67, 43]
2반 : [7, 17, 68, 48]
3반 : [14, 15, 77, 54]
여기서 각 반에서 16, 17, 15의 능력치를 가진 학생들을 선별하면, 실력의 최대치와 최소치의 차이가 17-15 = 2로 최소치가 된다.
반 대표로 선발된 학생들의 코딩 실력의 최대치와 최소치의 차이가 최소가 되는 경우의 값을 출력하는 프로그램을 작성하시오.

입력
첫 번째 줄에는 학급의 수를 나타내는 N과 각 학급의 학생의 수를 나타내는 M이 하나의 빈칸을 사이에 두고 주어진다. (1 ≤ N, M ≤ 1,000)
두 번째 줄부터 N개의 줄에는 각 줄마다 각 반의 학생들의 코딩 실력을 나타내는 M개의 양의 정수가 하나의 빈칸을 사이에 두고 주어진다.
능력치는 0이상 10^9이하이다.

출력
선발된 반 대표들의 코딩 실력의 최대치와 최소치의 차이가 최소가 되는 경우의 값을 하나의 정수로 출력한다.

입력 예시 1 
3 4
12 16 67 43
7 17 68 48
14 15 77 54

출력 예시 1
2

입력 예시 2 
4 3
10 20 30
40 50 60
70 80 90
100 110 120

출력 예시 2
70
 */
package type.TwoPointer;


import java.io.*;
import java.util.*; 

public class M1_6 {
	  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    static StringTokenizer st;

	    static class Std implements Comparable <Std> {
	        int abil;
	        int num;
	        Std(int abil, int num) {
	            this.abil = abil;
	            this.num = num;
	        }
	        // 능력치 기준으로 정렬 
	        @Override 
	        public int compareTo(Std next) {
	            if(abil < next.abil)
	                return -1;
	            if(abil > next.abil)
	                return 1;
	            return 0; 
	        }
	    }

	    // n : 반의 개수 
	    // m : 각 반의 학생의 수
	    static int n, m;


	    public static void main(String[] args) throws IOException{
	        st = new StringTokenizer(br.readLine());
	        n = Integer.parseInt(st.nextToken());
	        m = Integer.parseInt(st.nextToken());

	        // init -> 2차원을 1차원으로 누름
	        Std[] arr = new Std[n*m];

	        int idx = 0; 
	        for(int i = 0; i < n; i++) {
	            st = new StringTokenizer(br.readLine());
	            for(int j = 0; j < m; j++) {
	                int abil = Integer.parseInt(st.nextToken());
	                arr[idx] = new Std(abil, i);
	                idx++; 
	            }
	        }

	        // 투포인터 탐색을 위해 정렬
	        Arrays.sort(arr);

	        // two pointer 세팅
	        int left = 0;
	        int right = 0; 

	        int cnt = 0; // 모든 학급이 포함되었는지를 확인 -> cnt == n 이면 모든 학급 포함
	        // DAT : index = 반 번호, value = 해당 반의 학생이 총 몇명이 있는가? 
	        int[] DAT = new int[n]; 
	        int ans = Integer.MAX_VALUE;

	        while(right < n * m) {

	            //System.out.println(arr[right].abil + " " + arr[right].num);

	            // 조건 1 : 모든 학급을 포함시켜야 한다. 
	            // --> 모든 반 학생이 한명씩은 포함되었다면:
	            // cnt의 개수는 반의 개수와 동일하게 될 것. 
	            if(cnt == n) {

	                // 조건 2 (갱신) : 최대 능력치 - 최소 능력치 = min
	                // 포인터는 사용 -> 이동을 순서로 하고 있으므로, right 기준 이동하기 전의 값을 활용
	                // 모든 반에 한명씩은 차출이 되었다면, 최대 - 최소의 값을 확인하고 갱신
	                if(arr[right-1].abil - arr[left].abil < ans)
	                    ans = arr[right-1].abil - arr[left].abil; 
	          
	                // 방향 설정 : 가능성을 찾았으니 왼쪽부터 빼봄 -> 중복된 반 멤버가 있었다면 다시 확인하게 될 것.
	                // DAT에서 left 포인터가 가르키는 반의 학생은 해제 (한명 차감)
	                DAT[arr[left].num]--;

	                // 만약 left반의 학생이 전부 없어지면 cnt도 차감
	                if(DAT[arr[left].num] == 0)
	                    cnt--; 

	                // left 포인터 이동 
	                left++; 
	            }
	            // --> 모든 반 학생이 아직 포함되지 않았다면 : 
	            else {

	                // 처음 들어오는 반의 학생이면 cnt 누적 
	                if(DAT[arr[right].num] == 0)
	                    cnt++; 
	                // 해당 반 학생 한명 추가
	                DAT[arr[right].num]++;
	                // 포인터 이동
	                right++; 
	            }
	        }
	        System.out.println(ans);
	    }
	}
