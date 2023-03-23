package hackerRank;

import java.io.IOException;
import java.util.Scanner;

public class FormingAMagicSquare {

	
	
	
  static int formingMagicSquare(int[][] s) {
	  
	  
    int[][][] magicSquare = {
    		{{8, 1, 6}, {3, 5, 7}, {4, 9, 2}}, 
    		{{4, 3, 8}, {9, 5, 1}, {2, 7, 6}},
            {{2, 9, 4}, {7, 5, 3}, {6, 1, 8}}, 
            {{6, 7, 2}, {1, 5, 9}, {8, 3, 4}},
            {{6, 1, 8}, {7, 5, 3}, {2, 9, 4}}, 
            {{8, 3, 4}, {1, 5, 9}, {6, 7, 2}},
            {{4, 9, 2}, {3, 5, 7}, {8, 1, 6}}, 
            {{2, 7, 6}, {9, 5, 1}, {4, 3, 8}}
            };

    int ans = Integer.MAX_VALUE; //정수의 최대값 
    
    
    for (int i = 0; i < magicSquare.length; i++) {
      int cost = 0;
      for (int j = 0; j < s.length; j++) {
        for (int k = 0; k < s[j].length; k++) {
          if (s[j][k] != magicSquare[i][j][k]) { 
            cost += Math.abs(s[j][k] - magicSquare[i][j][k]);
          }
        }
      }
      ans = Math.min(ans, cost);
    }
    return ans;
  }


  public static void main(String[] args) throws IOException {
	  
	Scanner sc=new Scanner(System.in);   

    int[][] s = new int[3][3];
    
    for(int i=0;i<3;i++) {
    	for(int j=0;j<3;j++) {
    		 s[i][j]=sc.nextInt();
    	}
    }
    
       
    sc.close();
    
    

   int result = formingMagicSquare(s);
   System.out.println(result);

   
  }
}