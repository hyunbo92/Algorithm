/*20230314 20230323
최소 스패닝 트리

주어진 그래프의 최소 스패닝 트리를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 정점의 개수 V(1 ≤ V ≤ 10,000)와 간선의 개수 E(1 ≤ E ≤ 100,000)가 주어진다.
다음 E개의 줄에는 각 간선에 대한 정보를 나타내는 세 정수 A, B, C가 주어진다. 이는 A번 정점과 B번 정점이 가중치 C인 간선으로 연결되어 있다는 의미이다. C는 음수일 수도 있으며, 절댓값이 1,000,000을 넘지 않는다.
그래프의 정점은 1번부터 V번까지 번호가 매겨져 있고, 임의의 두 정점 사이에 경로가 있다. 최소 스패닝 트리의 가중치가 -2,147,483,648보다 크거나 같고, 2,147,483,647보다 작거나 같은 데이터만 입력으로 주어진다.

출력
첫째 줄에 최소 스패닝 트리의 총 가중치를 출력한다.

입력 예시 1 
3 3
1 2 1
2 3 2
1 3 3

출력 예시 1
3
 */
package type.MstKruskal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class M4_1 {


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st ;

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

    static int v, e;
    static ArrayList<Edge> al = new ArrayList<>();
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

    // kruskal 
    static int kruskal() {
        // 1. al  sort
        Collections.sort(al);
        int ans = 0; 

        // 이제 하나씩 둘러보면서 비용 작은거 + 이미 연결 안된거 순으로 선택 
        for(int i = 0; i < al.size(); i++) {
            Edge now = al.get(i);
            // 연결이 되어있다면 pass 
            if(find(now.a)== find(now.b))
                continue;
            // MST의 일원이다!
            ans += now.cost;
            union(now.a, now.b);
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        // parent 초기화
        parent = new int[v+1];
        for(int i = 0; i <= v; i++)
            parent[i] = i; 

        // 간선의 개수만큼 입력
        for(int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            al.add(new Edge(a, b, cost)); 
        }

        int ans = kruskal();
        System.out.println(ans);
    }
}