/*
콩쿠르 순위 예측기 20230314

Stanvard 예술대학교에는 한 해에 총 100만명이 입학 합니다.
이들은 각자 1 등 ~ 1,000,000 등 석차를 가지고 있습니다.
이 학생들은 입학 석차를 기준으로 학번을 발급 받습니다.
예를들어 32번은 32등으로 입학한 학생입니다.
이 예술대학교에서 콩쿠르를 개최합니다.
학생들은 자유롭게 참여하고, 참여 취소도 가능합니다.

image.png

당신은 Standvard 예술대학교의 시스템 관리자입니다.
제작해야 할 시스템에는 두 개의 Command가 존재합니다.
1 [학번] : 특정 학번의 사람이 대회에 참여합니다. 그리고 예상 순위를 출력합니다.
2 [학번] : 특정 학번의 사람이 대회 참여를 취소합니다. 그리고 남은 사람 수를 출력합니다.
만약  1, 3, 43번 학생이 대회에 참여를 희망한 상황에서,
7번 학생이 대회에 참여를 희망한다면, 예상 순위는 3위 입니다.
학생이 경연대회에 참여 신청을 했을 때, 예상 순위를 출력해주는 프로그램을 제작해주세요.

입력
첫 줄에는 Query의 개수 N 이 입력됩니다. ( 1 <= N <= 1,000,000)
두 번째 줄부터 [Command 번호] [학번] 이 N 줄에 걸쳐 입력됩니다.
(1 <= 학번 <= 1,000,000)
* 잘못된 입력 값은 주어지지 않습니다.

출력
대회에 참여할 때 마다,예상 순위를 출력합니다.
대회 참여 취소할 때 마다, 대회 참여 희망한 사람들의 수를 출력합니다.

입력 예시 1 
6
1 1
1 3
1 43
1 7
2 1
1 10

출력 예시 1
1위
2위
3위
3위
3명
3위

입력 예시 2 
18
1 15
1 14
1 13
1 12
1 11
2 11
1 11
1 1
1 2
1 3
1 20
2 1
2 2
2 3
1 21
1 1
1 2
1 3

출력 예시 2
1위
1위
1위
1위
1위
4명
1위
1위
2위
3위
9위
8명
7명
6명
7위
1위
2위
3위
 */
package type.dc;

import java.io.*;
import java.util.*;

public class M2_1 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int tree[];
	// init : 어떤 여러개의 data들을 한번에 초기화 ex) arr이라는 배열에 대한 segment tree를 관리해야 하는경우
	static int update(int node, int left, int right, int idx, int val) {
		// 1. idx라는 사람이 left~right범위에 없는 경우
		if(idx < left || right < idx)
			return tree[node];
		
		// 2. leaf까지 도달한 경우(단일 값을 갖는 node)
		if(left == right) // left == right == idx
			return tree[node] = val; // 각 학번의 사람이 1명이기에 바로 대입
		
		// 3. left~right '구간'에 idx가 포함되는 경우
		int mid = (left + right) / 2;
		int leftValue = update(node * 2, left, mid, idx, val);
		int rightValue = update(node * 2 + 1, mid+1, right, idx, val);
		return tree[node] = leftValue + rightValue;
	}
	
	static int query(int node, int left, int right, int start, int end) {
		// 1. left~right가 start~end범위를 벗어나는 경우
		if(right < start || end < left)
			return 0;
		
		// 2. left~right가 start~end범위 안에 완벽히 포함되는 경우
		if(start <= left && right <= end)
			return tree[node];
		
		// 3. left~right가 start~end범위에 '걸쳐'있는 경우
		int mid = (left + right) / 2;
		int leftValue = query(node * 2, left, mid, start, end);
		int rightValue = query(node * 2 + 1, mid +1, right, start, end);
		return leftValue + rightValue;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		tree = new int[1000001 * 4];
		
		int Q = Integer.parseInt(br.readLine());
		for(int i = 0; i < Q; i++)
		{
			st = new StringTokenizer(br.readLine());
			int oper = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			if(oper == 1)
			{
				update(1, 1, 1000000, num, 1);
				
				//1~num이라는 '내 이상의 능력'을 갖는 사람이 '몇 명'인가?
				System.out.println(query(1,1,1000000, 1, num) + "위");
			}
			else
			{
				update(1, 1, 1000000, num, 0);
				//전체명수
				System.out.println(query(1,1,1000000, 1, 1000000) + "명");
			}
		}
	}

}