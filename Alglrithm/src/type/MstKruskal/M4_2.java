/*20230314
별자리

올림푸스의 신 제우스는 서쪽 하늘에 널브러져 있는 n개의 별들을 이어 하나의 별자리를 만들려고 한다.
별자리는 다른 두 별을 일직선으로 이은 형태이고, 모든 별들은 서로 직/간접적으로 이어져 있어야 한다.
2차원 평면 위에 별들이 놓여 있다. 선을 이을 때 마다 두 별 사리의 거리만큼의 에너지가 든다고 했을 때, 별자리를 만드는 최소 에너지를 구하시오.

입력
첫번째 줄에 별의 개수 n이 주어진다. (1 ≤ n ≤ 100)
둘번째 줄부터 n개의 줄에 걸쳐 각 별의 x, y좌표가 실수 형태로 주어지며, 최대 소수점 둘째자리까지 주어진다.
좌표는 1000을 넘지 않는 양의 실수이다.

출력
정답을 소수점 2자리까지 반올림하여 출력한다.

입력 예시 1 
3
1.0 1.0
2.0 2.0
2.0 4.0

출력 예시 1
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
            // 이미 이간선을 이루는 두 별이 연결된 상태라면 pass
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

        // parent 초기화
        parent = new int[n];
        for(int i = 0; i < n; i++)
            parent[i] = i; 

        // 별의 위치 입력 
        for(int i = 0; i < n; i++) {
            double y, x; 
            st = new StringTokenizer(br.readLine());
            y = Double.parseDouble(st.nextToken());
            x = Double.parseDouble(st.nextToken());
            stars.add(new Star(y, x)); 
        }

        // 모든 별들의 조합을 활용하여 모든 간선들을 일단 al에 집어넣음 
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                // 대각선 길이 = sqrt((x2-x1)^2 + (y2-y1)^2)
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