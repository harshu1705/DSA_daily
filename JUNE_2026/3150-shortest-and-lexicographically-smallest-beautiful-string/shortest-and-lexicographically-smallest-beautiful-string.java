class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();

        List<Integer> ones = new ArrayList<>();

        // Store positions of all 1s
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                ones.add(i);
            }
        }

        // Not enough 1s
        if (ones.size() < k) {
            return "";
        }

        String answer = "";
        int bestLength = Integer.MAX_VALUE;

        for (int i = 0; i + k - 1 < ones.size(); i++) {

            int left = ones.get(i);
            int right = ones.get(i + k - 1);

            int length = right - left + 1;

            String candidate = s.substring(left, right + 1);

            if (length < bestLength) {
                bestLength = length;
                answer = candidate;
            } 
            else if (length == bestLength && candidate.compareTo(answer) < 0) {
                answer = candidate;
            }
        }

        return answer;
    }
}