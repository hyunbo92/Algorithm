/*
���� ���� �ô� 20230315

���� ���� �ô�� ���� �������� ����� ������ �����ϴ� �ô뿴���ϴ�.
�� ������ N���� ������ �����ϸ�, �� �������� P���� �α� ���� ������ �ֽ��ϴ�.
�Ʒ� �׸��� A���� G������ �� 7���� ���� ��Ÿ�� �����Դϴ�.

3fb1557cdc58422d4b62ce86afef4231.png

�� ������ �α� ���� ������ ���ٰ� �����սô�.
A : 10
B : 20
C : 30
D : 40
E : 50
F : 60
G : 70
B�� D�� ������ �ΰ�, A�� C�� F�� ������ �ξ��ٰ� ������ ���ô�.
������ ���� ���������� ������ ����Ű�� ������, ������ �ı��ϴ� �� ���� �����ϴ�.
�̷��� ��Ȳ����, B���ձ�(D + B) �� F���ձ�(A + C + F)�� ������ �ϰ� �ȴٰ� �սô�.

image.png

�̷� ��Ȳ������ �� �� ���ձ��� �α����� �� ū ������ �¸��մϴ�.
B���ձ��� �α� ����  60 (D + B), F���ձ��� �α� ���� 100 �Դϴ�. (A + C + F)
�� ���￡�� B���ձ��� �������� ����ϰ�, ��Ƴ��� ������ ���� 5���� �˴ϴ�. (A, C, E, F, G)
���� �� ������ �α� ���� �����ϴٸ�, �� ���� ��� ����մϴ�.

image.png

���� ���� �ô��� ���� ���Ͱ� ���� ���� ��Ƴ��� ������ ���� ����� �ּ���.

�Է�
ù��° �ٿ� �� ������ �� N�� �Է¹޽��ϴ�. (2 <= N <= 25)
�ι�° �ٿ� N�� �� ������ �α� ���� �Է� �޽��ϴ�. �α��� ���� 0 �̻�, 100,000 �����Դϴ�.
����° �ٿ� ���Ͱ� ������ ��Ȳ�� ���� T�� �Է� �޽��ϴ�. (1 <= T <= 100)
�� ���� T���� �ٿ��� ��Ȳ, ���� A, ���� B�� �� �ٷ� �Է� �޽��ϴ�.
��Ȳ���� "alliance"�� ����, "war"�� ������ �ǹ��մϴ�.

���
ù��° �ٿ� ���Ͱ� ���� ���� ��Ƴ��� ������ ������ ����մϴ�.

�Է� ���� 1 
7
10 20 30 40 50 60 70
5
alliance A C
alliance F C
alliance D B
alliance A F
war D F

��� ���� 1
5
 */
package type.UnionFind;


import java.io.*;
import java.util.*; 

public class M4_3 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
	static StringTokenizer st;
	static int de; 
	static int N ;
	static char parent[] = new char[200]; 
	static int memCnt[] = new int[200]; //
	static int pop[] = new int[200]; 
	
	static void input() throws NumberFormatException, IOException { 
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(char ch = 'A'; ch <'A' + N ; ch ++)
		{
			pop[ch] = Integer.parseInt(st.nextToken());
			parent[ch] = ch; 
			memCnt[ch] = 1; 
		}
	}
	
	static char Find(char ch) {
		if(ch == parent[ch] ) { 
			return ch ;
		}
		char ret = Find(parent[ch]);
		parent[ch] = ret; 
		return ret; 
	}
	static void Union(char a, char b) { 
		char pa = Find(a); 
		char pb = Find(b); 
		
		if(pa == pb)return ;
		parent[pa] = pb;  // a....->pa -> pb<-....b
		memCnt[pb] += memCnt[pa]; 
		memCnt[pa] = 0 ; 
		pop[pb] += pop[pa]; 
		pop[pa] = 0;
	}
	public static void main(String[] args)throws IOException {
		input();
		int T = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < T ; i++) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			char a = st.nextToken().charAt(0); 
			char b = st.nextToken().charAt(0);
			if(cmd.equals("alliance")) {
				Union(a,b); 
			}
			if(cmd.equals("war")) {
				char big = Find(a); 
				char small = Find(b); 
				if(pop[big] < pop[small] ) { 
					char tmp = big; 
					big = small; 
					small = tmp; 
				}
				pop[small] = 0 ;
			}
		}
		int answer = 0 ; 
		for(char ch = 'A'; ch < 'A' + N; ch ++)  
		{
			if(pop[ch] > 0) { 
				// ��Ƴ��� �����߿� ch�� ��ǥ�϶�
				answer  += memCnt[ch]; 
			}
		}
		System.out.println(answer );
	}
}



/*����§�ڵ�
package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n,t;
	static int[] member;
	static char[] parent;
	static int[] memcnt;
	
	static char find(char node) {
		
		if(node==parent[node])
			return node;
		
		return parent[node]=find(parent[node]);
	}
	
	static void union(char a, char b) {
		
		char pa=find(a);
		char pb=find(b);
		
		if(pa==pb) return;
		
		parent[pb]=pa;
		memcnt[pa]+=memcnt[pb];
		memcnt[pb]=0;
		
		member[pa]+=member[pb];
		member[pb]=0;
		
		


		
	}
	
	
	public static void main(String[] args) throws IOException	{
		n=Integer.parseInt(br.readLine());
		st=new StringTokenizer(br.readLine());
		member = new int[200];
		memcnt = new int[200];
		parent = new char[200];
		
		for(char i='A';i<'A'+n;i++) {
			parent[i]=i;
			
			
		}
		
		
		for(int i='A';i<'A'+n;i++) {
			member[i]=Integer.parseInt(st.nextToken());
			memcnt[i]=1;
			
		}
		
		t=Integer.parseInt(br.readLine());
		
		for(int i=0;i<t;i++) {
			st=new StringTokenizer(br.readLine());
			String command=st.nextToken();
			char a=st.nextToken().charAt(0);
			char b=st.nextToken().charAt(0);
			if(command.equals("alliance")) {
				union(a,b);
			}
			
			if(command.equals("war")) {
				a=find(a);
				b=find(b);

				if(member[a]>member[b]) {
					member[b]=0;
				}
				if(member[a]<member[b]) {
					member[a]=0;
				}
				if(member[a]==member[b]) {
					member[a]=0;
					member[b]=0;
				}
				
				
			}
			
		}
		
		int answer=0;
		for(char i ='A'; i<'A'+n;i++) {
			if(member[i]>0) {
				answer+=memcnt[i];
			}
		}
		
		System.out.println(answer);
		
	}


}


*/