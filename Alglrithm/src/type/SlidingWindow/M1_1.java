/*
�����̵� ������

���� ǳ�� ������ ������ �մϴ�.
������ �� �ִ� �ǻ�ü���� ������ ǥ���Ͽ����ϴ�.

image.png

������� ��� �ǻ�ü���� ��� ������, ������ �ȿ� ���� �� �ִ� ���� �����Ǿ� �ֽ��ϴ�.
������� �� �׸��� �ǻ�ü�� �� ������ 2 5 2 6 5 -3 9 4 2 -7 �̸�, �����Ⱑ ���� �� �ִ� ���� 5ĭ�� ��� �Դϴ�.
���� 0 �� �ε������� 4 ���ε������� �Կ� ��, ���� �� �ִ� ���� ������ 2 + 5 + 2 + 6 + 5 = 20 �Դϴ�.
���� 1 �� �ε������� 5 �� �ε������� �Կ� ��, ���� �� �ִ� ���� ������ 5 + 2 + 6 + 5 + -3 = 15 �Դϴ�.
�̷��� ���� 5�� ��� �� ǳ�濡�� ���� �� �ִ� ���� ���� ������ 6 + 5 + -3 + 9 + 4 = 21 �Դϴ�.
�ǻ�ü���� ������ �Է¹ް�, ���� ���� ������ ���� ������ ��� ���ؼ� ��� �ε������� ��� �ε������� �Կ��� �ؾߵǴ����� ������ּ���

�Է�
ù ��° �ٿ��� �׽�Ʈ���̽��� ��(T)�� �Էµ˴ϴ�. (1 <= T <= 50)
�� ��° �ٺ��� �� �׽�Ʈ���̽�����,
ù��° �ٿ����ǻ�ü�� ����(N), �������� ��(W) �� �Է¹ް�
�� ���� �ٿ��� �ǻ�ü�� ����ŭ ������ �Է¹޽��ϴ�.
(1 <= W < N <= 100,000)

���
�� �׽�Ʈ���̽� ����
�ִ뿹�� ������ ������ ���� ���� �ε���, �� �ε���, �ִ� ���������� ����մϴ�.
�ε��� ��ȣ�� 0�� �ε������� �����մϴ�.
����, �ִ� ���� ������ ������ ������ ���� ���� ��� ���� ���� ���ʿ� �ִ� ������ ����մϴ�.

�Է� ���� 1 
2
10 5
2 5 2 6 5 -3 9 4 2 -7 
5 2
-1 -2 -3 -4 -5

��� ���� 1
#1 3 7 21
#2 0 1 -3
 */
package type.SlidingWindow;

import java.util.*;
import java.io.*;

public class M1_1 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int test; 
    static int n, w;
    static int[] arr; 

    public static void main(String[] args) throws IOException {

        test = Integer.parseInt(br.readLine());
        for(int t = 1; t <= test; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            // init
            arr = new int[n]; 

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) 
                arr[i] = Integer.parseInt(st.nextToken());

            // sliding window

            // 1. ���� ���� ���� 
            // ������ �ΰ� ���� 
            int start = 0; 
            int end = w - 1; 
            int sum = 0;
            for(int i = start; i < end; i++) 
                sum += arr[i]; 

            int startidx = 0; 
            int endidx = 0; 
            int maxval = Integer.MIN_VALUE; 

            // 2. �����̵� ������ 
            while(end < n) {
                // 1. ���� �ϼ� 
                sum += arr[end]; 

                // 2. ���� 
                // ���� ���� ū ������ ��𼭺��� ���? 
                // ���� ���� �����ϰ� �ִ� ������ �� > ���ݱ��� ��ϵ� �ִ밪 -> ���� 
                if(sum > maxval) {
                    maxval = sum; 
                    // �� ������ index�� ��� -> �����͸� ���
                    startidx = start; 
                    endidx = end; 
                }

                // 3. ���� �̵� (������ �̵�)
                // ù��° ��ġ�� ���� 
                sum -= arr[start];

                // ������ �̵�
                start++;
                end++; 
            }
            System.out.println("#" + t + " " + startidx + " " + endidx + " " + maxval);
        }    
    }
}


/* ����§�ڵ�
package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int t;
	static int n,w;
	static int[] score;
	
	public static void main(String[] args) throws IOException	{
		
		t=Integer.parseInt(br.readLine());
		
		for(int test=1;test<=t;test++) {
			int maxvalue=Integer.MIN_VALUE ;
			int left=0,right=0;
			
			st=new StringTokenizer(br.readLine());
			n=Integer.parseInt(st.nextToken());//�ǻ�ä�� ����
			w=Integer.parseInt(st.nextToken());//�������� ��
			score=new int[n];
			
			st=new StringTokenizer(br.readLine());
			for(int i=0;i<n;i++) {
				score[i]=Integer.parseInt(st.nextToken());
			}
			
			//�ִ뿹�������� ������ ���� �����ε���,���ε���,�ִ� �������� ���
			
			int start=0;
			int end=w-1;
			int sum=0;
			
			for(int i=start;i<end;i++) {
				sum+=score[i];
			}
			
			while(end<n) {
				sum+=score[end];
				if(sum>maxvalue) {
					maxvalue=sum;
					
					right=end;
					left=start;
				}
				sum-=score[start];
				
			
				start++;
				end++;
			}
			System.out.println("#"+test+" "+left+" "+ right+" "+ maxvalue);
		}
	
	
	}
	
}

*/
