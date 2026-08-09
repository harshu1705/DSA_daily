public class Solution {
    public String smallestNumber(String num, long t) {
        long temp = t;
        int[] req = new int[4]; // Counts of prime factors: 2, 3, 5, 7
        int[] primes = {2, 3, 5, 7};
        for (int i = 0; i < 4; i++) {
            while (temp % primes[i] == 0) {
                req[i]++;
                temp /= primes[i];
            }
        }
        
        // If t has prime factors other than 2, 3, 5, 7, it's impossible
        if (temp > 1) {
            return "-1";
        }
        
        int n = num.length();
        int[][] prefixFactors = new int[n + 1][4];
        
        // If num contains '0', we can't keep the prefix from the first '0' onwards.
        int firstZero = -1;
        for (int i = 0; i < n; i++) {
            int d = num.charAt(i) - '0';
            if (d == 0) {
                firstZero = i;
                break;
            }
            for (int j = 0; j < 4; j++) {
                prefixFactors[i + 1][j] = prefixFactors[i][j];
            }
            int val = d;
            for (int j = 0; j < 4; j++) {
                while (val > 1 && val % primes[j] == 0) {
                    prefixFactors[i + 1][j]++;
                    val /= primes[j];
                }
            }
        }
        
        // 1. Try to find a valid number of the same length
        // We start from the rightmost possible digit to modify
        int startIdx = firstZero == -1 ? n - 1 : firstZero;
        
        // If there is no '0', check if the original number is already perfectly valid
        if (firstZero == -1) {
            if (isValid(req, prefixFactors[n], 0) != null) {
                return num;
            }
        }
        
        for (int i = startIdx; i >= 0; i--) {
            int currentDigit = (firstZero != -1 && i == firstZero) ? 0 : (num.charAt(i) - '0');
            
            for (int d = currentDigit + 1; d <= 9; d++) {
                int[] currentFactors = new int[4];
                System.arraycopy(prefixFactors[i], 0, currentFactors, 0, 4);
                
                int val = d;
                for (int j = 0; j < 4; j++) {
                    while (val > 1 && val % primes[j] == 0) {
                        currentFactors[j]++;
                        val /= primes[j];
                    }
                }
                
                int remainingLen = n - 1 - i;
                int[] suffix = isValid(req, currentFactors, remainingLen);
                if (suffix != null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(num, 0, i);
                    sb.append(d);
                    
                    int onesCount = remainingLen;
                    for (int count : suffix) onesCount -= count;
                    
                    for (int k = 0; k < onesCount; k++) sb.append('1');
                    for (int digit = 2; digit <= 9; digit++) {
                        for (int k = 0; k < suffix[digit]; k++) {
                            sb.append(digit);
                        }
                    }
                    return sb.toString();
                }
            }
        }
        
        // 2. If no valid number of the same length is found, expand length
        int len = n + 1;
        while (true) {
            int[] suffix = isValid(req, new int[4], len);
            if (suffix != null) {
                StringBuilder sb = new StringBuilder();
                int onesCount = len;
                for (int count : suffix) onesCount -= count;
                
                for (int k = 0; k < onesCount; k++) sb.append('1');
                for (int digit = 2; digit <= 9; digit++) {
                    for (int k = 0; k < suffix[digit]; k++) {
                        sb.append(digit);
                    }
                }
                return sb.toString();
            }
            len++;
        }
    }
    
    // Checks if we can fulfill the required factors with a certain amount of digits.
    // Returns frequency array of digits [0..9] to append, or null if impossible.
    private int[] isValid(int[] req, int[] current, int maxLen) {
        int r2 = Math.max(0, req[0] - current[0]);
        int r3 = Math.max(0, req[1] - current[1]);
        int r5 = Math.max(0, req[2] - current[2]);
        int r7 = Math.max(0, req[3] - current[3]);
        
        int[] counts = new int[10];
        
        // Greedily consume factors using larger digits to minimize required length
        counts[7] = r7;
        counts[5] = r5;
        
        counts[9] = r3 / 2;
        r3 %= 2;
        
        counts[8] = r2 / 3;
        r2 %= 3;
        
        // Distribute remaining 2s and 3s
        if (r3 > 0 && r2 > 0) {
            counts[6] = 1;
            r3 = 0;
            r2--;
        } else if (r3 > 0) {
            counts[3] = 1;
            r3 = 0;
        }
        
        if (r2 == 2) counts[4] = 1;
        else if (r2 == 1) counts[2] = 1;
        
        int totalDigitsUsed = counts[2] + counts[3] + counts[4] + counts[5] + 
                              counts[6] + counts[7] + counts[8] + counts[9];
                              
        if (totalDigitsUsed > maxLen) return null;
        
        return counts;
    }
}