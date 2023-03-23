/*20230314
���ڸ�

�ø�Ǫ���� �� ���콺�� ���� �ϴÿ� �κ귯�� �ִ� n���� ������ �̾� �ϳ��� ���ڸ��� ������� �Ѵ�.
���ڸ��� �ٸ� �� ���� ���������� ���� �����̰�, ��� ������ ���� ��/���������� �̾��� �־�� �Ѵ�.
2���� ��� ���� ������ ���� �ִ�. ���� ���� �� ���� �� �� �縮�� �Ÿ���ŭ�� �������� ��ٰ� ���� ��, ���ڸ��� ����� �ּ� �������� ���Ͻÿ�.

�Է�
ù��° �ٿ� ���� ���� n�� �־�����. (1 �� n �� 100)
�ѹ�° �ٺ��� n���� �ٿ� ���� �� ���� x, y��ǥ�� �Ǽ� ���·� �־�����, �ִ� �Ҽ��� ��°�ڸ����� �־�����.
��ǥ�� 1000�� ���� �ʴ� ���� �Ǽ��̴�.

���
������ �Ҽ��� 2�ڸ����� �ݿø��Ͽ� ����Ѵ�.

�Է� ���� 1 
3
1.0 1.0
2.0 2.0
2.0 4.0

��� ���� 1
3.41
 */
package type.MstKruskal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class M4_2 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st ;

    static class Star {
        double y;
        double x;
        Star(double y, double x) {
            this.y = y;
            this.x = x; 
        }
    }

    static class Edge implements Comparable <Edge> {
        int a;
        int b;
        double cost;
        Edge(int a, int b, double cost) {
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

    static ArrayList<Edge> al = new ArrayList<>();
    static ArrayList<Star> stars = new ArrayList<>(); 
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

    static double kruskal() {

        // 1. sort
        Collections.sort(al);
        double ans = 0;

        for(int i = 0; i < al.size(); i++) {
            Edge now = al.get(i);
            // �̹� �̰����� �̷�� �� ���� ����� ���¶�� pass
            if(find(now.a)== find(now.b))
                continue;
            ans += now.cost; 
            union(now.a, now.b);
        }
        return ans; 
    }

    static int n; 

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        // parent �ʱ�ȭ
        parent = new int[n];
        for(int i = 0; i < n; i++)
            parent[i] = i; 

        // ���� ��ġ �Է� 
        for(int i = 0; i < n; i++) {
            double y, x; 
            st = new StringTokenizer(br.readLine());
            y = Double.parseDouble(st.nextToken());
            x = Double.parseDouble(st.nextToken());
            stars.add(new Star(y, x)); 
        }

        // ��� ������ ������ Ȱ���Ͽ� ��� �������� �ϴ� al�� ������� 
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                // �밢�� ���� = sqrt((x2-x1)^2 + (y2-y1)^2)
                double distx = (stars.get(i).x - stars.get(j).x) * (stars.get(i).x - stars.get(j).x);
                double disty = (stars.get(i).y - stars.get(j).y) * (stars.get(i).y - stars.get(j).y);
                double dist = Math.sqrt(distx + disty);
                al.add(new Edge(i, j, dist));
            }
        }

        // kruskal
        double ans = kruskal();
        System.out.println(Math.round(ans * 100) / 100.0);
    }
}