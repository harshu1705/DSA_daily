/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode deleteMiddle(ListNode head) {
        // Base condition: If there's only 1 node, return null
        if (head == null || head.next == null) {
            return null;
        }
        
        ListNode slow = head;
        // Start fast pointer two steps ahead
        ListNode fast = head.next.next; 
        
        // Traverse until fast reaches the end
        while (fast != null && fast.next != null) {
            slow = slow.next;        // Moves 1 step
            fast = fast.next.next;   // Moves 2 steps
        }
        
        // slow is now pointing to the node right before the middle node.
        // Delete the middle node by skipping it
        slow.next = slow.next.next;
        
        return head;
    }
}