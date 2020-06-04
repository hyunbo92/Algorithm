//문자열압축

package kakao;

public class Kakao2020_1{ 
	
	
	public static void main(String[] args) { 
		
		solution("abcabcasdfedf"); 
		
	}

public static int solution(String s) {
	 if(s.length() == 1) return 1;
     
        int answer = 1001; //문자 길이 1000이하니까
	
        for (int i = 1; i <= s.length() / 2; i++) {//자르는 크기
            String now, next = "", result = "";
		int hit=1; //중복개수
		
		for(int j=0;j<=s.length()/i;j++) {//문자자를 위치
			 int start = j * i;
                int end = i * (j + 1) > s.length() ? s.length() : i * (j + 1);
                now = next;
                
                next = s.substring(start, end);
 
			
                if(now.equals(next)) {
                    hit++;
                } else {
                    result += (processHit(hit) + now);
                    hit = 1;
                }
            }
		
		 result += (processHit(hit) + next);
            System.out.println("result: "+result);
            answer = Math.min(answer, result.length());
            
		
		
				
		
	}
	
	
	

    System.out.println("minLenth: "+answer);
	return answer;
}

private static String processHit(int hit) {
	return hit>1 ? String.valueOf(hit): "";
//	Integer.parseInt(s) -->문자를 정수로
//String.valueOf(hit)-->정수를 문자로 
	
}
}

