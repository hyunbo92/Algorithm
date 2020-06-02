/*
leetcode 724 번 문항

https://leetcode.com/problems/find-pivot-index/


문제:

Given an array of integers nums, write a method that returns the "pivot" index of this array.

We define the pivot index as the index where the sum of all the numbers to the left of the index is equal to the sum of all the numbers to the right of the index.

If no such index exists, we should return -1. If there are multiple pivot indexes, you should return the left-most pivot index.


 */
package leetcode;

public class FindPivotIndex {
	
	
	public static void main(String[] args) {
		
		int[] nums = {1,7,3,6,100,5,6,6};
		System.out.println(pivotIndex(nums));
		
	}
	

	    public static int pivotIndex(int[] nums) {
	        int sum = 0, leftsum = 0;
	        
	        for (int x: nums) sum += x;
//	        for(int i=0;i<nums.length;i++) {
//	        	sum=nums[i]+sum;
//	        }
	        
//	        System.out.println("sum: "+sum);
	        
	        for (int i = 0; i < nums.length; i++) {
	            if (leftsum == sum - leftsum - nums[i]) return i;
	            
	            leftsum += nums[i];
//	            System.out.println("leftsumtotal: "+leftsum);
	        }
	        return -1;
	    }
	
}






