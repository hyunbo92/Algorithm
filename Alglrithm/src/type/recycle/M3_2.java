/*
��ʹ� ���

branch��Level�� �Է¹ް���� �Լ��� ȣ��Ǵ� Ƚ���� counting�ؼ� ��� ���ּ���.
���ε�� 3 2 �� �Է¹����� ����Լ��� �� 13ȸ ȣ�� �˴ϴ�.

ex)
3              2
(branch)    (level)

20190728093452_47438.png

�Է�
ù��° �ٿ� branch�� level�� �Է¹޽��ϴ�. (1 <= branch, level <= 20)

���
ù��° �ٿ� ��� �Լ��� ȣ��Ǵ� Ƚ���� ����մϴ�.

�Է� ���� 1 
4 3

��� ���� 1
85
 */
package type.recycle;


import java.io.*;
import java.util.*; 

public class M3_2 {


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    static StringTokenizer st;

    static int branch, level; 
    static int cnt = 0; 

    // ���� �������� ����������
    static void func(int lvl) {
        // 2. ��������
        // �־��� ������ ������ 
        if(lvl > level)
            return; 

        cnt++; 

        // 1. ��ͱ���
        // ���� ��� �������⸸ �ҰŰ�
        // -> ���� ������ �������� 
        for(int i = 0; i < branch; i++) {
            //System.out.println("�� : " + lvl + ", branch��ȣ: " + i);
            // �������鼭 ���� 
            func(lvl+1);
            // ���������鼭 ����
        }
    }

    public static void main(String[] args)throws IOException {
        st = new StringTokenizer(br.readLine());
        branch = Integer.parseInt(st.nextToken());
        level = Integer.parseInt(st.nextToken());
        // �� ���� ��
        func(0); 
        System.out.println(cnt);
    }
}