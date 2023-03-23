/*
�ε�� ��â�� 20230315

�Ǳ� ���ָ� ���ϴ� �ε�𿡰� A ~ Z ������ �� ������ �г����� �ο��մϴ�.
�̸��� �ο��޴� ������, ���ռ� �����ϰ� �� �� �ֽ��ϴ�.

image.png

�ε�� ������ ���� �����ڵ�� ���� ���� �����Ͽ� �ϳ��� ���ɽ�Ʈ�� �ϼ��մϴ�.
�ʱ���´� 26�� (A ~ Z)�� ���� �����ڷ� ���ɽ�Ʈ�� �����մϴ�.
�ε�� ������ A ~ Z ����� ��, ����� �������� ���� �ұԸ� ���� �����մϴ�.
���� ������ A, B �� ������ ����ϰ�, B, C�� ������ ����ϴٰ� �ǴܵǸ�
{A,B} / {B,C} �� �Է��� �־����ϴ�. �̷� ��� A, B, C �� �� ���Դϴ�.
�����ڵ��� ��������  ������ ���� ��,
�� ���� ���� �����ϰ�, ���� �������� ���� ���� ���� �����ڰ� ������� ����� �ּ���.

�Է�
ù �ٿ��� N ���� ����� ���ɴϴ�. (1 <= N <= 10,000)
���� N ���� �ٿ��� ���ĺ� 2���� ������ ���� �ε����� �г����� �Էµ˴ϴ�.
�Է¹��� �� �ε���� �̹� ���� ���ΰ��, ����� �����մϴ�.

���
ù �ٿ��� ������ ���� ���� ����ϼ���.
���� �ٿ��� ���� ������ �ε����� ���� ����մϴ�.

�Է� ���� 1 
6
A B
B C
E D
F E
G H
I J

��� ���� 1
4
16
 */
package type.UnionFind;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer; 

public class M4_2 {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int n;
	static int[][] t;
	static char parent[];
	static int memcnt[];
	
	
	static char find(char node) {
		
		if(node==parent[node])
			return node;
		
		
		
		return parent[node]=find(parent[node]);
	}
	
	static void union(char a,char b) {
		char pa=find(a);
		char pb=find(b);
		
		if(pa==pb)
			return;
		
		memcnt[pa]+=memcnt[pb];
		memcnt[pb]=0;
		
		parent[pb]=pa;
		
	}


	
	public static void main(String[] args) throws IOException {
		
		n=Integer.parseInt(br.readLine());
		
		parent= new char[200];
		memcnt= new int[200];
		
		for(char ch= 'A';ch <='Z';ch++	){//parent�ʱ�ȭ
			parent[ch]=ch;
			memcnt[ch]=1;
		}
		
		
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			char a=st.nextToken().charAt(0);
			char b=st.nextToken().charAt(0);
			union(a,b);
		}
		
		int teamcnt=0;
		int solocnt=0;
		for(char ch='A'; ch<='Z'; ch ++) {
			if(memcnt[ch]>1) {
				teamcnt++;
			}
			if(memcnt[ch]==1) {
				solocnt++;
			}
			
		}
		System.out.println(teamcnt+"\n"+solocnt);

	
	}

}

