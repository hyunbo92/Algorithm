/*
춘추 전국 시대 20230315

춘추 전국 시대는 여러 국가간의 전쟁과 동맹이 난무하던 시대였습니다.
한 때에는 N개의 국가가 존재하며, 각 국가들은 P명의 인구 수를 가지고 있습니다.
아래 그림은 A부터 G까지의 총 7개의 나라를 나타낸 지도입니다.

3fb1557cdc58422d4b62ce86afef4231.png

각 국가의 인구 수는 다음과 같다고 가정합시다.
A : 10
B : 20
C : 30
D : 40
E : 50
F : 60
G : 70
B와 D는 동맹을 맺고, A와 C와 F가 동맹을 맺었다고 가정해 봅시다.
동맹을 맺은 국가끼리는 전쟁을 일으키지 않으며, 동맹을 파기하는 일 또한 없습니다.
이러한 상황에서, B연합군(D + B) 와 F연합군(A + C + F)가 전쟁을 하게 된다고 합시다.

image.png

이런 상황에서는 둘 중 연합군의 인구수가 더 큰 국가가 승리합니다.
B연합군의 인구 수는  60 (D + B), F연합군의 인구 수는 100 입니다. (A + C + F)
이 전쟁에서 B연합군의 국가들은 멸망하고, 살아남은 국가의 수는 5개가 됩니다. (A, C, E, F, G)
만약 두 국가의 인구 수가 동일하다면, 두 국가 모두 멸망합니다.

image.png

춘추 전국 시대의 여러 동맹과 전쟁 이후 살아남은 국가의 수를 출력해 주세요.

입력
첫번째 줄에 각 국가의 수 N을 입력받습니다. (2 <= N <= 25)
두번째 줄에 N개 각 국가의 인구 수를 입력 받습니다. 인구의 수는 0 이상, 100,000 이하입니다.
세번째 줄에 동맹과 전쟁의 상황의 개수 T를 입력 받습니다. (1 <= T <= 100)
그 후의 T개의 줄에는 상황, 국가 A, 국가 B를 한 줄로 입력 받습니다.
상황에서 "alliance"는 동맹, "war"은 전쟁을 의미합니다.

출력
첫번째 줄에 동맹과 전쟁 이후 살아남은 국가의 개수를 출력합니다.

입력 예시 1 
7
10 20 30 40 50 60 70
5
alliance A C
alliance F C
alliance D B
alliance A F
war D F

출력 예시 1
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
				// 살아남은 연합중에 ch가 대표일때
				answer  += memCnt[ch]; 
			}
		}
		System.out.println(answer );
	}
}



/*내가짠코드
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