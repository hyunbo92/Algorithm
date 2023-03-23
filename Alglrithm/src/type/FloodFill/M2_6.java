/*
�÷����� 20230321

5 x 5 ũ���� �ʿ��� ��ǥ (y, x)�� 1�� �־����� ��, �ش� ��ġ�κ��� �÷����ʷ� ���� ä�� ���� ����� ����ϴ� ���α׷��� �ۼ��Ͻÿ�.
�Ʒ� ���ô� y(����) : 2, x(����) : 2�� ���� ��ǥ�� �־����� ��, �÷����ʷ� ���� ä�� ���� ����Դϴ�.

image.png

�Է�
ù��° �ٿ� �÷������� ������ ��ǥ y(����), x(����)�� �������� ���еǾ� �־����ϴ�.
��ǥ�� ����� �Է��� �־����� �ʽ��ϴ�.

���
�÷������� ������ ���� ����� ����մϴ�.

�Է� ���� 1 
0 0 

��� ���� 1
1 2 3 4 5 
2 3 4 5 6 
3 4 5 6 7 
4 5 6 7 8 
5 6 7 8 9 

�Է� ���� 2 
2 2

��� ���� 2
5 4 3 4 5 
4 3 2 3 4 
3 2 1 2 3 
4 3 2 3 4 
5 4 3 4 5 

�Է� ���� 3 
1 3

��� ���� 3
5 4 3 2 3 
4 3 2 1 2 
5 4 3 2 3 
6 5 4 3 4 
7 6 5 4 5 
 */
package type.FloodFill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class M2_6 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // visited = �湮 ����� ä��� ���� �迭
    // ���� -> �ٸ� ��������� �ִ� ��ΰ� ���� 
    static int[][] visited = new int[5][5]; 
    static int[] ydir = {0, 0, 1, -1};
    static int[] xdir = {1, -1, 0, 0}; 

    // �������� MAP
    // --> ��� ������� ������! 
    // static int[][] MAP;

    // 1. floodfill -> 2~N������ ��ǥ -> ������� class
    static class Node {
        // ��ǥ
        int y;
        int x;
        Node(int y, int x) {
            this.y = y;
            this.x = x; 
        }
    }

    static void bfs(int y, int x) {
        // 1. queue ����
        Queue<Node>q = new LinkedList<>();
        q.add(new Node(y, x)); 

        // 2. visited ���� 
        visited[y][x] = 1; 

        // bfs / floodfill 
        while(!q.isEmpty()) {
            Node now = q.poll();
            // �����¿�� ������ -> ���� �迭
            for(int i = 0; i < 4; i++) {
                // ���� ��ǥ
                int ny = now.y + ydir[i];
                int nx = now.x + xdir[i]; 
                //** �ʼ�üũ -> ���� üũ
                if(ny < 0 || nx < 0 || ny >= 5 || nx >= 5)
                    continue; 
                //  �̹� �湮�� ����̸� pass
                if(visited[ny][nx] > 0)
                    continue; 
                // MAP[now][i] == 0 
                // if(MAP[ny][nx] == ���� ���� ���̴�) continue

                // ���� ��ġ�� ���� ��ġ�������� 1��/��/�ð� �� �ɷ��� ����.  
                visited[ny][nx] = visited[now.y][now.x] + 1;  
                q.add(new Node(ny, nx)); 
            }
        }

    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        // floodfill; 
        bfs(y, x);

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                System.out.print(visited[i][j] + " " );
            }
            System.out.println();
        }
    }
}