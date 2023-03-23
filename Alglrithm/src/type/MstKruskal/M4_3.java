/*20230314
미팅 앱

민코는 지속되는 코로나 때문에 연애를 못한지 너무 오래되었다.
캠퍼스 커플을 꿈꾸던 민코는 금년부터 다시 학교에 돌아가면서반드시 연애를 하겠다는 마음으로 대학교간의 도로 정보를 기반으로 미팅 앱을 만들기로 결심했다.
이 앱은 사용자들이 원활히 이성들을 만나기 위해 LOVE 경로를 제공한다. 이 경로는 3가지 특징을 가지고 있다.
LOVE 경로는 이성의 대학간만으로 이루어져 있다. 즉, 남초 대학은 여초 대학, 여초 대학은 남초 대학으로만 경로가 연결된다.
사용자들이 다양한 사람들과 미팅할 수 있도록 어떤 대학교에서 출발해도 다른 대학교로 이동 가능한 경로이다.
연애를 하고픈 사용자들이 1초의 시간이라도 낭비를 방지하고자 LOVE 경로의 길이는 최소가 되어야 한다.
대학교들의 정보가 주어졌을 때, 미팅 앱에서 제공할 최소 LOVE 경로의 거리를 구해보자.

[예시]
아래 입력을 시각화 하면 다음과 같다.

image.png

미팅 앱에서 제공할 3가지 조건을 모두 만족하는 최소 LOVE 경로는 아래와 같다. (10 + 12 + 5 + 7 = 34)

image.png

입력
첫번째 줄에 학교의 수 N와 학교를 연결하는 도로의 개수 M이 주어진다. (2 ≤ N ≤ 1,000) (1 ≤ M ≤ 10,000)
두번쨰 줄에 각 학교가 남초 대학교라면 M, 여초 대학교라면 W이 주어진다.
다음 M개의 줄에 u v d가 주어지며 u학교와 v학교가 연결되어 있으며 이 거리는 d임을 나타낸다. (1 ≤ u, v ≤ N) , (1 ≤ d ≤ 1,000)

출력
앱에서 제공할 LOVE경로의 최소길이를 출력한다.
모든 학교를 조건에 맞게 연결하는 경로가 없을 경우 -1을 출력한다.

입력 예시 1 
5 7
M W W W M
1 2 12
1 3 10
4 2 5
5 2 5
2 5 10
3 4 3
5 4 7

출력 예시 1
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
	    // 대학의 성별을 저장할 DAT
	    static char[] gender;

	    static int kruskal() {
	        // 1. 정렬
	        Collections.sort(al);
	        int ans = 0; 
	        int cnt = 0; 

	        for(int i = 0; i < al.size(); i++) {
	            Edge now = al.get(i);
	            // 이미 연결된 상태인가? 
	            if(find(now.a) == find(now.b))
	                continue;
	            //* 추가 조건 -> 같은 성별이면 연결 X 
	            if(gender[now.a] == gender[now.b])
	                continue;

	            //--------
	            // 여기까지 내려왔으면 -> 해당 간선 = MsT의 일원이 되었다!
	            // 간선 하나를 선택했다!
	            cnt++; 

	            ans += now.cost;
	            union(now.a, now.b);
	        }
	        // 최종적으로 MST를 구성하려고 했을때, 선택된 간선의 개수 == 총 노드의 개수 - 1
	        // --> MST를 성공적으로 만들수 있었다!
	        if(cnt == n -1)
	            return ans; 
	        else
	            return -1;
	    }

	    public static void main(String[] args) throws IOException {
	        st = new StringTokenizer(br.readLine());
	        n = Integer.parseInt(st.nextToken());
	        m = Integer.parseInt(st.nextToken());

	        // gender 초기화
	        gender = new char[n+1];

	        // parent 초기화
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