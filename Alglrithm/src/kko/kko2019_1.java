package kko;
//괄호변환




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class kko2019_1{ 
	
	

	public static void main(String[] args) { 
		
		
	}
	
	
 
public static String[] solution(String[] record) {
	
	List<String> ansList =new ArrayList<String>();
	Map<String, String > idmap=new HashMap<String, String>();// 키,벨류 쌍만 필요하기때문에 해쉬맵 사용
	//id,닉네임
	
	for(String str : record) {
		StringTokenizer tokenizer= new StringTokenizer(str);
		String cmd=tokenizer.nextToken();
		if(cmd.equals("Enter")||cmd.equals("Change")) {
			String id=tokenizer.nextToken();
			String name=tokenizer.nextToken();
			idmap.put(id, name);
		}
	}
	
	for(String str : record) {
		StringTokenizer tokenizer= new StringTokenizer(str);
		String cmd=tokenizer.nextToken();
		if(cmd.equals("Enter")) {
			String id=tokenizer.nextToken();
			ansList.add(idmap.get(id)+"님이 들어왔습니다.");
		}else if(cmd.equals("Leave")) {
			String id=tokenizer.nextToken();
			ansList.add(idmap.get(id)+"님이 나갔습니다.");
		}
	}
	
	
	
	String[] answer= new String[ansList.size()];
	ansList.toArray(answer);
	
	
	
	
	return answer;
	
	
	
	
	
}

}
