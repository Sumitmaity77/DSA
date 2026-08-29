import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        
        // 1. Pair each value with its original index and sort by value
        int[][] sortedPairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            sortedPairs[i][0] = nums[i];
            sortedPairs[i][1] = i;
        }
        Arrays.sort(sortedPairs, (a, b) -> Integer.compare(a[0], b[0]));
        
        int[] result = new int[n];
        
        // 2. Identify and process connected groups using a two-pointer approach
        int i = 0;
        while (i < n) {
            int j = i + 1;
            // Extend the group as long as the difference between adjacent sorted values <= limit
            while (j < n && sortedPairs[j][0] - sortedPairs[j - 1][0] <= limit) {
                j++;
            }
            
            // Extract the original indices for this group
            int[] originalIndices = new int[j - i];
            for (int k = i; k < j; k++) {
                originalIndices[k - i] = sortedPairs[k][1];
            }
            // Sort the original positions so we fill them from left to right
            Arrays.sort(originalIndices);
            
            // Assign the sorted values to the sorted positions
            for (int k = i; k < j; k++) {
                result[originalIndices[k - i]] = sortedPairs[k][0];
            }
            
            // Move to the next group
            i = j;
        }
        
        return result;
    }
}
