class Solution {
    public boolean canBeEqual(String s1, String s2) {
        char[] a1 = new char[]{s1.charAt(0), s1.charAt(2)};
        char[] a2 = new char[]{s2.charAt(0), s2.charAt(2)};
        
        char[] b1 = new char[]{s1.charAt(1), s1.charAt(3)};
        char[] b2 = new char[]{s2.charAt(1), s2.charAt(3)};
        
        Arrays.sort(a1);
        Arrays.sort(a2);
        Arrays.sort(b1);
        Arrays.sort(b2);
        
        return Arrays.equals(a1, a2) && Arrays.equals(b1, b2);
    }
}