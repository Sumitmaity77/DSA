import java.util.*;

public class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        // Map to store key-value pairs for O(1) retrieval
        Map<String, String> map = new HashMap<>(knowledge.size());
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }
        
        StringBuilder result = new StringBuilder();
        int i = 0;
        int n = s.length();
        
        while (i < n) {
            char c = s.charAt(i);
            if (c == '(') {
                // Find the end of the key
                int j = i + 1;
                while (j < n && s.charAt(j) != ')') {
                    j++;
                }
                // Extract key and get mapped value or default to "?"
                String key = s.substring(i + 1, j);
                result.append(map.getOrDefault(key, "?"));
                
                // Move index past the closing bracket
                i = j + 1;
            } else {
                result.append(c);
                i++;
            }
        }
        
        return result.toString();
    }
}
