/*
二쇱궗�쐞 �뜕吏�湲�

二쇱궗�쐞瑜� �뜕吏� �슏�닔 N怨� 異쒕젰�삎�떇 M�쓣 �엯�젰 諛쏆븘�꽌 M�쓽 媛믪뿉 �뵲�씪 媛곴컖 �븘�옒�� 媛숈씠 異쒕젰�븯�뒗 �봽濡쒓렇�옩�쓣 �옉�꽦�븯�떆�삤.

dice.png

M = 1 : 二쇱궗�쐞瑜� N踰� �뜕�졇�꽌 �굹�삱 �닔 �엳�뒗 紐⑤뱺 寃쎌슦
M = 2 : 二쇱궗�쐞瑜� N踰� �뜕�졇�꽌 以묐났�씠 �릺�뒗 寃쎌슦瑜� �젣�쇅�븯怨� �굹�삱 �닔 �엳�뒗 紐⑤뱺 寃쎌슦
M = 3 : 二쇱궗�쐞瑜� N踰� �뜕�졇�꽌 紐⑤몢 �떎瑜� �닔媛� �굹�삱 �닔 �엳�뒗 紐⑤뱺 寃쎌슦
* 以묐났�쓽 �삁
1 1 2 �� 以묐났 : 1 2 1, 2 1 1
1 2 3 怨� 以묐났 : 1 3 2, 2 1 3, 2 3 1, 3 1 2��, 3 2 1

�엯�젰
泥� 以꾩뿉 二쇱궗�쐞瑜� �뜕吏� �슏�닔 N(2�돞N�돞5)怨� 異쒕젰紐⑥뼇 M(1�돞M�돞3)�씠 �뱾�뼱�삩�떎.

異쒕젰
二쇱궗�쐞瑜� �뜕吏� �슏�닔 N�뿉 ���븳 異쒕젰紐⑥뼇�쓣 異쒕젰�븳�떎. �옉�� �닽�옄遺��꽣 異쒕젰�븳�떎.

�엯�젰 �삁�떆 1 
3 1

異쒕젰 �삁�떆 1
1 1 1
1 1 2
1 1 3
1 1 4
1 1 5
1 1 6
1 2 1
1 2 2
��
6 6 6

�엯�젰 �삁�떆 2 
3 2

異쒕젰 �삁�떆 2
1 1 1
1 1 2
1 1 3
1 1 4
1 1 5
1 1 6
1 2 2
1 2 3
1 2 4
1 2 5
1 2 6
1 3 3
��
6 6 6

�엯�젰 �삁�떆 3 
3 3

異쒕젰 �삁�떆 3
1 2 3
1 2 4
1 2 5
1 2 6
1 3 2
1 3 4
1 3 5
��
6 5 4
 */
package type.recycle;


import java.io.*;
import java.util.*; 

public class M3_3 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
	static StringTokenizer st;
	
	static int m, n;
	static int[] path = new int[6];
	
	// M=1
	static void roll1(int level) {
		// 2. 湲곗� 議곌굔
		// n痢듦퉴吏� �룄�떖�뻽�떎硫�
		if(level == n) {
			// path 異쒕젰
			for(int i = 0; i < n; i++) {
				System.out.print(path[i] + " ");
			}
			System.out.println();
			return; 
		}
		
		// 1. �옱洹� 援ъ꽦
		// branch 媛쒖닔 留뚰겮 痢듭쓣 �궡�젮媛묐땲�떎. 
		for(int i = 1; i <= 6; i++) {
			// �궡媛� 吏�湲� 痢듭뿉�꽌 �꽑�깮�븳 寃껋쓣 path�뿉 湲곕줉
			path[level] = i; 
			roll1(level+1);
			// �굹�삤硫댁꽌 �빐�젣 
//			path[level] = 0; 
		}
	}
	
	static void roll2(int level) {
		if(level == n) {
			for(int i = 0; i < n; i++) {
				System.out.print(path[i] + " ");
			}
			System.out.println();
			return; 
		}
		for(int i = 1; i <= 6; i++) {
			// �떎�쓬 �꽑�깮�쓣 �븷�븣�뿉 -> �씠 �떎�쓬 �꽑�깮�씠 媛�留앹씠 �엳�뒗媛�? 
			// 媛�吏�移섍린 = 議곌굔臾� 1媛�
			// 吏�湲� �꽑�깮�븷�젮怨� �븯�뒗 踰덊샇 < �씠 �쟾�뿉 �꽑�깮�븳 踰덊샇? 
			// �씠 議곌굔�쓣 異⑹”�븳�떎硫� = 媛�留앹씠 �뾾�떎 => pass 
			if(level > 0)
				if(i < path[level - 1]) 
					continue; 
			path[level] = i; 
			roll2(level+1);
//			path[level] = 0; 
		}
	}
	
	static int[] DAT = new int[7]; 
	static void roll3(int level) {
		// 2. 湲곗�議곌굔 
		if(level == n) {
			for(int i = 0; i < n; i++) {
				System.out.print(path[i] + " ");
			}
			System.out.println();
			return; 
		}
		// 1. �옱洹�援ъ꽦 
		for(int i = 1; i <= 6; i++) {
			// 3. 媛�吏�移섍린 
			// �씠誘� �씠�쟾�뿉 �궗�슜�맂 �늿湲덉씠�씪硫� pass 
			// �궗�슜�릺 �늿湲� 湲곕줉 -> DAT
			// index : �늿湲덉쓽 踰덊샇(1~6), value : �궗�슜(1) �궗�슜�븯吏��븡�쓬(0)
			if(DAT[i] == 1)
				continue;
			
			// �뱾�뼱媛�硫댁꽌 i踰� �늿湲� �궗�슜 湲곕줉
			DAT[i] = 1;
			path[level] = i; 
			roll3(level+1);
//			path[level] = 0;
			// 鍮좎졇�굹�삤硫댁꽌 �궗�슜 湲곕줉 �빐�젣 
			DAT[i] = 0; 
		}
	}
	public static void main(String[] args)throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		if(m == 1)
			roll1(0);
		if(m==2) 
			roll2(0); 
		if(m==3)
			roll3(0); 
	}
}