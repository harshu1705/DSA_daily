import java.util.Arrays;

class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        // CRITICAL: Convert the initial mass to a 64-bit integer (long).
        // The total mass can reach 10^10, which exceeds the maximum limit 
        // of a standard 32-bit int (2,147,483,647) and causes overflow errors.
        long currentMass = mass;
        
        // Sort the asteroids in ascending order to always tackle the smallest ones first
        Arrays.sort(asteroids);
        
        // Simulate the collisions
        for (int asteroid : asteroids) {
            if (currentMass >= asteroid) {
                // The planet is big enough; absorb the asteroid's mass
                currentMass += asteroid;
            } else {
                // The planet is too small to destroy this asteroid; simulation fails
                return false;
            }
        }
        
        // If we made it through the whole array, all asteroids were destroyed
        return true;
    }
}