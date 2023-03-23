/*
좀비 바이러스

좀비 바이러스가 창궐하여, 좀비가 된 시민들에게 백신을 투여하고자 합니다.
백신을 투여하면 3초 후 완치됩니다.
그리고 백신은 1초 마다 상하좌우로 인접한 좀비에게 퍼집니다.
예를들어 아래 이미지에서
X, Y 기준으로 (3, 5) 좌표에 있는 좀비에게 백신을 투여하면,
총 9초 후 모든 좀비들이 치유가 됩니다.

zombie.png

오른쪽 이미지에 적힌 숫자들은, 치유가 완료될 때 까지 걸리는 시간입니다.
만약 백신을 지닌 좀비와 이웃하고있지 않은 좀비는 치유가 되지 않습니다.
한 명의 좀비에게 백신을 투여할 때,
백신으로 인해 치유가 끝날 때 까지 걸리는 시간과,
치료 받지 못한 좀비의 수를 출력해 주세요.

입력
먼저 맵의 크기가 입력됩니다. ( Width x Height, 최대 100칸)
둘째 줄 부터 맵이 입력됩니다.
맵 정보에 적혀있는 숫자 1은 좀비가 있는 곳입니다.
마지막 줄에는 백신을 투여하는 위치가 입력됩니다. (X, Y)

출력
첫 번째 줄에는 백신 치료가 완료될 때까지 걸리는 시간을 출력하세요.
다음 줄에는 백신이 닿지 않는 좀비의 수를 출력해 주세요.

입력 예시 1 
7 8 
0010000 
0011000 
0001100 
1011111 
1111010 
0011110 
0011100 
0001000
3 5

출력 예시 1
9
0
 */
package type.FloodFill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class M2_8 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static class Node {
        int y; 
        int x;
        Node(int y, int x) {
            this.y = y;
            this.x = x; 
        }
    }

    static int w, h;
    static int[][] MAP; 
    static int x, y;
    static int zcnt = 0; // 좀비의 수 

    static int[] ydir = {0, 0, 1, -1};
    static int[] xdir = {1, -1, 0, 0}; 

    static int bfs(int y, int x) {
        // 1. queue 세팅
        Queue<Node>q = new LinkedList<>();
        q.add(new Node(y, x)); 

        // 2. visited 세팅
        int[][] visited = new int[h][w];
        // 3초후부터 백신이 퍼지기 시작 -> 3초로 초기화하여 시작
        visited[y][x] = 3;

        int ans = 0; 

        // 3. bfs
        while(!q.isEmpty()) {
            Node now = q.poll();

            // 좀비가 뽑힐때마다 최장 치유시간 갱신 
            ans = visited[now.y][now.x];

            // 방문 가능한 노드 == 백신이 투여되어서 치료될 좀비
            // 치유될 좀비는 총 좀비 수에서 하나씩 제거
            zcnt--; 

            // 상하좌우 방향 탐색
            for(int i = 0; i < 4; i++) {
                int ny= now.y + ydir[i];
                int nx = now.x + xdir[i];
                // 필수체크 - 범위
                if(ny < 0 || nx < 0 || ny >= h || nx >= w)
                    continue;
                // 방문체크
                if(visited[ny][nx] > 0)
                    continue;
                // ** 우리에게 주어진 맵 상에서 갈수 없는 경로라면 -
                // 맵에서 다음 위치가 좀비가 아니라면: pass
                if(MAP[ny][nx] == 0)
                    continue;
                // flood fill
                visited[ny][nx] = visited[now.y][now.x]+ 1;
                q.add(new Node(ny, nx)); 
            }
        }
        return ans; 
    }

    // 치유되지 않은 좀비를 찾는 간단한 방법 : 
    // visited기록된거 돌려보면서 MAP이랑 비교
    // MAP==1 (좀비가 있다고 되어있는데) -> visited == 0(기록이 없다) 
    // --> 치유되지 않은 좀비다! 

    // 다른방법 
    // 총 좀비의 수 - 치유된 좀배의 수 = 치유되지 않은 좀비의 수  

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        // x y 뒤집혀있음
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        // MAP init
        MAP = new int[h][w];
        for(int i = 0; i < h; i++) {
            String inp = br.readLine();
            for(int j = 0; j < w; j++) {
                MAP[i][j] = inp.charAt(j) - '0'; // '1' -> 1 문자->숫자 변경
                // 입력시 1 == 좀비, 0 == 땅
                if(MAP[i][j] == 1)
                    // 하나의 좀비가 더 있다!
                    zcnt++; // <- 최종적으로는 총 좀비의 수를 기록 
            }
        }
        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken()) - 1;
        y = Integer.parseInt(st.nextToken()) - 1;

        int ans = bfs(y, x); 
        System.out.println(ans);
        System.out.println(zcnt);
    }
}