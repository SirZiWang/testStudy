package com.wangzi.test.algorithm;

import java.util.ArrayList;
import java.util.List;

public class Combine {
	
	 public List<List<Integer>> combine(int n, int k) {
		 List<List<Integer>> result = new ArrayList<>();
		 if( n<k || k<=0) return result;
		 List<Integer> path = new ArrayList<>();
		 dfs(result, n, k, 1, path);
		 return result;
	 }

	private void dfs(List<List<Integer>> result, int n, int k, int begin, List<Integer> path) {
		if(path.size() == k) {
			result.add(new ArrayList<>(path));
			return ;
		}
		
		for(int i=begin; i<=n; i++) {
			path.add(i);
			dfs(result, n, k, i+1, path);
			path.remove(path.size() - 1);
		}
	}
	
	public static void main(String[] args) {
		Combine c = new Combine();
		c.combine(4, 2);
	}
}
