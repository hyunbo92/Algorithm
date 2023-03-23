/*
인디언 합창단 20230315

악기 연주를 잘하는 인디언에게 A ~ Z 사이의 한 글자의 닉네임을 부여합니다.
이름을 부여받는 집안은, 대대손손 부유하게 살 수 있습니다.

image.png

인디언 족장은 개인 연주자들과 내부 팀을 조직하여 하나의 오케스트라를 완성합니다.
초기상태는 26명 (A ~ Z)의 개인 연주자로 오케스트라를 구성합니다.
인디언 족장은 A ~ Z 사람들 중, 비슷한 음색끼리 묶어 소규모 팀을 조직합니다.
만약 족장이 A, B 가 음색이 비슷하고, B, C가 음색이 비슷하다고 판단되면
{A,B} / {B,C} 로 입력이 주어집니다. 이런 경우 A, B, C 는 한 팀입니다.
연주자들을 여러개의  팀으로 묶은 뒤,
몇 개의 팀이 존재하고, 팀이 조직되지 못한 남은 개인 연주자가 몇명인지 출력해 주세요.

입력
첫 줄에는 N 개의 명령이 들어옵니다. (1 <= N <= 10,000)
다음 N 개의 줄에는 알파벳 2개씩 팀으로 묶을 인디언들의 닉네임이 입력됩니다.
입력받은 두 인디언이 이미 같은 팀인경우, 명령을 무시합니다.

출력
첫 줄에는 조직된 팀의 수를 출력하세요.
다음 줄에는 개인 연주자 인디언들의 수를 출력합니다.

입력 예시 1 
6
A B
B C
E D
F E
G H
I J

출력 예시 1
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
		
		for(char ch= 'A';ch <='Z';ch++	){//parent초기화
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

