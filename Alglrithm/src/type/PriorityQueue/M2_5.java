/*
������ź

N x N �ʵ� ������ ��ź�� ��ġ �Ͽ����ϴ�.
��ź�� ��, �Ʒ�, ����, ������ �������� ������ �˴ϴ�.
��ȣ ������� ��ź�� ��ġ�߰�, 1�ʿ� �ϳ��� ��ź�� �����ϴ�.
1 �ʰ� �Ǹ�, 1�� ��ź�� �����ϴ�.
1 �� ��ź�� ���Ͽ�, 14, 8, 7 �� ������ ��������, �װ��� �ִ� ��ź�� ������� �˴ϴ�.

2656630281c73294b6a21bcfdba16472.png

2 �ʰ� �Ǿ���, ���� 2�� ��ź�� ���۵ǰ� �ִ� �����Դϴ�.
10, 13, 9, 11, 2�� ������ ��ź�� ������ �Ǿ����ϴ�.

c689568420d8195c545c6cd3a71ace0f.png

3�ʰ� �Ǿ���, ���� 3�� ��ź�� ���۵ǰ� �ִ� �����Դϴ�.
3���� 6�� ������ ��ź�� ������ �Ǿ����ϴ�.

c26579ea87cbf0c5551d41288ac2f67c.png

4�ʰ� �Ǿ���, 4���� 15�� ������ ��ź�� ������ �Ǿ����ϴ�.

a2a2c54461ee3d9ea69ad45e5e88d4c9.png

5�ʰ� �Ǿ���, 5���� 16�� ������ ��ź�� ������ �Ǿ����ϴ�.

86e617dbf651342237cb1f3001471db6.png

12�ʰ� �� �� ����, �ƹ��� ��ź�� ������ �ʽ��ϴ�.
�׸��� 12�ʰ� �Ǿ� ���� �������� ��� ��ź�� ������ �Ǿ����ϴ�.

efc47034d618f7616e92f388767aa329.png

���� ������ ��� ��ź�� ���� �� ���� �ɸ��� �ð��� ���� ����� �ּ���.

�Է�
ù �ٿ��� N�� �ԷµǸ�,
N x N �������� ��ź ��ġ ������ �Էµ˴ϴ�.
(1 <= N <= 1,000)

���
�� �� �ʸ��� ��� ��ź�� �������� ���Ͽ� ����� �ּ���.

�Է� ���� 1 
4
16 14 1 8
5 10 7 3
13 2 9 6
12 11 4 15

��� ���� 1
12��
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
        // ���ı��� -> ���� ��ȣ ������
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
        // pq���� �����ź��� �����ͼ� ��Ʈ���� !
        while(!pq.isEmpty()) {
            // ������ ��ź
            Bomb now = pq.poll();
            // *����* ���� �̹� ���� �����̸� ����Ʈ����
            // �̹� ���� ���� = 0���� ǥ�Ⱑ �Ǿ��ִ�
            // �̹� ���� ��ġ��� -> ���� ��ź�� ��ŵ�ϰ� -> ���� ��ź���� �Ѿ
            if(MAP[now.y][now.x]== 0)
                continue;
            MAP[now.y][now.x]= 0; //���� ��ġ   
            MAP[now.y-1][now.x]= 0;  //�� 
            MAP[now.y+1][now.x]= 0;  //��
            MAP[now.y][now.x-1]= 0;  //��
            MAP[now.y][now.x+1]= 0;  //��
            // ���� : �� �������� ������ ���� ��ź ��ȣ
            ans = now.num; 
        }
        return ans; 

        /*
         ����迭  
         // �� ��ź ���� = n*n; 
         int total = n *n; 
         if (MAP[now.y][now.x] == 0) continue;
         int ydir[] = {0, 0, 1, -1, 0};
         int xdir[] = {1, -1, 0, 0, 0};
         for(int i = 0; i < 5; i++) {
             int ny = now.y + ydir[i];
             int nx = now.x + xdir[i]; 
             // ����üũ 
             if(ny < 0 || nx < 0 || ny >= n || nx >= n)
                 continue;
             // 1���� ��ź�� ������!
             MAP[ny][nx] = 0; 
             total -= 1; 
             if(total == 0)
                 return now.num; 
         }
         */
    }

    static int n;
    static int[][] MAP;
    // ��ź�� ���� ��ȣ ������ �̾ƿ� MINHEAP
    static PriorityQueue<Bomb>pq = new PriorityQueue<>();

    public static void main(String[] args)throws IOException {
        n = Integer.parseInt(br.readLine());
        // init -> MAP���� �����¿� padding
        MAP = new int[n+2][n+2]; 
        // �Է��� ������ 1~N(����)������ ������ ����
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <=n; j++) {
                MAP[i][j] = Integer.parseInt(st.nextToken());
                // priority queue�� �� ��ź�� ��ġ
                pq.add(new Bomb(MAP[i][j], i, j));  
            }
        }

        // ��Ʈ����!
        int ans = explode(); 

        // �� ���ʰ� �ɷȴ��� ���
        System.out.println(ans + "��");

    }
}



/* ���ڵ�
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
    	
    	System.out.println(ans+"��");
    	
    }
}


 
 */