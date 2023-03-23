/*
개구리 점프

통나무 N개가 가로 (수평) 방향으로 연못에 떠 있다. 개구리는 한 통나무 A에서 다른 통나무 B로 정확히 수직 방향으로 점프할 수 있습니다.
단, 점프할 때 다른 통나무 위를 (끝 점 포함) 지나면 안됩니다.
예를 들어 <그림 1>에서 1번 통나무에서 2번 통나무로 점선을 따라 개구리가 점프하는 것이 가능합니다.
1번 통나무에서 2번 통나무로 점프한 후 다시 3번 통나무로 점프하면 1번 통나무에서 3번 통나무로 이동하는 것이 가능합니다.
(통나무 위에서 걸어서 움직이는 것은 언제든 가능합니다.)

<그림 1>

통나무들의 위치를 입력받아 질문으로 주어진 통나무들의 쌍에 대해서 개구리가 한 통나무에서 다른 통나무로 한번 이상의 점프로 이동이 가능한지 판단하는 프로그램을 작성해 주세요.

입력
첫 번째 줄에 통나무 개수 N과 질문의 개수 Q가 주어집니다.
다음 N개의 줄에 각 통나무에 x1, x2, y의 세 정수 좌표가 주어집니다.
주어진 통나무는 두 점 (x1, y)와 (x2, y)를 잇는 형태입니다. (x1< x2) 모든 좌표는 0이상 1,000,000,000이하 입니다.
통나무들은 주어진 순서대로 1번부터 번호가 붙어 있습니다.
서로 다른 두 통나무는 (끝점에서도) 만나지 않습니다.
다음 Q개의 줄에 서로 다른 두 통나무의 번호가 주어집니다. (1 ≤ N ≤ 100,000, 1 ≤ Q ≤ 100,000)

출력
Q개의 줄을 출력합니다.
각 줄에는 주어진 순서대로 질문에 대한 대답이 출력되어야 합니다.
질문에 주어진 두 통나무에 대해서 개구리가 한 통나무에서 다른 통나무로 한번 이상의 점프로 이동이 가능한 경우 대답은 1, 그렇지 않은 경우 대답은 0 입니다.

 */
package type.dat;

import java.io.*;
import java.util.*; 

public class M2_3 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    static StringTokenizer st; 
    static int n, q; 

    // 통나무 class
    static class Log implements Comparable <Log> {
        int start;
        int end; 
        int height; 
        // ** 추가적으로 필요한것 : 통나무 번호
        int num; 

        // constructor 
        Log(int start, int end, int height, int num) {
            this.start = start;
            this.end = end;
            this.height = height;
            this.num = num; 
        }

        // 정렬 기준
        @Override
        public int compareTo(Log next) {
            // start 기준 가장 작은것부터 
            if(start < next.start)
                return -1;
            if(start > next.start)
                return 1;
            if(end < next.end)
                return -1;
            if(end > next.end)
                return 1;
            return 0; 
        }
    }

    static Log[] logs; 
    // 그룹을 할 DAT 
    // index : 통나무 번호, value : 어떤 그룹인가? 
    static int[] group; 

    public static void main(String[] args)throws IOException { 
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        // init 
        logs = new Log[n];
        // 최대 n+1개의 그룹이 생길수 있다!
        // (만약 모든 통나무가 떨어져있는 상태) 
        group = new int[n+1];

        // 통나무 input 
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken()); 
            // 통나무 시작위치, 끝위치, 높이, "통나무 번호" 
            logs[i] = new Log(start, end, height, i+1);
        }

        // 먼저 그룹핑 
        // 1. 저희가 정의한 기준으로 통나무 정렬 
        Arrays.sort(logs);

        // 2. 하나씩 선태해와서 그룹핑 작업 
        // 당연히 첫 통나무는 무조건 선택 <- 지금 내가 선택한 통나무 
        Log cur = logs[0]; 
        int g = 1; 
        // 지금 이 통나무의 번호 -> 1번 그룹으로 
        // -> 맨 왠쪽의 통나무 = 1번 그룹 -> 떨어진게 있으면 그룹++ 
        group[cur.num] = g; 

        // logic
        for(int i = 1; i < n; i++) {
            // 다음 통나무 
            Log next = logs[i];
            // 지금 내가 선택한 통나무의 시작점 <= 이 전 통나무의 끝점 
            if(next.start <= cur.end) {
                // --------
                //      ------- 
                // ----
                //     ----
                // ------ <-- 예외케이스 log가 갱신되선 안됨!
                //   --
                // 지금 선택한 통나무의 끝점 > 이 전 통나무의 끝점보다 멀리 나간다면
                if(next.end > cur.end) {
                    // 다음에 비교해야할 통나무 갱신 
                    cur = next; 
                }
            }
            // 만약 떨어져 있는 통나무라면 
            // ----         
            //           -----
            else {
                // 새로운 그룹 
                g++; 
                // 비교할 통나무 갱신 
                cur = next; 
            }
            // 지금 선택한 통나무의 그룹 확정 
            group[next.num] = g;  
        }

        // query 입력
        for(int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // a와 b가 같은 그룹에 있으면 1
            if(group[a] == group[b])
                System.out.println(1);
            // 아니면 0
            else
                System.out.println(0);
        }
    }
}