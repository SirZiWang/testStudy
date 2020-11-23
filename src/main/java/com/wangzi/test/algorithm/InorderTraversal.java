package com.wangzi.test.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.wangzi.test.algorithm.BinaryTreePaths.TreeNode;

public class InorderTraversal {
	// 左->根->右
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		inorderHelper(root, res);
		return res;
	}
	//迭代遍历
	private void inorderHelper(TreeNode root, List<Integer> res) {
		if(root == null) return ;
		inorderHelper(root.left, res);
		res.add(root.val);
		inorderHelper(root.right, res);
	}


	public List<Integer> inorderTraversal1(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		TreeNode node = root;
		while(node != null || !stack.isEmpty()) {
			while(node != null) {
				stack.push(node); // 添加根节点
				node = node.left; //  循环遍历左节点
			}
			node = stack.pop(); //当前栈顶已经是最底层左节点了，取出栈顶元素
            res.add(node.val);
            node  = node.right; // 添加右节点
		}
		return res;
	}
	
	// Morris 中序遍历
	public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        TreeNode predecessor = null;

        while(root != null) {
            if(root.left != null) {
                // predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
                predecessor = root.left;
                while(predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }
                
                // 让 predecessor 的右指针指向 root，继续遍历左子树
                if(predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                }
                // 说明左子树已经访问完了，我们需要断开链接
                else {
                    res.add(root.val);
                    predecessor.right = null;
                    root = root.right;
                }
            }
            // 如果没有左孩子，则直接访问右孩子
            else {
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }
}
