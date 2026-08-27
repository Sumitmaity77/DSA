class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

    
        int matchLen = 0;
        int[] currentFreq = freq.clone();
        for (int i = 0; i < n; i++) {
            int tIdx = target.charAt(i) - 'a';
            if (currentFreq[tIdx] > 0) {
                currentFreq[tIdx]--;
                matchLen++;
            } else {
                break;
            }
        }

        // Try to branch off from rightmost possible matching prefix position
        for (int i = matchLen; i >= 0; i--) {
            // Reconstruct available character counts after prefix of length i
            int[] available = freq.clone();
            for (int j = 0; j < i; j++) {
                available[target.charAt(j) - 'a']--;
            }

            // At position i, try to pick the smallest character strictly greater than target[i]
            if (i < n) {
                int targetCharIdx = target.charAt(i) - 'a';
                for (int c = targetCharIdx + 1; c < 26; c++) {
                    if (available[c] > 0) {
                        // Found a valid position to make the result strictly greater
                        available[c]--;
                        StringBuilder sb = new StringBuilder();
                        sb.append(target.substring(0, i));
                        sb.append((char) ('a' + c));
                        
                        // Append remaining available characters in sorted (smallest) order
                        for (int k = 0; k < 26; k++) {
                            while (available[k] > 0) {
                                sb.append((char) ('a' + k));
                                available[k]--;
                            }
                        }
                        return sb.toString();
                    }
                }
            }
        }

        return "";
    }
}
