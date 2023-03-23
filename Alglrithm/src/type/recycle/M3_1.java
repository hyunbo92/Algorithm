/*
마이클잭슨

마이클잭슨은 앞으로 갔다가 뒤로가는 백스탭 춤을 추곤합니다.
마이클잭슨이 밟은 곳을 재귀호출을 이용해 밟은 순서대로 출력해주세요.
배열이 입력되고 마이클잭슨이 춤을 추는 구간의 시작 인덱스와 끝 인덱스가 입력됩니다.
마이클잭슨의 움직임은 시작 인덱스에서 끝 인덱스까지 앞으로 가며 춤을 추고 끝 인덱스를 갔다가 다시 시작 인덱스까지 돌아옵니다.

image.png

만약,
춤추는 구간을 0 ~ 5 로 입력을 받고 3 5 4 6 2 9 를 입력 받으면
3 5 4 6 2 9 2 6 4 5 3 을 출력 합니다.
만약
춤추는 구간을 1 ~ 3로 입력을 받고 3 5 4 6 2 9 를 입력 받으면
5 4 6 4 5 를 출력합니다.

입력
첫 번째 줄에는 배열의 크기 N 이 입력됩니다.
두 번째 줄에는 N개의 배열값들이 입력됩니다.
세 번째 줄에는 마이클잭슨이 춤을 추는 구간의 시작 인덱스와 끝 인덱스가 입력됩니다.

출력
마이클잭슨이 춤을 추며 밟은 값을 출력해주세요.

입력 예시 1 
6
3 5 4 6 2 9
0 5

출력 예시 1
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