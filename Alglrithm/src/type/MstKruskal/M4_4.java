/*
통신병 민코씨

통신병 민코씨는 훈련에 대비하여 통신망을 구축하려 한다.
어느 한 노드와 다른 노드를 케이블로 연결하면 서로 통신이 가능해진다고 한다.
예를들어, A1 지점과 A2 지점이 연결되면 A1 과 A2는 통신이 가능해지고 A3 와 A4 가 연결되면 A3와 A4가 통신이 가능해진다.
여기서 추가로 A2와 A3가 연결된다고 하면 A2 와 A3 뿐만이 아니라,  A1과 A4 도 같은 통신망을 쓸 수 있어 통신이 가능해진다.
즉,  직접적으로 연결되지 않고도 다른 노드를 거치고 거쳐 통신을 할 수 있게 된다.

image.png
[그림1]

[그림1] 과 같이 칸과 칸사이의 거리는통신 케이블의 비용과 같다.(거리는 칸과 칸 사이로이동하기 위한 이동횟수이다. 즉, 그림에 적힌 숫자가 노드와 노드 사이의 거리이자 케이블 설치 비용이다.)
또한통신케이블은 벽(회색) 이 아닌 칸에만 설치할 수 있다.
[그림1] 에서 민코씨의 노드는 S 이고 다른 위치의 노드들은 A이다.
민코씨의 노드S가 모든 다른 노드와의 통신이 가능해지도록 할때, 드는 최소 비용이 얼마인지 출력하시오.
예를들어,[그림1] 처럼 통신망을 설치하게되면 비용이 2 + 4 + 2 = 8 으로 최소비용이 된다.

[맵정보 의미 및 제한조건]
S : 민코씨 노드
A : 다른 노드 (0<= A의 수 <= 100)
# : 벽 ( 통신케이블이 지날수 없는 영역, 그림1의 회색지역)
_ : 빈칸

입력
첫째 줄에 테스트케이스의 수가 입력된다.(1 <= T <= 15)
이어서 각 테스트 케이스별로 첫째줄에는 맵의 가로 크기  X,세로크기 Y가 입력된다. ( 1 <= X, Y <= 50 )
다음줄부터는 Y 줄의 맵정보가 각줄마다 X개씩 입력된다.
입력은 모든 노드들이 그림1 처럼 벽으로 둘러싸여져 있는 입력으로 주어지고 , S에서 출발하여 도달할 수 없는 A는 없다.

출력
각 케이스마다, S에서 모든 노드들을 연결하기 위한 최소비용을 출력한다.

입력 예시 1 
2
6 5
#####_
#A#A##
#_#_A#
#S__##
#####_
7 7
#####__
#AAA###
#____A#
#_S_###
#_____#
#AAA###
#####__

출력 예시 1
8
11
 */
package type.MstKruskal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class M4_4 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st ;

    static class Node{
        int y;
        int x;
        Node(int y, int x) {
            this.y = y;
            this.x = x; 
        }
    }

    static class Edge implements Comparable <Edge> {
        int a;
        int b;
        int cost;
        Edge(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge next) {
            if(cost < next.cost)
                return -1;
            if(cost > next.cost)
                return 1;
            return 0;
        }
    }

    static ArrayList<Edge> al; 
    static int[] parent; 

    // union find
    static int find(int node) {
        if(node == parent[node])
            return node;
        return parent[node] = find(parent[node]);
    }

    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if(pa == pb)
            return;
        parent[pb] = pa;
    }

    static int[] ydir = {0, 0, 1, -1};
    static int[] xdir = {1, -1, 0, 0}; 

    static void bfs(int yy, int xx) {
        // 1. queue 설정
        Queue<Node>q = new LinkedList<>();
        q.add(new Node(yy, xx));

        // 2. visited 설정
        int[][] visited = new int[y][x]; 
        visited[yy][xx] = 1; 

        // 3. bfs
        while(!q.isEmpty()) {
            Node now = q.poll();

            // 만약 내가 지금 접근하고 있는게 "다른" 노드라면 -> arraylist에 추가
            if(NUM[now.y][now.x] > 0 && !(now.y == yy && now.x == xx))
                // 새로운 노드를 발견했다!]
                // 이 노드까지 걸린 거리를 arraylist에 추가
                al.add(new Edge(NUM[yy][xx], NUM[now.y][now.x], visited[now.y][now.x] - 1));

            // 상하좌우로 뻗어나가가기
            for(int i = 0; i < 4; i++) {
                int ny = now.y + ydir[i];
                int nx = now.x + xdir[i];
                // 필수체크 -> 범위체크
                if(ny < 0 || nx < 0 || ny >= y || nx >= x)
                    continue;
                // 방문체크
                if(visited[ny][nx] > 0)
                    continue;
                // 맵에서 이동하지 못하는 공간 '#'
                if(MAP[ny][nx] == '#')
                    continue;
                visited[ny][nx] = visited[now.y][now.x] + 1; 
                q.add(new Node(ny, nx)); 
            }
        }
    }

    static int kruskal() {

        // 1. 정렬
        Collections.sort(al);
        int ans = 0;

        // 하나씩 작은거부터 MST를 완성시켜갑니다
        for(int i = 0; i < al.size(); i++) {
            Edge now = al.get(i);
            // 이미 연결된것인가? 
            if(find(now.a) == find(now.b))
                continue;
            ans += now.cost;
            union(now.a, now.b);
        }
        return ans; 
    }


    static int test;
    static int y, x; 
    static char[][] MAP; 
    static int [][] NUM; // A, S등의 번호를 새겨서 저장할 2차원 배열

    public static void main(String[] args) throws IOException {
        test = Integer.parseInt(br.readLine());
        for(int t = 1; t <= test; t++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            // init 
            MAP = new char[y][x]; 
            NUM = new int[y][x]; 
            // ---- Arraylist 초기화!------
            al = new ArrayList<>(); 

            // 1. S와 A 입력받으면서 노드 넘버링 
            int num = 1; 
            for(int i = 0; i < y; i++) {
                String inp = br.readLine();
                for(int j = 0; j < x; j++) {
                    MAP[i][j] = inp.charAt(j); 
                    if(MAP[i][j] == 'S' || MAP[i][j] == 'A') {
                        NUM[i][j] = num; 
                        // 다음꺼부터는 1 추가한 번호로 넘버링
                        num++; 
                    }
                }
            }
            // 2. 각 노드로부터 모든 노드사이의 간선의 거리를 구해서 arraylist에 저장 


            // N^2 * O(V^2) + O(ElogE) 

            // 2500 * 2500 = 625만
            for(int i = 0; i < y; i++) {
                for(int j = 0; j < x; j++) {
                    // 어떠한 노드를 발견헀으면
                    // 여기서부터 모든 다른 노드까지의 거리를 구하고 edge의 형태로 저장해라!
                    if(NUM[i][j] != 0)
                        bfs(i, j); 
                }
            }

            // parent init
            parent = new int[num+1];
            for(int i = 0; i <= num; i++)
                parent[i] = i; 

            // O(ElogE) 10000 * log(10000) = 13만
            int ans = kruskal();
            System.out.println(ans);
        }
    }
}