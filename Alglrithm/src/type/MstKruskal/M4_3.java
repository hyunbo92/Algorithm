/*20230314
���� ��

���ڴ� ���ӵǴ� �ڷγ� ������ ���ָ� ������ �ʹ� �����Ǿ���.
ķ�۽� Ŀ���� �޲ٴ� ���ڴ� �ݳ���� �ٽ� �б��� ���ư��鼭�ݵ�� ���ָ� �ϰڴٴ� �������� ���б����� ���� ������ ������� ���� ���� ������ ����ߴ�.
�� ���� ����ڵ��� ��Ȱ�� �̼����� ������ ���� LOVE ��θ� �����Ѵ�. �� ��δ� 3���� Ư¡�� ������ �ִ�.
LOVE ��δ� �̼��� ���а������� �̷���� �ִ�. ��, ���� ������ ���� ����, ���� ������ ���� �������θ� ��ΰ� ����ȴ�.
����ڵ��� �پ��� ������ ������ �� �ֵ��� � ���б����� ����ص� �ٸ� ���б��� �̵� ������ ����̴�.
���ָ� �ϰ��� ����ڵ��� 1���� �ð��̶� ���� �����ϰ��� LOVE ����� ���̴� �ּҰ� �Ǿ�� �Ѵ�.
���б����� ������ �־����� ��, ���� �ۿ��� ������ �ּ� LOVE ����� �Ÿ��� ���غ���.

[����]
�Ʒ� �Է��� �ð�ȭ �ϸ� ������ ����.

image.png

���� �ۿ��� ������ 3���� ������ ��� �����ϴ� �ּ� LOVE ��δ� �Ʒ��� ����. (10 + 12 + 5 + 7 = 34)

image.png

�Է�
ù��° �ٿ� �б��� �� N�� �б��� �����ϴ� ������ ���� M�� �־�����. (2 �� N �� 1,000) (1 �� M �� 10,000)
�ι��� �ٿ� �� �б��� ���� ���б���� M, ���� ���б���� W�� �־�����.
���� M���� �ٿ� u v d�� �־����� u�б��� v�б��� ����Ǿ� ������ �� �Ÿ��� d���� ��Ÿ����. (1 �� u, v �� N) , (1 �� d �� 1,000)

���
�ۿ��� ������ LOVE����� �ּұ��̸� ����Ѵ�.
��� �б��� ���ǿ� �°� �����ϴ� ��ΰ� ���� ��� -1�� ����Ѵ�.

�Է� ���� 1 
5 7
M W W W M
1 2 12
1 3 10
4 2 5
5 2 5
2 5 10
3 4 3
5 4 7

��� ���� 1
34
 */
package type.MstKruskal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class M4_3 {
	   static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    static StringTokenizer st ;

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

	    static ArrayList<Edge> al = new ArrayList<>();
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

	    static int n ,m; 
	    // ������ ������ ������ DAT
	    static char[] gender;

	    static int kruskal() {
	        // 1. ����
	        Collections.sort(al);
	        int ans = 0; 
	        int cnt = 0; 

	        for(int i = 0; i < al.size(); i++) {
	            Edge now = al.get(i);
	            // �̹� ����� �����ΰ�? 
	            if(find(now.a) == find(now.b))
	                continue;
	            //* �߰� ���� -> ���� �����̸� ���� X 
	            if(gender[now.a] == gender[now.b])
	                continue;

	            //--------
	            // ������� ���������� -> �ش� ���� = MsT�� �Ͽ��� �Ǿ���!
	            // ���� �ϳ��� �����ߴ�!
	            cnt++; 

	            ans += now.cost;
	            union(now.a, now.b);
	        }
	        // ���������� MST�� �����Ϸ��� ������, ���õ� ������ ���� == �� ����� ���� - 1
	        // --> MST�� ���������� ����� �־���!
	        if(cnt == n -1)
	            return ans; 
	        else
	            return -1;
	    }

	    public static void main(String[] args) throws IOException {
	        st = new StringTokenizer(br.readLine());
	        n = Integer.parseInt(st.nextToken());
	        m = Integer.parseInt(st.nextToken());

	        // gender �ʱ�ȭ
	        gender = new char[n+1];

	        // parent �ʱ�ȭ
	        parent = new int[n+1];
	        for(int i = 0; i <=n; i++)
	            parent[i] = i;
	        st = new StringTokenizer(br.readLine());
	        for(int i = 1; i <= n; i++)
	            gender[i] = st.nextToken().charAt(0);

	        for(int i = 0; i < m; i++) {
	            st = new StringTokenizer(br.readLine());
	            int a = Integer.parseInt(st.nextToken());
	            int b = Integer.parseInt(st.nextToken());
	            int cost = Integer.parseInt(st.nextToken());
	            al.add(new Edge(a, b, cost)); 
	        }
	        int ans = kruskal();
	        System.out.println(ans);
	    }
	}