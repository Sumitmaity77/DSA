class Solution {
    public int distinctSubseqII(String s) {
        long MOD = 1_000_000_007;
        // dp[c] stores the number of distinct subsequences ending with character (c - 'a')
        long[] dp = new long[26];
        
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            
            // Calculate total distinct subsequences possible by appending s[i] 
            // to all previous distinct subsequences + the single character itself
            long total = 1;
            for (int j = 0; j < 26; j++) {
                total = (total + dp[j]) % MOD;
            }
            
            // Update the count for the current character
            dp[c] = total;
        }
        
        // Sum up all distinct subsequences ending with any character
        long ans = 0;
        for (int i = 0; i < 26; i++) {
            ans = (ans + dp[i]) % MOD;
        }
        
        return (int) ans;
    }
}