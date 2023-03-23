/*
Ʈ�� BFS 20230320

Ʈ���� �Է� �ް�, BFS �� ������ Ž�� ������� ��尪�� ������ּ���.
0 ����� ���� Ž���� �����ϸ�, �� ������ �������� ��尡 �ִ� ���� ��� ��ȣ�� ���� ������ Ž���մϴ�.

image.png

���� ���, ���� ���� Ʈ���� �־����ٸ�
0 �� ������ Ž���� �����մϴ�.
0 �� ��忡�� Ž���� �� �ִ� ���� ���� 1��, 2��, 3�� ����Դϴ�.
�� �� ��� ��ȣ�� ���� 1�� ������ Ž���� �����ϸ� �˴ϴ�.
��°���� 0 1 1 3 2 4 5 2 �Դϴ�.

�Է�
Ʈ�� �Է��� �־����ϴ�.
ù ��° �ٿ��� ����� ���� N �� �Էµ˴ϴ�.
�� ��° �ٿ��� N ���� �� ��� ���� �Էµ˴ϴ�.
�� ��° �ٺ��ʹ� N x N �� ��������� �Էµ˴ϴ�.

�Է� ����
8 // ��� ����
0 1 1 3 2 4 5 2 // ��� ��
// �������
0 1 1 1 0 0 0 0
0 0 0 0 1 1 1 0
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 1
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0

���
0 �� ��忡�� BFS �� �����ؼ� �湮�� ����� ���� ���ʴ�� ����� �ּ���.

�Է� ���� 1 
8
0 1 1 3 2 4 5 2
0 1 1 1 0 0 0 0
0 0 0 0 1 1 1 0
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 1
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0

��� ���� 1
0 1 1 3 2 4 5 2
 */
package type.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class M2_1 {

	  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    static StringTokenizer st;

	    static int[] nodes; 
	    static int n; 
	    static int[][] MAP;

	    static void bfs(int node) {
	        // 1. queue 
	        Queue<Integer>q = new LinkedList<>();
	        q.add(node);

	        // 2. visited
	        int[] visited = new int[n]; 
	        visited[node] = 1; 

	        // 3. bfs
	        while(!q.isEmpty()) {
	            int now = q.poll(); 
	            System.out.print(nodes[now] + " ");
	            for(int i = 0; i < n; i++) {
	                // 1. ���� �ִ��� üũ
	                if(MAP[now][i] == 0)
	                    continue;
	                // 2. �湮 üũ
	                if(visited[i] == 1)
	                    continue;
	                // �����ִٸ� q������
	                q.add(i); 
	                // �湮 ��� 
	                visited[i] = 1;
	            }
	        }
	    }

	    public static void main(String[] args) throws IOException {
	        n = Integer.parseInt(br.readLine());
	        nodes = new int[n];
	        st = new StringTokenizer(br.readLine());
	        for(int i = 0; i < n; i++) 
	            nodes[i] = Integer.parseInt(st.nextToken());
	        MAP = new int[n][n];
	        for(int i = 0; i < n; i++) {
	            st = new StringTokenizer(br.readLine());
	            for(int j = 0; j < n; j++) {
	                MAP[i][j] = Integer.parseInt(st.nextToken());
	            }
	        }
	        bfs(0);
	    }
	}