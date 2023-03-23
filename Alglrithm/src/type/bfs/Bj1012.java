/*
 ������ ������ �ѳ��� ������ �������� ����� ���߸� ����ϱ�� �Ͽ���. ����� ���� �ʰ� ���߸� ����Ϸ��� ���߸� �������κ��� ��ȣ�ϴ� ���� �߿��ϱ� ������, �ѳ��� ���� ������ ȿ������ �����������̸� �����ϱ�� ����Ѵ�. 
 �� �����̴� ���߱�ó�� �����ϸ� ������ ��� �������ν� ���߸� ��ȣ�Ѵ�. Ư��, � ���߿� �����������̰� �� ������ ��� ������ �� �����̴� ������ �ٸ� ���߷� �̵��� �� �־�, �� ���ߵ� ���� �������κ��� ��ȣ���� �� �ִ�.

(�� ������ �����¿� �� ���⿡ �ٸ� ���߰� ��ġ�� ��쿡 ���� �������ִٰ� �����Ѵ�)

�ѳ��� ���߸� ����ϴ� ���� ���� ���ؼ� ���߸� �������� �ɾ���Ҵ�. ���ߵ��� ���ִ� ������ �����������̰� �� ������ ������ �ǹǷ� ���� �������ִ� ���ߵ��� �� ������ �����ִ��� �����ϸ� �� �� ������ �����̰� �ʿ����� �� �� �ִ�.

���� ��� ���߹��� �Ʒ��� ���� �����Ǿ� ������ �ּ� 5������ �����������̰� �ʿ��ϴ�.

(0�� ���߰� �ɾ��� ���� ���� ���̰�, 1�� ���߰� �ɾ��� �ִ� ���� ��Ÿ����.)


�Է��� ù �ٿ��� �׽�Ʈ ���̽��� ���� T�� �־�����. �� ���� �ٺ��� ������ �׽�Ʈ ���̽��� ���� ù° �ٿ��� ���߸� ���� ���߹��� ���α��� M(1 �� M �� 50)�� ���α��� N(1 �� N �� 50), 
�׸��� ���߰� �ɾ��� �ִ� ��ġ�� ���� K(1 �� K �� 2500)�� �־�����. �� ���� K�ٿ��� ������ ��ġ X(0 �� X �� M-1), Y(0 �� Y �� N-1)�� �־�����.


�� �׽�Ʈ ���̽��� ���� �ʿ��� �ּ��� ������������ ���� ���� ����Ѵ�.

�Է�
2
10 8 17
0 0
1 0
1 1
4 2
4 3
4 5
2 4
3 4
7 4
8 4
9 4
7 5
8 5
9 5
7 6
8 6
9 6
10 10 1
5 5

��� 
5 
1
 */

package type.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bj1012 {
	static int M,N;
	static int K;//���߰� �ɾ��� ���� 
	static int map[][];
	static boolean visited[][];
	static int cnt;
	static int dx[]= {1,-1,0,0};
	static int dy[]= {0,0,-1,1};
    public static void main(String[] args) {
    	
    	Scanner sc=new Scanner(System.in);
    	int T=sc.nextInt();
    	for(int test_case=0;test_case<T;test_case++) {
    		 M=sc.nextInt(); // ���߹��� ���α��� ���α��̰� ������ 
    		 N=sc.nextInt(); //���߹��� ���α��� ���α��̰� �����
    		 K=sc.nextInt();//���߰� �ɾ��� ����
    		 map=new int[M][N];
    		 visited=new boolean[M][N];
    		 cnt=0;
    		 
    		for(int i=0;i<K;i++) {//��ä��� 
    			int t1=sc.nextInt();
    			int t2=sc.nextInt();
    			map[t1][t2]=1;
    		}
    		
    		
    		
    	
    		
    		
    		for(int j=0;j<N;j++) {
    			for(int i=0;i<M;i++) {
    				
    				if(map[i][j]==1&&visited[i][j]==false) {
    					bfs(i,j);
    					cnt++;
    				}
    			}
    		}

    		
    		
    		System.out.println(cnt);
    		
    		
    		
    	}
    }
 
    public static void bfs(int x,int y) {
    	visited[x][y]=true;
    	Queue<direction> q=new LinkedList<direction>();
        q.add(new direction(x, y));
        
        while(!q.isEmpty()) {
//        	int size=q.size();
        	direction d=q.poll();
        	for(int i=0;i<4;i++) {
        		int nextX=d.x+dx[i];
        		int nextY=d.y+dy[i];
        		if(nextX>=0&&nextY>=0&&nextX<M&&nextY<N	) {
        		
        			 
        			if(map[nextX][nextY]==1&&visited[nextX][nextY]==false) {
        				visited[nextX][nextY]=true;
        				q.add(new direction(nextX, nextY));
        			}
        		}
        	}
        }
    }
    
}

class  direction {
    int x; int y;
    direction(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
