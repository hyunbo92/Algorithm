//괄호변환


package kakao;

import java.util.Stack;

public class Kakao2020_2{ 
	
	
	private static int pos;

	public static void main(String[] args) { 
		
		solution("()))((()"); 
		
	}
	
	
	static boolean isCorrect(String str) {
		boolean ret=true;
		int left=0,right=0;
		Stack<Character> mystack=new Stack<Character>();
		
		for(int i=0; i<str.length();++i) {
			if(str.charAt(i)=='(') {
				left++;
				mystack.push('('); //넣기
			}else {
				right++;
				if(mystack.empty()) {//pop을 하려했는데 스텍이 비어있다면 짝이 안맞는것
					ret =false;
				}else {
					mystack.pop(); //빼기
				}
			}
			
			if(left==right) { //가장짧은 균현잡힌 문자열을 찾아야 하기 때문에 매번 확인 
				pos =i+1;
				return ret;
			}
		}
		
		
		return true;//여기까지 오는경우는 없음 항상 균형잡힌 문자열을 준다고했으니
		
		
	}
 
public static String solution(String p) {
	
	if(p.isEmpty()) return p;
	
	boolean correct= isCorrect(p);
	String u=p.substring(0,pos);
	String v=p.substring(pos,p.length());
	
	if(correct) {//올바른 문자열 
		System.out.println(u+solution(v));
		return u+solution(v);
	}
	
	//여기로 오면 올바른 문자열이 아닌것임
	
	
	
	String answer="("+solution(v)+")";//4-3
	
	for(int i=1 ; i<u.length()-1 ; ++i) {//1부터시작해서 첫번째 문자제거, u.lenth-1 해서 마지막 문자 제거 효과 
		if(u.charAt(i)=='(') {
			answer+=")";
		}else {
			answer+= "(";
		}
	}
	
	
	System.out.println(answer);
	return answer;



}

}

