/*
����Ŭ�轼

����Ŭ�轼�� ������ ���ٰ� �ڷΰ��� �齺�� ���� �߰��մϴ�.
����Ŭ�轼�� ���� ���� ���ȣ���� �̿��� ���� ������� ������ּ���.
�迭�� �Էµǰ� ����Ŭ�轼�� ���� �ߴ� ������ ���� �ε����� �� �ε����� �Էµ˴ϴ�.
����Ŭ�轼�� �������� ���� �ε������� �� �ε������� ������ ���� ���� �߰� �� �ε����� ���ٰ� �ٽ� ���� �ε������� ���ƿɴϴ�.

image.png

����,
���ߴ� ������ 0 ~ 5 �� �Է��� �ް� 3 5 4 6 2 9 �� �Է� ������
3 5 4 6 2 9 2 6 4 5 3 �� ��� �մϴ�.
����
���ߴ� ������ 1 ~ 3�� �Է��� �ް� 3 5 4 6 2 9 �� �Է� ������
5 4 6 4 5 �� ����մϴ�.

�Է�
ù ��° �ٿ��� �迭�� ũ�� N �� �Էµ˴ϴ�.
�� ��° �ٿ��� N���� �迭������ �Էµ˴ϴ�.
�� ��° �ٿ��� ����Ŭ�轼�� ���� �ߴ� ������ ���� �ε����� �� �ε����� �Էµ˴ϴ�.

���
����Ŭ�轼�� ���� �߸� ���� ���� ������ּ���.

�Է� ���� 1 
6
3 5 4 6 2 9
0 5

��� ���� 1
3 5 4 6 2 9 2 6 4 5 3
 */
package type.recycle;


import java.io.*;
import java.util.*; 

public class M3_1 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st; 
    static int arr[];
    static int start,end;
    
    static void func(int idx) {
    	if(idx==end) {
    		System.out.print(arr[idx]+" ");
    		return;
    	}
    	System.out.print(arr[idx]+" ");
    	func(idx+1);
    	System.out.print(arr[idx]+" ");
    }
    public static void main(String[] args) throws IOException{
    	
    	int n=Integer.parseInt(br.readLine());
    	arr=new int[n];
    	
    	st=new StringTokenizer(br.readLine());
    	for(int i=0;i<n;i++) {
    		arr[i]=Integer.parseInt(st.nextToken());
    	}
    	
    	st=new StringTokenizer(br.readLine());
        start=Integer.parseInt(st.nextToken());
        end=Integer.parseInt(st.nextToken());

    	func(start);
    	
    }
}