class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;

        // start from last digit
        for (int i = n - 1; i >= 0; i--) {

            // if digit < 9, just add 1 and return
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }

            // digit == 9 → becomes 0 (carry)
            digits[i] = 0;
        }

        // if all digits were 9 (e.g., 999)
        int[] result = new int[n + 1];
        result[0] = 1;
        return result;
    }
}
