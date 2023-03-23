/*
Ugly Number

Ugly Number�� ���� 2, 3, 5 �� ����Ͽ� ����� �� �� �ִ� ���� ���մϴ�.
���ܷ� ���� 1�� ù ��° Ugly Number�Դϴ�.
Ugly Number���� ������� �����غ��� ������ �����ϴ�.
1, 2, 3, 4, 5, 6, 8, 9, 10, 12, ...

number.png
a, b, c ... �� n ���� ���� �Է� �ް�,  a, b, c ... ��° Ugly Number�� ã�� ����� �ּ���.
���� 1 9 10 �� �Է� �޾Ҵٸ�, ��� ����� 1 10 12 �Դϴ�.

�Է�
ù ��° �ٿ� ������ ���� Q�� �Էµ˴ϴ�. ( 1 <= Q <= 10,000)
�� ���� ���� K �� �Էµǰ� �ش� K ��° Ugly number �� �����ֽø� �˴ϴ�.
�� ��° �ٿ�  "K��°"�� �ش��ϴ� ���� ���� Q���� �Է� �޽��ϴ�. ( 1 <= K <= 1,500)

���
�� ���ǿ� �ش��ϴ� Ugly number���� ���� ������� ������ּ���.

�Է� ���� 1 
3
1 9 10

��� ���� 1
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
        // query�� �޾Ƽ� ���� -> �ִ� K���� ã���� 
        // ����� �츮�� ã�ƾ� �ϴ°� (�Ź� 1500���� ã�� �ʿ� X)
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        // �ִ� limit�� ������ ���� 
        int limit = -1; 
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            // ���� ���� �Է��� K���� limit���� ũ�� ���� 
            if(arr[i] > limit)
                limit = arr[i]; 
        }
        // limit��°������ ugly number�� ã���� �ȴ� 

        // idx��° ugly number�� ����ϱ� ���� ���� 
        int idx = 1; 
        // ���� ugly number ��ȣ
        long cur = 0; 
        // limit ��ŭ�� ugly number�� �̸� ���ص־� �� -> DAT
        long[] DAT = new long[limit + 1]; 

        // ù���� ugly number == 1
        pq.add((long)1); 

        // �����ߴ���� ���� 
        // limit��° ugly number�� ã�������� �ݺ� 
        while(idx <= limit) {
            // pq���� �� ������ �̾ƿ�
            long val = pq.poll(); 
            // ** ó������ -> ���� ���� �ߺ����̸� pass
            if(val == cur)
                continue;
            // ��� ugly number�ĺ����� ���� 
            pq.add(val *2);
            pq.add(val *3);
            pq.add(val *5);

            // idx��° ugly number�� ã�Ҵ�!
            DAT[idx] = val; 
            // ���� ugly number�� ��� �ϱ� ���� idx++
            idx++; 
            // ���ο� (���� ū) ugly number�� �������� �� ����� ���� 
            cur = val; 
        }
        // ó���� �Է��� Query������� ��� ó�� 
        for(int i = 0; i < n; i++) {
            System.out.print(DAT[arr[i]] + " ");
        }
    }
}


/*����§ �ڵ�
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
	static long dat[];//ugly number ���  index: ���° , value ��
	
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