//bfs ���ٲ��� ����
/*
�����̴� ������ ���ٲ����� �ϰ� �ִ�. �����̴� ���� �� N(0 �� N �� 100,000)�� �ְ�, ������ �� K(0 �� K �� 100,000)�� �ִ�. �����̴� �Ȱų� �����̵��� �� �� �ִ�. 
����, �������� ��ġ�� X�� �� �ȴ´ٸ� 1�� �Ŀ� X-1 �Ǵ� X+1�� �̵��ϰ� �ȴ�. �����̵��� �ϴ� ��쿡�� 1�� �Ŀ� 2*X�� ��ġ�� �̵��ϰ� �ȴ�.
�����̿� ������ ��ġ�� �־����� ��, �����̰� ������ ã�� �� �ִ� ���� ���� �ð��� �� �� ������ ���ϴ� ���α׷��� �ۼ��Ͻÿ�

ù ��° �ٿ� �����̰� �ִ� ��ġ N�� ������ �ִ� ��ġ K�� �־�����. N�� K�� �����̴�.

�����̰� ������ ã�� ���� ���� �ð��� ����Ѵ�

�Է� 5 17
��� 4
 */

package type.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bj1687 {


		static int N;//������ ��ġ(����)
		static int K;//���� ��ġ(��)
		static boolean visited[];
		static int cnt;
		
		public static void main(String[] args) {
			Scanner sc=new Scanner(System.in);
			N=sc.nextInt();//�����̰� �̵��ؼ� k�� ���°�
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
