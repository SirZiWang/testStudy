package com.wangzi.test.algorithm;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
	
	public List<List<Integer>> subsets(int[] nums) {
		// write your code here
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> list = new ArrayList<>();
		dfs(result, list, nums, 0);
		return result;
	}

	private void dfs(List<List<Integer>> result, List<Integer> list, int[] nums, int j){
		if(!result.contains(list))
			result.add(new ArrayList<Integer>(list));
		for(int i=j; i<nums.length; i++){
			list.add(nums[i]);
			dfs(result, list, nums, i+1);
			list.remove(list.size() - 1);
		}
	}
	public static void main(String[] args) {
		Subsets subsets = new Subsets();
		int[] nums = {1,2,2,3};
		List<List<Integer>> subsets2 = subsets.subsets(nums);
		System.out.println(subsets2);
		partitionArray(nums, 4);
	}
	
	 public static int partitionArray(int[] nums, int k) {
	        // write your code here
	        if(nums == null || nums.length == 0)
	            return 0;
	        int index = 0;
	        for(int i=0; i<nums.length; i++){
	            if(nums[i] < k){
	                int temp = nums[i];
	                nums[i] = nums[index];
	                nums[index] = temp;
	                index++;
	            }
	        }
	        return index;
	    }
}
