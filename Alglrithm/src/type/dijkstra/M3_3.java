/*
������ ���̱� 20230320

���ڴ� �л�ȸ�� �Ͽ��Դϴ�. ������ �̹��ֿ� ���� �б� ����� �����͸� ���̴� ���� �ؾ� �մϴ�.
���ڰ� �ٴϴ� ������ ķ�۽� ������ P���� �ǹ��� �ְ�, �̸� �մ� C���� ���� �ֽ��ϴ�.
�ǹ��� ���ǻ� 1��~P�� �ǹ��� ǥ���ϸ�, C���� ���� ����� ���Դϴ�.
���ڴ� �л�ȸ���� �ִ� K�� �ǹ����� �����Ͽ�, 2���� �ǹ��� �����͸� ���̷��� �մϴ�.
��, ���� ������ ���� �ʱ� ����, ���� ���� ���� �̿��Ͽ� ������ ������ �Ϸ��Ϸ��� �մϴ�.
�� �ǹ����� �մ� ���� �Ÿ� ������ ���ڰ� �ִ� �л�ȸ���� �ǹ� ��ȣ, �׸��� �����͸� �����ؾ� �ϴ� 2���� �ǹ� ��ȣ�� �־��� ��,
���ڰ� ������ ������ �Ϸ��ϱ� ���� �̵��ؾ� �ϴ� �ִ� �Ÿ��� ����ϴ� ���α׷��� �ۼ��Ͻÿ�.



[����]
������ ���� �Է��� �ð�ȭ �� ���Դϴ�.
C=9, P=7, K=5, A=1, B=4

image.png

���ڰ� ������ ������ �Ϸ��ϱ� ���� 5�� �ǹ����� �����Ͽ� 1��, 4�� �ǹ��� �鸮�� ��δ� �ټ��� �����մϴ�.
�Ʒ��� �� �� �ϳ��� ��θ� ��Ÿ�� ���Դϴ�.

image.png

���ڰ� ������ ������ �Ϸ��ϱ� ���� �̵��Ҽ� �ִ� �ִ� �Ÿ��� ��δ� �Ʒ��� �����ϴ�.

image.png

�Է�
ù��° �ٿ��� ���� ���� C(1 <= C <= 200,000), �ǹ��� ���� P(1 <= P <= 100,000), ������ �ǹ� ��ġ K, �׸��� �����͸� �����ؾ� �ϴ� �ǹ� ��ȣ A�� B�� �������� ���еǾ� �Էµ˴ϴ�. (1 <= K, A,B <= P, K =/= A =/= B)
�ι�° �ٺ��� C���� �ٿ� ���� �ǹ� P1�� P2, �׸��� �� �ǹ��� �մ� ���� �Ÿ� D�� �������� ���еǾ� �Էµ˴ϴ�.
��� D�� ���� 2,000,000,000�� �ʰ����� �ʽ��ϴ�.

���
ù��° �ٿ� ���ڰ� K�ǹ����� �����Ͽ� A, B�ǹ��� ������ ������ �Ϸ��ϱ� ���� �̵��ؾ� �ϴ� �ִ� �Ÿ��� ����մϴ�.

�Է� ���� 1 
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

��� ���� 1
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
        // 1. PQ ���� 
        PriorityQueue<Edge>pq = new PriorityQueue<>();
        pq.add(new Edge(node, 0));

        // 2. dist ����
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

    // c: ������ ����, p : �ǹ��� ����
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
            // ����� ����
            al[from].add(new Edge(to, cost));
            al[to].add(new Edge(from, cost));
        }

        // 1. A -> ��� ����� �ִ� �Ÿ��� ���մϴ�. (distA[k] : A -> K������ �Ÿ�)
        dijkstra(A, distA);
        // 2. B -> ��� ����� �ִ� �Ÿ��� ���մϴ�. (distB[k] : B -> K������ �Ÿ�)
        dijkstra(B, distB); 
        // 3. ������ ���� �� k������ �ִܰŸ� �� �ּҰ��� ���ϰ� min(distA[k], distB[k])
        // --> A -> B (B->A)�� �ִܰŸ��� ���� ���� ����. 
        int ans = Math.min(distA[k], distB[k]) + distA[B]; // distB[A]; 
        System.out.println(ans);
    }
}
