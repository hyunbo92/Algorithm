/*
영상 압축

민코 기획사는 느와르 장르의 영화를 제작하는 회사다. 수많은 영상들을 효율적으로 보관하기 위해 영상들을 압축하려고 한다.
하나의 흑백 영상은 흰 픽셀과 검은 픽셀로만 이루어져 있다.이를 N x N의 크기의 영상에서 흰 픽셀은 0, 검은 픽셀은 1로 표현된다.
영상의 모든 픽셀이 0으로만 되어 있으면 압축 결과는 "0" 이 된다. 이와 같이 모두 1로만 되어 있으면 압축 결과는 "1"이 된다.
만약 0 과 1이 섞여있으면, 전체를 한번에 나타내지 못하고, 왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래, 총 4개의 영역으로 나누어서 압축한다. 그리고 이 4개의 영역은 차례대로 괄호안에 묶어서 표현한다.
예를 들어 다음과 같은 N=8 크기의 영상이 있다고 하자.

image.png

해당 영상은 전부 0 또는 1로 되어있지 않기 때문에, 4개의 영역으로 분할해야 한다.()

image.png

여기서 왼쪽 위는 모두 0(흰 픽셀)으로 이루어져 있다. 그러므로 "0"으로 압축할 수 있다.(0)

image.png

다음으로 오른쪽 위는 전부 0 또는 1로 되어있지 않다. 그렇기 때문에 다시 해당 영역을 4개의 영역으로 분할한다.
4개의 영역으로 분할하면, 왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래가 모두 0 또는 1로 이루어지게 되며, 해당 4개의 영역을 순서대로 압축하면 0011로 표현할 수 있다.
현재까지 압축된 영상은 다음과 같이 나타낼 수 있다 :(0(0011))

image.png

다음 왼쪽 아래 영역도 전부 0 또는 1로 되어있지 않으므로, 4개의 영역으로 분할한다.
여기서 왼쪽 위, 왼쪽 아래, 오른쪽 아래는 모두 0 또는 1로 되어있지만, 오른쪽 위는 아니다. 그렇기 때문에 해당 영역은 다시 4개의 영역으로 분할한다.

image.png

해당 영역도 이제 모두 0과 1로만 되어 있다. 이를 순서대로 표현하면(0111) 이 된다.

image.png

다시 위 부분을 순서대로 압축하면,(0(0111)01)이 된다.
현재까지 압축된 영상은 다음과 같이 나타낼 수 있다 :(0(0011)(0(0111)01))

image.png

마지막 영역은 전부 1로 되어있다.
그렇기 때문에 최종적으로 압축된 영상은(0(0011)(0(0111)01)1)가 된다.
N ×N 크기의 영상이 주어질 때, 영상을 압축한 결과를 출력하는 프로그램을 작성하라.

입력
첫번째 줄에는 영상의 크기를 나타내는 숫자 N 이 주어진다. N 은 언제나 2의 제곱수로 주어지며, 1 ≤ N ≤ 64의 범위를 가진다.
두 번째 줄부터는 길이 N의 문자열이 N개 들어온다. 각 문자열은 0 또는 1의 숫자로 이루어져 있으며, 영상의 각 픽셀을 나타낸다.

출력
압축된 영상의 결과를 출력한다.

입력 예시 1 
8
00000000
00000000
00001111
00001111
00011111
00111111
00111111
00111111

출력 예시 1
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
                // 지금 접근하고 있는 픽셀 값이 기준 픽셀값과 다르다면 :
                if(MAP[i][j] != pixel)
                    // 추가 압축이  필요 
                    return false; 
            }
        }
        return true; 
    }

    // 분할정복 
    static void dc(int y, int x, int size) {

        // 기준 위치의 픽셀값 (0 또는 1)
        int cur = MAP[y][x]; 

        // 해당 size x size의 구간 단일 노드로 구성되어있는가?
        if(isCompressed(cur, y, x, size)) {
            // 기준 픽셀 출력
            System.out.print(cur);
        }
        else {
            // 분할 + 정복 
            // 분할 들어갈때 괄호 
            System.out.print("(");

            // 다음 구간( 분할정복된 부분)의 크기 = size의 절반 
            int half = size / 2; 

            // 왼쪽 위
            // 기준 y, x위치는 같고, 크기만 달라짐
            dc(y, x, half);

            // 오른쪽 위
            dc(y, x + half, half);

            // 왼쪽 아래
            dc(y+ half, x, half);

            // 오른쪽 아래 
            dc(y+ half, x+half, half);

            // 정복하고 나올때 괄호 
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
        // dc(y의 기준좌표, x의 기준좌표, 내가 지금 체크해야 할 크기)
        dc(0, 0, n);
    }
}