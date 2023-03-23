package type.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오.
//단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고, 
//더 이상 방문할 수 있는 점이 없는 경우 종료한다. 정점 번호는 1번부터 N번까지이다.


public class Bj1260 {

	static int[][] ad;//정점이 간선으로 서로 이어진 정보
	static boolean[] visit;//방문여부
	static int Nv,Ne;//정점의 개수,간선의 개수
	static int startpoint;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//기본 변수 입력
		Nv=sc.nextInt();
		Ne=sc.nextInt();
		startpoint=sc.nextInt();
		ad= new int[Nv+1][Nv+1];
		visit=new boolean[Nv+1];
		
		for(int i=0;i<Ne;i++) {//이건 그냥 간선들 입력 
			int t1,t2;
			t1=sc.nextInt();
			t2=sc.nextInt();
			
			ad[t1][t2]=ad[t2][t1]= 1;
			
		}
		dfs(startpoint);
		
		System.out.println();
		visit=new boolean[Nv+1];
		bfs(startpoint);
	}
	
	public static void bfs(int i ) {
		Queue<Integer> q= new LinkedList<Integer>();
		
		q.offer(i);// 큐에 넣기
		visit[i]= true;
		while(!q.isEmpty()) {
			int temp= q.poll();// 큐에서 빼기
			System.out.print(temp+" "); //뺀거 화면에 출력
			
			for(int j=1; j<=Nv; j++) {//큐에서 뺀거 주변에 간선으로 이어져 있는것들 확인
				if(ad[temp][j]==1&& visit[j]==false) {
					q.offer(j);
					visit[j]=true;
				}
			}
		}
	}
	
public static void dfs(int i) {
		
		visit[i]=true;
		System.out.print(i+" ");
		
		for(int j=1;j<=Nv;j++) {
			if(ad[i][j]==1&&visit[j]==false) {
				dfs(j);
			}
		}
	}
}

