/*
플러드필 20230321

5 x 5 크기의 맵에서 좌표 (y, x)가 1로 주어졌을 때, 해당 위치로부터 플러드필로 맵을 채운 후의 결과를 출력하는 프로그램을 작성하시오.
아래 예시는 y(세로) : 2, x(가로) : 2에 시작 좌표가 주어졌을 때, 플러드필로 맵을 채운 후의 결과입니다.

image.png

입력
첫번째 줄에 플러드필을 시작할 좌표 y(세로), x(가로)가 공백으로 구분되어 주어집니다.
좌표를 벗어나는 입력은 주어지지 않습니다.

출력
플러드필을 진행한 후의 결과를 출력합니다.

입력 예시 1 
0 0 

출력 예시 1
1 2 3 4 5 
2 3 4 5 6 
3 4 5 6 7 
4 5 6 7 8 
5 6 7 8 9 

입력 예시 2 
2 2

출력 예시 2
5 4 3 4 5 
4 3 2 3 4 
3 2 1 2 3 
4 3 2 3 4 
5 4 3 4 5 

입력 예시 3 
1 3

출력 예시 3
5 4 3 2 3 
4 3 2 1 2 
5 4 3 2 3 
6 5 4 3 4 
7 6 5 4 5 
 */
package type.FloodFill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class M2_6 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // visited = 방문 기록을 채우기 위한 배열
    // 시작 -> 다른 노드들까지의 최단 경로가 저장 
    static int[][] visited = new int[5][5]; 
    static int[] ydir = {0, 0, 1, -1};
    static int[] xdir = {1, -1, 0, 0}; 

    // 개별적인 MAP
    // --> 얘는 덮어씌우지 마세요! 
    // static int[][] MAP;

    // 1. floodfill -> 2~N차원의 좌표 -> 담기위한 class
    static class Node {
        // 좌표
        int y;
        int x;
        Node(int y, int x) {
            this.y = y;
            this.x = x; 
        }
    }

    static void bfs(int y, int x) {
        // 1. queue 세팅
        Queue<Node>q = new LinkedList<>();
        q.add(new Node(y, x)); 

        // 2. visited 세팅 
        visited[y][x] = 1; 

        // bfs / floodfill 
        while(!q.isEmpty()) {
            Node now = q.poll();
            // 상하좌우로 퍼진다 -> 방향 배열
            for(int i = 0; i < 4; i++) {
                // 다음 좌표
                int ny = now.y + ydir[i];
                int nx = now.x + xdir[i]; 
                //** 필수체크 -> 범위 체크
                if(ny < 0 || nx < 0 || ny >= 5 || nx >= 5)
                    continue; 
                //  이미 방문한 노드이면 pass
                if(visited[ny][nx] > 0)
                    continue; 
                // MAP[now][i] == 0 
                // if(MAP[ny][nx] == 갈수 없는 곳이다) continue

                // 다음 위치는 지금 위치에서부터 1초/분/시간 더 걸려서 갔다.  
                visited[ny][nx] = visited[now.y][now.x] + 1;  
                q.add(new Node(ny, nx)); 
            }
        }

    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        // floodfill; 
        bfs(y, x);

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                System.out.print(visited[i][j] + " " );
            }
            System.out.println();
        }
    }
}