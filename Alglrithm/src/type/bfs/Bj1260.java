package type.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//�׷����� DFS�� Ž���� ����� BFS�� Ž���� ����� ����ϴ� ���α׷��� �ۼ��Ͻÿ�.
//��, �湮�� �� �ִ� ������ ���� ���� ��쿡�� ���� ��ȣ�� ���� ���� ���� �湮�ϰ�, 
//�� �̻� �湮�� �� �ִ� ���� ���� ��� �����Ѵ�. ���� ��ȣ�� 1������ N�������̴�.


public class Bj1260 {

	static int[][] ad;//������ �������� ���� �̾��� ����
	static boolean[] visit;//�湮����
	static int Nv,Ne;//������ ����,������ ����
	static int startpoint;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//�⺻ ���� �Է�
		Nv=sc.nextInt();
		Ne=sc.nextInt();
		startpoint=sc.nextInt();
		ad= new int[Nv+1][Nv+1];
		visit=new boolean[Nv+1];
		
		for(int i=0;i<Ne;i++) {//�̰� �׳� ������ �Է� 
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
		
		q.offer(i);// ť�� �ֱ�
		visit[i]= true;
		while(!q.isEmpty()) {
			int temp= q.poll();// ť���� ����
			System.out.print(temp+" "); //���� ȭ�鿡 ���
			
			for(int j=1; j<=Nv; j++) {//ť���� ���� �ֺ��� �������� �̾��� �ִ°͵� Ȯ��
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

