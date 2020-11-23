package com.wangzi.test.algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class PermuteUnique {

	boolean[] used;
	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if(nums.length == 0) return res;
		Arrays.sort(nums);
		Deque<Integer> path = new ArrayDeque<>(nums.length);
		used = new boolean[nums.length];
		dfs(nums, 0, path, res);
		return res;
	}

	private void dfs(int[] nums,  int depth, Deque<Integer> path, List<List<Integer>> res) {
		if(nums.length == depth) {
			res.add(new ArrayList<>(path));
			return ;
		}
		for(int i=0; i<nums.length; ++i) {
			if(used[i] || ( i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) 
				continue;
			path.addLast(nums[i]);
			used[i] = true;
			dfs(nums, depth + 1, path, res);
			used[i] = false;
			path.removeLast();
		}
	}
	
	public static void main(String[] args) {
		PermuteUnique p = new PermuteUnique();
		int[] nums = new int[]{1,1,2};
		p.permuteUnique(nums);
		System.out.println(nums);
	}
}
