/*
N Castle

ü������  Castle�� ���� �׸��� ���� ����, ���� �������� �����Ӱ� �̵��� �� �ֽ��ϴ�.

image.png

N x N ü���ǿ� N ���� ĳ���� ��ġ���� ���� �� �ִ� ����� ���� ������ּ���
��, ��ġ�Ҷ� ���� �������� �ʾƾ��մϴ�. ���� Castle�� ��ġ�ؼ� �̵��� �� �ִ� ���� �� �ٸ� Castle�� �ִٸ�, �̴� ���ݰ����Ѱɷ� �����մϴ�.
�Ʒ� �̹����� N = 4 �� ���, ������ ��ġ���Ϻ��Դϴ�.

image.pngimage.pngimage.png

N �� �־����� ��, ���� ������ �Ұ����ϵ��Ϲ�ġ�� �� �ִ� ����� ���� ����� �ּ���

�Է�
N ���� �Էµ˴ϴ�. (1<= N <= 10)

���
ĳ������ �������� �ʰ� ��ġ�� �� �ִ� ����� ���� ������ּ���

�Է� ���� 1 
3

��� ���� 1
6
 */
package type.recycle;


import java.io.*;
import java.util.*; 

public class M3_4 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    static StringTokenizer st;
    static int n;

    // �� ��� ����� ����� DAT 
    static int[] col = new int[10];  
    static int cnt = 0; 

    // ���� ���� -level == row 
    static void func(int row) {
        // �������� 
        // ��� �࿡ ĳ���� �ϳ��� ���� �� �ִٸ� -> �ϳ��� ������ ã�� 
        // --> ���� n����� �����ߴٸ� -> ������ �� �ξ����� -> �ϳ��� ������ ã�Ҵ� 
        if(row == n) {
            cnt++; 
            return; 
        }

        // ��� ���� 
        // �� ���� �� �� �ִ°�? (i = ��)
        for(int i = 0; i < n; i++) {
            // ����ġ��
            // ���� : ���� row �࿡�� ��� ���� �ѷ�������,
            // �̹� ���� ���� ���ǰ� �ִٸ� -> ���ݴ��Ҽ� ������ -> pass 
            if(col[i] == 1)
                continue;
            // ���� ������ �Ѿ��!
            // ��� -> � ���� �ξ��°�? -> DAT
            // index : �� ��ȣ, value : �ξ��°�? (1, 0)
            // ���� -> i��° ���� ����ߴ�! 
            col[i] = 1; 
            func(row+1);
            // ���� �� -> i���� �� ��� ����
            col[i] = 0;
        }
    }

    public static void main(String[] args)throws IOException {
        n = Integer.parseInt(br.readLine());
        func(0); 
        System.out.println(cnt);
    }
}


/*���ҽ�
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
