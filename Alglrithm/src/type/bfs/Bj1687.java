//bfs 숨바꼭질 문제
/*
수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다. 수빈이는 걷거나 순간이동을 할 수 있다. 
만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.
수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오

첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.

수빈이가 동생을 찾는 가장 빠른 시간을 출력한다

입력 5 17
출력 4
 */

package type.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bj1687 {


		static int N;//수빈이 위치(시작)
		static int K;//동생 위치(끝)
		static boolean visited[];
		static int cnt;
		
		public static void main(String[] args) {
			Scanner sc=new Scanner(System.in);
			N=sc.nextInt();//수빈이가 이동해서 k로 가는것
			K=sc.nextInt();
			visited=new boolean[100001];
			cnt=0;
			
			bfs(N);
	}
	
		
		public static void bfs(int x) {
			visited[x]=true;
			Queue<Integer> q=new LinkedList<Integer>();
			q.add(x);
			while(!q.isEmpty()) {
				int size=q.size();
				for(int i=0;i<size;i++) {
				int temp=q.poll();
				//System.out.print(temp+" ");
				if(temp==K) {
					System.out.println(cnt);
					return;
				}
				int nextX=temp+1;
				int nextY=temp-1;
				int nextZ=2*temp;
				
				if(nextX>=0&&nextX<=100000&&visited[nextX]==false) {
					visited[nextX]=true;
					q.add(nextX);
				}
				if(nextY>=0&&nextY<=100000&&visited[nextY]==false) {
					visited[nextY]=true;
					q.add(nextY);
				}
				if(nextZ>=0&&nextZ<=100000&&visited[nextZ]==false) {
					visited[nextZ]=true;
					q.add(nextZ);
					
				}
				}
				cnt++;
			}
			
		}

}
