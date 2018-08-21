package com.wangzi.test.dataStructure;

import java.util.LinkedList;
import java.util.List;

/**
 * 链式存储
 * 二叉树每个结点最多有两个孩子，所以为它设计一个数据域和两个指针域即可。我们称这样的链表为二叉链表
 * @author Administrator
 *
 * @param <E>
 */
public class LinkedBinaryTree<E> {
	private List<Node> nodeList = null;
	
	private class Node {  
		Node leftChild;  
		Node rightChild;  
		E data;  
		Node(E data) {  
			this.data = data;  
		}  
	}

	public Node getRoot(){
		return nodeList.get(0);
	}
	public void createBinTree(E[] array) {  
		nodeList = new LinkedList<Node>();  

		for (int i = 0; i < array.length; i++) {  
			nodeList.add(new Node(array[i]));  
		}  
		// 对前lasti-1个父节点按照父节点与孩子节点的数字关系建立二叉树  
		for (int i = 0; i < array.length / 2 - 1; i++) {   
			nodeList.get(i).leftChild = nodeList.get(i * 2 + 1);    
			nodeList.get(i).rightChild = nodeList.get(i * 2 + 2);  
		}  
		// 最后一个父节点:因为最后一个父节点可能没有右孩子，所以单独拿出来处理  
		int lastParent = array.length / 2 - 1;  
		nodeList.get(lastParent).leftChild = nodeList.get(lastParent * 2 + 1);  

		// 右孩子,如果数组的长度为奇数才建立右孩子  
		if (array.length % 2 == 1) {  
			nodeList.get(lastParent).rightChild = nodeList.get(lastParent * 2 + 2);  
		}  
	} 

	public static void main(String[] args) {
		Character[] data = {'A','B','C','D','E','F','G','H','I','J'};
		LinkedBinaryTree<Character> ldt = new LinkedBinaryTree<>();
		ldt.createBinTree(data);
	}
}
