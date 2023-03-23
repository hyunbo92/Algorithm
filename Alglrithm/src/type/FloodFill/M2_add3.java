/*
��ź ����


4 x 5 �������� ���ڿ�  �迭�� �غ��մϴ�.
���� '_' ����� ���ڷ� �� ä��ϴ�.
��ź�� ������ ��ǥ �� ���� �Է¹޾��ּ���.
�������,
���� ��ź��(1,1)�� ���ϵǸ� 8�������� ��ź�� �����ϴ�.

20180721225427_50765.jpg

�� ��Ȳ���� (3,3)�� ��ź�� ���ϵǸ� 8�������� ��ź�� �����ϴ�.

20180721225436_32392.jpg

��ź 2���� ���� �Ǿ����� �� ������ ��� ���ּ���.

�Է�
ù �ٿ��� ù��° ��ź�� ���� �� ��ǥ (Y, X) �� �Է¹޽��ϴ�.
�� ��° �ٿ��� �� ��° ��ź�� ���� �� ��ǥ (Y, X)�� �Է� �޽��ϴ�.

���
��ź�� ���ϵ� �� ����� ����մϴ�.

�Է� ���� 1 
1 1
3 3

��� ���� 1
# # # _ _
# _ # _ _
# # # # #
_ _ # _ #
 */
package type.FloodFill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class M2_add3 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int h = 4;
    static int w = 5;

    static char[][] MAP = new char[h][w];

    static int[] ydir = {-1,1,0,0,-1,-1, 1, 1};
    static int[] xdir = {0,0,-1,1,-1, 1, -1, 1};

    public static void main(String[] args) throws IOException {
        // init
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                MAP[i][j] = '_';
            }
        }
        // input
        for(int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            //����迭 ��Ʈ���� 8���� 
            for(int j = 0; j < 8; j++) {
                int ny = y + ydir[j];
                int nx = x + xdir[j];
                // ** �ʼ� ����üũ 
                if(ny < 0 || nx < 0 || ny >= h || nx >= w)
                    continue;
                // ����!
                MAP[ny][nx] = '#'; 
            }
        }
        // ���� ���� MAP �� ���
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                System.out.print(MAP[i][j] + " ");
            }
            System.out.println();
        }
    }
}