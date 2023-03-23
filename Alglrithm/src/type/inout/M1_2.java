/*
��¸𳻱�

�𳻱� ö�� ����

����� ��������, �˱����� �� '���� Ʒ�� ��'�� ���� �ɰ� �߽��ϴ�.
�� ���� ���� �� ����� ������ ���ϰ�, �Ϸķ� �� �ɽ��ϴ�.
���� ������ �ƴ� ����� ��������,
�� �ɾ�� �� ��ġ�� �Ǽ��� �Ѿ�� ��찡 �����ϴ�.
�� ���� ������ 1,�� ���� ���� ������ 0 ���� ǥ��˴ϴ�.
cc1.png

����� �������� �� ���� ���� ����� �Է� �޽��ϴ�.
�� ���κ� �� ���� �� �������� �ɾ����� Counting�Ͽ� ����� �ּ���.

�Է�
input �Լ�(�޼���) �� �����,input �Լ����� ��� �Է��� ó���� �ּ���.
ù �ٿ��� Height�� Width�� �Էµ˴ϴ�. (1 <= Height, Width <= 1,000)
���� �ٿ��� Height x Width �������� �� ���� �� ����� �Է� �޽��ϴ�.

���
output �Լ�(�޼���)�� �����, ����� ��� �� �ּ���.
�� ���δ� ���� ���� ������ ����� �ּ���.

�Է� ���� 1 
5 6
100111
111101
011110
110001
011100

��� ���� 1
4
5
4
3
3
 */
package type.inout;

import java.io.*;
import java.util.*; 

public class M1_2 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int height, width;

    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());

        for(int i = 0; i < height; i++) {
            //"100111" 
            // 1. ���ڿ��� ���·� �Է��� �޽��ϴ�. 
            // 2. ���ڿ��� ���ڸ� �ϳ��� �����ϸ鼭 -> �̰��� ó�� (Integer�� ��ȯ)
            String str = br.readLine(); // "100111"
            int cnt = 0; 
            for(int j = 0; j < width; j++) {
                // ���ڿ��� ���, j��° ���ڿ� �����ϱ� ���ؼ��� "charAt" ���
                // str.charAt(j); -> char -> 1 (X) '1' (O)
                // Ascii -> ��ȣȭ ��������ؼ� ���� ���� ü�� 
                // 0~127 (128���� ���� -> ���� ���� -> ����)
                // 'A' -> 65
                // 'a' -> 97
                // '0' -> 40 // '0' - '0' = 0 
                // '1' -> 41 // '1' - '0' = 1 
                // ascii ������ ������ �켱���������� ������ �ٲ�� �� 
                // ���� ���� - '0' -> ���� 
                //System.out.print((str.charAt(j) -'0') + " ");
                cnt += str.charAt(j) - '0'; 
            }
            System.out.println(cnt);
        }

    }
}