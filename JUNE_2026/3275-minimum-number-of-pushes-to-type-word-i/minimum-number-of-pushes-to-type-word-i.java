class Solution {
    public int minimumPushes(String word) {

        HashMap<Integer, Integer> map = new HashMap<>();

        int result = 0;
        int assignKey = 2;

        for (char ch : word.toCharArray()) {

            // After key 9, start again from key 2
            if (assignKey > 9) {
                assignKey = 2;
            }

            // Increase number of letters assigned to this key
            map.put(assignKey, map.getOrDefault(assignKey, 0) + 1);

            // Current count = pushes required
            result += map.get(assignKey);

            // Move to next key
            assignKey++;
        }

        return result;
    }
}