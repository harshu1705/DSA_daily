class Solution {

    Set<String> result = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {

        int leftRem = 0;
        int rightRem = 0;

        // Step 1: Find the minimum number of
        // '(' and ')' that must be removed.
        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                leftRem++;

            } else if (ch == ')') {

                if (leftRem > 0) {
                    // Match this ')' with an existing '('
                    leftRem--;
                } else {
                    // Extra ')'
                    rightRem++;
                }
            }
        }

        // Step 2: Backtracking
        backtrack(s, 0, leftRem, rightRem, 0, new StringBuilder());

        return new ArrayList<>(result);
    }

    private void backtrack(
            String s,
            int index,
            int leftRem,
            int rightRem,
            int balance,
            StringBuilder current) {

        // Invalid state:
        // More ')' than '('
        if (balance < 0) {
            return;
        }

        // End of string
        if (index == s.length()) {

            if (leftRem == 0 &&
                rightRem == 0 &&
                balance == 0) {

                result.add(current.toString());
            }

            return;
        }

        char ch = s.charAt(index);

        // ------------------------------------------------
        // CASE 1: '('
        // ------------------------------------------------
        if (ch == '(') {

            // OPTION 1: Remove '('
            if (leftRem > 0) {
                backtrack(
                    s,
                    index + 1,
                    leftRem - 1,
                    rightRem,
                    balance,
                    current
                );
            }

            // OPTION 2: Keep '('
            current.append('(');

            backtrack(
                s,
                index + 1,
                leftRem,
                rightRem,
                balance + 1,
                current
            );

            // Backtrack
            current.deleteCharAt(current.length() - 1);

        }

        // ------------------------------------------------
        // CASE 2: ')'
        // ------------------------------------------------
        else if (ch == ')') {

            // OPTION 1: Remove ')'
            if (rightRem > 0) {
                backtrack(
                    s,
                    index + 1,
                    leftRem,
                    rightRem - 1,
                    balance,
                    current
                );
            }

            // OPTION 2: Keep ')'
            // We can only keep ')' if there is
            // an unmatched '('.
            if (balance > 0) {

                current.append(')');

                backtrack(
                    s,
                    index + 1,
                    leftRem,
                    rightRem,
                    balance - 1,
                    current
                );

                // Backtrack
                current.deleteCharAt(current.length() - 1);
            }

        }

        // ------------------------------------------------
        // CASE 3: Letter
        // ------------------------------------------------
        else {

            current.append(ch);

            backtrack(
                s,
                index + 1,
                leftRem,
                rightRem,
                balance,
                current
            );

            // Backtrack
            current.deleteCharAt(current.length() - 1);
        }
    }
}