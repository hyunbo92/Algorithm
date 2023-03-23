/*
무서운 시어머니 20230320

시어머니께 죄송한 말씀이지만, 자유로운 생활을 위해 독립을 하기로 결심했습니다.
산악회 회장 출신인 시어머니는새로 이사를 가더라도,최적의 루트로 우리집에 자주 방문할 것이 분명했습니다.
따라서 시어머니가 오시기 가장 힘든 곳으로 이사가려고 합니다.

wevwevwe.png

시어머니는 위, 아래, 왼쪽, 오른쪽 방향으로만 이동할 수 있으며,
각 칸에 쓰여진 숫자는 해당 위치로 이동했을 때 쌓이는 피로도 수치 입니다.

tretw.png

시어머니는 피로도가 최소가 되게끔 올 수 있는 경로로 우리집을 방문합니다.
피로도가 가장 높게 쌓이는 위치로 이사를 가는 경우,
시어머니가 우리집을 방문할 때 얻는피로도들의 총합을 출력해 주세요.
(위 예시에서는 1 + 0 + 0 + 1 + 1 + 1 + 8 = 12 만큼 피로도를 얻습니다.)

입력
첫 줄에는 시댁의 위치 좌표를 (Y, X)를 입력 받습니다.
다음 줄에는 마을의 크기 N 을 입력 받습니다. (3 <= N <= 1,000)
다음 줄부터 N x N 의 마을의 상태를 입력 받습니다.
-1은 벽이고, 그외의 수는 특정 칸에 방문했을때 얻는 피로도 입니다. (0 <= 피로도 <= 1,000)

출력
시어머니가 오기 힘든 위치로 이사를 갈 경우,
시어머니가 방문시 얻게되는 피로도의 총합을 출력해 주세요.

입력 예시 1 
2 1
4
3 3 3 0
3 -1 8 1
0 1 -1 1
2 0 0 1

출력 예시 1
12

입력 예시 2 
0 0
5
1 -1 2 1 2
3 -1 1 -1 1
0 2 0 -1 0
0 -1 0 -1 0
0 0 0 -1 5

출력 예시 2
16
 */
package type.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class M3_5 {


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static class Node implements Comparable <Node> {
        int y;
        int x;
        int cost;
        Node(int y, int x, int cost) {
            this.y= y;
            this.x = x;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node next) {
            if(cost < next.cost)
                return -1;
            if(cost > next.cost)
                return 1;
            return 0;
        }
    }

    static int sy, sx;
    static int n; 
    static int[][] MAP;

    static int[] ydir = {0, 0, 1, -1};
    static int[] xdir = {1, -1, 0, 0}; 

    static int dijkstra(int y, int x) {
        // 1. PQ 설정
        PriorityQueue<Node>pq = new PriorityQueue<>();
        pq.add(new Node(y, x, MAP[y][x]));

        // 2. dist 설정
        int[][]dist = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j =0; j < n; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        // 집까지 가는 비용 초기화 
        dist[y][x] = MAP[y][x]; 

        // 3. dijkstra
        int ans = Integer.MIN_VALUE;
        while(!pq.isEmpty()) {
            Node now = pq.poll();

            // 지금 노드에 오기까지 누적된 총 cost가 여태까지 기록된 가장 비싼 cost보다 크면
            if(now.cost > ans )
                // 정답 갱신 ! (여기는 우리가 방문 가능한 노드만 올수 있다)
                ans = now.cost; 

            for(int i = 0; i < 4; i++ ) {
                int ny = now.y + ydir[i];
                int nx = now.x + xdir[i];
                if(ny < 0 || nx < 0 || ny >= n || nx >=n )
                    continue;
                // 만약 갈수 없는 길이라면 pass
                if(MAP[ny][nx] == -1)
                    continue;
                int ncost = dist[now.y][now.x] + MAP[ny][nx];
                // [ny][nx]까지 이 전에 더 짧은 경로를 찾았었다 contine 
                if(dist[ny][nx] <= ncost)
                    continue;
                dist[ny][nx] = ncost;
                pq.add(new Node(ny, nx, ncost)); 
            }
        }
        /*
        int ans = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            for(int j =0; j < n; j++) {
                if(dist[i][j] > ans && dist[i][j] != Integer.MAX_VALUE)
                    ans = dist[i][j];
            }
        }
        */
        return ans;

    }


    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        sy = Integer.parseInt(st.nextToken());
        sx = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(br.readLine());
        // MAP init
        MAP = new int[n][n];
        // input
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                MAP[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 저희가 찾고자 하는것 :
        // 시어머니가 항상 최단거리로 이동을 하는데, 그 와중에 가장 힘들게 올수 있는 곳
        // --> "시어머니 집의 위치에서 모든 좌표의 최단 거리 중" 가장 먼 곳. 
        int ans = dijkstra(sy, sx);
        System.out.println(ans);
    }
}