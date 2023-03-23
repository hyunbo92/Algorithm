/*
�� ��ǥ

���ڵ�����б����� N���� ���� ������, �ݸ��� M���� �л��� �ִ�. ��� �л����� ��������  �ڵ� �Ƿ��� ������ �ְ�, �� �Ƿ��� �л����� ���� �ٸ���.
���ڵ� ����б����� �����ϴ� �ڵ����׽�Ʈ�� �� �ݸ��� �� ���� �� ��ǥ�� �����Ͽ� �����Ѵ�.
��ȸ�� ���尨�� ���, �׸��� ������ ���Ͽ� �� �ݿ��� ���ߵ� ��ǥ���� �Ƿ��� �ִ��� �����ϰ� �Ϸ��� �Ѵ�.
�׷��� �ϱ� ���� ������ �� ��ǥ���� �ִ� �Ƿ°� �ּ� �Ƿ��� ���̰� �ּҰ� �Ǿ�� �Ѵ�.
���� ���, N=3, M=4�϶�, ������ ���� ���� �ִٰ� �����غ���.
1�� : [12, 16, 67, 43]
2�� : [7, 17, 68, 48]
3�� : [14, 15, 77, 54]
���⼭ �� �ݿ��� 16, 17, 15�� �ɷ�ġ�� ���� �л����� �����ϸ�, �Ƿ��� �ִ�ġ�� �ּ�ġ�� ���̰� 17-15 = 2�� �ּ�ġ�� �ȴ�.
�� ��ǥ�� ���ߵ� �л����� �ڵ� �Ƿ��� �ִ�ġ�� �ּ�ġ�� ���̰� �ּҰ� �Ǵ� ����� ���� ����ϴ� ���α׷��� �ۼ��Ͻÿ�.

�Է�
ù ��° �ٿ��� �б��� ���� ��Ÿ���� N�� �� �б��� �л��� ���� ��Ÿ���� M�� �ϳ��� ��ĭ�� ���̿� �ΰ� �־�����. (1 �� N, M �� 1,000)
�� ��° �ٺ��� N���� �ٿ��� �� �ٸ��� �� ���� �л����� �ڵ� �Ƿ��� ��Ÿ���� M���� ���� ������ �ϳ��� ��ĭ�� ���̿� �ΰ� �־�����.
�ɷ�ġ�� 0�̻� 10^9�����̴�.

���
���ߵ� �� ��ǥ���� �ڵ� �Ƿ��� �ִ�ġ�� �ּ�ġ�� ���̰� �ּҰ� �Ǵ� ����� ���� �ϳ��� ������ ����Ѵ�.

�Է� ���� 1 
3 4
12 16 67 43
7 17 68 48
14 15 77 54

��� ���� 1
2

�Է� ���� 2 
4 3
10 20 30
40 50 60
70 80 90
100 110 120

��� ���� 2
70
 */
package type.TwoPointer;


import java.io.*;
import java.util.*; 

public class M1_6 {
	  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    static StringTokenizer st;

	    static class Std implements Comparable <Std> {
	        int abil;
	        int num;
	        Std(int abil, int num) {
	            this.abil = abil;
	            this.num = num;
	        }
	        // �ɷ�ġ �������� ���� 
	        @Override 
	        public int compareTo(Std next) {
	            if(abil < next.abil)
	                return -1;
	            if(abil > next.abil)
	                return 1;
	            return 0; 
	        }
	    }

	    // n : ���� ���� 
	    // m : �� ���� �л��� ��
	    static int n, m;


	    public static void main(String[] args) throws IOException{
	        st = new StringTokenizer(br.readLine());
	        n = Integer.parseInt(st.nextToken());
	        m = Integer.parseInt(st.nextToken());

	        // init -> 2������ 1�������� ����
	        Std[] arr = new Std[n*m];

	        int idx = 0; 
	        for(int i = 0; i < n; i++) {
	            st = new StringTokenizer(br.readLine());
	            for(int j = 0; j < m; j++) {
	                int abil = Integer.parseInt(st.nextToken());
	                arr[idx] = new Std(abil, i);
	                idx++; 
	            }
	        }

	        // �������� Ž���� ���� ����
	        Arrays.sort(arr);

	        // two pointer ����
	        int left = 0;
	        int right = 0; 

	        int cnt = 0; // ��� �б��� ���ԵǾ������� Ȯ�� -> cnt == n �̸� ��� �б� ����
	        // DAT : index = �� ��ȣ, value = �ش� ���� �л��� �� ����� �ִ°�? 
	        int[] DAT = new int[n]; 
	        int ans = Integer.MAX_VALUE;

	        while(right < n * m) {

	            //System.out.println(arr[right].abil + " " + arr[right].num);

	            // ���� 1 : ��� �б��� ���Խ��Ѿ� �Ѵ�. 
	            // --> ��� �� �л��� �Ѹ��� ���ԵǾ��ٸ�:
	            // cnt�� ������ ���� ������ �����ϰ� �� ��. 
	            if(cnt == n) {

	                // ���� 2 (����) : �ִ� �ɷ�ġ - �ּ� �ɷ�ġ = min
	                // �����ʹ� ��� -> �̵��� ������ �ϰ� �����Ƿ�, right ���� �̵��ϱ� ���� ���� Ȱ��
	                // ��� �ݿ� �Ѹ��� ������ �Ǿ��ٸ�, �ִ� - �ּ��� ���� Ȯ���ϰ� ����
	                if(arr[right-1].abil - arr[left].abil < ans)
	                    ans = arr[right-1].abil - arr[left].abil; 
	          
	                // ���� ���� : ���ɼ��� ã������ ���ʺ��� ���� -> �ߺ��� �� ����� �־��ٸ� �ٽ� Ȯ���ϰ� �� ��.
	                // DAT���� left �����Ͱ� ����Ű�� ���� �л��� ���� (�Ѹ� ����)
	                DAT[arr[left].num]--;

	                // ���� left���� �л��� ���� �������� cnt�� ����
	                if(DAT[arr[left].num] == 0)
	                    cnt--; 

	                // left ������ �̵� 
	                left++; 
	            }
	            // --> ��� �� �л��� ���� ���Ե��� �ʾҴٸ� : 
	            else {

	                // ó�� ������ ���� �л��̸� cnt ���� 
	                if(DAT[arr[right].num] == 0)
	                    cnt++; 
	                // �ش� �� �л� �Ѹ� �߰�
	                DAT[arr[right].num]++;
	                // ������ �̵�
	                right++; 
	            }
	        }
	        System.out.println(ans);
	    }
	}
