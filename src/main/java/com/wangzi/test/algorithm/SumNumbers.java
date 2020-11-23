package com.wangzi.test.algorithm;

import java.util.LinkedList;
import java.util.Queue;

public class SumNumbers {

	public int sumNumbers(TreeNode root) {
		return dfs(root, 0);
	}

	private int dfs(TreeNode root, int prev) {
		if(root == null) return 0;
		int sum = root.val + prev * 10;
		if(root.left == null && root.right == null)
			return sum;
		else
			return dfs(root.left, sum) + dfs(root.right, sum);
	}

	public int sumNumbers1(TreeNode root) {
		if(root == null) return 0;
		int sum = 0;
		Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
		Queue<Integer> numQueue = new LinkedList<Integer>();
		nodeQueue.offer(root);
		numQueue.offer(root.val);
		while(!nodeQueue.isEmpty()) {
			TreeNode node = nodeQueue.poll();
			int num = numQueue.poll();
			TreeNode left = node.left, right = node.right;
			if(left == null && right == null) 
				sum += num;
			else {
				if(left != null) {
					nodeQueue.offer(left);
					numQueue.offer(num * 10 + left.val);
				}
				if(right != null) {
					nodeQueue.offer(right);
					numQueue.offer(num * 10 + right.val);
				}
			}	
		}
		return sum;
	}
}
