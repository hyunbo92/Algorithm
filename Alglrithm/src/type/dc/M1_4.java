/*
같은 이진트리 거리

각 간선에 양수 가중치가 부여된 높이가 K인 포화 이진트리 (Perect Binary Tree, PBT)가 있다.
포화 이진 트리는 2 x k 만큼의 리프 노드의 개수를 가지며, 총 2^(k+1) -1 개의 노드를 가진다.
아래의 예시와 같이 k = 2인 포화 이진트리는 4개의 리프 노드, 그리고 총 7개의 노드의 개수를 가진다.

image.png

루트 노드로부터 리프 노드까지의 거리는 그 경로상에 있는 모든 간선들의 가중치의 합이다.
예를 들어 루트 노드로부터 맨 왼쪽 아래 리프 노드 까지의 거리는 2 + 2 = 4 이다.
우리는 포화 이진트리가 주어졌을 때, 간선들의 가중치를증가시켜, 루트 노드로부터 모든 리프 노드까지의 거리를 같도록 만드려고 한다.단, 간선들의 가중치들의 총 합은 최소가 되어야 한다.
예를 들어 아래와 같은 방법으로 간선의 가중치를 증가시켜 루트 노드로부터 모든 리프 노드까지의 거리를 같게 만들 수 있다.
하지만 가중치의 합이 최소가 되는 방법은 오른쪽 방법이다.

image.png

포화 이진트리의 모든 간선들의 가중치가 주어졌을 때, 어떤 간선들의 가중치를 증가시켜 루트 노드에서 모든 리프 노드까지의 거리가 같게 하면서 모든 간선의 가중치들의 총 합이 최소가 되도록 하는 프로그램을 작성하라.

입력
첫번째 줄에는 포화이진트리의 높이 k가 주어진다.  k(1 ≤ k ≤ 20)
두 번째 줄에는 모든 간선들의 가중치가 루트 노드에서 가까운 레벨에 있는 것부터 왼쪽에서 오른쪽의 순서대로 주어진다.
각 간선의 가중치는 1 이상 1,000 이하인 정수이다.

출력
루트 노드에서 모든 리프 노드까지의 거리가 같게 하는 간선들의 가중치의 합의 최소값을 출력한다.

입력 예시 1 
2
2 2 2 1 1 3

출력 예시 1
15
 */
package type.dc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class M1_4 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int k; 
    static int nodecnt; 
    static int[] tree; 
    static int ans = 0; 
    
    static int dc(int node) {
    	
    	// 방문한 노드의 값을 일단 누적
    	ans += tree[node]; 
    	
    	// 종료 조건
    	// 지금 노드 -> 왼쪽 아래로 내려가볼려 했는데, 총 노드 개수를 넘어간다 
    	// --> 지금 이 노드는 leaf node다!
    	if(node * 2 > nodecnt) { 
    		// 현재 노드의 값을 return 
    		return tree[node];
    	}
    	
    	// 재귀 구성 -> 설계 1번
    	// 왼쪽으로 분할 
    	int left = dc(node * 2);
    	// 오른쪽으로 분할
    	int right = dc(node * 2 + 1);
    	
    	//==========================
    	// left와 right, 두개의 비교 대상의 노드

    	// 설계 2번 -> left와 right를 통일된 값으로 변하기 위해 필요한 "차이" ans에 누적 
    	ans += Math.abs(left-right); 
    	
    	// 설계 3번 -> left와 right가 어떤 값으로 통일을 헀는지 -> 상위 노드에 알려주고
    	// 이를 계속해서 반복해서 root까지 올라갈것.
    	// 아래에서 통일하기로 한 값 = left, right 둘 중 더 "큰 값" -> 저희는 증가밖에 안됨.
    	return tree[node] + Math.max(left, right);
    }

    public static void main(String[] args) throws IOException {
    	
    	// height 입력 
    	k = Integer.parseInt(br.readLine());
    	
    	// height을 가지고 PBT의 총 노드 개수 ?? 
    	nodecnt = (int) Math.pow(2, k+1) - 1;
    	
    	// tree init <-- 트리의 구성 root = 1번부터 구성
    	tree = new int[nodecnt + 1]; 
    	
    	// input --> 2번 node부터 입력받기
    	st = new StringTokenizer(br.readLine());
    	for(int i = 2; i <= nodecnt; i++)
    		tree[i] = Integer.parseInt(st.nextToken());
    	
    	// root에서부터 내려가면서 트리의 분할정복 
    	dc(1);
    	System.out.println(ans);
    }
}