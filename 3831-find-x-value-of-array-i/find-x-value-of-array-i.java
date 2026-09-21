class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        // dp[r] stores the number of subarrays ending at the current position with product % k == r
        long[] dp = new long[k];

        for (int num : nums) {
            long[] nextDp = new long[k];
            int currentMod = num % k;

            // Single element subarray starting at the current index
            nextDp[currentMod]++;

            // Extend all previous subarrays ending at the previous index
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int newMod = (r * currentMod) % k;
                    nextDp[newMod] += dp[r];
                }
            }

            dp = nextDp;

            // Add the counts from the current index to the final result
            for (int r = 0; r < k; r++) {
                result[r] += dp[r];
            }
        }

        return result;
    }
}