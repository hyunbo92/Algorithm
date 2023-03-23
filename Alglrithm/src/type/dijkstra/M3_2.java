/*
오프라인 모임 20230314

코딩프로는 알고리즘 문제들을 풀어볼 수 있는 문제 풀이 사이트이다.
여기서 활발하게 활동하던 N명의 학생들이 온라인상에서 같이 문제를 풀며 친분을 쌓았다.
N명의 학생들은 날짜를 잡아 오프라인 모임을 가지기로 하였다.
이들은 모두 각각 다른 마을에 살고 있어, N개의 마을 중 하나를 랜덤으로 선택한 마을 P에서 만나기로 하였다.
이 마을들 사이에는 총 M개의 단방향 도로들이 존재한다. 그리고 각 도로들을 지나는 데에는 특정 시간이 소비된다.
모임을 가지게 될 마을 P가 선택되면, 모든 학생들은 P마을까지 이동하고, 그리고 다시 자신의 마을로 돌아와야 한다.
단, 마을을 잇는 도로들은 단방향이기 때문에, 각자의 마을에서 P마을로 가는 길과 P마을에서 다시 자신의 마을로 이동하는 경로는 다를 수 있다.
매일 알고리즘 문제들을 푸는데에 지치고 체력이 약해진 학생들은 최단 시간으로 이동할 수 있는 루트로 이동한다.
자신의 마을에서 P마을까지 왔다가 다시 돌아가는데에 가장 많은 시간을 소비한 학생을 배려하여 모임에서 먹을 치킨 값을 빼주려고 한다.
치킨 값을 면제 받을 수 있는 학생은 누구인지 구하는 프로그램을 작성하라.

[예시]
다음은 예제 입력을 시각화 한 것이다.
N=4, M=8, P=2

image.png

각 마을에서 P=2 마을까지 이동하는 최소 시간은 다음과 같다.
1 : 1 -> 2 (4)
2 : (0)
3 : 3 -> 1 -> 2 (2 + 4 = 6)
4 : 4 -> 2 (3)
그리고 마을 P에서 각자의 마을까지 돌아가는 최소 시간은 다음과 같다.
1 : 2 -> 1 (1)
2 : (0)
3 : 2 -> 1 -> 3 (1 + 2 = 3)
4 : 2 -> 1 -> 3 -> 4 (1 + 2 + 4 = 7)
각 마을에서 마을 P까지 오고 가는 총 시간은 다음과 같다.
1 : 4 + 1 = 5
2 : 0 + 0 = 0
3 : 6 + 3 = 9
4 : 3 + 7 = 10
오고 가는 시간이 가장 많이 소비되는 학생은 4번 학생의 10으로, 10을 출력한다.

입력
첫번째 줄에 학생과 마을의 개수 N,  마을을 잇는 도로의 개수 M, 그리고 마을 P가 공백으로 구분되어 주어진다. (1 ≤ N ≤ 1,000,1 ≤ M ≤ 10,000,1 ≤ P ≤ N)
두번째 줄부터 M+1줄에 걸쳐 i번째 도로의 시작점, 끝점, 그리고 이 도로를 지나는데에 소비되는 시간이 공백으로 구분되어 주어진다.
시작점과 끝점이 동일한 도로는 주어지지 않으며, 시작점과 마을 A에서 다른 마을 B로 가는 도로의 개수는 최대 1개이다.
모든 학생들이 각자의 마을에서 마을 P로, 그리고 마을 P로부터 각자의 마을로 돌아올 수 있는 입력이 주어진다.

출력
첫 번째 줄에 N명의 학생들 P마을로 오고 가는데 가장 오래 걸리는 학생의  소비 시간을 출력한다.

입력 예시 1 
4 8 2
1 2 4
1 3 2
1 4 7
2 1 1
2 3 5
3 1 2
3 4 4
4 2 3

출력 예시 1
10
 */
package type.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class M3_2 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static class Edge implements Comparable <Edge>{
        int num; 
        int cost; 

        Edge(int num, int cost){
            this.num = num;
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

    static ArrayList<Edge>al[]; 

    static void dijkstra(int node, int[] dist) {
        // 1. PQ 세팅 
        PriorityQueue<Edge>pq = new PriorityQueue<>();
        pq.add(new Edge(node, 0));

        // 2. dist 세팅
        for(int i = 0; i <= n; i++) 
            dist[i] = Integer.MAX_VALUE;
        dist[node] = 0;

        // dijkstra
        while(!pq.isEmpty()) {
            Edge now = pq.poll(); 
            for(int i = 0; i < al[now.num].size(); i++) {
                Edge next = al[now.num].get(i); 
                int ncost = dist[now.num] + next.cost;
                if(dist[next.num] <= ncost)
                    continue;
                dist[next.num] = ncost;
                pq.add(new Edge(next.num, ncost));
            }
        }
    }

    static int n, m, p;
    static int[] dist; // i -> P까지의 최단 거리를 저장 
    static int[] distp; // p -> i까지의 최단 거리를 저장 (한번만 dijkstra하고 계속쓸것) 

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());

        // init
        al = new ArrayList[n+1];
        for(int i = 0; i <= n; i++)
            al[i] = new ArrayList<>();
        dist = new int[n+1];
        distp = new int[n+1];

        // edge input
        for(int i = 0; i < m; i++) {
            int from, to, cost;
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());
            // 단방향 연결
            al[from].add(new Edge(to, cost));
        }

        // p -> 모든 노드까지의 최단 거리를 distp에 저장
        // 시작노드 : P, 어디다 저장할지 인자를 하나더 추가 
        dijkstra(p, distp);

        int ans = Integer.MIN_VALUE;

        // 다른 모든 도시들부터 P까지의 최단 경로를 구하고
        // distp -> 그 도시까지의 거리를 더해서 -> 이게 최대인가? 
        for(int i = 1; i <= n; i++) {
            if(i == p)
                continue;
            // 1~n까지 모두 p도시에 가볼 것
            dijkstra(i, dist); 
            // 지금 dist에 저장되어있는것 > i -> 모든 노드까지의 최단거리 
            int temp = dist[p] + distp[i];
            // 지금 도시 -> p + p -> 도시의 비용이 최대라면 갱신
            if(temp > ans)
                ans = temp; 
        }
        System.out.println(ans);
    }
}