
public class LinkedListProblems {
	
	public static ListNode deleteCheck(ListNode anc) {
		ListNode eval = anc.next;
		ListNode check = eval.next;
		
		if(eval.val == check.val) {
			anc.next = eval.next;
		}
		return null;
	}

	/*
	Given a sorted linked list, delete all duplicates such that each element appear only once.

	Example 1:
	Input: 1->1->2
	Output: 1->2

	Example 2:
	Input: 1->1->2->3->3->3
	Output: 1->2->3
	 */

	public static ListNode deleteDuplicates(ListNode head) {
		ListNode anc = head;
		ListNode eval = anc.next;
		while(true) {
			if(anc.next == null) {
				break;
			}
			if(anc.val == eval.val) {
				anc.next = eval.next;
				eval = anc.next;
				continue;
			}
			else if(eval.next == null) {
				break;
			}
			else {
				anc = eval;
				eval = eval.next;
			}
		}
		
		return head;
	}

	/*
	 * Reverse a singly linked list.
		Example:
		Input: 1->2->3->4->5->NULL
		Output: 5->4->3->2->1->NULL
	 */
	public static ListNode reverseList(ListNode head) {
		ListNode newHead = head;
		ListNode temp = head;
		
		while(newHead.next!= null){
			newHead = newHead.next;
		}
		
		ListNode tempHead = newHead;
		while(tempHead != head) {
			while(temp.next!=tempHead) {
				temp = temp.next;
			}
			tempHead.next = temp;
			tempHead = temp;
			temp = head;
		}
		temp.next = null;
		return newHead;
	}
}
