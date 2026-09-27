import java.util.Stack;

class Solution {
    public String reverseParentheses(String s) {
        Stack<StringBuilder> stack = new Stack<>();
        StringBuilder current = new StringBuilder();
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                // Push the current string to the stack and start a new one
                stack.push(current);
                current = new StringBuilder();
            } else if (c == ')') {
                // Reverse the current substring
                current.reverse();
                // Append it to the previous string popped from the stack
                StringBuilder previous = stack.pop();
                previous.append(current);
                current = previous;
            } else {
                // Append normal characters
                current.append(c);
            }
        }
        
        return current.toString();
    }
}