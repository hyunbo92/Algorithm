/*
N x M 최단 거리 20230320

N(행) x M(열) 크기의 2차원 맵이 주어졌을 때, (1, 1)위치에서 시작하여 (N, M)위치에 도달하기까지의 최단 거리를 출력하는 프로그램을 작성하시오.
각 격자에는 해당 위치로 이동하기 위한 거리가 주어지며, 상하좌우 방향으로만 이동이 가능합니다.
** 다익스트라 알고리즘을 활용하여 풀어 주세요.
[예시]
다음은 예제 입력 1을 시각화 한 것입니다.
예제 입력에서 주어진 맵에서, (1,1)으로부터 (N,M)까지의 최단 거리는 15입니다.

image.png

입력
첫번째 줄에 행의 크기 N과 열의 크기 M이 공백으로 구분되어 주어집니다. (1 <= N, M <= 100)
두번째 줄부터 N개의 줄에 걸처 M개의 양의 정수가 공백으로 구분되어 주어집니다.
정수는 1 이상, 10 이하의 정수가 주어집니다.

출력
첫번째 줄에 (1,1) 위치로부터 (N, M) 위치까지의 최단 거리를 출력합니다.

입력 예시 1 
4 4
0 1 3 8
1 2 3 4
2 9 7 5
3 4 6 0 

출력 예시 1
15

입력 예시 2 
5 4
10 5 9 5 
4 1 1 7 
7 5 1 6 
3 4 8 6 
3 4 3 6 

출력 예시 2
34
 */
package type.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class M3_4 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, m;
    static int[][] MAP; 

    static class Node implements Comparable <Node> {
        int y;
        int x;
        int cost;
        Node(int y, int x, int cost) {
            this.y= y;
            this.x=x;
            this.cost=cost;
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

    static int[] ydir = {0, 0, 1, -1};
    static int[] xdir = {1, -1, 0, 0}; 

    static void dijkstra(int y, int x) {
        // 1. PQ 설정
        PriorityQueue<Node>pq = new PriorityQueue<>();
        // MAP[0][0] = 0, 0 위치로 진입하는 비용 
        pq.add(new Node(y, x, MAP[y][x]));

        // 2. dist 설정
        // dist[] = index : 노드번호 value : 최단거리 
        // dist[][] = [y][x] 좌표의 최단거리 
        int[][] dist = new int[n][m];
        // 초기화
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        dist[y][x] = MAP[y][x]; 

        // dijkstra
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            // 갈 수 있는 인접한 방향 체크 
            for(int i = 0; i < 4; i++) {
                int ny = now.y + ydir[i];
                int nx = now.x + xdir[i];
                // ** 필수체크 ** 범위체크 
                if(ny < 0 || nx < 0 || ny >= n || nx >= m)
                    continue;
                // 다음 노드까지의 비용
                // 지금 좌표까지 오기 위해 사용한 비용 + 다음 좌표로 진입하기 위한 비용
                int ncost = dist[now.y][now.x] + MAP[ny][nx];
                // 지금까지 기록된 [ny][nx]까지의 최소비용보다 같거나 크면 pass
                if(dist[ny][nx] <= ncost)
                    continue; 
                dist[ny][nx] = ncost;
                pq.add(new Node(ny, nx, ncost));
            }
        }
        System.out.println(dist[n-1][m-1]);    
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        // map init
        MAP = new int[n][m];
        // input
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                MAP[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dijkstra(0, 0);
    }
}