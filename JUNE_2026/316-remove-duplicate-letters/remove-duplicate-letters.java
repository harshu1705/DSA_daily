class Solution {
    public String removeDuplicateLetters(String s) {

        int n = s.length();

        String result = "";

        boolean[] taken = new boolean[26];
        int[] lastIndex = new int[26];

        // Store last occurrence of every character
        for (int i = 0; i < n; i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        for (int i = 0; i < n; i++) {

            char ch = s.charAt(i);
            int idx = ch - 'a';

            if (taken[idx])
                continue;

            while (result.length() > 0 &&
                    result.charAt(result.length() - 1) > ch &&
                    lastIndex[result.charAt(result.length() - 1) - 'a'] > i) {

                taken[result.charAt(result.length() - 1) - 'a'] = false;

                result = result.substring(0, result.length() - 1);
            }

            result += ch;
            taken[idx] = true;
        }

        return result;
    }
}