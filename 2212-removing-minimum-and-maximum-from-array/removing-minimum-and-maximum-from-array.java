class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }

        int minIdx = 0;
        int maxIdx = 0;

        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIdx]) {
                minIdx = i;
            }
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }

        int left = Math.min(minIdx, maxIdx);
        int right = Math.max(minIdx, maxIdx);

        // Scenario 1: Both from front
        int frontOnly = right + 1;
        
        // Scenario 2: Both from back
        int backOnly = n - left;
        
        // Scenario 3: One from front, one from back
        int bothSides = (left + 1) + (n - right);

        return Math.min(frontOnly, Math.min(backOnly, bothSides));
    }
}