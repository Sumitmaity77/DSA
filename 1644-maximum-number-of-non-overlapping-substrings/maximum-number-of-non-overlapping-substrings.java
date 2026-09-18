import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);

        // Step 1: Record first and last occurrences of each character
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] == -1) {
                first[c] = i;
            }
            last[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Step 2: Find valid substrings for each character by expanding boundaries
        for (int i = 0; i < 26; i++) {
            if (first[i] == -1) continue;
            
            int l = first[i];
            int r = last[i];
            boolean valid = true;

            for (int j = l; j <= r; j++) {
                int c = s.charAt(j) - 'a';
                // If a character appears earlier than our current start, 
                // this substring cannot be formed independently.
                if (first[c] < l) {
                    valid = false;
                    break;
                }
                // Expand the right boundary if needed
                r = Math.max(r, last[c]);
            }

            if (valid) {
                intervals.add(new int[]{l, r});
            }
        }

        // Step 3: Greedy interval scheduling (sort by end index)
        intervals.sort(Comparator.comparingInt(a -> a[1]));

        List<String> result = new ArrayList<>();
        int prevEnd = -1;

        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];

            // If the current interval doesn't overlap with the last chosen one
            if (start > prevEnd) {
                result.add(s.substring(start, end + 1));
                prevEnd = end;
            }
        }

        return result;
    }
}