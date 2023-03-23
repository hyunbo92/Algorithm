/*
트리 BFS 20230320

트리를 입력 받고, BFS 를 돌려서 탐색 순서대로 노드값을 출력해주세요.
0 번노드 부터 탐색을 시작하며, 한 레벨에 여러개의 노드가 있는 경우는 노드 번호가 작은 노드부터 탐색합니다.

image.png

예를 들어, 위와 같은 트리가 주어진다면
0 번 노드부터 탐색을 시작합니다.
0 번 노드에서 탐색할 수 있는 다음 노드는 1번, 2번, 3번 노드입니다.
이 중 노드 번호가 작은 1번 노드부터 탐색을 시작하면 됩니다.
출력결과는 0 1 1 3 2 4 5 2 입니다.

입력
트리 입력이 주어집니다.
첫 번째 줄에는 노드의 갯수 N 이 입력됩니다.
두 번째 줄에는 N 개의 각 노드 값이 입력됩니다.
세 번째 줄부터는 N x N 의 인접행렬이 입력됩니다.

입력 예시
8 // 노드 갯수
0 1 1 3 2 4 5 2 // 노드 값
// 인접행렬
0 1 1 1 0 0 0 0
0 0 0 0 1 1 1 0
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 1
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0

출력
0 번 노드에서 BFS 를 시작해서 방문한 노드의 값을 차례대로 출력해 주세요.

입력 예시 1 
8
0 1 1 3 2 4 5 2
0 1 1 1 0 0 0 0
0 0 0 0 1 1 1 0
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 1
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0

출력 예시 1
0 1 1 3 2 4 5 2
 */
package type.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class M2_1 {

	  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    static StringTokenizer st;

	    static int[] nodes; 
	    static int n; 
	    static int[][] MAP;

	    static void bfs(int node) {
	        // 1. queue 
	        Queue<Integer>q = new LinkedList<>();
	        q.add(node);

	        // 2. visited
	        int[] visited = new int[n]; 
	        visited[node] = 1; 

	        // 3. bfs
	        while(!q.isEmpty()) {
	            int now = q.poll(); 
	            System.out.print(nodes[now] + " ");
	            for(int i = 0; i < n; i++) {
	                // 1. 갈수 있는지 체크
	                if(MAP[now][i] == 0)
	                    continue;
	                // 2. 방문 체크
	                if(visited[i] == 1)
	                    continue;
	                // 갈수있다면 q에삽입
	                q.add(i); 
	                // 방문 기록 
	                visited[i] = 1;
	            }
	        }
	    }

	    public static void main(String[] args) throws IOException {
	        n = Integer.parseInt(br.readLine());
	        nodes = new int[n];
	        st = new StringTokenizer(br.readLine());
	        for(int i = 0; i < n; i++) 
	            nodes[i] = Integer.parseInt(st.nextToken());
	        MAP = new int[n][n];
	        for(int i = 0; i < n; i++) {
	            st = new StringTokenizer(br.readLine());
	            for(int j = 0; j < n; j++) {
	                MAP[i][j] = Integer.parseInt(st.nextToken());
	            }
	        }
	        bfs(0);
	    }
	}