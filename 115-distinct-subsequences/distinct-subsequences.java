class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        
        // dp[j] stores the number of distinct subsequences of s that match t[0..j-1]
        int[] dp = new int[n + 1];
        
        // Base case: An empty string t is a subsequence of any string s exactly 1 time
        dp[0] = 1;
        
        for (int i = 1; i <= m; i++) {
            // Traverse backwards to safely use the values from the previous state (i-1)
            for (int j = n; j >= 1; j--) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    // If characters match, we can either use this character or ignore it
                    dp[j] = dp[j] + dp[j - 1];
                }
                // If they don't match, dp[j] remains unchanged (implicitly dp[j] = dp[j])
            }
        }
        
        return dp[n];
    }
}