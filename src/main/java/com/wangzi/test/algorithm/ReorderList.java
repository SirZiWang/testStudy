package com.wangzi.test.algorithm;

import java.util.ArrayList;
import java.util.List;

public class ReorderList {
	
	public void reorderList(ListNode head) {
		if(head == null) return ;
		List<ListNode> list = new ArrayList<>();
		ListNode node = head;
		while(node != null) {
			list.add(node);
			node = node.next;
		}
		int i = 0, j = list.size() - 1;
		while(i < j) {
			list.get(i).next = list.get(j);
			i++;
			if(i == j) break;
			list.get(j).next = list.get(i);
			j--;
		}
		list.get(i).next = null;
	}
	
	public void reorderList1(ListNode head){
		if(head == null) return ;
		ListNode mid = middleNode(head); // 找到原链表的中点
		ListNode l1 = head;
		ListNode l2 = mid.next;
		mid.next = null;
		l2 = reverseList(l2); // 将原链表的右半端反转
		mergeList(l1, l2); // 将原链表的两端合并
	}

	private void mergeList(ListNode l1, ListNode l2) {
		ListNode l1Temp;
		ListNode l2Temp;
		while(l1 != null && l2 != null) {
			l1Temp = l1.next;
			l2Temp = l2.next;
			
			l1.next = l2;
			l1 = l1Temp;
			
			l2.next = l1;
			l2 = l2Temp;
		}
	}

	private ListNode reverseList(ListNode head) {
		ListNode prev = null;
		ListNode curr = head;
		while(curr != null) {
			ListNode nextTemp = curr.next;
			curr.next = prev;
            prev = curr;
            curr = nextTemp;
		}
		return prev;
	}

	private ListNode middleNode(ListNode head) {
		ListNode slow = head;
		ListNode fast = head;
		while(fast.next != null && fast.next.next !=null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}
	public static void main(String[] args) {
		System.out.println(java.lang.Runtime.getRuntime().maxMemory());
		System.out.println(java.lang.Runtime.getRuntime().totalMemory());
		System.out.println(java.lang.Runtime.getRuntime().freeMemory());
	}
}
