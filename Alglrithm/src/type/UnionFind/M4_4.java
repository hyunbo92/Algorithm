/*
통신탑 20230320

2차원 평면 위의 N곳에 적군의 진영이 설치되어 있다. 각 적군의 진영들에는 하나의 통신탑이 설치되어 있다.
각 통신탑은 설치 위치로부터 R이내 거리에 포함되는 모든 영역에 신호를 보내 통신을 할 수 있다.
예를 들어 A와 B진형의 통신탑의 신호 영역이 닿거나 겹치는 부분이 있다면, 두 진영은 직접적으로 통신이 가능하다.

image.png

물론 직접적으로 통신이 가능하지 않더라도, 임의의 진영의 통신탑이 여러 통신탑을 거쳐 최종적으로 통신이 가능하다면 A와 C는 상호간에 통신이 가능한 것으로 본다.
아래의 예에서는 A진형과 C진형이 직접적으로 통신이 불가능하지만, B진형을 거쳐 통신이 가능하다.

image.png

상호간 통신이 가능한 진영끼리는 같은 작전을 수행하는 하나의 부대로 파악된다.
적군의 통신탑의 정보가 주어졌을 때, 이를 분석하여 몇 개의 부대가 편성되어 있는지를 파악하라.

입력
입력 파일의 첫 번째 줄에 테스트 케이스의 수를 의미하는 자연수 T가 주어진다. 그 다음에는 T개의 테스트 케이스가 주어진다.
각각의 테스트 케이스에 대해서 적군 진영의 숫자 N (1 ≤ N ≤ 3,000)이 주어진다.
이어서 N줄에 걸쳐 적군 진영의 좌표 x, y (0 ≤ x, y ≤ 5,000), 그리고 해당 진영의 R (0 ≤ R ≤ 5,000)이 주어진다.
주어지는 수는 모두 정수이다.

출력
각 테스트 케이스에 대해서 한 줄에 걸쳐 적군 진영의 그룹 개수를 출력한다.

입력 예시 1 
2
2
0 0 1
1 0 1
3
0 0 1
2 0 1
10 0 5

출력 예시 1
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
				// 각 nodeA에 대해서 
				for(int nodeB = nodeA + 1; nodeB < N; nodeB ++) 
				{
					// (nodeA vs nodeB ) 그룹을 지을 수 있는지 ? 
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