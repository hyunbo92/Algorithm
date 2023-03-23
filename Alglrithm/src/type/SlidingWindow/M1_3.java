/*
������ ����

����� VIP �մԵ��� �����ϴ� �������� ���� �Ƹ�����Ʈ�� �մϴ�.
�� �����忡�� ����Ź�ڿ� ���� N���� �մԵ鿡��, N���� ���ִ� �丮�� �����մϴ�.
N���� �丮 �̸��� ���ڷ� �Ǿ�������,
���� ���ڴ� ���� ������ ���մϴ�.

t1.png

�Ʒ� ���ô� 7���� ���� Ź�ڿ� �ɾ��ְ�,
���� ���� ���� ���ĵ��� �̸��� �����ֽ��ϴ�.
(12�� ������� ������� 65 - 65 - 81 - 86 - 65 - 71 - 69)

image.png

�ڸ��� �ɾ��ִ� ������� ������ ������ �������� ������ �Ծ�� �;��մϴ�.
�ɾ��ִ� ����� �Ÿ� R ��ŭ ������ ���� �ִ� ������ ������ �� �ֽ��ϴ�.
������� R = 2 �϶�, 86 ������ �ִ� ���� �ɾ��ִ� ����� 71 65 86 81 65 ������ ���� �� �ֽ��ϴ�.
�� ���, �ߺ��� ������ ���⿡ ������ ������ ���Դϴ�.

image.png

���� ���� ���ÿ�����
R = 2 �϶�, 66 �ڸ����� ���� �� �ִ� ������ 65 65 66 81 65 �Դϴ�.
�ߺ��� ������ 2�� ������ �մԵ��� �Ҹ��� ������, 3���� �ִٸ� �Ҹ��� ����մϴ�.
���� ������ �������� ���� ���̽��Դϴ�.
���̺� ���� ��� ����� ��������
�ɾƼ� ���� �� �ִ� ���ĵ��� 2�� ������ �ߺ��ǰԲ� ���� ���ִ��ٸ� YES ��,
�׷��� �ʴٸ� NO �� ��� �� �ּ���.

�Է�
ù ��° �ٿ� �׽�Ʈ ���̽��� �� T �� �־����ϴ�. (1 <= T <= 100)
�� �׽�Ʈ ���̽��� ù ��° �ٿ��� �� ���� N, R �� �־����ϴ�. (1 <= N <= 1,000,000)
(1 <= R <= 50,000)
�� �׽�Ʈ ���̽��� �� ��° �ٿ��� N ���� ������ ���ڷ� ����� ���еǾ� �־����ϴ�. (������ ������ 0 ~ 200 �Դϴ�)

���
�� �׽�Ʈ ���̽����� ���������� �����̸� YES,
�Ҹ��������� �����̸� NO �� ����մϴ�.

�Է� ���� 1 
2
7 2
65 66 81 86 65 71 82
8 2
65 65 81 66 65 65 69 69

��� ���� 1
#1 YES
#2 NO
 */
package type.SlidingWindow;


import java.io.*;
import java.util.*; 

public class M1_3 {

	   static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    static StringTokenizer st;

	    static int test;
	    static int n,r ; 
	    static int[] arr; 

	    public static void main(String[] args) throws IOException {
	        test = Integer.parseInt(br.readLine());
	        for(int t = 1; t <= test; t++) {
	            st = new StringTokenizer(br.readLine());
	            n = Integer.parseInt(st.nextToken());
	            r = Integer.parseInt(st.nextToken());

	            // init -> �迭�� ũ�⸦ �ʱ�ȭ
	            arr = new int[n * 2];

	            // �迭 input 
	            st = new StringTokenizer(br.readLine());
	            for(int i = 0; i < n; i++) {
	                arr[i] = Integer.parseInt(st.nextToken());
	                // [ .., ] [N ... n * 2 -1] 
	                arr[i+n] = arr[i]; 
	            }

	            // solve
	            // 1\. ���� ���� 
	            int start = 0; 
	            int end = r*2; 
	            int flag = 0; 

	            // ** DAT�� Ȱ���Ҷ� �׻� "��� [����] �����ؾ��ϴ°�"? 
	            // ���� ��ȣ 200 [����] = 200�� ������ �� �ֵ��� -> 201 
	            // ** �� : �迭�� �����ҋ� �׻� �׳� +1 ũ�⸸ŭ���� ����� �ּ���. (+10)
	            // index : ���Ĺ�ȣ : value : ���� 
	            int[] dat = new int[201];

	            for(int i = start; i < end; i++) {
	                // arr�� ����� ������ ��ȣ ������ ����
	                dat[arr[i]]++; 
	                // ���뱸���� �ߺ�üũ (����)
	                // ���� ��ϵ� ������ 3�� �̻� �����Ѵ� -> FAIL 
	                if(dat[arr[i]] > 2) {
	                    flag = 1;
	                    break; 
	                }
	            }

	            // 2\. sliding window 
	            while(end < n*2 && flag == 0) {
	                // 1\. ���� �ϼ� 
	                dat[ arr[end] ]++;

	                // 2\. ���� 
	                if(dat[arr[end]] > 2) {
	                    flag = 1;
	                    break; 
	                }

	                // 3\. ���� �̵�
	                // �� �� ���� ���� 
	                dat[ arr[start] ]--; 
	                start++;
	                end++; 
	            }

	            if(flag == 0) 
	                System.out.println("#" + t + " YES");
	            else
	                System.out.println("#" + t + " NO");

	        }

	    }
	}


/* ���� § �ڵ�
package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int t,n,r;
	
	static int[] food;
	static int[] dat; // inedex :����, value :����
	
	public static void main(String[] args) throws IOException	{
		t=Integer.parseInt(br.readLine());
		for(int test=1;test<=t;test++) {
			
			st= new StringTokenizer(br.readLine());
			n=Integer.parseInt(st.nextToken());// n���� ����
			r=Integer.parseInt(st.nextToken());// +-r �ǰŸ���ŭ ���������� --> �迭�����ؼ�+r ��üũ
			
			food=new int[2*n];
			st=new StringTokenizer(br.readLine());
			for(int i=0;i<n;i++) {
				food[i]=Integer.parseInt(st.nextToken());
				food[i+n]=food[i];
			}
			
			dat=new int[201];
			
			int start=0;
			int end =2*r;
			int flag=0;
			for(int i=start;i<end;i++) {
				dat[food[i]]++;
				
				if(dat[food[i]]>2) {
					flag=1;
				}
			}
			
			while(end<2*n && flag==0 ) {
				dat[food[end]]++;
				if(dat[food[end]]>2) {
					flag=1;
					break;
				}
				dat[food[start]]--;
				start++;
				end++;
			}
			
			if(flag==1) {
				System.out.println("#"+test+" NO");
			}else {
				System.out.println("#"+test+" YES");
			}
			
			
			
			
			

			
			

		
		}
	}
	
}


 */

