/*
������Ʈ 20230315

��ö�̴� ����Ʈ�� �̻縦 ���� �մϴ�.
������ ���� ������ ����Ʈ�� ���ٺ��� ������Ʈ���� �ִ� ����Ʈ�� �̻縦 �� �� �ۿ� ���� �Ǿ����ϴ�.
������, �� ���� ������Ʈ�� ���� ���̸� �Լ��Ͽ� �̻簥 ����Ʈ�� �����غ��� �մϴ�.
������Ʈ ������ ������ ����Ʈ�� �Ϲ� �ù��� ����̰� ������Ʈ�� ������� ������ּ���.

black.png

�Է�
����Ʈ ������ �Էµ˴ϴ�.
���� ����Ʈ�� height(����) �� width(����) �� �Էµ˴ϴ�. (1 <= height, width <= 1,000)
�׸��� ����Ʈ ������ 2���� �迭 ���·� �Էµ˴ϴ�. ����Ʈ �ֹ� �̸� ������ ���ڷ� ǥ��˴ϴ�.
�ߺ��� ���� ������ �� �ֽ��ϴ�.
���� �ٿ��� ������Ʈ ������ ��Ÿ���� 2���� �迭�� bheight(����), bwidth(����) ����� �Էµ˴ϴ�. (1 <= bheight, bwidth <= 1,000)
�׸��� ������Ʈ ������ 2���� �迭 ���·� �Էµ˴ϴ�. ������Ʈ�� �̸� ������ ���ڷ� ǥ��˴ϴ�.
�ߺ��� ���� ������ �� �ֽ��ϴ�.
����Ʈ �ֹ��� ��ȣ�� ������Ʈ�� ��ȣ�� 0 ~ 100,000 ������ ��ȣ�Դϴ�.

���
ù ��° �ٿ��� ������Ʈ�� ��� �ִ��� ����մϴ�.
�� ��° �ٿ��� �Ϲ� �ù��� ��� �ִ��� ����մϴ�.

�Է� ���� 1 
3 4
15 42 65 60
15 30 15 17
5 5 3 15
2 3
15 5 4
17 6 2

��� ���� 1
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
    // apt ��ȣ 0~10���� ������ �ֹ��� ����� �ִ����� ���
    static int[] apt = new int[100001]; 
    // index : �ֹ� ��ȣ, value : �̹� ������Ʈ�� üũ�Ǿ��°�? 
    // 1 :üũ�Ǿ���, 0 : ���� üũ �ȵǾ���  
    static int[] check = new int[100001]; 


    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        // ����Ʈ �ֹε� �Է� -> 
        // �Է� �����鼭 �� ��ȣ�� �ֹ��� ������� counting �صѰ�
        // DAT => index : �ֹ� ��ȣ, value : �ش� �ֹ� ��ȣ�� �ֹ��� ����ΰ�?
        // 1000 x 1000 = O(N^2) = 1,000,000��
        for(int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < w; j++) {
                int num = Integer.parseInt(st.nextToken());
                // dat�� �� num ��ȣ�� �ֹ��� �Ѹ� �� ã�Ҵ�!
                apt[num]++; 
            }
        }

        // blacklist �Է� �����鼭 -> ó��(solve)
        int blacklist = 0; 
        st = new StringTokenizer(br.readLine());
        bh = Integer.parseInt(st.nextToken());
        bw = Integer.parseInt(st.nextToken());
        // 1000 x 1000 = O(N^2) = 1,000,000��
        for(int i = 0; i < bh; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < bw; j++) {
                // ���� ���� �Է¹��� ��ȣ == ������Ʈ
                int bnum = Integer.parseInt(st.nextToken());
                // ** ���ǻ��� : �̹� ���� ������ ������Ʈ�� �� ���ؼ��� �ȵ�.
                // ���ο� DAT -> ���࿡ ���� �Էµ� bnum�� ���� �������� �ʴ� ��ȣ���
                if(check[bnum] == 0) {
                    // �� ��ȣ�� apt�� �����ϴ� ��ŭ -> ������Ʈ ���ڴ� ����
                    blacklist += apt[bnum];
                    // ** ���� üũ�� �� ��ȣ�ϱ� �� ��ȣ�� ����ߴ� -> üũ
                    check[bnum] = 1; 
                }
            }
        }
        // 1���� -> 2 x 1,000,0000 -> 2,000,000 
        System.out.println(blacklist); 
        // �Ϲ� �ù� -> ����Ʈ�� �� �ο��� - blacklist
        System.out.println(h*w - blacklist);
    }
}

/* ��Ǯ�� 
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
    	dat=new int[100001];//index = bl ��ȣ , value= bl ��
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

