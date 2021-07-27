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

	public static Node reverseList(Node head) {
		Node prev = null;
		Node curr = head;
		while (curr != null) {
			Node temp = curr.next;
			curr.next = prev;
			prev = curr;
			curr = temp;
		}
		return prev;
	}
	public static void main(String[] args) {
		int[] nodeValues = new int[]{1,2,3,4,5};
		Node dummyRoot = new Node(0);
        Node ptr = dummyRoot;
        for(int item : nodeValues) {
            ptr.next = new Node(item);
            ptr = ptr.next;
        }
        Node next = dummyRoot.next;
		ReverserLinkedList rl = new ReverserLinkedList();
		reverseList(next);
	}
}
