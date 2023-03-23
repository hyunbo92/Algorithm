/*
������ �þ�Ӵ� 20230320

�þ�Ӵϲ� �˼��� ����������, �����ο� ��Ȱ�� ���� ������ �ϱ�� ����߽��ϴ�.
���ȸ ȸ�� ����� �þ�Ӵϴ»��� �̻縦 ������,������ ��Ʈ�� �츮���� ���� �湮�� ���� �и��߽��ϴ�.
���� �þ�Ӵϰ� ���ñ� ���� ���� ������ �̻簡���� �մϴ�.

wevwevwe.png

�þ�Ӵϴ� ��, �Ʒ�, ����, ������ �������θ� �̵��� �� ������,
�� ĭ�� ������ ���ڴ� �ش� ��ġ�� �̵����� �� ���̴� �Ƿε� ��ġ �Դϴ�.

tretw.png

�þ�Ӵϴ� �Ƿε��� �ּҰ� �ǰԲ� �� �� �ִ� ��η� �츮���� �湮�մϴ�.
�Ƿε��� ���� ���� ���̴� ��ġ�� �̻縦 ���� ���,
�þ�Ӵϰ� �츮���� �湮�� �� ����Ƿε����� ������ ����� �ּ���.
(�� ���ÿ����� 1 + 0 + 0 + 1 + 1 + 1 + 8 = 12 ��ŭ �Ƿε��� ����ϴ�.)

�Է�
ù �ٿ��� �ô��� ��ġ ��ǥ�� (Y, X)�� �Է� �޽��ϴ�.
���� �ٿ��� ������ ũ�� N �� �Է� �޽��ϴ�. (3 <= N <= 1,000)
���� �ٺ��� N x N �� ������ ���¸� �Է� �޽��ϴ�.
-1�� ���̰�, �׿��� ���� Ư�� ĭ�� �湮������ ��� �Ƿε� �Դϴ�. (0 <= �Ƿε� <= 1,000)

���
�þ�Ӵϰ� ���� ���� ��ġ�� �̻縦 �� ���,
�þ�Ӵϰ� �湮�� ��ԵǴ� �Ƿε��� ������ ����� �ּ���.

�Է� ���� 1 
2 1
4
3 3 3 0
3 -1 8 1
0 1 -1 1
2 0 0 1

��� ���� 1
12

�Է� ���� 2 
0 0
5
1 -1 2 1 2
3 -1 1 -1 1
0 2 0 -1 0
0 -1 0 -1 0
0 0 0 -1 5

��� ���� 2
16
 */
package type.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class M3_5 {


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static class Node implements Comparable <Node> {
        int y;
        int x;
        int cost;
        Node(int y, int x, int cost) {
            this.y= y;
            this.x = x;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node next) {
            if(cost < next.cost)
                return -1;
            if(cost > next.cost)
                return 1;
            return 0;
        }
    }

    static int sy, sx;
    static int n; 
    static int[][] MAP;

    static int[] ydir = {0, 0, 1, -1};
    static int[] xdir = {1, -1, 0, 0}; 

    static int dijkstra(int y, int x) {
        // 1. PQ ����
        PriorityQueue<Node>pq = new PriorityQueue<>();
        pq.add(new Node(y, x, MAP[y][x]));

        // 2. dist ����
        int[][]dist = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j =0; j < n; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        // ������ ���� ��� �ʱ�ȭ 
        dist[y][x] = MAP[y][x]; 

        // 3. dijkstra
        int ans = Integer.MIN_VALUE;
        while(!pq.isEmpty()) {
            Node now = pq.poll();

            // ���� ��忡 ������� ������ �� cost�� ���±��� ��ϵ� ���� ��� cost���� ũ��
            if(now.cost > ans )
                // ���� ���� ! (����� �츮�� �湮 ������ ��常 �ü� �ִ�)
                ans = now.cost; 

            for(int i = 0; i < 4; i++ ) {
                int ny = now.y + ydir[i];
                int nx = now.x + xdir[i];
                if(ny < 0 || nx < 0 || ny >= n || nx >=n )
                    continue;
                // ���� ���� ���� ���̶�� pass
                if(MAP[ny][nx] == -1)
                    continue;
                int ncost = dist[now.y][now.x] + MAP[ny][nx];
                // [ny][nx]���� �� ���� �� ª�� ��θ� ã�Ҿ��� contine 
                if(dist[ny][nx] <= ncost)
                    continue;
                dist[ny][nx] = ncost;
                pq.add(new Node(ny, nx, ncost)); 
            }
        }
        /*
        int ans = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            for(int j =0; j < n; j++) {
                if(dist[i][j] > ans && dist[i][j] != Integer.MAX_VALUE)
                    ans = dist[i][j];
            }
        }
        */
        return ans;

    }


    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        sy = Integer.parseInt(st.nextToken());
        sx = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(br.readLine());
        // MAP init
        MAP = new int[n][n];
        // input
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                MAP[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // ���� ã���� �ϴ°� :
        // �þ�Ӵϰ� �׻� �ִܰŸ��� �̵��� �ϴµ�, �� ���߿� ���� ����� �ü� �ִ� ��
        // --> "�þ�Ӵ� ���� ��ġ���� ��� ��ǥ�� �ִ� �Ÿ� ��" ���� �� ��. 
        int ans = dijkstra(sy, sx);
        System.out.println(ans);
    }
}