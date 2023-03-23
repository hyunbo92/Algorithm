/*
������ ��

N���� ������ �� ���� A�� �ֽ��ϴ�.
�� ������ i ���� j��° ���� ������ ��(A[i] + A[i+1] ... A[j-1] + A[j])�� M�� �Ǵ� ����� ���� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.
[����]
[1, 2, 3, 4, 2, 5, 3, 1, 1, 2] ��� ������ �־��� ��, ������ ���� 10�� �Ǵ� ���� 3���� �����մϴ�.

image.png

�Է�
ù��° �ٿ� N(1 �� N �� 10,000), M(1 �� M �� 300,000,000)�� �־����ϴ�.
���� �ٿ��� A[1], A[2], ��, A[N]�� �������� �и��Ǿ� �־����ϴ�. ������ A[x]�� 30,000�� ���� �ʴ� �ڿ����Դϴ�.

���
M�� ���� �� �ִ� ������ ���� ����� ���� ����մϴ�.

�Է� ���� 1 
4 2
1 1 1 1

��� ���� 1
3

�Է� ���� 2 
10 5
1 2 3 4 2 5 3 1 1 2

��� ���� 2
3
 */
package type.TwoPointer;


import java.io.*;
import java.util.*; 

public class M1_4 {


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // ** ������ �������� Ȯ���ϱ����� 
    // �迭�� ũ�� = N + 1
    // arr[n+1]; 
    static int[] arr; 
    static int n, target; 

    public static void main(String[] args) throws IOException {
        // input
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        // init -> �迭�� ũ��� ũ�� + 1 (n+1)
        arr = new int[n+1]; 

        st = new StringTokenizer(br.readLine()); 
        for(int i = 0; i < n; i++) 
            arr[i] = Integer.parseInt(st.nextToken());

        // 1. left, right ������ ����
        int left = 0;
        int right = 0; 
        int sum = 0; 
        int cnt = 0; 

        // 2. �ݺ� ���� ����
        while(right <= n) {
            // ���ɼ��� ���� �������� �̵� ������ ����
            // 1. sum > target
            if(sum > target) {
                sum -= arr[left]; 
                left++; 
            }

            // 2. sum < target 
            else if(sum < target) {
                sum += arr[right]; 
                right++;
            }

            // 3. sum == target 
            else {
                // ����
                cnt++; 
                // ���Ḧ ��ų�� �ִ� �������� �����͸� �̵�
                sum += arr[right]; 
                right++;
            }
        }
        System.out.println(cnt);
    }
}


/*����§�ڵ�
package pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n,target;
	
    static int[] arr;
	public static void main(String[] args) throws IOException	{
		
		st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		target=Integer.parseInt(st.nextToken());

		
		st=new StringTokenizer(br.readLine());
		arr= new int [n+1];
		
		for(int i =0;i<n;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		
		int left=0;
		int right=0;
		int sum=0;
		int ans=0;
		
		while(right<=n) {
			if(sum>target) {
				sum-=arr[left];
				left++;
			}
			if(sum<target) {
				sum+=arr[right];
				right++;
			}
			if(sum==target) {
				ans++;
				sum+=arr[right];
				right++;
			}
		}
		System.out.println(ans);
	}
}


*/