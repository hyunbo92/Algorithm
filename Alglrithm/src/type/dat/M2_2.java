/*
회의실 배정

회의실이 단 하나인 중소기업이 있습니다. 하나밖에 없는 회의실을 사용하기 위해, 여러 팀들이 예약을 합니다. 회의실 관리자는 한 회의가 얼마나 길고 짧은지는 고려하지 않고, 더욱 많은 회의가 이루어지길 원합니다. 예약 스케쥴을 입력받고, 최대 몇번의 회의가 가능한지 출력해 주세요. image.png 위 이미지는 총 여섯 번 회의 예약의 예시입니다.
예약 신청 : 1 \~ 6시 까지
예약 신청 : 3 \~ 8시 까지
예약 신청 : 8 \~ 9시 까지
예약 신청 : 2 \~ 4시 까지
예약 신청 : 4 \~ 6시 까지
예약 신청 : 7 \~ 9시 까지
위와 같은 예시에서 최대 세 번의 회의가 가능하므로, 출력결과는 3 입니다.

입력
회의실 예약 스케쥴의 개수 N을 입력 받습니다. (1 <= N <= 100,000) 그 다음 줄 부터, N개의 예약스케쥴을 입력 받습니다. [시작값(a) \~ 끝값(b)] (1 <= a, b <= 100,000,000)

출력
가능한 회의의 최대 개수를 출력해 주세요.

입력 예시 1 
6
1 6
3 8
8 9
2 4
4 6
7 9

출력 예시 1
3
 */
package type.dat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer; 

public class M2_2 {

	   static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
	    static StringTokenizer st; 

	    static class Meeting implements Comparable <Meeting> {
	        // 회의의 시작시간 
	        int start;
	        // 회의의 종료 시간
	        int end; 

	        //constructor
	        Meeting(int start, int end) {
	            this.start = start;
	            this.end = end; 
	        }

	        // 정렬 기준
	        // 종료 시간이 빠른것 우선으로 정렬
	        @Override 
	        public int compareTo(Meeting next) {
	            // 만약 지금 회의의 종료 시간이 다음 종료시간보다 빠르다면 = 정상! -1
	            if(end < next.end)
	                return -1;
	            if(end > next.end) 
	                return 1; 
	            // 종료시간이 같으면 -> 시작 시간이 빠른거부터
	            if(start < next.start)
	                return -1;
	            if(start > next.start)
	                return 1;
	            return 0; 
	        }
	    }

	    public static void main(String[] args)throws IOException { 
	        int n = Integer.parseInt(br.readLine());

	        // init
	        Meeting[] m = new Meeting[n]; 
	        for(int i = 0; i < n; i++) {
	            int start, end;
	            st = new StringTokenizer(br.readLine());
	            start = Integer.parseInt(st.nextToken());
	            end = Integer.parseInt(st.nextToken());
	            m[i] = new Meeting(start, end) ;
	        }

	        // solve
	        // 1. 빨리 끝나는 순으로 정렬 
	        Arrays.sort(m); 

	        // 2. 하나씩 처음부터 빨리 끝나는 회의 순으로 접근해서 
	        // 그 회의에 들어갈수 있으면 -> cnt 누적한다 

	        // 첫번째 회의를 기준으로 세움
	        int cnt = 1; 
	        // 지금 내가 들어간 회의의 "종료 시간" 
	        int time = m[0].end; 

	        // 남은 회의들을 하나씩 탐색하며 비교
	        for(int i = 1; i < n; i++) {
	            // 만약 i번쨰 회의에 들어갈 수 있다면 
	            // -> i번째 회의의 시작 시간이 >= 지금 진행하고 있는 회의 시간의 종료 시간  
	            // 위 조건이 맞으면 i번째 회의를 진행할 수 있다!
	            if(m[i].start >= time) {
	                cnt++; 
	                // 이제 새로운 회의를 진행 -> 내가 기록하고 있는 "지금" 종료시간을 갱신
	                time = m[i].end;
	            }
	        }
	        System.out.println(cnt);
	    }
	}