/*
�۱� ã��

������ ���� �а�, ���İ��� ���� ���� �����մϴ�.
�����ϴ� ��, �Ҽ� ���� �а� ������ ��� �۱͸� ���İ��� ����ΰ��մϴ�.

cc2.png

���� å���� û���ϴ� ��, �� ���İ��� �о�Ұ�,
�װ��� �� ������ �� �� ������ �����ֽ��ϴ�.
������ �д� å���� ��� �κ��� �۱Ϳ����� ã�ƺ����� �մϴ�.

gg3.png

���� "booms." ��� �۱Ͱ� ������ �����,
�� �۱ʹ� å (3,3)�� å (3,8) ���� ã�� �� �ֽ��ϴ�.
2���� �迭�� �幮�� �����ã�ƾ��ϴ� �۱͸� �Է¹ް�,
�۱Ͱ� �幮 ��� ��ǥ�� �����ϴ��� ã�� ����� �ּ���.


[����]
���� �Ʒ��� ���� AAA �۱͸� ã�ƾ��Ѵٸ�,
�� 4���� �߰��� �� �ֽ��ϴ�.
(0,0) / (0,1) / (1,0) / (1,1)

cc3.png

�Է�
ù �ٿ��� H, W (Height, Width)�� �Է¹޽��ϴ�. (1 <= Height, Width <= 1,000)
���� �ٿ��� H x W �������� å ������ �Է� �޽��ϴ�.
����� '_' (�����)�� ǥ��Ǹ�, �� ĭ�� �����ϴ�.
�� �����ٿ��� ã�ƾ��ϴ� �۱��� ���� n�� �Է� �޽��ϴ�.(1 <= n <= 100)
�����ٿ��� n ������ �۱͸� �Է� �޽��ϴ�.

���
�۱Ͱ� å ��� ��ǥ�� �ִ��� ã��,��ǥ�� ����� �ּ���.
���࿩�� ��ǥ���� �۱Ͱ� �߰ߵȴٸ�y ��ǥ�� �۰�,x ��ǥ�� ���� ������� ����� �ּ���.
���� (3,1) /(1,2) /(3,5) ���� �߰ߵǾ��ٸ�,����ϴ� ������ (1,2) / (3,1) / (3,5) �Դϴ�.

�Է� ���� 1 
5 9
This_is_o
n..I_love
_your_sou
nd.boom.b
oom.he___
5
boom.

��� ���� 1
(3,3)
(3,8)

�Է� ���� 2 
3 2
AA
AA
AA
3
AAA

��� ���� 2
(0,0)
(0,1)
(1,0)
(1,1)
 */
package type.inout;

import java.io.*;
import java.util.*; 

public class M1_4 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static String str = "";
    static int h, w; 

    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        for(int i = 0; i < h; i++)
            str += br.readLine(); 

        int findlen = Integer.parseInt(br.readLine());
        String findstr = br.readLine(); 

        for(int i = 0; i <= h * w - findlen; i++) {
            int cnt = 0;
            for(int j = 0; j < findlen; j++) {
                if(findstr.charAt(j) == str.charAt(i+j)) 
                    cnt++; 
            }
            if(cnt == findlen) {
                System.out.println("(" + i / w + "," + i % w + ")");
            }
        }
    }
}