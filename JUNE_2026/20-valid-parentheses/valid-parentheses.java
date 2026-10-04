class Solution {
    public boolean isValid(String s) {
        // Stack to keep track of expected closing brackets
        Stack<Character> stack = new Stack<>();
        
        // Iterate through each character in the string
        for (char c : s.toCharArray()) {
            
            // Push the matching closing bracket for each opening bracket
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } 
            // If it's a closing bracket
            else {
                // If stack is empty or top element doesn't match -> invalid
                if (stack.isEmpty() || stack.pop() != c) {
                    return false;
                }
            }
        }
        
        // If stack is empty, all brackets matched correctly
        return stack.isEmpty();
    }
}