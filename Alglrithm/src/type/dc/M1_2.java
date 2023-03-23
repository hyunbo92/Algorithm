/*
���� ����

���� ��ȹ��� ���͸� �帣�� ��ȭ�� �����ϴ� ȸ���. ������ ������� ȿ�������� �����ϱ� ���� ������� �����Ϸ��� �Ѵ�.
�ϳ��� ��� ������ �� �ȼ��� ���� �ȼ��θ� �̷���� �ִ�.�̸� N x N�� ũ���� ���󿡼� �� �ȼ��� 0, ���� �ȼ��� 1�� ǥ���ȴ�.
������ ��� �ȼ��� 0���θ� �Ǿ� ������ ���� ����� "0" �� �ȴ�. �̿� ���� ��� 1�θ� �Ǿ� ������ ���� ����� "1"�� �ȴ�.
���� 0 �� 1�� ����������, ��ü�� �ѹ��� ��Ÿ���� ���ϰ�, ���� ��, ������ ��, ���� �Ʒ�, ������ �Ʒ�, �� 4���� �������� ����� �����Ѵ�. �׸��� �� 4���� ������ ���ʴ�� ��ȣ�ȿ� ��� ǥ���Ѵ�.
���� ��� ������ ���� N=8 ũ���� ������ �ִٰ� ����.

image.png

�ش� ������ ���� 0 �Ǵ� 1�� �Ǿ����� �ʱ� ������, 4���� �������� �����ؾ� �Ѵ�.()

image.png

���⼭ ���� ���� ��� 0(�� �ȼ�)���� �̷���� �ִ�. �׷��Ƿ� "0"���� ������ �� �ִ�.(0)

image.png

�������� ������ ���� ���� 0 �Ǵ� 1�� �Ǿ����� �ʴ�. �׷��� ������ �ٽ� �ش� ������ 4���� �������� �����Ѵ�.
4���� �������� �����ϸ�, ���� ��, ������ ��, ���� �Ʒ�, ������ �Ʒ��� ��� 0 �Ǵ� 1�� �̷������ �Ǹ�, �ش� 4���� ������ ������� �����ϸ� 0011�� ǥ���� �� �ִ�.
������� ����� ������ ������ ���� ��Ÿ�� �� �ִ� :(0(0011))

image.png

���� ���� �Ʒ� ������ ���� 0 �Ǵ� 1�� �Ǿ����� �����Ƿ�, 4���� �������� �����Ѵ�.
���⼭ ���� ��, ���� �Ʒ�, ������ �Ʒ��� ��� 0 �Ǵ� 1�� �Ǿ�������, ������ ���� �ƴϴ�. �׷��� ������ �ش� ������ �ٽ� 4���� �������� �����Ѵ�.

image.png

�ش� ������ ���� ��� 0�� 1�θ� �Ǿ� �ִ�. �̸� ������� ǥ���ϸ�(0111) �� �ȴ�.

image.png

�ٽ� �� �κ��� ������� �����ϸ�,(0(0111)01)�� �ȴ�.
������� ����� ������ ������ ���� ��Ÿ�� �� �ִ� :(0(0011)(0(0111)01))

image.png

������ ������ ���� 1�� �Ǿ��ִ�.
�׷��� ������ ���������� ����� ������(0(0011)(0(0111)01)1)�� �ȴ�.
N ��N ũ���� ������ �־��� ��, ������ ������ ����� ����ϴ� ���α׷��� �ۼ��϶�.

�Է�
ù��° �ٿ��� ������ ũ�⸦ ��Ÿ���� ���� N �� �־�����. N �� ������ 2�� �������� �־�����, 1 �� N �� 64�� ������ ������.
�� ��° �ٺ��ʹ� ���� N�� ���ڿ��� N�� ���´�. �� ���ڿ��� 0 �Ǵ� 1�� ���ڷ� �̷���� ������, ������ �� �ȼ��� ��Ÿ����.

���
����� ������ ����� ����Ѵ�.

�Է� ���� 1 
8
00000000
00000000
00001111
00001111
00011111
00111111
00111111
00111111

��� ���� 1
(0(0011)(0(0111)01)1)
 */
package type.dc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class M1_2 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n; 
    static int[][] MAP; 

    static boolean isCompressed(int pixel, int y, int x, int size) {
        for(int i = y; i < y+ size; i++) {
            for(int j = x; j < x+size; j++) {
                // ���� �����ϰ� �ִ� �ȼ� ���� ���� �ȼ����� �ٸ��ٸ� :
                if(MAP[i][j] != pixel)
                    // �߰� ������  �ʿ� 
                    return false; 
            }
        }
        return true; 
    }

    // �������� 
    static void dc(int y, int x, int size) {

        // ���� ��ġ�� �ȼ��� (0 �Ǵ� 1)
        int cur = MAP[y][x]; 

        // �ش� size x size�� ���� ���� ���� �����Ǿ��ִ°�?
        if(isCompressed(cur, y, x, size)) {
            // ���� �ȼ� ���
            System.out.print(cur);
        }
        else {
            // ���� + ���� 
            // ���� ���� ��ȣ 
            System.out.print("(");

            // ���� ����( ���������� �κ�)�� ũ�� = size�� ���� 
            int half = size / 2; 

            // ���� ��
            // ���� y, x��ġ�� ����, ũ�⸸ �޶���
            dc(y, x, half);

            // ������ ��
            dc(y, x + half, half);

            // ���� �Ʒ�
            dc(y+ half, x, half);

            // ������ �Ʒ� 
            dc(y+ half, x+half, half);

            // �����ϰ� ���ö� ��ȣ 
            System.out.print(")");
        }
    }

    public static void main(String[] args) throws IOException {
        // n input
        n = Integer.parseInt(br.readLine());

        // MAP init
        MAP = new int[n][n]; 

        // MAP input
        for(int i = 0; i < n; i++) {
            String inp = br.readLine();
            for(int j = 0; j < n; j++) {
                MAP[i][j] = inp.charAt(j) -'0'; 
            }
        }

        // D&C (Divide & Conquer)
        // dc(y�� ������ǥ, x�� ������ǥ, ���� ���� üũ�ؾ� �� ũ��)
        dc(0, 0, n);
    }
}