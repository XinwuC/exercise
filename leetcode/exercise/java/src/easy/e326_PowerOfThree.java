package easy;

/**
 * 326. Power of Three
 * 
 * Total Accepted: 46540 Total Submissions: 124828
 * 
 * Difficulty: Easy
 * 
 * Given an integer, write a function to determine if it is a power of three.
 * 
 * Follow up: Could you do it without using any loop / recursion?
 * 
 * Leetcode: https://leetcode.com/problems/power-of-three/
 * 
 * @author xinwu
 *
 */
public class e326_PowerOfThree {
	private static int maxPowerOfThree = 0;

	public boolean isPowerOfThree(int n) {
		return recursive(n);
	}

	private boolean recursive(int n) {
		while (0 == n % 3) {
			n /= 3;
		}

		return 1 == n;
	}

	static {
		maxPowerOfThree = (int) Math.pow(3, Math.floor(Math.log(Integer.MAX_VALUE) / Math.log(3)));
	}

	public static void main(String[] args) {
		e326_PowerOfThree target = new e326_PowerOfThree();
		System.out.println("max power of three: " + maxPowerOfThree);
		System.out.println("power of three: " + target.isPowerOfThree(maxPowerOfThree));
	}

}
