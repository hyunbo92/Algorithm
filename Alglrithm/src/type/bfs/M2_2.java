/*
 ��̳� ���� 20230321
 
 �ڵ������ 1������ N�� ���ÿ� M���� �ܹ��� ���ΰ� �����ϴ� �����̴�.
�ڵ� ������ ������ ������ 5�� 5�� ��̳��� �ٸ� ���÷� 1�� 2�� ������ ������ �Ѵ�.
�������� ���� ������ ������ ������ �����ִ� �⸧���� ĭ�� ��� �Ҹ��Ͽ� �� �� �ִ� ���÷� �������� ���Ϸ��� �Ѵ�.
�ڵ����󿡼� ��� ���� ���� ������ �Ÿ��� �����ϸ�, �� ���÷κ��� �ٸ� ���ñ����� ��Ȯ�� �� ĭ�� �⸧�� �Ҹ�ȴ�.
�ڵ������� ����, ���� ������, ���� �⸧���� ĭ ��, �׸��� ������ ������ �� ��� �ִ� ���� ��ȣ�� �־����� ��, ������ ������ ��̳� �������� �� �� �ִ� �ĺ� ���õ��� ������������ ����ϴ� ���α׷��� �ۼ��϶�.

[���� 1]
������ ���� �Է�1�� �ð�ȭ�� ���̴�.

image.png

������ ���� 1���̰�, ���� �⸧���� ĭ�� 2��� ���� �� :
1 -> 2 : �⸧�� 1ĭ �Ҹ� (1 - 2)
1 -> 3 : �⸧�� 2ĭ �Ҹ� (1 - 2 - 3)
1 -> 4 : �⸧�� 2ĭ �Ҹ� (1 - 2 - 4)
�� ���� �⸧���� ���� �� �� �ִ� ���� ��  2���� ������ 3, 4�� �������� �ĺ��� �Ǹ�, ������������ 3, 4�� ������� ����Ѵ�.
2�� ���ô� ���� �⸧���� ��� �Ҹ����� ���Ͽ�, ������ �ĺ��� ���Ե��� �ʴ´�.

[���� 2]
������ ���� �Է�2�� �ð�ȭ�� ���̴�.

image.png

������ ���� 1���̰�, ���� �⸧���� ĭ�� 2��� ���� �� :
1 -> 2 : �⸧�� 1ĭ �Ҹ� (1 - 2)
2�� ���ô� ���� �⸧���� ��� �Ҹ����� ���Ͽ�, ������ �ĺ��� ���Ե��� �ʴ´�.
�⸧���� ��� �Ҹ��Ͽ� �� �� �ִ� ���ð� ���� ������, -1�� ����Ѵ�.

�Է�
ù��° �ٿ� ������ ���� N, ������ ���� M, ���� �⸧���� ĭ �� K, �׸��� ������ ������ ��� ���� ��ȣ P�� �־�����. (2 ��N�� 300,000, 1 ��M�� 1,000,000, 1 ��K�� 300,000, 1 ��P��N)
�ι�° �ٺ��� M���� �ٿ� ���� �� ���� �ڿ��� A, B�� �������� �и��Ǿ� �־�����. �̴� A���� B�� ���ϴ� �ܹ��� ���ΰ� �����Ѵٴ� �ǹ��̴�.(1 ��A,B��N)
A�� B�� ������ �Է��� �־����� �ʴ´�.

���
������ ������ ���� �⸧���� ��� �Ҹ��Ͽ� �� �� �ִ� ������ �ĺ��� ���õ��� �� �� �� �ϳ��� ������������ ����Ѵ�.
���� �⸧���� ��� �Ҹ��Ͽ� �� �� �ִ� ������ �ĺ��� ���ٸ�, -1�� ����Ѵ�.

�Է� ���� 1 
4 4 2 1 
1 2
2 3
3 4
2 4

��� ���� 1
3
4

�Է� ���� 2 
4 3 2 1
1 2
4 2
3 2

��� ���� 2
-1 
 */
//�߿�!!!!!!!!!!!!!!
package type.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class M2_2 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, m, k, p; 
    // �� 300,000 ������� ��� X -> ���� ����Ʈ
    static ArrayList<Integer>al[]; 
    // K�Ÿ����� �湮 ������ ��带 ������ Arraylist
    static ArrayList<Integer>ans = new ArrayList<>(); 

    static class Node {
        int num; // ����� ��ȣ 
        int cnt; // ����� �⸧���� ����
        //constructor
        Node(int num, int cnt){
            this.num = num;
            this.cnt = cnt; 
        }
    }

    static void bfs(int node) {
        // 1. queue ����
        // ��� ��ȣ -> (����ȣ, ���±��� ��� �⸧���� �Ἥ �Դ���)
        // �⸧���� ��뷮 == K -> �� �� �ִ� �ϳ��� �ĺ����� ã�Ҵ�!
        Queue<Node>q = new LinkedList<>();
        // ù ��� ���� 
        q.add(new Node(node, 0));

        // 2. visited
        int[] visited = new int[n+1];
        visited[node] = 1; 

        // bfs
        while(!q.isEmpty()) {
            // �Ǿ��� ��� ����
            Node now = q.poll(); 

            // ** ���� �� ������ ������ ���� �ڵ� 
            // k������ �⸧���� �Ѿ�°� == > ������ ������ ��
            // ���ݱ��� ���� �⸧���� > K --> ����
            if(now.cnt > k)
                return; 

            // K��ŭ�� ����ؼ� �����ִ� ������ = ���信 �߰�
            // ���� ������ ���µ��� ���� �⸧���� �� == k -> �ϳ��� �ĺ����� ã�Ҵ�!
            if(now.cnt == k)
                ans.add(now.num);

            for(int i = 0; i < al[now.num].size(); i++) {
                // �湮üũ -> �ߺ��湮 X 
                int next = al[now.num].get(i);
                if(visited[next] == 1)
                    continue;
                visited[next] = 1;
                // ���� ��� : next ��ȣ , ���� ������� ���� ���ؼ� ����� �⸧�� + 1
                q.add(new Node(next, now.cnt+1));
            }
        }

    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());

        // ��������Ʈ init
        al = new ArrayList[n+1];
        for(int i = 0; i <= n; i++)
            al[i] = new ArrayList<>();

        // input
        for(int i = 0; i < m; i++) {
            int from, to;
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            // ��������Ʈ -> �ܹ��� ���� 
            al[from].add(to); 
        }
        // p�������� �����ϴ� bfs
        // bfs -> K�Ÿ���ŭ ���� �ִ� ��� ��带 ����. 
        bfs(p);

        // ��� 
        // ���� �ĺ����� ������ = -1 
        if(ans.size() == 0)
            System.out.println(-1);
        else {
            // �������� ��� --> ���� 
            Collections.sort(ans);
            for(int i = 0; i < ans.size(); i++) {
                System.out.println(ans.get(i));
            }
        }

    }
}