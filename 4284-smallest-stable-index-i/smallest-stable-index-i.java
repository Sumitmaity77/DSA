class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        
        // Step 1: Precompute the suffix minimums
        int[] suffixMin = new int[n];
        suffixMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(suffixMin[i + 1], nums[i]);
        }
        
        // Step 2: Iterate to find the prefix maximum and check stability
        int prefixMax = nums[0];
        for (int i = 0; i < n; i++) {
            prefixMax = Math.max(prefixMax, nums[i]);
            
            // Calculate instability score
            if (prefixMax - suffixMin[i] <= k) {
                return i;
            }
        }
        
        return -1;
    }
}