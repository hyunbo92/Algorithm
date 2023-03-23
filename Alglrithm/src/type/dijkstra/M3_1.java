/*
알뜰 기차여행 20230314

아르바이트로 모은 돈으로, 아영이와 기차 여행을 하고 있습니다.
아래 그래프에는 이동 경로마다 기차 비용이 적혀있습니다.
출발 지점에서 도착지점까지 가장 저렴한 방법으로 이동하려고 합니다.

image.png

만약 0번에서 출발하고 4번 지점이 도착지라면,
아래와 같은 경로로 이동하는 것이 최소 비용의 경로입니다.
이 비용이라면, 돈을 아껴 맛있는 식사를 할 수 있을 것 같습니다.

image.png

아영이 커플을 위해,가장 저렴한 노선의 비용을 알려주는 프로그램을 제작해 주세요.

입력
첫 줄에는 정점의 개수 N과 간선의 수 T를 입력 받습니다. (1 <= N <= 20,000 , 1 <= T <= 300,000)
모든 정점에는 0번 부터 N-1번까지 번호가 매겨져 있다고 가정합니다.
둘째 줄부터 T 개의 노선 정보를 입력 받는데, 각 줄마다 3개의 정수 (a, b, w)로 입력 받습니다. ( a와 b는 서로 다르며, 1 <= w <= 10,000 의 자연수)
이는 시간이 w 만큼 걸리는a 에서 b 로 가는 간선이 존재한다는 뜻입니다.
예를들어 0 2 4 인경우 0번에서 2번 노드까지 도착하는데 걸리는 시간은 4 입니다.

출력
0번 노드에서 출발하여 N - 1 노드에 도착해야 합니다.
이때, 가장 저렴하게 갈수 있는 비용을 출력해 주세요.
만약 갈수 있는 길이 없다면, "impossible" 을 출력합니다.

입력 예시 1 
5 8
0 1 6
0 2 1
0 3 7
2 3 2
3 1 2
1 3 4
1 4 1
4 3 3

출력 예시 1
6

입력 예시 2 
4 6
0 2 5
0 1 7
2 1 4
3 0 2
3 2 9
3 1 5
출력 예시 2

impossible
 */
package type.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class M3_1 {

	 static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    static StringTokenizer st;
	    static int n, t; 
	    static ArrayList<Edge>al[]; 
	    static Edge saveRoute[];
	    
	    static class Edge implements Comparable <Edge>{
	        int num; // 갈려고 하는 노드의 번호
	        int cost; // 갈려고 하는 노드까지의 간선의 값

	        Edge(int num, int cost){
	            this.num = num;
	            this.cost = cost; 
	        }

	        // PriorityQueue에서 cost가 작은순으로 나올수 있도록 처리 
	        @Override
	        public int compareTo(Edge next) {
	            if(cost < next.cost)
	                return -1;
	            if(cost > next.cost)
	                return 1;
	            return 0;
	        }
	    }
	    

	    static void routeSearch(int node,int dist[]) {
	        Queue<Integer> q=new LinkedList<Integer>();
	        q.add(n-1);
	        int maxcost=Integer.MIN_VALUE;
	        while(!q.isEmpty()) {
	        	int now=q.poll();
	        	System.out.println("now.num "+now + " , now.cost: "+saveRoute[now].cost);
	        	q.add(saveRoute[now].num);
	        	
	        	if(maxcost<saveRoute[now].cost){
	        		maxcost=saveRoute[now].cost;
	        	}
	        	if(now==node) {
	        		break;
	        	}
	        }
	        
	        System.out.println("maxcost: "+maxcost);
	        
	        System.out.println("최대cost 를 제외한 값 : "+(dist[n-1]-maxcost));
	        
	    }

	    
	    static void dijkstra(int node) {
	        // 1. PQ 세팅 
	        PriorityQueue<Edge>pq = new PriorityQueue<>();
	        // 시작 노드는 삽입 -> 시작까지의 cost = 0 
	        pq.add(new Edge(node, 0));

	        // 2. dist 세팅
	        int[] dist = new int[n]; 
	        
	        // dist는 항상 "최소값"을 저장을 하고 있을것이기 때문에 -> 최대값으로 세팅 
	        for(int i = 0; i < n; i++) 
	            dist[i] = Integer.MAX_VALUE;
	        dist[node] = 0;
	        
	        saveRoute[node]=new Edge(node,0);
	        // dijkstra
	        while(!pq.isEmpty()) {
	            // 맨 위의 Edge를 가져옵니다
	            Edge now = pq.poll(); 
	            // 여기서부터 갈 수 있는 간선들을 확인 
	            for(int i = 0; i < al[now.num].size(); i++) {
	                // 다음에 갈수 있는 노드/간선을 가져옴
	                Edge next = al[now.num].get(i); 
	                // 다음 노드까지의 총 비용 = 
	                // 내가 지금 있는 노드까지 오기 위해 사용한 비용 + 다음 노드까지 가기 위한 비용 
	                int ncost = dist[now.num] + next.cost;
	                // ncost가 정말 최소인가?
	                // 다음 노드까지 가기 위한 총 비용 < 여태까지 기록된 다음 노드까지의 총 비용보다 작으면 갱신
	                if(dist[next.num] <= ncost)
	                    continue;
	                // 최소 비용이 갱신되면 갱신
	                dist[next.num] = ncost;
	                saveRoute[next.num]=new Edge(now.num,next.cost);
	                
	                
	                // cost를 넣을때 여태까지 누적된 비용으로 새로 삽입 
	                pq.add(new Edge(next.num, ncost));
	            }
	        }
	        // 마지막으로 출력 -> n-1번이 못가는 지역 -> integer.max-value가 저장되어있을것.
	        if(dist[n-1] == Integer.MAX_VALUE)
	            System.out.println("impossible");
	        else
	            System.out.println(dist[n-1]);

	        
	        routeSearch(node,dist);
	        
	    
	        
	        
	    }
	   


	    public static void main(String[] args) throws IOException {
	        st = new StringTokenizer(br.readLine());
	        n = Integer.parseInt(st.nextToken());
	        t = Integer.parseInt(st.nextToken());

	        // arraylist init
	        al = new ArrayList[n]; 
	        saveRoute=new Edge[n];
	        
	        for(int i = 0; i <n; i++) {
	            al[i] = new ArrayList<>();
	        }

	        // 간선 입력 
	        for(int i = 0; i < t; i++) {
	            int from, to, cost;
	            st = new StringTokenizer(br.readLine());
	            from = Integer.parseInt(st.nextToken());
	            to = Integer.parseInt(st.nextToken());
	            cost = Integer.parseInt(st.nextToken());

	            //단방향 연결 
	            al[from].add(new Edge(to, cost)); 
	        }

	        dijkstra(0); 

	    }
	}