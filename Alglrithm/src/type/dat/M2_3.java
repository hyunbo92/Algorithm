/*
������ ����

�볪�� N���� ���� (����) �������� ������ �� �ִ�. �������� �� �볪�� A���� �ٸ� �볪�� B�� ��Ȯ�� ���� �������� ������ �� �ֽ��ϴ�.
��, ������ �� �ٸ� �볪�� ���� (�� �� ����) ������ �ȵ˴ϴ�.
���� ��� <�׸� 1>���� 1�� �볪������ 2�� �볪���� ������ ���� �������� �����ϴ� ���� �����մϴ�.
1�� �볪������ 2�� �볪���� ������ �� �ٽ� 3�� �볪���� �����ϸ� 1�� �볪������ 3�� �볪���� �̵��ϴ� ���� �����մϴ�.
(�볪�� ������ �ɾ �����̴� ���� ������ �����մϴ�.)

<�׸� 1>

�볪������ ��ġ�� �Է¹޾� �������� �־��� �볪������ �ֿ� ���ؼ� �������� �� �볪������ �ٸ� �볪���� �ѹ� �̻��� ������ �̵��� �������� �Ǵ��ϴ� ���α׷��� �ۼ��� �ּ���.

�Է�
ù ��° �ٿ� �볪�� ���� N�� ������ ���� Q�� �־����ϴ�.
���� N���� �ٿ� �� �볪���� x1, x2, y�� �� ���� ��ǥ�� �־����ϴ�.
�־��� �볪���� �� �� (x1, y)�� (x2, y)�� �մ� �����Դϴ�. (x1< x2) ��� ��ǥ�� 0�̻� 1,000,000,000���� �Դϴ�.
�볪������ �־��� ������� 1������ ��ȣ�� �پ� �ֽ��ϴ�.
���� �ٸ� �� �볪���� (����������) ������ �ʽ��ϴ�.
���� Q���� �ٿ� ���� �ٸ� �� �볪���� ��ȣ�� �־����ϴ�. (1 �� N �� 100,000, 1 �� Q �� 100,000)

���
Q���� ���� ����մϴ�.
�� �ٿ��� �־��� ������� ������ ���� ����� ��µǾ�� �մϴ�.
������ �־��� �� �볪���� ���ؼ� �������� �� �볪������ �ٸ� �볪���� �ѹ� �̻��� ������ �̵��� ������ ��� ����� 1, �׷��� ���� ��� ����� 0 �Դϴ�.

 */
package type.dat;

import java.io.*;
import java.util.*; 

public class M2_3 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    static StringTokenizer st; 
    static int n, q; 

    // �볪�� class
    static class Log implements Comparable <Log> {
        int start;
        int end; 
        int height; 
        // ** �߰������� �ʿ��Ѱ� : �볪�� ��ȣ
        int num; 

        // constructor 
        Log(int start, int end, int height, int num) {
            this.start = start;
            this.end = end;
            this.height = height;
            this.num = num; 
        }

        // ���� ����
        @Override
        public int compareTo(Log next) {
            // start ���� ���� �����ͺ��� 
            if(start < next.start)
                return -1;
            if(start > next.start)
                return 1;
            if(end < next.end)
                return -1;
            if(end > next.end)
                return 1;
            return 0; 
        }
    }

    static Log[] logs; 
    // �׷��� �� DAT 
    // index : �볪�� ��ȣ, value : � �׷��ΰ�? 
    static int[] group; 

    public static void main(String[] args)throws IOException { 
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        // init 
        logs = new Log[n];
        // �ִ� n+1���� �׷��� ����� �ִ�!
        // (���� ��� �볪���� �������ִ� ����) 
        group = new int[n+1];

        // �볪�� input 
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken()); 
            // �볪�� ������ġ, ����ġ, ����, "�볪�� ��ȣ" 
            logs[i] = new Log(start, end, height, i+1);
        }

        // ���� �׷��� 
        // 1. ���� ������ �������� �볪�� ���� 
        Arrays.sort(logs);

        // 2. �ϳ��� �����ؿͼ� �׷��� �۾� 
        // �翬�� ù �볪���� ������ ���� <- ���� ���� ������ �볪�� 
        Log cur = logs[0]; 
        int g = 1; 
        // ���� �� �볪���� ��ȣ -> 1�� �׷����� 
        // -> �� ������ �볪�� = 1�� �׷� -> �������� ������ �׷�++ 
        group[cur.num] = g; 

        // logic
        for(int i = 1; i < n; i++) {
            // ���� �볪�� 
            Log next = logs[i];
            // ���� ���� ������ �볪���� ������ <= �� �� �볪���� ���� 
            if(next.start <= cur.end) {
                // --------
                //      ------- 
                // ----
                //     ----
                // ------ <-- �������̽� log�� ���ŵǼ� �ȵ�!
                //   --
                // ���� ������ �볪���� ���� > �� �� �볪���� �������� �ָ� �����ٸ�
                if(next.end > cur.end) {
                    // ������ ���ؾ��� �볪�� ���� 
                    cur = next; 
                }
            }
            // ���� ������ �ִ� �볪����� 
            // ----         
            //           -----
            else {
                // ���ο� �׷� 
                g++; 
                // ���� �볪�� ���� 
                cur = next; 
            }
            // ���� ������ �볪���� �׷� Ȯ�� 
            group[next.num] = g;  
        }

        // query �Է�
        for(int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // a�� b�� ���� �׷쿡 ������ 1
            if(group[a] == group[b])
                System.out.println(1);
            // �ƴϸ� 0
            else
                System.out.println(0);
        }
    }
}