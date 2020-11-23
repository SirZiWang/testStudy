package com.wangzi.test.algorithm;

import java.util.HashMap;
import java.util.Map;

public class BuildTree {
	
	private Map<Integer, Integer> map = new HashMap<>();
	public TreeNode buildTree(int[] inorder, int[] postorder) {
		int len = inorder.length;
		if(len == 0) return null;
		for(int i=0; i<len; i++)
			map.put(inorder[i], i);
		return buildHelper(postorder, len - 1, inorder, 0, len - 1);
    }

	private TreeNode buildHelper(int[] postorder, int point, int[] inorder, int left, int right) {
		if(left > right) return null;
		TreeNode root = new TreeNode(postorder[point]); //获取树的根节点
		int index = map.get(postorder[point]);
		// 右子树的长度
		int rLen = right - index;
		//为跟节点的左右孩子赋值
        root.left = buildHelper(postorder, point - rLen - 1, inorder, left, index - 1);
        root.right = buildHelper(postorder, point - 1, inorder, index + 1, right);
		return root;
	}
}
