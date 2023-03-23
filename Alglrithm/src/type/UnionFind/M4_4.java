/*
���ž 20230320

2���� ��� ���� N���� ������ ������ ��ġ�Ǿ� �ִ�. �� ������ �����鿡�� �ϳ��� ���ž�� ��ġ�Ǿ� �ִ�.
�� ���ž�� ��ġ ��ġ�κ��� R�̳� �Ÿ��� ���ԵǴ� ��� ������ ��ȣ�� ���� ����� �� �� �ִ�.
���� ��� A�� B������ ���ž�� ��ȣ ������ ��ų� ��ġ�� �κ��� �ִٸ�, �� ������ ���������� ����� �����ϴ�.

image.png

���� ���������� ����� �������� �ʴ���, ������ ������ ���ž�� ���� ���ž�� ���� ���������� ����� �����ϴٸ� A�� C�� ��ȣ���� ����� ������ ������ ����.
�Ʒ��� �������� A������ C������ ���������� ����� �Ұ���������, B������ ���� ����� �����ϴ�.

image.png

��ȣ�� ����� ������ ���������� ���� ������ �����ϴ� �ϳ��� �δ�� �ľǵȴ�.
������ ���ž�� ������ �־����� ��, �̸� �м��Ͽ� �� ���� �δ밡 ���Ǿ� �ִ����� �ľ��϶�.

�Է�
�Է� ������ ù ��° �ٿ� �׽�Ʈ ���̽��� ���� �ǹ��ϴ� �ڿ��� T�� �־�����. �� �������� T���� �׽�Ʈ ���̽��� �־�����.
������ �׽�Ʈ ���̽��� ���ؼ� ���� ������ ���� N (1 �� N �� 3,000)�� �־�����.
�̾ N�ٿ� ���� ���� ������ ��ǥ x, y (0 �� x, y �� 5,000), �׸��� �ش� ������ R (0 �� R �� 5,000)�� �־�����.
�־����� ���� ��� �����̴�.

���
�� �׽�Ʈ ���̽��� ���ؼ� �� �ٿ� ���� ���� ������ �׷� ������ ����Ѵ�.

�Է� ���� 1 
2
2
0 0 1
1 0 1
3
0 0 1
2 0 1
10 0 5

��� ���� 1
1
2
 */
package type.UnionFind;


import java.io.*;
import java.util.*; 
class Node { 
	int x,y ; 
	int range; 
	Node(int x, int y , int range) { 
		this.x = x; 
		this.y = y; 
		this.range = range; 
	}
}
public class M4_4 {


	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
	static StringTokenizer st;
	static int de; 
	static int N ;
	static Node arr[];
	static int parent[]; 
	static void input() throws IOException { 
		N = Integer.parseInt(br.readLine());
		arr = new Node[N]; 
		for(int i = 0 ; i < N; i ++) { 
			int x, y , range; 
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			range = Integer.parseInt(st.nextToken());
			arr[i] = new Node(x,y,range); 
		}
		parent = new int[N]; 
		for(int i = 0 ;i < N; i ++) { 
			parent[i] = i; 
		}
	}
	static int Find(int a) {
		if(parent[a] == a) return a; 
		int ret = Find(parent[a]); 
		parent[a] = ret; 
		return ret; 
	}
	static void Union(int a, int b)  { 
		int pa = Find(a); 
		int pb = Find(b); 
		if(pa == pb) return; 
		parent[pa] = pb; 
	}
	public static void main(String[] args)throws IOException {
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc ++) { 
			input() ;
			int answer = N; 
			for(int nodeA = 0 ; nodeA < N; nodeA ++)
			{
				// �� nodeA�� ���ؼ� 
				for(int nodeB = nodeA + 1; nodeB < N; nodeB ++) 
				{
					// (nodeA vs nodeB ) �׷��� ���� �� �ִ��� ? 
					int r = (arr[nodeA].range + arr[nodeB].range) ;
					r *= r; 
					int dist = (arr[nodeA].x - arr[nodeB].x) *(arr[nodeA].x - arr[nodeB].x)
							+ (arr[nodeA].y - arr[nodeB].y) *(arr[nodeA].y - arr[nodeB].y);
					if(dist > r) continue; 
					if(Find(nodeA) == Find(nodeB))continue; 
					Union(nodeA, nodeB); 
					answer -- ; 
				}
			}
			System.out.println(answer);
		}
	}
}