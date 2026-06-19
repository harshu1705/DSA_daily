class Solution {
    public int largestAltitude(int[] gain) {
        int n =gain.length;
        int maxalt=0;
        int curr =0;
       for(int g : gain){
curr+=g;
maxalt = Math.max(maxalt,curr);
       }
       return maxalt;
    }
}