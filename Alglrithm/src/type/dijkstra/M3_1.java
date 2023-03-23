/*
�˶� �������� 20230314

�Ƹ�����Ʈ�� ���� ������, �ƿ��̿� ���� ������ �ϰ� �ֽ��ϴ�.
�Ʒ� �׷������� �̵� ��θ��� ���� ����� �����ֽ��ϴ�.
��� �������� ������������ ���� ������ ������� �̵��Ϸ��� �մϴ�.

image.png

���� 0������ ����ϰ� 4�� ������ ���������,
�Ʒ��� ���� ��η� �̵��ϴ� ���� �ּ� ����� ����Դϴ�.
�� ����̶��, ���� �Ʋ� ���ִ� �Ļ縦 �� �� ���� �� �����ϴ�.

image.png

�ƿ��� Ŀ���� ����,���� ������ �뼱�� ����� �˷��ִ� ���α׷��� ������ �ּ���.

�Է�
ù �ٿ��� ������ ���� N�� ������ �� T�� �Է� �޽��ϴ�. (1 <= N <= 20,000 , 1 <= T <= 300,000)
��� �������� 0�� ���� N-1������ ��ȣ�� �Ű��� �ִٰ� �����մϴ�.
��° �ٺ��� T ���� �뼱 ������ �Է� �޴µ�, �� �ٸ��� 3���� ���� (a, b, w)�� �Է� �޽��ϴ�. ( a�� b�� ���� �ٸ���, 1 <= w <= 10,000 �� �ڿ���)
�̴� �ð��� w ��ŭ �ɸ���a ���� b �� ���� ������ �����Ѵٴ� ���Դϴ�.
������� 0 2 4 �ΰ�� 0������ 2�� ������ �����ϴµ� �ɸ��� �ð��� 4 �Դϴ�.

���
0�� ��忡�� ����Ͽ� N - 1 ��忡 �����ؾ� �մϴ�.
�̶�, ���� �����ϰ� ���� �ִ� ����� ����� �ּ���.
���� ���� �ִ� ���� ���ٸ�, "impossible" �� ����մϴ�.

�Է� ���� 1 
5 8
0 1 6
0 2 1
0 3 7
2 3 2
3 1 2
1 3 4
1 4 1
4 3 3

��� ���� 1
6

�Է� ���� 2 
4 6
0 2 5
0 1 7
2 1 4
3 0 2
3 2 9
3 1 5
��� ���� 2

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
	        int num; // ������ �ϴ� ����� ��ȣ
	        int cost; // ������ �ϴ� �������� ������ ��

	        Edge(int num, int cost){
	            this.num = num;
	            this.cost = cost; 
	        }

	        // PriorityQueue���� cost�� ���������� ���ü� �ֵ��� ó�� 
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
	        
	        System.out.println("�ִ�cost �� ������ �� : "+(dist[n-1]-maxcost));
	        
	    }

	    
	    static void dijkstra(int node) {
	        // 1. PQ ���� 
	        PriorityQueue<Edge>pq = new PriorityQueue<>();
	        // ���� ���� ���� -> ���۱����� cost = 0 
	        pq.add(new Edge(node, 0));

	        // 2. dist ����
	        int[] dist = new int[n]; 
	        
	        // dist�� �׻� "�ּҰ�"�� ������ �ϰ� �������̱� ������ -> �ִ밪���� ���� 
	        for(int i = 0; i < n; i++) 
	            dist[i] = Integer.MAX_VALUE;
	        dist[node] = 0;
	        
	        saveRoute[node]=new Edge(node,0);
	        // dijkstra
	        while(!pq.isEmpty()) {
	            // �� ���� Edge�� �����ɴϴ�
	            Edge now = pq.poll(); 
	            // ���⼭���� �� �� �ִ� �������� Ȯ�� 
	            for(int i = 0; i < al[now.num].size(); i++) {
	                // ������ ���� �ִ� ���/������ ������
	                Edge next = al[now.num].get(i); 
	                // ���� �������� �� ��� = 
	                // ���� ���� �ִ� ������ ���� ���� ����� ��� + ���� ������ ���� ���� ��� 
	                int ncost = dist[now.num] + next.cost;
	                // ncost�� ���� �ּ��ΰ�?
	                // ���� ������ ���� ���� �� ��� < ���±��� ��ϵ� ���� �������� �� ��뺸�� ������ ����
	                if(dist[next.num] <= ncost)
	                    continue;
	                // �ּ� ����� ���ŵǸ� ����
	                dist[next.num] = ncost;
	                saveRoute[next.num]=new Edge(now.num,next.cost);
	                
	                
	                // cost�� ������ ���±��� ������ ������� ���� ���� 
	                pq.add(new Edge(next.num, ncost));
	            }
	        }
	        // ���������� ��� -> n-1���� ������ ���� -> integer.max-value�� ����Ǿ�������.
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

	        // ���� �Է� 
	        for(int i = 0; i < t; i++) {
	            int from, to, cost;
	            st = new StringTokenizer(br.readLine());
	            from = Integer.parseInt(st.nextToken());
	            to = Integer.parseInt(st.nextToken());
	            cost = Integer.parseInt(st.nextToken());

	            //�ܹ��� ���� 
	            al[from].add(new Edge(to, cost)); 
	        }

	        dijkstra(0); 

	    }
	}