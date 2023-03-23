/*
Ugly Number

Ugly Number는 숫자 2, 3, 5 를 사용하여 만들어 낼 수 있는 수를 뜻합니다.
예외로 숫자 1은 첫 번째 Ugly Number입니다.
Ugly Number들을 순서대로 나열해보면 다음과 같습니다.
1, 2, 3, 4, 5, 6, 8, 9, 10, 12, ...

number.png
a, b, c ... 등 n 개의 수를 입력 받고,  a, b, c ... 번째 Ugly Number를 찾아 출력해 주세요.
만약 1 9 10 을 입력 받았다면, 출력 결과는 1 10 12 입니다.

입력
첫 번째 줄에 질의의 개수 Q가 입력됩니다. ( 1 <= Q <= 10,000)
각 질의 별로 K 가 입력되고 해당 K 번째 Ugly number 를 구해주시면 됩니다.
두 번째 줄에  "K번째"에 해당하는 양의 정수 Q개를 입력 받습니다. ( 1 <= K <= 1,500)

출력
각 질의에 해당하는 Ugly number들을 질문 순서대로 출력해주세요.

입력 예시 1 
3
1 9 10

출력 예시 1
1 10 12
 */
package type.PriorityQueue;

import java.io.*;
import java.util.*; 

public class M2_7 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    static StringTokenizer st; 

    // MINHEAP
    static PriorityQueue<Long>pq = new PriorityQueue<>();

    public static void main(String[] args)throws IOException {
        int n = Integer.parseInt(br.readLine());
        // query를 받아서 저장 -> 최대 K값을 찾을것 
        // 몇개까지 우리가 찾아야 하는가 (매번 1500개를 찾을 필요 X)
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        // 최대 limit을 저장할 변수 
        int limit = -1; 
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            // 만약 지금 입력한 K값이 limit보다 크면 갱신 
            if(arr[i] > limit)
                limit = arr[i]; 
        }
        // limit번째까지의 ugly number만 찾으면 된다 

        // idx번째 ugly number를 기록하기 위한 변수 
        int idx = 1; 
        // 현재 ugly number 번호
        long cur = 0; 
        // limit 만큼의 ugly number를 미리 구해둬야 함 -> DAT
        long[] DAT = new long[limit + 1]; 

        // 첫번쨰 ugly number == 1
        pq.add((long)1); 

        // 설계했던대로 구현 
        // limit번째 ugly number를 찾을때까지 반복 
        while(idx <= limit) {
            // pq에서 맨 위에거 뽑아옴
            long val = pq.poll(); 
            // ** 처리사항 -> 지금 값이 중복값이면 pass
            if(val == cur)
                continue;
            // 모든 ugly number후보들을 삽입 
            pq.add(val *2);
            pq.add(val *3);
            pq.add(val *5);

            // idx번째 ugly number를 찾았다!
            DAT[idx] = val; 
            // 다음 ugly number를 기록 하기 위해 idx++
            idx++; 
            // 새로운 (가장 큰) ugly number가 나왔으니 비교 대상을 갱신 
            cur = val; 
        }
        // 처음에 입력한 Query순서대로 출력 처리 
        for(int i = 0; i < n; i++) {
            System.out.print(DAT[arr[i]] + " ");
        }
    }
}


/*내가짠 코드
 package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n;
	static PriorityQueue<Long> pq = new PriorityQueue<>();
	static long dat[];//ugly number 기록  index: 몇번째 , value 값
	
	static void UglyNumber(int num) {
		pq.add((long) 1);
		int idx=0;
		for(int i=1;i<=10000;i++) {
			long cur=pq.poll();
			if(dat[idx]==cur)
				continue;
			idx++;
			dat[idx]=cur;
			pq.add(cur*2);
			pq.add(cur*3);
			pq.add(cur*5);
		}
		System.out.print(dat[num]+" ");
	}
	
	public static void main(String[] args) throws IOException{
		n=Integer.parseInt(br.readLine());
		st=new StringTokenizer(br.readLine());
		dat=new long[10000];
		for( int i=0;i<n;i++) {
			UglyNumber(Integer.parseInt(st.nextToken()));
		}
	}
}

*/