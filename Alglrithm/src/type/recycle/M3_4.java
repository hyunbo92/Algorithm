/*
N Castle

체스에서  Castle은 다음 그림과 같이 가로, 세로 방향으로 자유롭게 이동할 수 있습니다.

image.png

N x N 체스판에 N 개의 캐슬을 배치시켜 나올 수 있는 경우의 수를 출력해주세요
단, 배치할때 서로 공격하지 않아야합니다. 만약 Castle을 배치해서 이동할 수 있는 곳에 또 다른 Castle이 있다면, 이는 공격가능한걸로 간주합니다.
아래 이미지는 N = 4 인 경우, 가능한 배치의일부입니다.

image.pngimage.pngimage.png

N 이 주어졌을 때, 서로 공격이 불가능하도록배치할 수 있는 경우의 수를 출력해 주세요

입력
N 값이 입력됩니다. (1<= N <= 10)

출력
캐슬끼리 공격하지 않고 배치할 수 있는 경우의 수를 출력해주세요

입력 예시 1 
3

출력 예시 1
6
 */
package type.recycle;


import java.io.*;
import java.util.*; 

public class M3_4 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    static StringTokenizer st;
    static int n;

    // 열 사용 기록을 기록할 DAT 
    static int[] col = new int[10];  
    static int cnt = 0; 

    // 원래 쓰던 -level == row 
    static void func(int row) {
        // 기저조건 
        // 모든 행에 캐슬을 하나씩 놓을 수 있다면 -> 하나의 조합을 찾음 
        // --> 만약 n행까지 도달했다면 -> 위에도 다 두었으니 -> 하나의 조합을 찾았다 
        if(row == n) {
            cnt++; 
            return; 
        }

        // 재귀 구성 
        // 각 열에 둘 수 있는가? (i = 열)
        for(int i = 0; i < n; i++) {
            // 가지치기
            // 조건 : 지금 row 행에서 모든 열을 둘러볼껀데,
            // 이미 지금 열이 사용되고 있다면 -> 공격당할수 있으니 -> pass 
            if(col[i] == 1)
                continue;
            // 다음 행으로 넘어가라!
            // 기록 -> 어떤 열에 두었는가? -> DAT
            // index : 열 번호, value : 두었는가? (1, 0)
            // 들어갈때 -> i번째 열을 사용했다! 
            col[i] = 1; 
            func(row+1);
            // 나올 때 -> i번쨰 열 사용 해제
            col[i] = 0;
        }
    }

    public static void main(String[] args)throws IOException {
        n = Integer.parseInt(br.readLine());
        func(0); 
        System.out.println(cnt);
    }
}


/*내소스
package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n;
	static int ans=1;

	static void recursion(int level) {
		if(level==n) {
			System.out.println(ans*n);
			return;
		}
		ans=ans*level;
		recursion(level+1);
		
				
	}
	public static void main(String[] args) throws IOException{
		
		n=Integer.parseInt(br.readLine());
		
		recursion(1);
		
	}
}

*/
