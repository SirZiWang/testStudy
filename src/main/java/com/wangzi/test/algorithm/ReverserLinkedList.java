package com.wangzi.test.algorithm;

public class ReverserLinkedList {

	public Node reverserLinkedList(Node node){
		if(node == null || node.next == null)
			return node;
		Node temp = node.next;
		Node newNode = reverserLinkedList(node.next);
		temp.next = node;
		node.next = null;
		return newNode;
	}

	public static Node reverseList(Node node) {
		Node pre = null;
		Node next = null;
		while (node != null) {
			next = node.next;
			node.next = pre;
			pre = node;
			node = next;
		}
		return pre;
	}
}
