package com.wangzi.test.algorithm;

public class InsertionSortList {
	
	public ListNode insertionSortList(ListNode head) {
		if(head == null) return head;
		ListNode res = new ListNode(0);
		res.next = head;
		ListNode lastSortedNode = head, curr = head.next;
		while(curr != null) {
			if(lastSortedNode.val <= curr.val) {
				lastSortedNode = lastSortedNode.next;
			} else {
				ListNode prev = res;
				while(prev.next.val <= curr.val) {
					prev =prev.next;
				}
				lastSortedNode.next = curr.next;
				curr.next = prev.next;
				prev.next = curr;
			}
			curr = lastSortedNode.next;
		}
		return res.next;
    }
}
