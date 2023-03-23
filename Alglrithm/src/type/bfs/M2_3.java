/*
하이퍼 튜브 20230321 

N개의 역이 있는 은하계에서 아주 먼 거리를 가장 빠르게 이동할 수 있는 교통수단으로 하이퍼 튜브가 있습니다.
각 하이퍼 튜브는 서로 다른K개의 역을 연결하고 있습니다.
최소한의 환승을 하여 1번 역에서N번 역으로 이동하고자 할 때,방문하는 역의 수는 몇 개인지 출력하는 프로그램을 작성해주세요.

[예시]

아래는 예제 입력을 시각화 한 것입니다.
빨간색 점들은 하이퍼 튜브를, 파란색 점들은 역을 표현합니다.

image.png

1번 역에서 시작하여 N(9)번 역으로 이동하고자 할 때, 방문하는 최소 역의 개수는 아래 경로로4개가 됩니다.
1 -> 3 -> 6 -> 9

image.png

1 -> 5 -> 6 -> 9
image.png

입력
첫째 줄에는3개의 자연수N, K, M이 주어집니다. N은 역의 수, K는 하나의 하이퍼 튜브가 연결하는 역의 수, M은 하이퍼 튜브의 수를 의미합니다.(N ≤ 100,000, K≤ 1,000, M≤ 1,000)
이후 M개의 줄에 걸쳐 한 줄에 하나의 하이퍼 튜브에 대한 정보가 주어집니다.
하이퍼 튜브에 대한 정보는 총K개의 자연수로 주어지며 이 자연수는 해당 하이퍼 튜브가 연결하는 역의 번호입니다.

출력
최소한의 환승으로1번 역에서N번 역으로 이동할 때,방문하는 역의 수를 출력합니다.
1번 역에서N번 역으로 갈 수 없을 경우 -1를 출력합니다.

입력 예시 1 
9 3 5
1 2 3
1 4 5
3 6 7
5 6 7
6 8 9

출력 예시 1
4
 */
package type.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class M2_3 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, k, m;
    // 인접 리스트
    static ArrayList<Integer>al[]; 

    static int bfs(int node) {
        // 1. queue
        Queue<Integer>q = new LinkedList<>();
        q.add(node); 

        // 2. visited
        int[]visited = new int[n+m+1];
        visited[node] = 1;

        // bfs
        while(!q.isEmpty()) {
            int now = q.poll();
            // now로 부터 갈수있는 노드 확인
            for(int i = 0; i < al[now].size(); i++) {
                int next = al[now].get(i);
                // 방문 체크
                if(visited[next] > 0)
                    continue;
                // ** 여태까지의 문제와 다른것 
                // 내가 N번 노드까지 도착하기 위해서 몇개를 거쳐왔는가?
                // visited를 다르게 쓸수 있는 방법 
                // --> 여태까지 몇개의 노드를 거쳐왔는가를 기록하는데에 사용 가능
                visited[next] = visited[now] + 1;
                q.add(next); 
            }
        }
        // visited[N] -> N번 노드까지 총 몇개의 노드를 거쳐왔는가? 
        return visited[n]; 
    }

    public static void main(String[] args) throws IOException {
         st = new StringTokenizer(br.readLine());
         n = Integer.parseInt(st.nextToken());
         k = Integer.parseInt(st.nextToken());
         m = Integer.parseInt(st.nextToken());

         // arraylist init 
         // 노드의 개수(1부터 시작) + 하이퍼 튜브도 하나의 노드로 인식 
         al = new ArrayList[n + m + 1];
         for(int i = 0; i <= n + m; i++)
             al[i] = new ArrayList<>();

         // 하이퍼튜브 input 
         for(int i = 1; i <= m; i++) {
             st = new StringTokenizer(br.readLine());
             // 하이퍼 튜브의 번호 작업
             // 노드 : 1~N의 번호를 사용 
             // 하이퍼 튜브들 : N+@ 넘어가는 번호들을 사용
             int ht = n + i; 
             for(int j = 0; j < k; j++) {
                 // from -> to 연결 인접리스트
                 // 양방향 연결
                 int to = Integer.parseInt(st.nextToken());
                 al[ht].add(to);
                 al[to].add(ht);
             }
         }
         // 1 -> n번노드까지
         int ans = bfs(1);
         if(ans == 0)
             System.out.println(-1);
         // 노드 -> 튜브 ->노드 ->튜브 ->....->노드
         // 최종 목적지 = 항상 노드개수 = 튜브의 개수 + 1
         // 지금까지 거쳐온 (경로 + 1) / 2 -> 방문한 노드의 개수 
         else
             System.out.println((ans+1) / 2);

    }
}