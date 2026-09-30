class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] result = new int[n];
        int depth = 0;
        
        for (int i = 0; i < n; i++) {
            if (seq.charAt(i) == '(') {
                // Alternate the assignment of '(' based on the current depth
                result[i] = depth % 2;
                depth++;
            } else {
                depth--;
                // ')' matches the corresponding '(' assigned to the same group
                result[i] = depth % 2;
            }
        }
        
        return result;
    }
}