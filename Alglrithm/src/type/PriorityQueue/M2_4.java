/*
Priority Queue

Priority Queue �� �̿��Ͽ� ���� ��ɾ���� ó���ϴ� ���α׷��� �ۼ����ּ���
push A
���� A �� Priority Queue �� �ֽ��ϴ�.
pop B
B ���� ���� Priority Queue ���� ���� ����մϴ�. ( B �� ���� Priority Queue ũ�⺸�� ���� ���� �Էµ˴ϴ�.) ��� �� ������ �����մϴ�.
add C
Priority Queue ���� �� ���� ������ C�� ���� �� �ٽ� ���� �ֽ��ϴ�
Priority Queue �� Max Heap ���� ������ �մϴ�.

��ɾ� ���� )
push 2
push 3
pop 2 
push 10
push 5
push 12
add -3
pop 3
�� ��ɾ ���� ������ ���� ��µ˴ϴ�.
3 2
10 9 5

�Է�
��ɾ� ���� N �� �Է¹޽��ϴ�.
�� N ���ٿ� ���� ����� �Էµ˴ϴ�.

���
��ɾ �˸´� ó���� �ϰ� ����� ���ּ���

�Է� ���� 1 
8
push 2
push 3
pop 2 
push 10
push 5
push 12
add -3
pop 3

��� ���� 1
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
            // push : MH ���� 
            if(cmd.equals("push"))
                pq.add(num);

            // pop : num��ŭ ���� ��� 
            if(cmd.equals("pop")) {
                for(int j = 0; j < num; j++){
                    System.out.print(pq.poll() + " ");
                }
                System.out.println();
            }

            // add : ������ �ִ°��� ������ num�� ���ؼ� �ٽ� �ֱ�
            if(cmd.equals("add")) {
                pq.add(pq.poll() + num); 
            }
        }
    }
}