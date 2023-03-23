/*
���� ���̷���

���� ���̷����� â���Ͽ�, ���� �� �ùε鿡�� ����� �����ϰ��� �մϴ�.
����� �����ϸ� 3�� �� ��ġ�˴ϴ�.
�׸��� ����� 1�� ���� �����¿�� ������ ���񿡰� �����ϴ�.
������� �Ʒ� �̹�������
X, Y �������� (3, 5) ��ǥ�� �ִ� ���񿡰� ����� �����ϸ�,
�� 9�� �� ��� ������� ġ���� �˴ϴ�.

zombie.png

������ �̹����� ���� ���ڵ���, ġ���� �Ϸ�� �� ���� �ɸ��� �ð��Դϴ�.
���� ����� ���� ����� �̿��ϰ����� ���� ����� ġ���� ���� �ʽ��ϴ�.
�� ���� ���񿡰� ����� ������ ��,
������� ���� ġ���� ���� �� ���� �ɸ��� �ð���,
ġ�� ���� ���� ������ ���� ����� �ּ���.

�Է�
���� ���� ũ�Ⱑ �Էµ˴ϴ�. ( Width x Height, �ִ� 100ĭ)
��° �� ���� ���� �Էµ˴ϴ�.
�� ������ �����ִ� ���� 1�� ���� �ִ� ���Դϴ�.
������ �ٿ��� ����� �����ϴ� ��ġ�� �Էµ˴ϴ�. (X, Y)

���
ù ��° �ٿ��� ��� ġ�ᰡ �Ϸ�� ������ �ɸ��� �ð��� ����ϼ���.
���� �ٿ��� ����� ���� �ʴ� ������ ���� ����� �ּ���.

�Է� ���� 1 
7 8 
0010000 
0011000 
0001100 
1011111 
1111010 
0011110 
0011100 
0001000
3 5

��� ���� 1
9
0
 */
package type.FloodFill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class M2_8 {

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

    static int w, h;
    static int[][] MAP; 
    static int x, y;
    static int zcnt = 0; // ������ �� 

    static int[] ydir = {0, 0, 1, -1};
    static int[] xdir = {1, -1, 0, 0}; 

    static int bfs(int y, int x) {
        // 1. queue ����
        Queue<Node>q = new LinkedList<>();
        q.add(new Node(y, x)); 

        // 2. visited ����
        int[][] visited = new int[h][w];
        // 3���ĺ��� ����� ������ ���� -> 3�ʷ� �ʱ�ȭ�Ͽ� ����
        visited[y][x] = 3;

        int ans = 0; 

        // 3. bfs
        while(!q.isEmpty()) {
            Node now = q.poll();

            // ���� ���������� ���� ġ���ð� ���� 
            ans = visited[now.y][now.x];

            // �湮 ������ ��� == ����� �����Ǿ ġ��� ����
            // ġ���� ����� �� ���� ������ �ϳ��� ����
            zcnt--; 

            // �����¿� ���� Ž��
            for(int i = 0; i < 4; i++) {
                int ny= now.y + ydir[i];
                int nx = now.x + xdir[i];
                // �ʼ�üũ - ����
                if(ny < 0 || nx < 0 || ny >= h || nx >= w)
                    continue;
                // �湮üũ
                if(visited[ny][nx] > 0)
                    continue;
                // ** �츮���� �־��� �� �󿡼� ���� ���� ��ζ�� -
                // �ʿ��� ���� ��ġ�� ���� �ƴ϶��: pass
                if(MAP[ny][nx] == 0)
                    continue;
                // flood fill
                visited[ny][nx] = visited[now.y][now.x]+ 1;
                q.add(new Node(ny, nx)); 
            }
        }
        return ans; 
    }

    // ġ������ ���� ���� ã�� ������ ��� : 
    // visited��ϵȰ� �������鼭 MAP�̶� ��
    // MAP==1 (���� �ִٰ� �Ǿ��ִµ�) -> visited == 0(����� ����) 
    // --> ġ������ ���� �����! 

    // �ٸ���� 
    // �� ������ �� - ġ���� ������ �� = ġ������ ���� ������ ��  

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        // x y ����������
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        // MAP init
        MAP = new int[h][w];
        for(int i = 0; i < h; i++) {
            String inp = br.readLine();
            for(int j = 0; j < w; j++) {
                MAP[i][j] = inp.charAt(j) - '0'; // '1' -> 1 ����->���� ����
                // �Է½� 1 == ����, 0 == ��
                if(MAP[i][j] == 1)
                    // �ϳ��� ���� �� �ִ�!
                    zcnt++; // <- ���������δ� �� ������ ���� ��� 
            }
        }
        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken()) - 1;
        y = Integer.parseInt(st.nextToken()) - 1;

        int ans = bfs(y, x); 
        System.out.println(ans);
        System.out.println(zcnt);
    }
}