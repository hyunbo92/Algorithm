/*
Bloom 20230322

2���� �迭 ������ ȭ���� �ֽ��ϴ�. �׸��� ��,��,��,��� ������ ������ �ֽ��ϴ�.
������ ��� �� ������ �ɰ� �Ǹ�, �� ������ ���� �ǰ� ������ ������ �˴ϴ�.
������ ������ ����� ������ �ɾ��� �������� ��,��,��,��� ������ �Ǹ�
�̹� ���� �� �����̳�, ������ �ɾ��� �������� ������ �ɾ����� ���մϴ�.

image.png

�� �׸��� ���� ��� �� ������ ������ �ɾ�����, �� ���������� ���� �ǰ� ������ ��,��,��,�� �������� �ɾ����� �˴ϴ�.
ó������ �� ���� ������ �ɾ����ϴ�.
�� ������ ������ ȭ�ܿ� ���� ���� �Ǵ��� �����ִ� ���α׷��� ����� �ּ���.
(ó�� �ɾ��� �� ���� ������ ���� �ǿ� ���� 1 ���̰�, ���� �ǿ��� ���� ������ �۶߸��ϴ�.)

�Է�
ù ��° �ٿ��� ȭ���� ũ�� height, width �� �Էµ˴ϴ�. ( 1 <= height, width <= 100)
�� ��° �ٿ��� ���� �� ���� ��ǥ�� y,x ���·� �Էµ˴ϴ�. ( 0 <= y <  height )
�� ��° �ٿ��� ���� �� ���� ��ǥ�� y,x ���·� �Էµ˴ϴ�. ( 0 <= x < width )

���
�� �� �ϸ��� ȭ�ܿ� ���� ���� ���ԵǴ��� ������ּ���.

�Է� ���� 1 
2 3
0 0 
1 2

��� ���� 1
2

��Ʈ
������ ���ѵ��� �ɾ����� �� ������ ���� �� ���� ����մϴ�.
����, 1 x 1 �迭�� 0,0 ��ġ�� ������ �ɾ����� �� 1�� ���� ������ ���� ���ϴ�.
 */
package type.FloodFill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class M2_7 {
	   static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    static StringTokenizer st;

	    static class Node {
	        int y, x;
	        Node(int y, int x){
	            this.y = y;
	            this.x = x; 
	        }
	    }

	    static int h, w;
	    static int y, x;
	    static int[][] visited; 
	    static Queue<Node>q = new LinkedList<>();

	    static int[] ydir = {0, 0, 1, -1};
	    static int[] xdir = {1, -1, 0, 0}; 

	    static int bfs() {
	        // 1. queue ����
	        // 2. visited ���� 
	        // 3. bfs
	        int ans = 0; 
	        while(!q.isEmpty()) {
	            Node now = q.poll();
	            // q���� ���������� ������ ��� = ���������� �� ��
	            // ���ö����� ������ ���� -> �������� �� ���������� �Ǵ� ���� ���� �����ϰ� ��
	            ans = visited[now.y][now.x];
	            // ����迭�� �����¿� Ȯ��
	            for(int i = 0; i < 4; i++) {
	                int ny = now.y + ydir[i];
	                int nx = now.x + xdir[i]; 
	                // **�ʼ�üũ - ����üũ**
	                if(ny < 0 || nx < 0 || ny >= h || nx >= w)
	                    continue;
	                // �湮�Ѱ��̸� pass
	                if(visited[ny][nx] > 0 )
	                    continue;
	                // flood fill 
	                visited[ny][nx] = visited[now.y][now.x] + 1; 
	                q.add(new Node(ny, nx));
	            }
	        }
	        return ans;
	    }

	    public static void main(String[] args) throws IOException {
	        st = new StringTokenizer(br.readLine());
	        h = Integer.parseInt(st.nextToken());
	        w = Integer.parseInt(st.nextToken());

	        // init
	        visited = new int[h][w]; 

	        // ���� 2�� input
	        for(int i = 0; i < 2; i++) {
	            st = new StringTokenizer(br.readLine());
	            y = Integer.parseInt(st.nextToken());
	            x = Integer.parseInt(st.nextToken());
	            // �� 2���� ������ ��ġ -> ���� -> Queue���� ����  
	            q.add(new Node(y, x));
	            // ó�� �����ϴ� ����� �湮 üũ
	            visited[y][x] = 1; 
	        }
	        
	        
	        int ans = bfs();
	        
	    	for(int i=0;i<h;i++) {
				for(int j=0;j<w;j++) {
					System.out.print(visited[i][j]+" ");
				}
				System.out.println();
			}
	        System.out.println(ans);
	    }
	}