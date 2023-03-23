/*
���� ����Ʈ�� �Ÿ�

�� ������ ��� ����ġ�� �ο��� ���̰� K�� ��ȭ ����Ʈ�� (Perect Binary Tree, PBT)�� �ִ�.
��ȭ ���� Ʈ���� 2 x k ��ŭ�� ���� ����� ������ ������, �� 2^(k+1) -1 ���� ��带 ������.
�Ʒ��� ���ÿ� ���� k = 2�� ��ȭ ����Ʈ���� 4���� ���� ���, �׸��� �� 7���� ����� ������ ������.

image.png

��Ʈ ���κ��� ���� �������� �Ÿ��� �� ��λ� �ִ� ��� �������� ����ġ�� ���̴�.
���� ��� ��Ʈ ���κ��� �� ���� �Ʒ� ���� ��� ������ �Ÿ��� 2 + 2 = 4 �̴�.
�츮�� ��ȭ ����Ʈ���� �־����� ��, �������� ����ġ����������, ��Ʈ ���κ��� ��� ���� �������� �Ÿ��� ������ ������� �Ѵ�.��, �������� ����ġ���� �� ���� �ּҰ� �Ǿ�� �Ѵ�.
���� ��� �Ʒ��� ���� ������� ������ ����ġ�� �������� ��Ʈ ���κ��� ��� ���� �������� �Ÿ��� ���� ���� �� �ִ�.
������ ����ġ�� ���� �ּҰ� �Ǵ� ����� ������ ����̴�.

image.png

��ȭ ����Ʈ���� ��� �������� ����ġ�� �־����� ��, � �������� ����ġ�� �������� ��Ʈ ��忡�� ��� ���� �������� �Ÿ��� ���� �ϸ鼭 ��� ������ ����ġ���� �� ���� �ּҰ� �ǵ��� �ϴ� ���α׷��� �ۼ��϶�.

�Է�
ù��° �ٿ��� ��ȭ����Ʈ���� ���� k�� �־�����.  k(1 �� k �� 20)
�� ��° �ٿ��� ��� �������� ����ġ�� ��Ʈ ��忡�� ����� ������ �ִ� �ͺ��� ���ʿ��� �������� ������� �־�����.
�� ������ ����ġ�� 1 �̻� 1,000 ������ �����̴�.

���
��Ʈ ��忡�� ��� ���� �������� �Ÿ��� ���� �ϴ� �������� ����ġ�� ���� �ּҰ��� ����Ѵ�.

�Է� ���� 1 
2
2 2 2 1 1 3

��� ���� 1
15
 */
package type.dc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class M1_4 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int k; 
    static int nodecnt; 
    static int[] tree; 
    static int ans = 0; 
    
    static int dc(int node) {
    	
    	// �湮�� ����� ���� �ϴ� ����
    	ans += tree[node]; 
    	
    	// ���� ����
    	// ���� ��� -> ���� �Ʒ��� ���������� �ߴµ�, �� ��� ������ �Ѿ�� 
    	// --> ���� �� ���� leaf node��!
    	if(node * 2 > nodecnt) { 
    		// ���� ����� ���� return 
    		return tree[node];
    	}
    	
    	// ��� ���� -> ���� 1��
    	// �������� ���� 
    	int left = dc(node * 2);
    	// ���������� ����
    	int right = dc(node * 2 + 1);
    	
    	//==========================
    	// left�� right, �ΰ��� �� ����� ���

    	// ���� 2�� -> left�� right�� ���ϵ� ������ ���ϱ� ���� �ʿ��� "����" ans�� ���� 
    	ans += Math.abs(left-right); 
    	
    	// ���� 3�� -> left�� right�� � ������ ������ ������ -> ���� ��忡 �˷��ְ�
    	// �̸� ����ؼ� �ݺ��ؼ� root���� �ö󰥰�.
    	// �Ʒ����� �����ϱ�� �� �� = left, right �� �� �� "ū ��" -> ����� �����ۿ� �ȵ�.
    	return tree[node] + Math.max(left, right);
    }

    public static void main(String[] args) throws IOException {
    	
    	// height �Է� 
    	k = Integer.parseInt(br.readLine());
    	
    	// height�� ������ PBT�� �� ��� ���� ?? 
    	nodecnt = (int) Math.pow(2, k+1) - 1;
    	
    	// tree init <-- Ʈ���� ���� root = 1������ ����
    	tree = new int[nodecnt + 1]; 
    	
    	// input --> 2�� node���� �Է¹ޱ�
    	st = new StringTokenizer(br.readLine());
    	for(int i = 2; i <= nodecnt; i++)
    		tree[i] = Integer.parseInt(st.nextToken());
    	
    	// root�������� �������鼭 Ʈ���� �������� 
    	dc(1);
    	System.out.println(ans);
    }
}