package com.wangzi.test.algorithm;

public class GetMinimumDifference {
	
	int pre;
	int ans;
	public int getMinimumDifference(TreeNode root) {
		ans = Integer.MAX_VALUE;
		pre = -1; // root.val 前一个节点
		dfs(root);
		return ans;
	}
	
	private void dfs(TreeNode root) {
		if(root == null) return ;
		dfs(root.left);
		if(pre == -1)
			pre = root.val;
		else {
			ans = Math.min(ans, root.val - pre);
			pre = root.val;
		}
		dfs(root.right);
	}
}
