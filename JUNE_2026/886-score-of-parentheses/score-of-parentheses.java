class Solution {
    public int scoreOfParentheses(String s) {

        Stack<Integer> stack = new Stack<>();
        int score = 0;

        for (int i = 0; i < s.length(); i++) {

            // Opening bracket
            if (s.charAt(i) == '(') {

                // Save the current score
                stack.push(score);

                // Start calculating inside this bracket
                score = 0;

            } 
            // Closing bracket
            else {

                // Case 1: ()
                if (s.charAt(i - 1) == '(') {

                    score = stack.peek() + 1;

                }
                // Case 2: (A)
                else {

                    score = stack.peek() + 2 * score;
                }

                // We are done with this level
                stack.pop();
            }
        }

        return score;
    }
}