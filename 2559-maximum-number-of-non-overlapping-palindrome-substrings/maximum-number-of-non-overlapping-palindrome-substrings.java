class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        // dp[i] stores the max number of non-overlapping palindromes in s[0...i-1]
        int[] dp = new int[n + 1];
        
        for (int i = 0; i < n; i++) {
            // By default, carrying over the result from the previous character
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
            
            // 1. Check for ODD length palindromes centered at i
            expandAroundCenter(s, i, i, k, dp, n);
            
            // 2. Check for EVEN length palindromes centered between i and i+1
            expandAroundCenter(s, i, i + 1, k, dp, n);
        }
        
        return dp[n];
    }
    
    private void expandAroundCenter(String s, int left, int right, int k, int[] dp, int n) {
        while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
            int currentLength = right - left + 1;
            
            // If we found a valid palindrome of at least length k
            if (currentLength >= k) {
                dp[right + 1] = Math.max(dp[right + 1], dp[left] + 1);
                // Greedy Optimization: If we find a valid palindrome of length k or k+1, 
                // expanding further will only increase overlap risks, so we can stop early.
                if (currentLength > k + 1) {
                    break;
                }
            }
            // Expand outward to check for larger palindromes
            left--;
            right++;
        }
    }
}
