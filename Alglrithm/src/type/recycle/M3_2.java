/*
재귀는 몇번

branch와Level을 입력받고재귀 함수가 호출되는 횟수를 counting해서 출력 해주세요.
예로들어 3 2 을 입력받으면 재귀함수는 총 13회 호출 됩니다.

ex)
3              2
(branch)    (level)

20190728093452_47438.png

입력
첫번째 줄에 branch와 level을 입력받습니다. (1 <= branch, level <= 20)

출력
첫번째 줄에 재귀 함수가 호출되는 횟수를 출력합니다.

입력 예시 1 
4 3

출력 예시 1
85
 */
package type.recycle;


import java.io.*;
import java.util.*; 

public class M3_2 {


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    static StringTokenizer st;

    static int branch, level; 
    static int cnt = 0; 

    // 층을 기준으로 퍼져나간다
    static void func(int lvl) {
        // 2. 기저조건
        // 주어진 마지막 층까지 
        if(lvl > level)
            return; 

        cnt++; 

        // 1. 재귀구성
        // 층은 계속 내려가기만 할거고
        // -> 다음 층으로 내려간다 
        for(int i = 0; i < branch; i++) {
            //System.out.println("층 : " + lvl + ", branch번호: " + i);
            // 내려가면서 수행 
            func(lvl+1);
            // 빠져나오면서 수행
        }
    }

    public static void main(String[] args)throws IOException {
        st = new StringTokenizer(br.readLine());
        branch = Integer.parseInt(st.nextToken());
        level = Integer.parseInt(st.nextToken());
        // 맨 상위 층
        func(0); 
        System.out.println(cnt);
    }
}