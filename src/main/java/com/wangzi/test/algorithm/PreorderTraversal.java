package com.wangzi.test.algorithm;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class PreorderTraversal {

	//递归遍历 （前序遍历）  根->左->右
	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		helper(root, res);
		return res;
	}

	private void helper(TreeNode root, List<Integer> res) {
		if(root == null) return ;
		res.add(root.val);
		helper(root.left, res);
		helper(root.right, res);
	}

	//迭代
	public List<Integer> preorderTraversal1(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if (root == null)
			return res;
		Deque<TreeNode> deque = new LinkedList<TreeNode>();
		TreeNode node = root;
		while (!deque.isEmpty() || node != null) {
			while (node != null) {
				res.add(node.val);
				deque.push(node);
				node = node.left;
			}
			node = deque.pop();
			node = node.right;
		}
		return res;
	}

	public List<Integer> preorderTraversal2(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		if (root == null)
			return res;
		TreeNode p1 = root, p2 = null;
		while (p1 != null) {
			p2 = p1.left;
			if (p2 != null) {
				while (p2.right != null && p2.right != p1) {
					p2 = p2.right;
				}
				if (p2.right == null) {
					res.add(p1.val);
					p2.right = p1;
					p1 = p1.left;
					continue;
				} else {
					p2.right = null;
				}
			} else {
				res.add(p1.val);
			}
			p1 = p1.right;
		}
		return res;
	}

}
