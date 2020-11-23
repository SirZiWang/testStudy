package com.wangzi.test.algorithm;

import java.util.ArrayList;
import java.util.List;

import com.wangzi.test.algorithm.BinaryTreePaths.TreeNode;

public class FindMode {
	
	/**
	 * Morris 中序遍历的一个重要步骤就是寻找当前节点的前驱节点，并且 Morris 中序遍历寻找下一个点始终是通过转移到 right指针指向的位置来完成的。
	 * 如果当前节点没有左子树，则遍历这个点，然后跳转到当前节点的右子树。
	 * 如果当前节点有左子树，那么它的前驱节点一定在左子树上，我们可以在左子树上一直向右行走，找到当前点的前驱节点。
	 * 如果前驱节点没有右子树，就将前驱节点的 right 指针指向当前节点。这一步是为了在遍历完前驱节点后能找到前驱节点的后继，也就是当前节点。
	 * 如果前驱节点的右子树为当前节点，说明前驱节点已经被遍历过并被修改了 right 指针，这个时候我们重新将前驱的右孩子设置为空，遍历当前的点，然后跳转到当前节点的右子树。
	 * 
	 */
	
	// 表示当前节点的值
	// 表示当前节点的数量
	// 最大的重复数量
	int base, count, maxCount;
	List<Integer> answer = new ArrayList<>();
	public int[] findMode(TreeNode root) {
		TreeNode cur = root, pre = null;
		while(cur != null) {
			if(cur.left == null) {
				update(cur.val);
				cur = cur.right;
				continue;
			}
			pre = cur.left;
			while(pre.right !=null && pre.right != cur) 
				pre = pre.right;
			if(pre.right == null) {
				pre.right = cur;
				cur = cur.left;
			} else {
				pre.right = null;
				update(cur.val);
				cur = cur.right;
			}
		}
		int[] mode = new int[answer.size()];
        for (int i = 0; i < answer.size(); ++i) {
            mode[i] = answer.get(i);
        }
        return mode;
    }
	
	private void update(int x) {
		if(x == base)
			++count;
		else {
			count = 1;
			base = x;
		}
		if(count == maxCount)
			answer.add(base);
		if(count > maxCount) {
			maxCount = count;
			answer.clear();
			answer.add(base);
		}
	}
}
