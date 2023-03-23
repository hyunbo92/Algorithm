/*
연쇄폭탄

N x N 필드 지역에 폭탄을 설치 하였습니다.
폭탄은 위, 아래, 왼쪽, 오른쪽 영역으로 터지게 됩니다.
번호 순서대로 폭탄을 설치했고, 1초에 하나씩 폭탄이 터집니다.
1 초가 되면, 1번 폭탄이 터집니다.
1 번 폭탄에 의하여, 14, 8, 7 번 지역도 터졌으며, 그곳에 있는 폭탄은 사라지게 됩니다.

2656630281c73294b6a21bcfdba16472.png

2 초가 되었고, 아직 2번 폭탄은 동작되고 있는 상태입니다.
10, 13, 9, 11, 2번 지역에 폭탄이 터지게 되었습니다.

c689568420d8195c545c6cd3a71ace0f.png

3초가 되었고, 아직 3번 폭탄은 동작되고 있는 상태입니다.
3번과 6번 지역에 폭탄이 터지게 되었습니다.

c26579ea87cbf0c5551d41288ac2f67c.png

4초가 되었고, 4번과 15번 지역에 폭탄이 터지게 되었습니다.

a2a2c54461ee3d9ea69ad45e5e88d4c9.png

5초가 되었고, 5번과 16번 지역에 폭탄이 터지게 되었습니다.

86e617dbf651342237cb1f3001471db6.png

12초가 될 때 까지, 아무런 폭탄이 터지지 않습니다.
그리고 12초가 되어 남은 지역까지 모두 폭탄이 터지게 되었습니다.

efc47034d618f7616e92f388767aa329.png

적군 필지에 모든 폭탄이 터질 때 까지 걸리는 시간을 구해 출력해 주세요.

입력
첫 줄에는 N이 입력되며,
N x N 사이즈의 폭탄 설치 정보가 입력됩니다.
(1 <= N <= 1,000)

출력
총 몇 초만에 모든 폭탄이 터지는지 구하여 출력해 주세요.

입력 예시 1 
4
16 14 1 8
5 10 7 3
13 2 9 6
12 11 4 15

출력 예시 1
12초
 */
package type.PriorityQueue;

import java.io.*;
import java.util.*; 

public class M2_5 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    static StringTokenizer st; 

    static class Bomb implements Comparable <Bomb> {
        int num;
        int y;
        int x;
        //constructor
        Bomb(int num, int y, int x){
            this.num = num;
            this.y = y;
            this.x = x; 
        }
        // 정렬기준 -> 작은 번호 순으로
        public int compareTo(Bomb next) {
            if(num < next.num)
                return -1;
            if(num > next.num)
                return 1;
            return 0;
        }
    }

    static int explode() {
        int ans = 0; 
        // pq에서 작은거부터 꺼내와서 터트린다 !
        while(!pq.isEmpty()) {
            // 지금의 폭탄
            Bomb now = pq.poll();
            // *조건* 만약 이미 터진 공간이면 안터트릴것
            // 이미 터진 공간 = 0으로 표기가 되어있다
            // 이미 터진 위치라면 -> 지금 폭탄은 스킵하고 -> 다음 폭탄으로 넘어감
            if(MAP[now.y][now.x]== 0)
                continue;
            MAP[now.y][now.x]= 0; //지금 위치   
            MAP[now.y-1][now.x]= 0;  //상 
            MAP[now.y+1][now.x]= 0;  //하
            MAP[now.y][now.x-1]= 0;  //좌
            MAP[now.y][now.x+1]= 0;  //우
            // 정답 : 맨 마지막에 실제로 터진 폭탄 번호
            ans = now.num; 
        }
        return ans; 

        /*
         방향배열  
         // 총 폭탄 개수 = n*n; 
         int total = n *n; 
         if (MAP[now.y][now.x] == 0) continue;
         int ydir[] = {0, 0, 1, -1, 0};
         int xdir[] = {1, -1, 0, 0, 0};
         for(int i = 0; i < 5; i++) {
             int ny = now.y + ydir[i];
             int nx = now.x + xdir[i]; 
             // 범위체크 
             if(ny < 0 || nx < 0 || ny >= n || nx >= n)
                 continue;
             // 1개의 폭탄이 터졌다!
             MAP[ny][nx] = 0; 
             total -= 1; 
             if(total == 0)
                 return now.num; 
         }
         */
    }

    static int n;
    static int[][] MAP;
    // 폭탄은 작은 번호 순으로 뽑아올 MINHEAP
    static PriorityQueue<Bomb>pq = new PriorityQueue<>();

    public static void main(String[] args)throws IOException {
        n = Integer.parseInt(br.readLine());
        // init -> MAP에는 상하좌우 padding
        MAP = new int[n+2][n+2]; 
        // 입력을 받을때 1~N(포함)까지로 범위를 설정
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <=n; j++) {
                MAP[i][j] = Integer.parseInt(st.nextToken());
                // priority queue에 각 폭탄의 위치
                pq.add(new Bomb(MAP[i][j], i, j));  
            }
        }

        // 터트린다!
        int ans = explode(); 

        // 총 몇초가 걸렸는지 출력
        System.out.println(ans + "초");

    }
}



/* 내코드
package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    static StringTokenizer st; 
    static int n,ans=0;
    static int[][] map;
    static PriorityQueue<bomb> pq=new PriorityQueue<>();
    
    static class bomb implements Comparable<bomb>{
    	int x,y,num;
    	
    	bomb(int x, int y, int num){
    		this.x=x;
    		this.y=y;
    		this.num=num;
    	}
    	

		@Override
		public int compareTo(bomb next) {
			if(num<next.num)
				return -1;
			if(num>next.num)
				return 1;
			return 0;
		}
    	
    }

    
    static int explode() {
    	
    	while(!pq.isEmpty()) {
    		bomb pop=pq.poll();
    		
    		if(map[pop.x][pop.y]==0	) 
    			continue;
    		
    		int[] dirx= {1,-1,0,0,0};
    		int[] diry= {0,0,1,-1,0};
    		
    		for(int i=0;i<5;i++) {
    			int nx=pop.x+dirx[i];
    			int ny=pop.y+diry[i];
    			if(nx<0||ny<0||nx>=n||ny>=n)
    				continue;
    			map[nx][ny]=0;
    		
    		}
    	
    	
    		ans=pop.num;
    	}
    	
    	
    	return ans;
    }

    public static void main(String[] args)throws IOException {
    	
    	n=Integer.parseInt(br.readLine());
    	map = new int [n][n];
    	for(int i=0;i<n;i++) {
    		st=new StringTokenizer(br.readLine());
    		for(int j=0;j<n;j++) {
    			map[i][j]=Integer.parseInt(st.nextToken());
    			pq.add(new bomb(i,j,map[i][j]));
    		}
    	}
    	
    	explode();
    	
    	System.out.println(ans+"초");
    	
    }
}


 
 */