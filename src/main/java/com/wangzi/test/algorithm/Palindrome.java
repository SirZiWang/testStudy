package com.wangzi.test.algorithm;

public class Palindrome {
	
	public boolean isPalindrome(ListNode head) {
		if(head == null) return true;
		//使用快慢指针找到中间位置
		ListNode mid = middleNode(head);
		//翻转右半部分链表
		ListNode rightListNode = reverseList(mid.next);
		while(rightListNode != null) {
			if(head.val != rightListNode.val) 
				return false;
			rightListNode = rightListNode.next;
			head = head.next;
		}
		return true;
	}

	private ListNode reverseList(ListNode head) {
		ListNode prev = null;
		ListNode curr = head;
		while(curr!= null) {
			ListNode temp = curr.next;
			curr.next = prev;
            prev = curr;
            curr = temp;		
		}
		return prev;
	}

	private ListNode middleNode(ListNode head) {
		ListNode fast = head;
		ListNode slow = head;
		while(fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}
}
