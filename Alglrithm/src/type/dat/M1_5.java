/*
블랙리스트 20230315

민철이는 아파트로 이사를 가려 합니다.
가격이 적게 나가는 아파트를 고르다보니 블랙리스트들이 있는 아파트로 이사를 갈 수 밖에 없게 되었습니다.
하지만, 운 좋게 블랙리스트가 적힌 종이를 입수하여 이사갈 아파트를 조사해보려 합니다.
블랙리스트 정보를 가지고 아파트에 일반 시민은 몇명이고 블랙리스트는 몇명인지 출력해주세요.

black.png

입력
아파트 정보가 입력됩니다.
먼저 아파트의 height(세로) 와 width(가로) 가 입력됩니다. (1 <= height, width <= 1,000)
그리고 아파트 정보가 2차원 배열 형태로 입력됩니다. 아파트 주민 이름 정보는 숫자로 표기됩니다.
중복된 값이 존재할 수 있습니다.
다음 줄에는 블랙리스트 정보를 나타내는 2차원 배열의 bheight(세로), bwidth(가로) 사이즈가 입력됩니다. (1 <= bheight, bwidth <= 1,000)
그리고 블랙리스트 정보가 2차원 배열 형태로 입력됩니다. 블랙리스트의 이름 정보는 숫자로 표기됩니다.
중복된 값이 존재할 수 있습니다.
아파트 주민의 번호와 블랙리스트의 번호는 0 ~ 100,000 사이의 번호입니다.

출력
첫 번째 줄에는 블랙리스트가 몇명 있는지 출력합니다.
두 번째 줄에는 일반 시민이 몇명 있는지 출력합니다.

입력 예시 1 
3 4
15 42 65 60
15 30 15 17
5 5 3 15
2 3
15 5 4
17 6 2

출력 예시 1
7
5

 */
package type.dat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer; 

public class M1_5 {


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int h, w; 
    static int bh, bw; 

    // DAT
    // apt 번호 0~10만번 까지의 주민이 몇명이 있는지를 기록
    static int[] apt = new int[100001]; 
    // index : 주민 번호, value : 이미 블랙리스트로 체크되었는가? 
    // 1 :체크되었고, 0 : 아직 체크 안되었다  
    static int[] check = new int[100001]; 


    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        // 아파트 주민들 입력 -> 
        // 입력 받으면서 각 번호의 주민이 몇명인지 counting 해둘것
        // DAT => index : 주민 번호, value : 해당 주민 번호의 주민이 몇명인가?
        // 1000 x 1000 = O(N^2) = 1,000,000번
        for(int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < w; j++) {
                int num = Integer.parseInt(st.nextToken());
                // dat에 이 num 번호의 주민을 한명 더 찾았다!
                apt[num]++; 
            }
        }

        // blacklist 입력 받으면서 -> 처리(solve)
        int blacklist = 0; 
        st = new StringTokenizer(br.readLine());
        bh = Integer.parseInt(st.nextToken());
        bw = Integer.parseInt(st.nextToken());
        // 1000 x 1000 = O(N^2) = 1,000,000번
        for(int i = 0; i < bh; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < bw; j++) {
                // 지금 내가 입력받은 번호 == 블랙리스트
                int bnum = Integer.parseInt(st.nextToken());
                // ** 주의사항 : 이미 수를 누적한 블랙리스트는 또 더해서는 안됨.
                // 새로운 DAT -> 만약에 지금 입력된 bnum이 아직 누적하지 않는 번호라면
                if(check[bnum] == 0) {
                    // 이 번호가 apt에 존재하는 만큼 -> 블랙리스트 숫자는 증가
                    blacklist += apt[bnum];
                    // ** 이제 체크가 된 번호니까 이 번호를 사용했다 -> 체크
                    check[bnum] = 1; 
                }
            }
        }
        // 1조번 -> 2 x 1,000,0000 -> 2,000,000 
        System.out.println(blacklist); 
        // 일반 시민 -> 아파트의 총 인원수 - blacklist
        System.out.println(h*w - blacklist);
    }
}

/* 내풀이 
package pro;

import java.io.*;
import java.util.*; 

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int h,w,bh,bw;
    static int apt[][];
    static int bl[][];
    static int dat[];
    

    

    public static void main(String[] args) throws IOException{
    	dat=new int[100001];//index = bl 번호 , value= bl 수
    	st= new StringTokenizer(br.readLine());
    	h=Integer.parseInt(st.nextToken());
    	w=Integer.parseInt(st.nextToken());
    	apt= new int[h][w];
    	
    	
    	for(int i=0;i<h;i++) {
    		st=new StringTokenizer(br.readLine());
    		for(int j=0;j<w;j++) {
    			apt[i][j]=Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	st=new StringTokenizer(br.readLine());
    	
    	bh=Integer.parseInt(st.nextToken());
    	bw=Integer.parseInt(st.nextToken());
    	
    	bl=new int[bh][bw];
    	
    	for(int i=0;i<bh;i++) {
    		st=new StringTokenizer(br.readLine());
    		for(int j=0;j<bw;j++) {
    			bl[i][j]=Integer.parseInt(st.nextToken());
    			dat[bl[i][j]]++;
    		}
    	}
    	
    	int blcnt=0;
    	for(int i=0;i<h;i++) {
    		for(int j=0;j<w;j++) {
    			if(dat[apt[i][j]]!=0) 
    				blcnt++;
    			
    		}
    	}
    	
    	
    	System.out.println(blcnt);
    	System.out.println(h*w-blcnt);
    	
    }
}
 */

