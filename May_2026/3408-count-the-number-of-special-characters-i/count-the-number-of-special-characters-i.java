class Solution {
    public int numberOfSpecialChars(String word) {
        int[] freq = new int[123];
        int count = 0;

        for(char ch : word.toCharArray()) {
            freq[ch]++;

            // Current ch is lower case , then look for upper case
            if(ch >= 'a' && ch <= 'z') {
                char capital = (char)(ch - 'a' + 'A');
                if(freq[ch] == 1 && freq[capital] > 0) // lowercase just appeared for first time
                    count++;
            } else { // Current ch is upper case , then look for lower case
                char lower = (char)(ch - 'A' + 'a');
                if(freq[ch] == 1 && freq[lower] > 0) // uppercase just appeared for first time
                    count++;
            }
        }

        return count;
    }
}
