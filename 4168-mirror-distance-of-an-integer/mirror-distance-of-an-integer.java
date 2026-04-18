class Solution {
    public static int reversenum(int n){
        int rev =0;
        while(n>0){
            int digit = n%10;
            rev = rev*10 + digit;
            n/=10;
        }
        return rev;
    }
    public int mirrorDistance(int n) {
        int ans = reversenum(n);
        int result =0;
        result = Math.abs(n - reversenum(n));
        return result;
    }
}