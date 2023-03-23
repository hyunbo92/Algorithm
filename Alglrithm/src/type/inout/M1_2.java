/*
어설픈모내기

모내기 철이 오면

현모네 가족들은, 옹기종기 모여 '싹을 틔운 모'를 논이 심곤 했습니다.
모를 심을 때면 한 사람씩 라인을 정하고, 일렬로 모를 심습니다.
전문 농사꾼이 아닌 현모네 가족들은,
모를 심어야 할 위치를 실수로 넘어가는 경우가 많습니다.
모를 심은 지역은 1,모를 심지 않은 지역은 0 으로 표기됩니다.
cc1.png

현모네 가족들이 모를 심은 후의 모습을 입력 받습니다.
각 라인별 몇 개의 모를 성공리에 심었는지 Counting하여 출력해 주세요.

입력
input 함수(메서드) 를 만들고,input 함수에서 모든 입력을 처리해 주세요.
첫 줄에는 Height와 Width가 입력됩니다. (1 <= Height, Width <= 1,000)
다음 줄에는 Height x Width 사이즈의 모를 심은 후 모습을 입력 받습니다.

출력
output 함수(메서드)를 만들고, 결과를 출력 해 주세요.
각 라인당 심은 모의 개수를 출력해 주세요.

입력 예시 1 
5 6
100111
111101
011110
110001
011100

출력 예시 1
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
            // 1. 문자열의 형태로 입력을 받습니다. 
            // 2. 문자열의 문자를 하나씩 접근하면서 -> 이것을 처리 (Integer로 변환)
            String str = br.readLine(); // "100111"
            int cnt = 0; 
            for(int j = 0; j < width; j++) {
                // 문자열의 경우, j번째 문자에 접근하기 위해서는 "charAt" 사용
                // str.charAt(j); -> char -> 1 (X) '1' (O)
                // Ascii -> 암호화 통신을위해서 만든 문자 체계 
                // 0~127 (128개의 문자 -> 정수 정수 -> 문자)
                // 'A' -> 65
                // 'a' -> 97
                // '0' -> 40 // '0' - '0' = 0 
                // '1' -> 41 // '1' - '0' = 1 
                // ascii 문자의 연산은 우선수위순으로 정수로 바뀌게 됨 
                // 문자 정수 - '0' -> 정수 
                //System.out.print((str.charAt(j) -'0') + " ");
                cnt += str.charAt(j) - '0'; 
            }
            System.out.println(cnt);
        }

    }
}