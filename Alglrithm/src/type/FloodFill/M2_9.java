/*
�ȳ��Ϳ���M

���Ͽձ��� ����� �ȳ��� ���� ������� ���� �Ҿ����ϴ�.
���� �Ӹ��츦 �ϰ� �ִ� �ȳ��� ���縦 ã�� ����,
�հ� ���� �ִ� ����� �ȳ��� ã�� ����,
���� 1�� ���� �� ĭ�����ÿ� �̵��ϸ缭�θ� ã���ϴ�.

anna.png

[����� �ȳ��� ������]
����� �ȳ��� 1 �ʿ� �� ĭ�� ��, ��, ��, ��� ������ �� �ֽ��ϴ�.
�׸��� �� �ڸ��� ���߾� �޽��� ���ϴ� �͵� �����մϴ�.
��, ���ϴ� �������� ���մϴ�.
������ �Է� �ް�, ���ϸ� ���� ����� �ȳ��� ���� ������ ���� �� �ִ� �̵��ð��� ����� �ּ���.

[���ǻ���]

�ȳ��� ���簡 ���ÿ� ������ ������ ���� �ֽ��ϴ�.
�ݵ�� ���� �� �ִ� ���� �ִ� �Է� ���� �־����ϴ�.

�Է�
ù �ٿ� ���� ũ�⸦ ��Ÿ���� �� N �� �Է� �޾��ּ���. (1 <= N <= 5)
���� �ٺ��� N x N �� ������ �Է� �޽��ϴ�.
��ĭ�� '_' (�����) �̸�, ���ϴ� '#' ���� ǥ��˴ϴ�.
�� �����ٿ��¿����� ��ġ (y, x) �� �ȳ��� ��ġ (y, x) �� �Է� �˴ϴ�.

���
�� ����� ���� ��ǥ���� ������ �Ǵ�, �ּ� �ð��� ����� �ּ���.

�Է� ���� 1 
5
___#_
___#_
##___
__#__
_____
0 0 4 0

��� ���� 1
5
 */
package type.FloodFill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class M2_9 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static class Node {
        int y; 
        int x;
        Node(int y, int x) {
            this.y = y;
            this.x = x; 
        }
    }

    static int n; 
    static char[][] MAP;
    static int annay, annax, elsay, elsax; 

    // ����迭
    static int[] ydir = {0, 0, 1, -1};
    static int[] xdir = {1, -1, 0, 0}; 

    static int bfs(int annay, int annax, int elsay, int elsax) {
        // 1. queue ����
        // anna -> elsa 
        Queue<Node>q = new LinkedList<>();
        q.add(new Node(annay, annax));

        // 2. visited ����
        int[][] visited = new int[n][n]; 
        visited[annay][annax] = 1; 

        // 3. bfs 
        while(!q.isEmpty()) {
            Node now = q.poll();
            // �����¿� 
            for(int i =0; i < 4; i++) {
                int ny = now.y + ydir[i];
                int nx = now.x + xdir[i];
                // �ʼ� ����üũ
                if(ny < 0 || nx < 0 || ny >= n || nx >= n)
                    continue;
                // �湮 üũ 
                if(visited[ny][nx] > 0)
                    continue;
                // ���ϴ� �������� ����
                if(MAP[ny][nx] == '#')
                    continue;
                visited[ny][nx] = visited[now.y][now.x] + 1;
                q.add(new Node(ny, nx));
            }
        }
        return visited[elsay][elsax] -1; 
    }


    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        // init
        MAP = new char[n][n];
        for(int i = 0; i < n; i++) {
            String inp = br.readLine();
            for(int j = 0; j < n; j++) {
                MAP[i][j] = inp.charAt(j); 
            }
        }
        st = new StringTokenizer(br.readLine());
        elsay = Integer.parseInt(st.nextToken());
        elsax = Integer.parseInt(st.nextToken());
        annay = Integer.parseInt(st.nextToken());
        annax = Integer.parseInt(st.nextToken());

        // anna -> elsa / elsa -> bfs -> �� ĳ���Ͱ� ������ �ִ� �ִ� ��� 
        int ans = bfs(annay, annax, elsay, elsax);
        // ans�� Ȧ���϶��� ó���Ҽ� �ֵ��� +1 �� �ϰ� /2�ؼ� �߰������� ã����.
        // elsa -> anna : 9�϶� : [4][4] -> �Ѹ��� ������ �ְ�, �Ѹ��� �̵��� �ؾ� �ϴ� + 1
        // esla -> anna : 10�϶� �׳� 5���� ������ �Ǵµ�, (10+1)/2 => floor �� �Ǵ� 5�� ���������� ���
        System.out.println((ans+1)/2);
    }
}