/*
������ Ʃ�� 20230321 

N���� ���� �ִ� ���ϰ迡�� ���� �� �Ÿ��� ���� ������ �̵��� �� �ִ� ����������� ������ Ʃ�갡 �ֽ��ϴ�.
�� ������ Ʃ��� ���� �ٸ�K���� ���� �����ϰ� �ֽ��ϴ�.
�ּ����� ȯ���� �Ͽ� 1�� ������N�� ������ �̵��ϰ��� �� ��,�湮�ϴ� ���� ���� �� ������ ����ϴ� ���α׷��� �ۼ����ּ���.

[����]

�Ʒ��� ���� �Է��� �ð�ȭ �� ���Դϴ�.
������ ������ ������ Ʃ�긦, �Ķ��� ������ ���� ǥ���մϴ�.

image.png

1�� ������ �����Ͽ� N(9)�� ������ �̵��ϰ��� �� ��, �湮�ϴ� �ּ� ���� ������ �Ʒ� ��η�4���� �˴ϴ�.
1 -> 3 -> 6 -> 9

image.png

1 -> 5 -> 6 -> 9
image.png

�Է�
ù° �ٿ���3���� �ڿ���N, K, M�� �־����ϴ�. N�� ���� ��, K�� �ϳ��� ������ Ʃ�갡 �����ϴ� ���� ��, M�� ������ Ʃ���� ���� �ǹ��մϴ�.(N �� 100,000, K�� 1,000, M�� 1,000)
���� M���� �ٿ� ���� �� �ٿ� �ϳ��� ������ Ʃ�꿡 ���� ������ �־����ϴ�.
������ Ʃ�꿡 ���� ������ ��K���� �ڿ����� �־����� �� �ڿ����� �ش� ������ Ʃ�갡 �����ϴ� ���� ��ȣ�Դϴ�.

���
�ּ����� ȯ������1�� ������N�� ������ �̵��� ��,�湮�ϴ� ���� ���� ����մϴ�.
1�� ������N�� ������ �� �� ���� ��� -1�� ����մϴ�.

�Է� ���� 1 
9 3 5
1 2 3
1 4 5
3 6 7
5 6 7
6 8 9

��� ���� 1
4
 */
package type.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class M2_3 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, k, m;
    // ���� ����Ʈ
    static ArrayList<Integer>al[]; 

    static int bfs(int node) {
        // 1. queue
        Queue<Integer>q = new LinkedList<>();
        q.add(node); 

        // 2. visited
        int[]visited = new int[n+m+1];
        visited[node] = 1;

        // bfs
        while(!q.isEmpty()) {
            int now = q.poll();
            // now�� ���� �����ִ� ��� Ȯ��
            for(int i = 0; i < al[now].size(); i++) {
                int next = al[now].get(i);
                // �湮 üũ
                if(visited[next] > 0)
                    continue;
                // ** ���±����� ������ �ٸ��� 
                // ���� N�� ������ �����ϱ� ���ؼ� ��� ���ĿԴ°�?
                // visited�� �ٸ��� ���� �ִ� ��� 
                // --> ���±��� ��� ��带 ���ĿԴ°��� ����ϴµ��� ��� ����
                visited[next] = visited[now] + 1;
                q.add(next); 
            }
        }
        // visited[N] -> N�� ������ �� ��� ��带 ���ĿԴ°�? 
        return visited[n]; 
    }

    public static void main(String[] args) throws IOException {
         st = new StringTokenizer(br.readLine());
         n = Integer.parseInt(st.nextToken());
         k = Integer.parseInt(st.nextToken());
         m = Integer.parseInt(st.nextToken());

         // arraylist init 
         // ����� ����(1���� ����) + ������ Ʃ�굵 �ϳ��� ���� �ν� 
         al = new ArrayList[n + m + 1];
         for(int i = 0; i <= n + m; i++)
             al[i] = new ArrayList<>();

         // ������Ʃ�� input 
         for(int i = 1; i <= m; i++) {
             st = new StringTokenizer(br.readLine());
             // ������ Ʃ���� ��ȣ �۾�
             // ��� : 1~N�� ��ȣ�� ��� 
             // ������ Ʃ��� : N+@ �Ѿ�� ��ȣ���� ���
             int ht = n + i; 
             for(int j = 0; j < k; j++) {
                 // from -> to ���� ��������Ʈ
                 // ����� ����
                 int to = Integer.parseInt(st.nextToken());
                 al[ht].add(to);
                 al[to].add(ht);
             }
         }
         // 1 -> n��������
         int ans = bfs(1);
         if(ans == 0)
             System.out.println(-1);
         // ��� -> Ʃ�� ->��� ->Ʃ�� ->....->���
         // ���� ������ = �׻� ��尳�� = Ʃ���� ���� + 1
         // ���ݱ��� ���Ŀ� (��� + 1) / 2 -> �湮�� ����� ���� 
         else
             System.out.println((ans+1) / 2);

    }
}