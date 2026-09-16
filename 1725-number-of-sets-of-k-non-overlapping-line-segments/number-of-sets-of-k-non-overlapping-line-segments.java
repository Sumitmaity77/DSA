class Solution {
    public int numberOfSets(int n, int k) {
        long MOD = 1_000_000_007;
        int totalObjects = n + k - 1;
        int r = 2 * k;
        
        return combination(totalObjects, r, MOD);
    }
    
    private int combination(int n, int r, long mod) {
        if (r < 0 || r > n) return 0;
        if (r == 0 || r == n) return 1;
        if (r > n / 2) r = n - r; // C(n, r) == C(n, n - r)
        
        long numerator = 1;
        long denominator = 1;
        
        for (int i = 0; i < r; i++) {
            numerator = (numerator * (n - i)) % mod;
            denominator = (denominator * (i + 1)) % mod;
        }
        
        // Multiply numerator by modular inverse of denominator
        long invDenominator = power(denominator, mod - 2, mod);
        return (int)((numerator * invDenominator) % mod);
    }
    
    private long power(long base, long exp, long mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }
}