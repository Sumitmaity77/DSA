class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int minDistance = Integer.MAX_VALUE;
        int firstIndex = -1;
        int prevIndex = -1;
        int currIndex = 1;

        ListNode prev = head;
        if (prev == null || prev.next == null) return new int[]{-1, -1};
        ListNode curr = prev.next;

        while (curr.next != null) {
            ListNode nextNode = curr.next;
            
            // Check for local maxima or minima
            if ((curr.val > prev.val && curr.val > nextNode.val) || 
                (curr.val < prev.val && curr.val < nextNode.val)) {
                
                if (firstIndex == -1) {
                    firstIndex = currIndex;
                } else {
                    minDistance = Math.min(minDistance, currIndex - prevIndex);
                }
                prevIndex = currIndex;
            }

            prev = curr;
            curr = nextNode;
            currIndex++;
        }

        // If less than 2 critical points exist
        if (firstIndex == -1 || prevIndex == firstIndex) {
            return new int[]{-1, -1};
        }

        int maxDistance = prevIndex - firstIndex;
        return new int[]{minDistance, maxDistance};
    }
}