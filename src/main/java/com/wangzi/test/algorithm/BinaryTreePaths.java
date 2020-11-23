package com.wangzi.test.algorithm;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths {
	public List<String> binaryTreePaths(TreeNode root) {
		List<String> result = new ArrayList<>();
		constructPaths(root, "", result);
		return result;
	}

	private void constructPaths(TreeNode root, String path, List<String> result) {
		if(root == null) return ;
		path += root.val;
		if(root.left == null && root.right == null)
			result.add(path);
		else {
			constructPaths(root.left, path + "->", result);
			constructPaths(root.right, path + "->", result);
		}
	}

	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { 
			val = x; 
		}
	}
}
