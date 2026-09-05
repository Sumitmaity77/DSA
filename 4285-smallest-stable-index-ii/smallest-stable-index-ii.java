class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        
        // Array to store the minimum values from index i to n-1
        int[] suffMin = new int[n];
        suffMin[n - 1] = nums[n - 1];
        
        // Precompute the suffix minimums
        for (int i = n - 2; i >= 0; i--) {
            suffMin[i] = Math.min(suffMin[i + 1], nums[i]);
        }
        
        int currentMax = nums[0];
        
        // Iterate to find the first index that satisfies the condition
        for (int i = 0; i < n; i++) {
            // Keep track of the maximum value from index 0 to i
            currentMax = Math.max(currentMax, nums[i]);
            
            // Calculate instability score and check against k
            if (currentMax - suffMin[i] <= k) {
                return i;
            }
        }
        
        return -1;
    }
}