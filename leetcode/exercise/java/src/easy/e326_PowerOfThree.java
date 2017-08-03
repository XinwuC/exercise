package easy;

/**
 * 326. Power of Three
 * <p>
 * Total Accepted: 46540 Total Submissions: 124828
 * <p>
 * Difficulty: Easy
 * <p>
 * Given an integer, write a function to determine if it is a power of three.
 * <p>
 * Follow up: Could you do it without using any loop / recursion?
 * <p>
 * Leetcode: https://leetcode.com/problems/power-of-three/
 *
 * @author xinwu
 */
public class e326_PowerOfThree {
    private static int maxPowerOfThree = 0;

    public boolean isPowerOfThree(int n) {
        return n > 0 && maxPowerOfThree % n == 0;
    }

    static {
        maxPowerOfThree = (int) Math.pow(2, Math.floor(Math.log(Integer.MAX_VALUE) / Math.log(2)));
        maxPowerOfThree = (int) Math.pow(3, Math.floor(Math.log(Integer.MAX_VALUE) / Math.log(3)));
    }

    public static void main(String[] args) {
        e326_PowerOfThree target = new e326_PowerOfThree();
        System.out.println("max power of three: " + maxPowerOfThree);
        System.out.println("power of three: " + target.isPowerOfThree(maxPowerOfThree));
    }

}
