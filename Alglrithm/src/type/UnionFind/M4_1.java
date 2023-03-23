/*
순환회로 검사 20230315

전제제품을 만들 때,
순환회로가 발생하지 않도록, 회로를 설계하는 것이 중요합니다.
아래는 C - D 케이블 연결 직 후 이미지이며,
이로인해 C - D - E 순환회로가 발생합니다.

image.png

회로의 노드 간, 연결정보를 인접행렬로 입력 받습니다.
해당 회로에서 순환 회로가 존재하는지 확인해 주세요.

image.png

위 그래프에서는 순환 회로가 존재합니다.따라서 "WARNING" 를 출력합니다.

image.png

입력
노드의 개수 N을 입력 받습니다. (3 <= N <= 1000)
다음 줄부터 N x N 사이즈의 전자회로의 상태를 인접행렬 형태로 입력됩니다.

출력
입력받은 데이터에 순환회로가 존재한다면 "WARNING"을,
순환회로가 존재하지 않다면 "STABLE" 을 출력하세요.

입력 예시 1 
5
0 0 0 1 0
0 0 1 0 0
0 1 0 1 1
1 0 1 0 1
0 0 1 1 0

출력 예시 1
WARNING

입력 예시 2 
4
0 1 0 0
1 0 1 0
0 1 0 1
0 0 1 0

출력 예시 2
STABLE
 */
package type.UnionFind;


import java.io.*;
import java.util.*; 

public class M4_1 {
	
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    static StringTokenizer st; 
    static int n;
    static int map[][];
    static int parent[];
    
    static void union(int a,int b) {
    	int pa=find(a);
    	int pb=find(b);
    	
    	if(pa==pb) return;
    	
    	parent[pb]=pa;
    	
    }
    
    static int find(int node) {
    	if(node==parent[node]) return node;
    	
    	
    	
    	return parent[node]=find(parent[node]);
    }
    
    
	public static void main(String[] args) throws IOException {
		
		n=Integer.parseInt(br.readLine());
		map=new int[n][n];
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		parent=new int[n];
		for(int i=0;i<n;i++) {
			parent[i]=i;
		}
		
		int flag=0;
		for(int i=0;i<n;i++) {
			for(int j=i;j<n;j++) {  
				if(map[i][j]==0) continue;
				System.out.println(i+" " + j + " "+map[i][j]);
				if(find(i)==find(j)) {//map[i][j]=1 (둘이 이어져있고 ), 부모가 같다면 순환회로
					flag=1;
				}else {
					union(i,j);
				}
			}
		}
		
		if(flag==0) {
			System.out.println("stable");
		}else {
			System.out.println("warning");
		}
		
		
		
		

	}

}