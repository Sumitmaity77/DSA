/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverse(ListNode head) {
        ListNode c = head;
        ListNode p = null;
        ListNode f = null;

        while (c != null) {
            f = c.next;
            c.next = p;
            p = c;
            c = f;
        }
        return p;
    }

    public boolean isPalindrome(ListNode head) {
        
        if (head == null || head.next == null) return true ;
        ListNode slow = head ;
        ListNode fast = head ;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next ;
            fast = fast.next.next ;

        }
        ListNode head2 = slow.next;
        slow.next = null ;
        head2 = reverse(head2);

        ListNode i = head ;
        ListNode j = head2 ;
        while ( j != null){
            if (i.val != j.val) return false ;
            i = i.next ;
            j=j.next ;
        }
        return true ;
    }
}