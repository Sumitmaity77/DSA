class Solution {
    public int reverseDegree(String s) {
        int totalDegree = 0;
        int n = s.length();
        
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            // Position in the reversed alphabet ('a' = 26, 'z' = 1)
            int revAlphabetPos = 26 - (ch - 'a');
            // 1-indexed position in the string
            int stringPos = i + 1;
            
            totalDegree += revAlphabetPos * stringPos;
        }
        
        return totalDegree;
    }
}