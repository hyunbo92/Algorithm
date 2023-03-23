/*
ȸ�ǽ� ����

ȸ�ǽ��� �� �ϳ��� �߼ұ���� �ֽ��ϴ�. �ϳ��ۿ� ���� ȸ�ǽ��� ����ϱ� ����, ���� ������ ������ �մϴ�. ȸ�ǽ� �����ڴ� �� ȸ�ǰ� �󸶳� ��� ª������ ������� �ʰ�, ���� ���� ȸ�ǰ� �̷������ ���մϴ�. ���� �������� �Է¹ް�, �ִ� ����� ȸ�ǰ� �������� ����� �ּ���. image.png �� �̹����� �� ���� �� ȸ�� ������ �����Դϴ�.
���� ��û : 1 \~ 6�� ����
���� ��û : 3 \~ 8�� ����
���� ��û : 8 \~ 9�� ����
���� ��û : 2 \~ 4�� ����
���� ��û : 4 \~ 6�� ����
���� ��û : 7 \~ 9�� ����
���� ���� ���ÿ��� �ִ� �� ���� ȸ�ǰ� �����ϹǷ�, ��°���� 3 �Դϴ�.

�Է�
ȸ�ǽ� ���� �������� ���� N�� �Է� �޽��ϴ�. (1 <= N <= 100,000) �� ���� �� ����, N���� ���ེ������ �Է� �޽��ϴ�. [���۰�(a) \~ ����(b)] (1 <= a, b <= 100,000,000)

���
������ ȸ���� �ִ� ������ ����� �ּ���.

�Է� ���� 1 
6
1 6
3 8
8 9
2 4
4 6
7 9

��� ���� 1
3
 */
package type.dat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer; 

public class M2_2 {

	   static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
	    static StringTokenizer st; 

	    static class Meeting implements Comparable <Meeting> {
	        // ȸ���� ���۽ð� 
	        int start;
	        // ȸ���� ���� �ð�
	        int end; 

	        //constructor
	        Meeting(int start, int end) {
	            this.start = start;
	            this.end = end; 
	        }

	        // ���� ����
	        // ���� �ð��� ������ �켱���� ����
	        @Override 
	        public int compareTo(Meeting next) {
	            // ���� ���� ȸ���� ���� �ð��� ���� ����ð����� �����ٸ� = ����! -1
	            if(end < next.end)
	                return -1;
	            if(end > next.end) 
	                return 1; 
	            // ����ð��� ������ -> ���� �ð��� �����ź���
	            if(start < next.start)
	                return -1;
	            if(start > next.start)
	                return 1;
	            return 0; 
	        }
	    }

	    public static void main(String[] args)throws IOException { 
	        int n = Integer.parseInt(br.readLine());

	        // init
	        Meeting[] m = new Meeting[n]; 
	        for(int i = 0; i < n; i++) {
	            int start, end;
	            st = new StringTokenizer(br.readLine());
	            start = Integer.parseInt(st.nextToken());
	            end = Integer.parseInt(st.nextToken());
	            m[i] = new Meeting(start, end) ;
	        }

	        // solve
	        // 1. ���� ������ ������ ���� 
	        Arrays.sort(m); 

	        // 2. �ϳ��� ó������ ���� ������ ȸ�� ������ �����ؼ� 
	        // �� ȸ�ǿ� ���� ������ -> cnt �����Ѵ� 

	        // ù��° ȸ�Ǹ� �������� ����
	        int cnt = 1; 
	        // ���� ���� �� ȸ���� "���� �ð�" 
	        int time = m[0].end; 

	        // ���� ȸ�ǵ��� �ϳ��� Ž���ϸ� ��
	        for(int i = 1; i < n; i++) {
	            // ���� i���� ȸ�ǿ� �� �� �ִٸ� 
	            // -> i��° ȸ���� ���� �ð��� >= ���� �����ϰ� �ִ� ȸ�� �ð��� ���� �ð�  
	            // �� ������ ������ i��° ȸ�Ǹ� ������ �� �ִ�!
	            if(m[i].start >= time) {
	                cnt++; 
	                // ���� ���ο� ȸ�Ǹ� ���� -> ���� ����ϰ� �ִ� "����" ����ð��� ����
	                time = m[i].end;
	            }
	        }
	        System.out.println(cnt);
	    }
	}