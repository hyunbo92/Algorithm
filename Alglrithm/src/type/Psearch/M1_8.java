/*
���� ������

������, �⸧�� ������� ä�������� �˷��ִ� ��ġ�� ��������մϴ�.
������ ���� O(N) ���� ������ �����ϸ�, �մ��� ��ĥ �� �ֱ� ������
���� ������������ �����ϴ� ���α׷��� �ۼ��ؾ��մϴ�.

20190723111923_93068.png

�⸧�� ���¸� ���ڿ��� �Է� �޾��ּ���.
�� �� % ���ᰡ ä������������� �ּ���.

�Է�
�⸧�� ���¸� ��Ÿ���� N���� ���ڿ��� �Է� �޽��ϴ�.
(1 <= N <= 10,000 / ���ڿ� ���̴� �ִ� 10��)
��ĭ�� ����� ("_") �� ǥ���ϸ�, �⸧�� ä���� ���� �� ("#") ���� ǥ��˴ϴ�.

���
�� �ٸ��� ���ᰡ �� % ���Ҵ����� ����մϴ�.
������ (����ĭ�� x 100 / ��üĭ�� ) ������ ���������� ����մϴ�.

�Է� ���� 1 
4
######____
#___
____
#######

��� ���� 1
60%
25%
0%
100%
 */
package type.Psearch;


import java.io.*;
import java.util.*; 

public class M1_8 {
	  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    static StringTokenizer st;

	    static int n ;
	    static String str; 

	    static void psearch() {
	        // �⺻ ���� == binary search
	        // 1. ���� ���� 
	        int left = 0;
	        int right = str.length() - 1;
	        int ans = -1;

	        // Ž��
	        while(left <= right) {
	            int mid = (left + right) / 2; 

	            // psearch -> ���ɼ��� ���� ������ ����

	            // �߰��� Ȯ�� ->  ���̴� (���ᰡ ���ִ� ���̴�)
	            // --> �� �� ������ ��� ������ ���̴� (����)
	            // --> ��? �׷� �� �����ʵ� ������ ���ɼ��� ������? -> ������ ������
	            if(str.charAt(mid) == '#') {
	                // ���ɼ��� ���Ҵ� -> ����� �ϴ� ������ �ɼ� �ִµ�, ���ķε� �ɼ� �ִ� ���ɼ��� �ִ� ����
	                // ���ɼ��� �ִ� ���⿡�� ���� ����
	                ans = mid; 
	                // ������ �� ������ �� �ָ� �� �� �ִ��� (���ᰡ �� ���ִ���)�� Ȯ���غ���!
	                left = mid + 1; 
	            }
	            // ���Ⱑ ���� ����ִ� ���� ������
	            // �� ���� ���� ��򰡿� ������ ��
	            else {
	                right = mid - 1; 
	            }
	        }

	        System.out.println(((ans+1) * 100) / str.length() + "%");
	    }


	    public static void main(String[] args) throws IOException {
	        n = Integer.parseInt(br.readLine());
	        for(int i = 0; i < n; i++) {
	            str = br.readLine(); 
	            psearch(); // parametric search 
	        }

	    }
	}