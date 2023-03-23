/*
Sort Library

�Էµ� ������Sort Library �� ����Ͽ� ���� �� �ּ���.
�� �� ���� Ÿ������ ���� �� ��� �ϸ� �˴ϴ�.
���� ������ ���� �Է��� �޾Ҵٸ�,

dd1.png

[Type 1]
���ڸ� �������� �����Ͽ� ����մϴ�.

dd2.png

[Type 2]
���ڸ� �������� �����Ͽ� ����մϴ�.

dd3.png

[Type 3]
���ڿ� ���ڸ� ������ ���� �켱���� �������� ���߾� ���� �� ����մϴ�.
¦�� �켱
���� ��������
���� ��������

dd4.png

�� �ٿ� Type 1, 2, 3 �� ���ĵ� ����� ��� ���ֽø� �˴ϴ�.

�Է�
������ ���� ���� N �� �Է� �޽��ϴ�. (1 <= N <= 100,000)
�� ��° �ٿ� ���ڷ� ������ N���� ���� �Է� �޽��ϴ�.
�� ��° �ٿ� ���ڷ� ������ N���� ���� �Է� �޽��ϴ�.

���
ù ��° �ٿ��� [Type 1] �� ����� ��� �ϼ���.
�� ��° �ٿ��� [Type 2] �� ����� ����ϼ���.
�� ��°�� �� ��°�� [Type 3] �� ����� ����ϼ���.

�Է� ���� 1 
7
1 3 5 2 4 5 5
A B C D E G F

��� ���� 1
1 2 3 4 5 5 5 
5 5 5 4 3 2 1 
2 4 1 3 5 5 5 
D E A B C F G 
 */
package type.sort;

/****************************�߿�*********************************/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer; 

public class M2_1 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n; 

    // 1. ���� �Է¹����͸� ���� �迭 
    static Integer[] arr; 
    static Node[] nodearr; 

    // 2. * class�� ���� �迭 
    // num, char 
    static class Node implements Comparable <Node> {
        int num;
        char c; 
        // constructor
        Node(int num, char c){
            this.num = num;
            this.c = c; 
        }
        //¦�� �켱
        //���� ��������
        //���� ��������
        @Override
        public int compareTo(Node next) {
            // ������ ¦���̰� �������� Ȧ�� = �ùٸ� ���� = -1
            if(num % 2 == 0 && next.num % 2 == 1)
                return -1;
            // ������ Ȧ���̰� �������� ¦�� = Ʋ�� ���� = 1
            if(num % 2 == 1 && next.num % 2 == 0)
                return 1; 
            if(num < next.num)
                return -1;
            if(num > next.num)
                return 1; 
            if(c < next.c)
                return -1;
            if(c > next.c)
                return 1;
            return 0; 
        }
    }

    public static void main(String[] args) throws IOException{
        n = Integer.parseInt(br.readLine());
        // init
        arr = new Integer[n];
        nodearr = new Node[n]; 
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine()); 
        for(int i = 0; i < n; i++) {
            nodearr[i] = new  Node(arr[i], st.nextToken().charAt(0));
        }

        // type 1 - ���ڸ� �������� 
        Arrays.sort(arr);
        for(int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");
        System.out.println();

        // type 2 - ���ڸ� ��������
        Arrays.sort(arr, Collections.reverseOrder());
        for(int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");
        System.out.println();

        // type 3 - custom ����
        Arrays.sort(nodearr);
        for(int i = 0; i < n; i++)
            System.out.print(nodearr[i].num + " ");
        System.out.println();
        for(int i = 0; i < n; i++)
            System.out.print(nodearr[i].c + " ");
        System.out.println();

    }
}