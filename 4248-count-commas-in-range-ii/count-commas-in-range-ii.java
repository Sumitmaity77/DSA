class Solution {
    public long countCommas(long n) {
        long totalCommas = 0;
        long start = 1000; // Commas start appearing from 1,000 onwards
        long commas = 1;

        while (start <= n) {
            long end = start * 1000 - 1;
            long count = Math.min(n, end) - start + 1;
            totalCommas += count * commas;
            
            start *= 1000;
            commas++;
        }

        return totalCommas;
    }
}