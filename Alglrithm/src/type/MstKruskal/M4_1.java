/*20230314 20230323
�ּ� ���д� Ʈ��

�־��� �׷����� �ּ� ���д� Ʈ���� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

�Է�
ù° �ٿ� ������ ���� V(1 �� V �� 10,000)�� ������ ���� E(1 �� E �� 100,000)�� �־�����.
���� E���� �ٿ��� �� ������ ���� ������ ��Ÿ���� �� ���� A, B, C�� �־�����. �̴� A�� ������ B�� ������ ����ġ C�� �������� ����Ǿ� �ִٴ� �ǹ��̴�. C�� ������ ���� ������, ������ 1,000,000�� ���� �ʴ´�.
�׷����� ������ 1������ V������ ��ȣ�� �Ű��� �ְ�, ������ �� ���� ���̿� ��ΰ� �ִ�. �ּ� ���д� Ʈ���� ����ġ�� -2,147,483,648���� ũ�ų� ����, 2,147,483,647���� �۰ų� ���� �����͸� �Է����� �־�����.

���
ù° �ٿ� �ּ� ���д� Ʈ���� �� ����ġ�� ����Ѵ�.

�Է� ���� 1 
3 3
1 2 1
2 3 2
1 3 3

��� ���� 1
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

        // ���� �ϳ��� �ѷ����鼭 ��� ������ + �̹� ���� �ȵȰ� ������ ���� 
        for(int i = 0; i < al.size(); i++) {
            Edge now = al.get(i);
            // ������ �Ǿ��ִٸ� pass 
            if(find(now.a)== find(now.b))
                continue;
            // MST�� �Ͽ��̴�!
            ans += now.cost;
            union(now.a, now.b);
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        // parent �ʱ�ȭ
        parent = new int[v+1];
        for(int i = 0; i <= v; i++)
            parent[i] = i; 

        // ������ ������ŭ �Է�
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