
package kko;
//½ÇÆÐÀ²
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class kko2019_2{ 
	
	
	
	public static class Fail{
		int stage;
		double rate;
		Fail(int stage,double rate){
			this.stage=stage;
			this.rate=rate;
		}
		
	}

	public static void main(String[] args) { 
		
	}



 static Comparator<Fail> comp=new Comparator<Fail>()	{
	public int compare(Fail a,Fail b) {
		if(a.rate<b.rate)
			return 1;
		else if(a.rate>b.rate)
			return -1;
		else {
			if(a.stage>b.stage)
			return 1;
			else if(a.stage<b.stage)
				return -1;
			else{
				return 0;
			}
		}
	}
};
	
	
 
public static int[] solution(int N,int[] stages) {
	
	int[] answer =new int[N];
	
	List<Fail> fails= new ArrayList<Fail>();
	int total=stages.length;
	
	int[] users=new int[N+1];
	for(int s:stages) {
		users[s-1]++;
	}
	
	for(int i=0;i<N;i++) {
		if(users[i]==0)
			fails.add(new Fail(i+1,0));
		else {
			fails.add(new Fail(i+1,(double)users[i]/total));
			total-=users[i];
		}
	}
	
	Collections.sort(fails,comp);
	for(int i=0;i<N;i++)
		answer[i]= fails.get(i).stage;
	
	
	
	
	
	return answer;

}

}