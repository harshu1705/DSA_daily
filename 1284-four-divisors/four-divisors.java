class Solution {

    // Helper function: returns sum of divisors
    // ONLY if the number has exactly 4 divisors
    public int findSumDivisors(int num) {
        int divisors = 0; // count of divisors
        int sum = 0;      // sum of divisors

        // loop till sqrt(num)
        for (int fact = 1; fact * fact <= num; fact++) {
            if (num % fact == 0) {
                int other = num / fact;

                // perfect square case
                if (other == fact) {
                    divisors += 1;
                    sum += fact;
                } else {
                    divisors += 2;
                    sum += (fact + other);
                }
            }
        }

        // only return sum if exactly 4 divisors
        return divisors == 4 ? sum : 0;
    }

    public int sumFourDivisors(int[] nums) {
        int result = 0;

        for (int num : nums) {
            result += findSumDivisors(num);
        }

        return result;
    }
}
