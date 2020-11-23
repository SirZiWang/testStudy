package com.wangzi.test.algorithm;

import java.util.HashSet;
import java.util.Set;

public class HasCycle {

	public boolean hasCycle(ListNode head) {
		Set<ListNode> set = new HashSet<>();
		while(head != null) {
			if(!set.add(head)) 
				return true;
			head = head.next;
		}
		return false;
	}

	public ListNode detectCycle(ListNode head) {
		ListNode pos = head;
		Set<ListNode> set = new HashSet<>();
		while(pos != null) {
			if(set.contains(pos)) 
				return pos;
			else 
				set.add(pos);
			pos = pos.next;
		}
		return null;
	}

	public ListNode detectCycle1(ListNode head) {
		if(head == null) return null;
		ListNode slow = head, fast = head;
		while(fast != null) {
			slow = slow.next;
			if(fast.next != null)
				fast = fast.next.next;
			else
				return null;
			if(fast == slow) {
				ListNode pos = head;
				while( pos != slow) {
					pos = pos.next;
					slow = slow.next;
				}
				return pos;
			}
		}
		return null;
	}

	public boolean hasCycle1(ListNode head) {
		if (head == null || head.next == null)
			return false;
		ListNode slow = head;
		ListNode fast = head.next;
		while (slow != fast) {
			if (fast == null || fast.next == null) 
				return false;
			slow = slow.next;
			fast = fast.next.next;
		}
		return true;
	}
}
