package com.wangzi.test.algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class CombinationSum {

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> result = new ArrayList<>();
		if (candidates.length == 0)
			return result;
		List<Integer> list = new ArrayList<>();
		dfs(candidates, result, list, 0, target);
		return result;

	}

	/**
	 * 
	 * @param candidates
	 * @param result 
	 * @param list
	 * @param begin 搜索起点
	 * @param target
	 * 
	 */
	private void dfs(int[] candidates, List<List<Integer>> result, List<Integer> list, int begin, int target) {
		if(target < 0)
			return ;
		if(target == 0) {
			result.add(new ArrayList<>(list));
			return ;
		}
		for(int i = begin; i<candidates.length; i++) {
			list.add(candidates[i]);
			System.out.println("递归之前 => " + list + "，剩余 = " + (target - candidates[i]));

			dfs(candidates, result, list, i, target - candidates[i]);
			//重置状态
			list.remove(list.size() - 1);
			System.out.println("递归之后 => " + list);
		}
	}


	public List<List<Integer>> combinationSum1(int[] candidates, int target) {
		List<List<Integer>> res = new ArrayList<>();
		if(candidates.length == 0) 
			return res;
		Arrays.sort(candidates);
		Deque<Integer> path = new ArrayDeque<>();
		dfs(candidates, 0, target, path, res);
		return res;
	}

	private void dfs(int[] candidates, int begin, int target, Deque<Integer> path, List<List<Integer>> res) {
		if(target == 0) {
			res.add(new ArrayList<>(path));
			return;
		}
		for(int i = begin; i < candidates.length; i++) {
			if (target - candidates[i] < 0)
				break;
			//去重复
			if(i > begin && candidates[i] == candidates[i-1])
				continue;
			path.addLast(candidates[i]);
			System.out.println("递归之前 => " + path + "，剩余 = " + (target - candidates[i]));
			dfs(candidates, i + 1, target - candidates[i], path, res);
			path.removeLast();
			System.out.println("递归之后 => " + path);
		}
	}

	public List<List<Integer>> combinationSum3(int k, int n) {
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> temp = new ArrayList<>();
		dfs(k, n, 1, temp, res);
		return res;
	}

	private void dfs(int k, int n, int begin, List<Integer> temp, List<List<Integer>> res) {
		if(temp.size() == k && n == 0) {
			res.add(new ArrayList<Integer>(temp));
			return ;
		}
		for (int i = begin; i <= 9; i++) {
			temp.add(i);
			dfs(k, n - i, i + 1, temp, res);
			temp.remove(temp.size() - 1);
		}
	}


	public static void main(String[] args) {
		CombinationSum s = new CombinationSum();
		int[] candidates = new int[]{10,1,2,7,6,1,5};
		int target = 8;
		//		List<List<Integer>> res = s.combinationSum1(candidates, target);
		List<List<Integer>> res = s.combinationSum3(3, 9);
		System.out.println("输出 => " + res);
	}

}
