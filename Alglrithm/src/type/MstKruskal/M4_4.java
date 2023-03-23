/*
��ź� ���ھ�

��ź� ���ھ��� �Ʒÿ� ����Ͽ� ��Ÿ��� �����Ϸ� �Ѵ�.
��� �� ���� �ٸ� ��带 ���̺�� �����ϸ� ���� ����� ���������ٰ� �Ѵ�.
�������, A1 ������ A2 ������ ����Ǹ� A1 �� A2�� ����� ���������� A3 �� A4 �� ����Ǹ� A3�� A4�� ����� ����������.
���⼭ �߰��� A2�� A3�� ����ȴٰ� �ϸ� A2 �� A3 �Ӹ��� �ƴ϶�,  A1�� A4 �� ���� ��Ÿ��� �� �� �־� ����� ����������.
��,  ���������� ������� �ʰ� �ٸ� ��带 ��ġ�� ���� ����� �� �� �ְ� �ȴ�.

image.png
[�׸�1]

[�׸�1] �� ���� ĭ�� ĭ������ �Ÿ������ ���̺��� ���� ����.(�Ÿ��� ĭ�� ĭ ���̷��̵��ϱ� ���� �̵�Ƚ���̴�. ��, �׸��� ���� ���ڰ� ���� ��� ������ �Ÿ����� ���̺� ��ġ ����̴�.)
����������̺��� ��(ȸ��) �� �ƴ� ĭ���� ��ġ�� �� �ִ�.
[�׸�1] ���� ���ھ��� ���� S �̰� �ٸ� ��ġ�� ������ A�̴�.
���ھ��� ���S�� ��� �ٸ� ������ ����� ������������ �Ҷ�, ��� �ּ� ����� ������ ����Ͻÿ�.
�������,[�׸�1] ó�� ��Ÿ��� ��ġ�ϰԵǸ� ����� 2 + 4 + 2 = 8 ���� �ּҺ���� �ȴ�.

[������ �ǹ� �� ��������]
S : ���ھ� ���
A : �ٸ� ��� (0<= A�� �� <= 100)
# : �� ( ������̺��� ������ ���� ����, �׸�1�� ȸ������)
_ : ��ĭ

�Է�
ù° �ٿ� �׽�Ʈ���̽��� ���� �Էµȴ�.(1 <= T <= 15)
�̾ �� �׽�Ʈ ���̽����� ù°�ٿ��� ���� ���� ũ��  X,����ũ�� Y�� �Էµȴ�. ( 1 <= X, Y <= 50 )
�����ٺ��ʹ� Y ���� �������� ���ٸ��� X���� �Էµȴ�.
�Է��� ��� ������ �׸�1 ó�� ������ �ѷ��ο��� �ִ� �Է����� �־����� , S���� ����Ͽ� ������ �� ���� A�� ����.

���
�� ���̽�����, S���� ��� ������ �����ϱ� ���� �ּҺ���� ����Ѵ�.

�Է� ���� 1 
2
6 5
#####_
#A#A##
#_#_A#
#S__##
#####_
7 7
#####__
#AAA###
#____A#
#_S_###
#_____#
#AAA###
#####__

��� ���� 1
8
11
 */
package type.MstKruskal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class M4_4 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st ;

    static class Node{
        int y;
        int x;
        Node(int y, int x) {
            this.y = y;
            this.x = x; 
        }
    }

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

    static ArrayList<Edge> al; 
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

    static int[] ydir = {0, 0, 1, -1};
    static int[] xdir = {1, -1, 0, 0}; 

    static void bfs(int yy, int xx) {
        // 1. queue ����
        Queue<Node>q = new LinkedList<>();
        q.add(new Node(yy, xx));

        // 2. visited ����
        int[][] visited = new int[y][x]; 
        visited[yy][xx] = 1; 

        // 3. bfs
        while(!q.isEmpty()) {
            Node now = q.poll();

            // ���� ���� ���� �����ϰ� �ִ°� "�ٸ�" ����� -> arraylist�� �߰�
            if(NUM[now.y][now.x] > 0 && !(now.y == yy && now.x == xx))
                // ���ο� ��带 �߰��ߴ�!]
                // �� ������ �ɸ� �Ÿ��� arraylist�� �߰�
                al.add(new Edge(NUM[yy][xx], NUM[now.y][now.x], visited[now.y][now.x] - 1));

            // �����¿�� ���������
            for(int i = 0; i < 4; i++) {
                int ny = now.y + ydir[i];
                int nx = now.x + xdir[i];
                // �ʼ�üũ -> ����üũ
                if(ny < 0 || nx < 0 || ny >= y || nx >= x)
                    continue;
                // �湮üũ
                if(visited[ny][nx] > 0)
                    continue;
                // �ʿ��� �̵����� ���ϴ� ���� '#'
                if(MAP[ny][nx] == '#')
                    continue;
                visited[ny][nx] = visited[now.y][now.x] + 1; 
                q.add(new Node(ny, nx)); 
            }
        }
    }

    static int kruskal() {

        // 1. ����
        Collections.sort(al);
        int ans = 0;

        // �ϳ��� �����ź��� MST�� �ϼ����Ѱ��ϴ�
        for(int i = 0; i < al.size(); i++) {
            Edge now = al.get(i);
            // �̹� ����Ȱ��ΰ�? 
            if(find(now.a) == find(now.b))
                continue;
            ans += now.cost;
            union(now.a, now.b);
        }
        return ans; 
    }


    static int test;
    static int y, x; 
    static char[][] MAP; 
    static int [][] NUM; // A, S���� ��ȣ�� ���ܼ� ������ 2���� �迭

    public static void main(String[] args) throws IOException {
        test = Integer.parseInt(br.readLine());
        for(int t = 1; t <= test; t++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            // init 
            MAP = new char[y][x]; 
            NUM = new int[y][x]; 
            // ---- Arraylist �ʱ�ȭ!------
            al = new ArrayList<>(); 

            // 1. S�� A �Է¹����鼭 ��� �ѹ��� 
            int num = 1; 
            for(int i = 0; i < y; i++) {
                String inp = br.readLine();
                for(int j = 0; j < x; j++) {
                    MAP[i][j] = inp.charAt(j); 
                    if(MAP[i][j] == 'S' || MAP[i][j] == 'A') {
                        NUM[i][j] = num; 
                        // ���������ʹ� 1 �߰��� ��ȣ�� �ѹ���
                        num++; 
                    }
                }
            }
            // 2. �� ���κ��� ��� �������� ������ �Ÿ��� ���ؼ� arraylist�� ���� 


            // N^2 * O(V^2) + O(ElogE) 

            // 2500 * 2500 = 625��
            for(int i = 0; i < y; i++) {
                for(int j = 0; j < x; j++) {
                    // ��� ��带 �߰�������
                    // ���⼭���� ��� �ٸ� �������� �Ÿ��� ���ϰ� edge�� ���·� �����ض�!
                    if(NUM[i][j] != 0)
                        bfs(i, j); 
                }
            }

            // parent init
            parent = new int[num+1];
            for(int i = 0; i <= num; i++)
                parent[i] = i; 

            // O(ElogE) 10000 * log(10000) = 13��
            int ans = kruskal();
            System.out.println(ans);
        }
    }
}