/*
��ȯȸ�� �˻� 20230315

������ǰ�� ���� ��,
��ȯȸ�ΰ� �߻����� �ʵ���, ȸ�θ� �����ϴ� ���� �߿��մϴ�.
�Ʒ��� C - D ���̺� ���� �� �� �̹����̸�,
�̷����� C - D - E ��ȯȸ�ΰ� �߻��մϴ�.

image.png

ȸ���� ��� ��, ���������� ������ķ� �Է� �޽��ϴ�.
�ش� ȸ�ο��� ��ȯ ȸ�ΰ� �����ϴ��� Ȯ���� �ּ���.

image.png

�� �׷��������� ��ȯ ȸ�ΰ� �����մϴ�.���� "WARNING" �� ����մϴ�.

image.png

�Է�
����� ���� N�� �Է� �޽��ϴ�. (3 <= N <= 1000)
���� �ٺ��� N x N �������� ����ȸ���� ���¸� ������� ���·� �Էµ˴ϴ�.

���
�Է¹��� �����Ϳ� ��ȯȸ�ΰ� �����Ѵٸ� "WARNING"��,
��ȯȸ�ΰ� �������� �ʴٸ� "STABLE" �� ����ϼ���.

�Է� ���� 1 
5
0 0 0 1 0
0 0 1 0 0
0 1 0 1 1
1 0 1 0 1
0 0 1 1 0

��� ���� 1
WARNING

�Է� ���� 2 
4
0 1 0 0
1 0 1 0
0 1 0 1
0 0 1 0

��� ���� 2
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
				if(find(i)==find(j)) {//map[i][j]=1 (���� �̾����ְ� ), �θ� ���ٸ� ��ȯȸ��
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