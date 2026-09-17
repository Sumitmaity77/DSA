import java.util.Arrays;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        // minLen[i] stores the minimum length of a sub-array with sum = target ending at or before index i
        int[] minLen = new int[n];
        Arrays.fill(minLen, n + 1);
        
        int sum = 0, left = 0;
        int ans = n + 1;
        int bestSoFar = n + 1;
        
        for (int right = 0; right < n; right++) {
            sum += arr[right];
            
            // Shrink the window from the left if the current sum exceeds target
            while (sum > target) {
                sum -= arr[left++];
            }
            
            // If we found a valid sub-array
            if (sum == target) {
                int currLen = right - left + 1;
                
                // If there is a valid non-overlapping sub-array before 'left'
                if (left > 0 && minLen[left - 1] <= n) {
                    ans = Math.min(ans, currLen + minLen[left - 1]);
                }
                
                // Update bestSoFar with the minimum sub-array length found up to the current index
                bestSoFar = Math.min(bestSoFar, currLen);
            }
            
            minLen[right] = bestSoFar;
        }
        
        return ans > n ? -1 : ans;
    }
}