/*
Priority Queue

Priority Queue 를 이용하여 다음 명령어들을 처리하는 프로그램을 작성해주세요
push A
정수 A 를 Priority Queue 에 넣습니다.
pop B
B 개의 값을 Priority Queue 에서 빼서 출력합니다. ( B 는 현재 Priority Queue 크기보다 작은 수가 입력됩니다.) 출력 후 한줄을 개행합니다.
add C
Priority Queue 에서 한 개를 꺼내서 C를 더한 후 다시 집어 넣습니다
Priority Queue 는 Max Heap 으로 구성을 합니다.

명령어 예시 )
push 2
push 3
pop 2 
push 10
push 5
push 12
add -3
pop 3
위 명령어에 대해 다음과 같이 출력됩니다.
3 2
10 9 5

입력
명령어 갯수 N 을 입력받습니다.
총 N 개줄에 걸쳐 명령이 입력됩니다.

출력
명령어에 알맞는 처리를 하고 출력을 해주세요

입력 예시 1 
8
push 2
push 3
pop 2 
push 10
push 5
push 12
add -3
pop 3

출력 예시 1
3 2
10 9 5
 */
package type.PriorityQueue;

import java.io.*;
import java.util.*; 

public class M2_4 {


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    static StringTokenizer st; 

    static int n; 
    // MAXHEAP
    static PriorityQueue<Integer>pq = new PriorityQueue<>(Collections.reverseOrder()); 

    public static void main(String[] args)throws IOException {
        n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            int num = Integer.parseInt(st.nextToken());
            // push : MH 삽입 
            if(cmd.equals("push"))
                pq.add(num);

            // pop : num만큼 뺴서 출력 
            if(cmd.equals("pop")) {
                for(int j = 0; j < num; j++){
                    System.out.print(pq.poll() + " ");
                }
                System.out.println();
            }

            // add : 맨위에 있는것을 꺼내서 num을 더해서 다시 넣기
            if(cmd.equals("add")) {
                pq.add(pq.poll() + num); 
            }
        }
    }
}