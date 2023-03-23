/*
��Ȯ�� ��ȣ


"M1nCod1n9"
357.482 KHz�� ��ȣ�� ������ ��ȣ�� �������ϴ�.
���ĺ� ��ҹ��ڿ� 0 ~ 9 ������ ���ڷ� �̷��� �ִ� ��ȣ�Դϴ�.
�� ������ ��ȣ�� ũ�Ⱑ M �� ���������� �ߺ��� ���ڰ� �������� �ʴ´ٴ� ���� �˾Ƴ½��ϴ�.
������Ʈ �˷���� �ڻ�� �� �ܰ� ��ȣ�� �м��Ͽ���, �ٽ� �۽��� �� �޽����� ����� �½��ϴ�.
�ڻ簡 ���� �޽������� ũ�Ⱑ M�� ������ �ߺ��� ���ڰ� �ִ��� üũ���ִ� ���α׷��� ����� �ּ���.

�Է�
ù�ٿ� �׽�Ʈ���̽��� ��(T)�� �Էµ˴ϴ�.
�� �����ٺ��� �� �׽�Ʈ ���̽� ����,
���� M�� �ԷµǸ�

�����ٿ��� �ڻ簡 ���� �޽����� �Էµ˴ϴ�.

�޽����� �ִ���̴� 10���̸� , M�� �޽����� ���̺��� �� �������� �Էµ˴ϴ�.

���
�� �׽�Ʈ���̽� ���� ��ȿ�� �˻� ����� PASS , FAIL �� ������ּ���.

�Է� ���� 1 

3
4
1bacDBTAab12D
3
734734734
2
10101010111
��� ���� 1

#1 PASS
#2 PASS
#3 FAIL
 */
package type.SlidingWindow;


import java.io.*;
import java.util.*; 

public class M1_2 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int test;
    static int m;
    static String str; 

    public static void main(String[] args) throws IOException {
        test = Integer.parseInt(br.readLine());
        for(int t = 1; t <= test; t++) {
            // input 
            m = Integer.parseInt(br.readLine());
            str = br.readLine(); 

            // solve -> sliding window 

            // 1. ���뱸�� 
            int start = 0;
            int end = m - 1; 
            int[] DAT = new int[128];
            int flag = 0; // <-- �ߺ��� ã�Ҵ°�? 

            for(int i = start; i < end; i++) {
                // str���� ���� i��° ���ڿ� ����������, �ߺ��� �ִ°�? 
                // �����ϴ� i��° ���ڵ� -> DAT�� ���
                char c = str.charAt(i);

                // c�� ���� �������� 1���� �� ���Ǿ���! -> ��� 
                DAT[c]++; 

                // �ߺ�üũ -> ���� �� ���ڰ� 2�� �̻� ���Ǿ��� -> �ߺ��� �ִ�!
                // AAABCDEFG
                if(DAT[c] >= 2) {
                    flag = 1; 
                    break; // �ߺ��� Ȯ�εǾ����� ���̻� ������ �� �ʿ� ����. 
                }    
            }

            // 2. sliding window 
            // â���� �� ���������� Ȯ�� (O) -> �ߺ��� Ȯ�����ٸ� ���̻� ���� X 
            // �ߺ��� Ȯ������ ���� ��쿡�� ����ؼ� ����
            while(end < str.length() && flag == 0) {

                // 1. ���� �ϼ� (������ ��ҵ��� ���� Ȯ�� �ϼ�)
                char c = str.charAt(end); 
                DAT[c]++; 

                // 2. ���� -> �� ���� ���� �ߺ��� �ִ°�? 
                if(DAT[c] >= 2) {
                    flag = 1; 
                    break; 
                }

                // 3. ���� �̵� 
                // ���� ��ġ�� �ִ� ������ ���� �����Ȱ� -> ����
                DAT[ str.charAt(start) ]--; 

                // ������ �̵�
                start++;
                end++; 
            }
            // �׽�Ʈ���̽��� ���� ��� ���
            // flag == 0 : �ߺ��� ���� �� �ϼ��Ǿ���!
            if(flag == 0) 
                System.out.println("#" + t + " PASS");
            else
                System.out.println("#" + t + " FAIL");
        }
    }
}

/*����§�ڵ�
package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int t,m;
	static char[] msg;
	
	public static void main(String[] args) throws IOException	{
		t=Integer.parseInt(br.readLine());
		for(int test=1;test<=t;test++) {
			m=Integer.parseInt(br.readLine());//���� 
			String message=br.readLine();
			msg=new char[message.length()];
			
			for(int i=0;i<message.length();i++) {
				msg[i]=message.charAt(i);
			}
			int start=0;
			int end=m-1;
			int flag=0;
			int dat[]=new int[100000];//INDEX ���� , VALUE ����
			for(int i=start;i<end;i++) {
				dat[msg[i]]++;
				if(dat[msg[end]]>=2) {
						flag=1;
						break;
					}
			}
			while(end<message.length()) {
				dat[msg[end]]++;
					if(dat[msg[end]]>=2) {
						flag=1;
						break;
					}
				dat[msg[start]]--;
				start++;
				end ++;
			}
			if(flag==1) {
				System.out.println("#"+test+" FAIL");
			}else {
				System.out.println("#"+test+" PASS");
			}
		}
	}
	
}


*/