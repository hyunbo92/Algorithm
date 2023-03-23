/*
안나와엘사M

빙하왕국의 엘사와 안나가 빙하 평원에서 길을 잃었습니다.
리본 머리띠를 하고 있는 안나는 엘사를 찾기 위해,
왕관 쓰고 있는 엘사는 안나를 찾기 위해,
서로 1초 마다 한 칸씩동시에 이동하며서로를 찾습니다.

anna.png

[엘사와 안나의 움직임]
엘사와 안나는 1 초에 한 칸씩 상, 하, 좌, 우로 움직일 수 있습니다.
그리고 제 자리에 멈추어 휴식을 취하는 것도 가능합니다.
단, 빙하는 지나가지 못합니다.
지도를 입력 받고, 빙하를 피해 엘사와 안나가 가장 빠르게 만날 수 있는 이동시간을 계산해 주세요.

[유의사항]

안나와 엘사가 동시에 움직여 엇갈릴 수도 있습니다.
반드시 만날 수 있는 길이 있는 입력 값이 주어집니다.

입력
첫 줄에 맵의 크기를 나타내는 수 N 을 입력 받아주세요. (1 <= N <= 5)
다음 줄부터 N x N 맵 정보를 입력 받습니다.
빈칸은 '_' (언더바) 이며, 빙하는 '#' 으로 표기됩니다.
그 다음줄에는엘사의 위치 (y, x) 와 안나의 위치 (y, x) 가 입력 됩니다.

출력
두 사람이 같은 좌표에서 만나게 되는, 최소 시간을 출력해 주세요.

입력 예시 1 
5
___#_
___#_
##___
__#__
_____
0 0 4 0

출력 예시 1
5
 */
package type.FloodFill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class M2_9 {

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

    static int n; 
    static char[][] MAP;
    static int annay, annax, elsay, elsax; 

    // 방향배열
    static int[] ydir = {0, 0, 1, -1};
    static int[] xdir = {1, -1, 0, 0}; 

    static int bfs(int annay, int annax, int elsay, int elsax) {
        // 1. queue 세팅
        // anna -> elsa 
        Queue<Node>q = new LinkedList<>();
        q.add(new Node(annay, annax));

        // 2. visited 세팅
        int[][] visited = new int[n][n]; 
        visited[annay][annax] = 1; 

        // 3. bfs 
        while(!q.isEmpty()) {
            Node now = q.poll();
            // 상하좌우 
            for(int i =0; i < 4; i++) {
                int ny = now.y + ydir[i];
                int nx = now.x + xdir[i];
                // 필수 범위체크
                if(ny < 0 || nx < 0 || ny >= n || nx >= n)
                    continue;
                // 방문 체크 
                if(visited[ny][nx] > 0)
                    continue;
                // 빙하는 지나갈수 없음
                if(MAP[ny][nx] == '#')
                    continue;
                visited[ny][nx] = visited[now.y][now.x] + 1;
                q.add(new Node(ny, nx));
            }
        }
        return visited[elsay][elsax] -1; 
    }


    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        // init
        MAP = new char[n][n];
        for(int i = 0; i < n; i++) {
            String inp = br.readLine();
            for(int j = 0; j < n; j++) {
                MAP[i][j] = inp.charAt(j); 
            }
        }
        st = new StringTokenizer(br.readLine());
        elsay = Integer.parseInt(st.nextToken());
        elsax = Integer.parseInt(st.nextToken());
        annay = Integer.parseInt(st.nextToken());
        annax = Integer.parseInt(st.nextToken());

        // anna -> elsa / elsa -> bfs -> 두 캐릭터가 만날수 있는 최단 경로 
        int ans = bfs(annay, annax, elsay, elsax);
        // ans가 홀수일때를 처리할수 있도록 +1 을 하고 /2해서 중간지점을 찾을것.
        // elsa -> anna : 9일때 : [4][4] -> 한명은 가만히 있고, 한명은 이동을 해야 하니 + 1
        // esla -> anna : 10일때 그냥 5에서 만나면 되는데, (10+1)/2 => floor 가 되니 5가 정상적으로 출력
        System.out.println((ans+1)/2);
    }
}