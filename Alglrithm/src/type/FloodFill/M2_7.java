/*
Bloom 20230322

2차원 배열 형태의 화단이 있습니다. 그리고 상,하,좌,우로 퍼지는 씨앗이 있습니다.
씨앗을 어느 한 지점에 심게 되면, 그 다음날 꽃이 피고 씨앗이 퍼지게 됩니다.
씨앗이 퍼지는 방식은 씨앗이 심어진 지점에서 상,하,좌,우로 퍼지게 되며
이미 꽃이 핀 지점이나, 씨앗이 심어진 지점에는 씨앗이 심어지지 못합니다.

image.png

위 그림과 같이 어느 한 지점에 씨앗이 심어지면, 그 다음날에는 꽃이 피고 씨앗이 상,하,좌,우 방향으로 심어지게 됩니다.
처음에는 두 개의 씨앗이 심어집니다.
총 몇일이 지나야 화단에 꽃이 전부 피는지 구해주는 프로그램을 만들어 주세요.
(처음 심어진 두 개의 씨앗이 꽃을 피울 때가 1 일이고, 꽃을 피우자 마자 씨앗을 퍼뜨립니다.)

입력
첫 번째 줄에는 화단의 크기 height, width 가 입력됩니다. ( 1 <= height, width <= 100)
두 번째 줄에는 씨앗 한 개의 좌표가 y,x 형태로 입력됩니다. ( 0 <= y <  height )
세 번째 줄에는 씨앗 한 개의 좌표가 y,x 형태로 입력됩니다. ( 0 <= x < width )

출력
총 몇 일만에 화단에 꽃이 가득 차게되는지 출력해주세요.

입력 예시 1 
2 3
0 0 
1 2

출력 예시 1
2

힌트
마지막 씨앗들이 심어지고 그 다음날 꽃이 핀 날을 출력합니다.
만약, 1 x 1 배열에 0,0 위치에 씨앗이 심어지면 총 1일 만에 꽃으로 가득 찹니다.
 */
package type.FloodFill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class M2_7 {
	   static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    static StringTokenizer st;

	    static class Node {
	        int y, x;
	        Node(int y, int x){
	            this.y = y;
	            this.x = x; 
	        }
	    }

	    static int h, w;
	    static int y, x;
	    static int[][] visited; 
	    static Queue<Node>q = new LinkedList<>();

	    static int[] ydir = {0, 0, 1, -1};
	    static int[] xdir = {1, -1, 0, 0}; 

	    static int bfs() {
	        // 1. queue 설정
	        // 2. visited 설정 
	        // 3. bfs
	        int ans = 0; 
	        while(!q.isEmpty()) {
	            Node now = q.poll();
	            // q에서 마지막으로 나오는 노드 = 마지막으로 필 꽃
	            // 나올때마다 정답을 갱신 -> 언젠가는 맨 마지막으로 피는 꽃의 값을 저장하게 됨
	            ans = visited[now.y][now.x];
	            // 방향배열로 상하좌우 확인
	            for(int i = 0; i < 4; i++) {
	                int ny = now.y + ydir[i];
	                int nx = now.x + xdir[i]; 
	                // **필수체크 - 범위체크**
	                if(ny < 0 || nx < 0 || ny >= h || nx >= w)
	                    continue;
	                // 방문한곳이면 pass
	                if(visited[ny][nx] > 0 )
	                    continue;
	                // flood fill 
	                visited[ny][nx] = visited[now.y][now.x] + 1; 
	                q.add(new Node(ny, nx));
	            }
	        }
	        return ans;
	    }

	    public static void main(String[] args) throws IOException {
	        st = new StringTokenizer(br.readLine());
	        h = Integer.parseInt(st.nextToken());
	        w = Integer.parseInt(st.nextToken());

	        // init
	        visited = new int[h][w]; 

	        // 씨앗 2개 input
	        for(int i = 0; i < 2; i++) {
	            st = new StringTokenizer(br.readLine());
	            y = Integer.parseInt(st.nextToken());
	            x = Integer.parseInt(st.nextToken());
	            // 이 2개의 씨앗의 위치 -> 시작 -> Queue만들어서 삽입  
	            q.add(new Node(y, x));
	            // 처음 시작하는 노드의 방문 체크
	            visited[y][x] = 1; 
	        }
	        
	        
	        int ans = bfs();
	        
	    	for(int i=0;i<h;i++) {
				for(int j=0;j<w;j++) {
					System.out.print(visited[i][j]+" ");
				}
				System.out.println();
			}
	        System.out.println(ans);
	    }
	}