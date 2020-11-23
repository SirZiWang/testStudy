package com.wangzi.test.algorithm;

import com.wangzi.test.algorithm.BinaryTreePaths.TreeNode;

public class ConvertBST {

	int sum = 0;
	public TreeNode convertBST(TreeNode root) {
		if(root != null) {
			convertBST(root.right);
			sum += root.val;
			root.val = sum;
			convertBST(root.left);
		}
		return root;
	}
	
	/**
	 * Morris 遍历的核心思想是利用树的大量空闲指针，实现空间开销的极限缩减。其反序中序遍历规则总结如下：
	 * 如果当前节点的右子节点为空，处理当前节点，并遍历当前节点的左子节点；
	 * 如果当前节点的右子节点不为空，找到当前节点右子树的最左节点（该节点为当前节点中序遍历的前驱节点）；
	 * 如果最左节点的左指针为空，将最左节点的左指针指向当前节点，遍历当前节点的右子节点；
	 * 如果最左节点的左指针不为空，将最左节点的左指针重新置为空（恢复树的原状），处理当前节点，并将当前节点置为其左节点；
	 * 重复步骤 1 和步骤 2，直到遍历结束。
	 * @param root
	 * @return
	 */
	
	public TreeNode convertBST1(TreeNode root) {
		int sum = 0;
		TreeNode node = root;

		while (node != null) {
			if (node.right == null) {
				sum += node.val;
				node.val = sum;
				node = node.left;
			} else {
				TreeNode succ = getSuccessor(node);
				if (succ.left == null) {
					succ.left = node;
					node = node.right;
				} else {
					succ.left = null;
					sum += node.val;
					node.val = sum;
					node = node.left;
				}
			}
		}

		return root;
	}

	public TreeNode getSuccessor(TreeNode node) {
		TreeNode succ = node.right;
		while (succ.left != null && succ.left != node) {
			succ = succ.left;
		}
		return succ;
	}

}
