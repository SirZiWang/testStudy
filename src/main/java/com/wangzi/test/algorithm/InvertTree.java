package com.wangzi.test.algorithm;

import java.util.LinkedList;
import java.util.Queue;

import com.wangzi.test.algorithm.BinaryTreePaths.TreeNode;

public class InvertTree {
	
	public TreeNode invertTree(TreeNode root) {
		if(root == null) return null;
		TreeNode left = invertTree(root.left);
		TreeNode right = invertTree(root.right);
		root.left = right;
		root.right = left;
		return root;	
    }
	
	public TreeNode invertTree1(TreeNode root) {
		if(root == null) return null;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while(!queue.isEmpty()) {
			TreeNode node = queue.poll();
            TreeNode rightTree = node.right;
            node.right = node.left;
            node.left = rightTree;
            if (node.left != null){
                queue.offer(node.left);
            }
            if (node.right != null){
                queue.offer(node.right);
            }
        }
        return root;
    }

}
