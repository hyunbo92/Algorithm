/*
N x M �ִ� �Ÿ� 20230320

N(��) x M(��) ũ���� 2���� ���� �־����� ��, (1, 1)��ġ���� �����Ͽ� (N, M)��ġ�� �����ϱ������ �ִ� �Ÿ��� ����ϴ� ���α׷��� �ۼ��Ͻÿ�.
�� ���ڿ��� �ش� ��ġ�� �̵��ϱ� ���� �Ÿ��� �־�����, �����¿� �������θ� �̵��� �����մϴ�.
** ���ͽ�Ʈ�� �˰����� Ȱ���Ͽ� Ǯ�� �ּ���.
[����]
������ ���� �Է� 1�� �ð�ȭ �� ���Դϴ�.
���� �Է¿��� �־��� �ʿ���, (1,1)���κ��� (N,M)������ �ִ� �Ÿ��� 15�Դϴ�.

image.png

�Է�
ù��° �ٿ� ���� ũ�� N�� ���� ũ�� M�� �������� ���еǾ� �־����ϴ�. (1 <= N, M <= 100)
�ι�° �ٺ��� N���� �ٿ� ��ó M���� ���� ������ �������� ���еǾ� �־����ϴ�.
������ 1 �̻�, 10 ������ ������ �־����ϴ�.

���
ù��° �ٿ� (1,1) ��ġ�κ��� (N, M) ��ġ������ �ִ� �Ÿ��� ����մϴ�.

�Է� ���� 1 
4 4
0 1 3 8
1 2 3 4
2 9 7 5
3 4 6 0 

��� ���� 1
15

�Է� ���� 2 
5 4
10 5 9 5 
4 1 1 7 
7 5 1 6 
3 4 8 6 
3 4 3 6 

��� ���� 2
34
 */
package type.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class M3_4 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, m;
    static int[][] MAP; 

    static class Node implements Comparable <Node> {
        int y;
        int x;
        int cost;
        Node(int y, int x, int cost) {
            this.y= y;
            this.x=x;
            this.cost=cost;
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

    static int[] ydir = {0, 0, 1, -1};
    static int[] xdir = {1, -1, 0, 0}; 

    static void dijkstra(int y, int x) {
        // 1. PQ ����
        PriorityQueue<Node>pq = new PriorityQueue<>();
        // MAP[0][0] = 0, 0 ��ġ�� �����ϴ� ��� 
        pq.add(new Node(y, x, MAP[y][x]));

        // 2. dist ����
        // dist[] = index : ����ȣ value : �ִܰŸ� 
        // dist[][] = [y][x] ��ǥ�� �ִܰŸ� 
        int[][] dist = new int[n][m];
        // �ʱ�ȭ
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        dist[y][x] = MAP[y][x]; 

        // dijkstra
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            // �� �� �ִ� ������ ���� üũ 
            for(int i = 0; i < 4; i++) {
                int ny = now.y + ydir[i];
                int nx = now.x + xdir[i];
                // ** �ʼ�üũ ** ����üũ 
                if(ny < 0 || nx < 0 || ny >= n || nx >= m)
                    continue;
                // ���� �������� ���
                // ���� ��ǥ���� ���� ���� ����� ��� + ���� ��ǥ�� �����ϱ� ���� ���
                int ncost = dist[now.y][now.x] + MAP[ny][nx];
                // ���ݱ��� ��ϵ� [ny][nx]������ �ּҺ�뺸�� ���ų� ũ�� pass
                if(dist[ny][nx] <= ncost)
                    continue; 
                dist[ny][nx] = ncost;
                pq.add(new Node(ny, nx, ncost));
            }
        }
        System.out.println(dist[n-1][m-1]);    
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        // map init
        MAP = new int[n][m];
        // input
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                MAP[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dijkstra(0, 0);
    }
}