/*
포스터 붙이기 20230320

코코는 학생회의 일원입니다. 오늘은 이번주에 있을 학교 행사의 포스터를 붙이는 일을 해야 합니다.
코코가 다니는 대학의 캠퍼스 내에는 P개의 건물이 있고, 이를 잇는 C개의 길이 있습니다.
건물은 편의상 1번~P번 건물로 표현하며, C개의 길은 양방향 길입니다.
코코는 학생회실이 있는 K번 건물에서 시작하여, 2개의 건물에 포스터를 붙이려고 합니다.
단, 다음 수업에 늦지 않기 위해, 가장 빠른 길을 이용하여 포스터 부착을 완료하려고 합니다.
각 건물들을 잇는 길의 거리 정보와 코코가 있는 학생회실의 건물 번호, 그리고 포스터를 부착해야 하는 2개의 건물 번호가 주어질 때,
코코가 포스터 부착을 완료하기 위해 이동해야 하는 최단 거리를 출력하는 프로그램을 작성하시오.



[예시]
다음은 예제 입력을 시각화 한 것입니다.
C=9, P=7, K=5, A=1, B=4

image.png

코코가 포스터 부착을 완료하기 위해 5번 건물에서 시작하여 1번, 4번 건물에 들리는 경로는 다수가 존재합니다.
아래는 그 중 하나의 경로를 나타낸 것입니다.

image.png

코코가 포스터 부착을 완료하기 위해 이동할수 있는 최단 거리의 경로는 아래와 같습니다.

image.png

입력
첫번째 줄에는 길의 개수 C(1 <= C <= 200,000), 건물의 개수 P(1 <= P <= 100,000), 코코의 건물 위치 K, 그리고 포스터를 부착해야 하는 건물 번호 A와 B가 공백으로 구분되어 입력됩니다. (1 <= K, A,B <= P, K =/= A =/= B)
두번째 줄부터 C개의 줄에 걸쳐 건물 P1와 P2, 그리고 두 건물을 잇는 길의 거리 D가 공백으로 구분되어 입력됩니다.
모든 D의 합은 2,000,000,000을 초과하지 않습니다.

출력
첫번째 줄에 코코가 K건물에서 시작하여 A, B건물에 포스터 부착을 완료하기 위해 이동해야 하는 최단 거리를 출력합니다.

입력 예시 1 
9 7 5 1 4
5 1 7
6 7 2
4 7 2
5 6 1
5 2 4
4 3 2
1 2 3
3 2 2
2 6 3

출력 예시 1
12
 */
package type.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class M3_3 {

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
        for(int i = 0; i <= p; i++) 
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

    // c: 도로의 개수, p : 건물의 개수
    static int c, p, k, A, B; 

    static int[] distA;
    static int[] distB; 


    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        c = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        // init 
        al = new ArrayList[p+1];
        for(int i = 0; i <= p; i++)
            al[i] = new ArrayList<>();
        distA = new int[p+1];
        distB = new int[p+1];

        //edge input
        for(int i = 0; i < c; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            // 양방향 연결
            al[from].add(new Edge(to, cost));
            al[to].add(new Edge(from, cost));
        }

        // 1. A -> 모든 노드의 최단 거리를 구합니다. (distA[k] : A -> K까지의 거리)
        dijkstra(A, distA);
        // 2. B -> 모든 노드의 최단 거리를 구합니다. (distB[k] : B -> K까지의 거리)
        dijkstra(B, distB); 
        // 3. 위에서 구한 두 k까지의 최단거리 중 최소값을 구하고 min(distA[k], distB[k])
        // --> A -> B (B->A)의 최단거리를 더한 것이 정답. 
        int ans = Math.min(distA[k], distB[k]) + distA[B]; // distB[A]; 
        System.out.println(ans);
    }
}
