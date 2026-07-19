import java.util.Stack;

class Solution {
    public String smallestSubsequence(String s) {

        Stack<Character> stack = new Stack<>();

        boolean[] taken = new boolean[26];
        int[] lastIndex = new int[26];

        // Store last occurrence of each character
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);
            int idx = ch - 'a';

            if (taken[idx])
                continue;

            while (!stack.isEmpty()
                    && stack.peek() > ch
                    && lastIndex[stack.peek() - 'a'] > i) {

                taken[stack.pop() - 'a'] = false;
            }

            stack.push(ch);
            taken[idx] = true;
        }

        StringBuilder ans = new StringBuilder();

        for (char c : stack) {
            ans.append(c);
        }

        return ans.toString();
    }
}