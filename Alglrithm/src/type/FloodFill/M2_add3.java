/*
폭탄 투하


4 x 5 사이즈의 문자열  배열을 준비합니다.
값은 '_' 언더바 문자로 꽉 채웁니다.
폭탄을 투하할 좌표 두 곳을 입력받아주세요.
예를들어,
만약 폭탄이(1,1)에 투하되면 8방향으로 폭탄이 터집니다.

20180721225427_50765.jpg

위 상황에서 (3,3)에 폭탄이 투하되면 8방향으로 폭탄이 터집니다.

20180721225436_32392.jpg

폭탄 2개가 투하 되었을때 그 현장을 출력 해주세요.

입력
첫 줄에는 첫번째 폭탄을 투하 할 좌표 (Y, X) 를 입력받습니다.
두 번째 줄에는 두 번째 폭탄을 투하 할 좌표 (Y, X)를 입력 받습니다.

출력
폭탄이 투하된 후 결과를 출력합니다.

입력 예시 1 
1 1
3 3

출력 예시 1
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
            //방향배열 터트리기 8방향 
            for(int j = 0; j < 8; j++) {
                int ny = y + ydir[j];
                int nx = x + xdir[j];
                // ** 필수 범위체크 
                if(ny < 0 || nx < 0 || ny >= h || nx >= w)
                    continue;
                // 폭파!
                MAP[ny][nx] = '#'; 
            }
        }
        // 터진 후의 MAP 을 출력
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                System.out.print(MAP[i][j] + " ");
            }
            System.out.println();
        }
    }
}