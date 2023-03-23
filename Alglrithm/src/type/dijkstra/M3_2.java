/*
�������� ���� 20230314

�ڵ����δ� �˰��� �������� Ǯ� �� �ִ� ���� Ǯ�� ����Ʈ�̴�.
���⼭ Ȱ���ϰ� Ȱ���ϴ� N���� �л����� �¶��λ󿡼� ���� ������ Ǯ�� ģ���� �׾Ҵ�.
N���� �л����� ��¥�� ��� �������� ������ ������� �Ͽ���.
�̵��� ��� ���� �ٸ� ������ ��� �־�, N���� ���� �� �ϳ��� �������� ������ ���� P���� ������� �Ͽ���.
�� ������ ���̿��� �� M���� �ܹ��� ���ε��� �����Ѵ�. �׸��� �� ���ε��� ������ ������ Ư�� �ð��� �Һ�ȴ�.
������ ������ �� ���� P�� ���õǸ�, ��� �л����� P�������� �̵��ϰ�, �׸��� �ٽ� �ڽ��� ������ ���ƿ;� �Ѵ�.
��, ������ �մ� ���ε��� �ܹ����̱� ������, ������ �������� P������ ���� ��� P�������� �ٽ� �ڽ��� ������ �̵��ϴ� ��δ� �ٸ� �� �ִ�.
���� �˰��� �������� Ǫ�µ��� ��ġ�� ü���� ������ �л����� �ִ� �ð����� �̵��� �� �ִ� ��Ʈ�� �̵��Ѵ�.
�ڽ��� �������� P�������� �Դٰ� �ٽ� ���ư��µ��� ���� ���� �ð��� �Һ��� �л��� ����Ͽ� ���ӿ��� ���� ġŲ ���� ���ַ��� �Ѵ�.
ġŲ ���� ���� ���� �� �ִ� �л��� �������� ���ϴ� ���α׷��� �ۼ��϶�.

[����]
������ ���� �Է��� �ð�ȭ �� ���̴�.
N=4, M=8, P=2

image.png

�� �������� P=2 �������� �̵��ϴ� �ּ� �ð��� ������ ����.
1 : 1 -> 2 (4)
2 : (0)
3 : 3 -> 1 -> 2 (2 + 4 = 6)
4 : 4 -> 2 (3)
�׸��� ���� P���� ������ �������� ���ư��� �ּ� �ð��� ������ ����.
1 : 2 -> 1 (1)
2 : (0)
3 : 2 -> 1 -> 3 (1 + 2 = 3)
4 : 2 -> 1 -> 3 -> 4 (1 + 2 + 4 = 7)
�� �������� ���� P���� ���� ���� �� �ð��� ������ ����.
1 : 4 + 1 = 5
2 : 0 + 0 = 0
3 : 6 + 3 = 9
4 : 3 + 7 = 10
���� ���� �ð��� ���� ���� �Һ�Ǵ� �л��� 4�� �л��� 10����, 10�� ����Ѵ�.

�Է�
ù��° �ٿ� �л��� ������ ���� N,  ������ �մ� ������ ���� M, �׸��� ���� P�� �������� ���еǾ� �־�����. (1 �� N �� 1,000,1 �� M �� 10,000,1 �� P �� N)
�ι�° �ٺ��� M+1�ٿ� ���� i��° ������ ������, ����, �׸��� �� ���θ� �����µ��� �Һ�Ǵ� �ð��� �������� ���еǾ� �־�����.
�������� ������ ������ ���δ� �־����� ������, �������� ���� A���� �ٸ� ���� B�� ���� ������ ������ �ִ� 1���̴�.
��� �л����� ������ �������� ���� P��, �׸��� ���� P�κ��� ������ ������ ���ƿ� �� �ִ� �Է��� �־�����.

���
ù ��° �ٿ� N���� �л��� P������ ���� ���µ� ���� ���� �ɸ��� �л���  �Һ� �ð��� ����Ѵ�.

�Է� ���� 1 
4 8 2
1 2 4
1 3 2
1 4 7
2 1 1
2 3 5
3 1 2
3 4 4
4 2 3

��� ���� 1
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
        // 1. PQ ���� 
        PriorityQueue<Edge>pq = new PriorityQueue<>();
        pq.add(new Edge(node, 0));

        // 2. dist ����
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
    static int[] dist; // i -> P������ �ִ� �Ÿ��� ���� 
    static int[] distp; // p -> i������ �ִ� �Ÿ��� ���� (�ѹ��� dijkstra�ϰ� ��Ӿ���) 

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
            // �ܹ��� ����
            al[from].add(new Edge(to, cost));
        }

        // p -> ��� �������� �ִ� �Ÿ��� distp�� ����
        // ���۳�� : P, ���� �������� ���ڸ� �ϳ��� �߰� 
        dijkstra(p, distp);

        int ans = Integer.MIN_VALUE;

        // �ٸ� ��� ���õ���� P������ �ִ� ��θ� ���ϰ�
        // distp -> �� ���ñ����� �Ÿ��� ���ؼ� -> �̰� �ִ��ΰ�? 
        for(int i = 1; i <= n; i++) {
            if(i == p)
                continue;
            // 1~n���� ��� p���ÿ� ���� ��
            dijkstra(i, dist); 
            // ���� dist�� ����Ǿ��ִ°� > i -> ��� �������� �ִܰŸ� 
            int temp = dist[p] + distp[i];
            // ���� ���� -> p + p -> ������ ����� �ִ��� ����
            if(temp > ans)
                ans = temp; 
        }
        System.out.println(ans);
    }
}