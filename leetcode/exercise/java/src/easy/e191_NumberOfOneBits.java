/**
 * 
 */
package easy;

/**
 * 
 * 191. Number of 1 Bits
 * 
 * Total Accepted: 102334 Total Submissions: 271664 Difficulty: Easy
 * 
 * Write a function that takes an unsigned integer and returns the number of ’1'
 * bits it has (also known as the Hamming weight).
 * 
 * For example, the 32-bit integer ’11' has binary representation
 * 00000000000000000000000000001011, so the function should return 3.
 * 
 * Leetcode: https://leetcode.com/problems/number-of-1-bits/
 * 
 * @author xinwu
 *
 */
public class e191_NumberOfOneBits {

	// you need to treat n as an unsigned value
	public int hammingWeight(int n) {
		int count = 0;

		for (int i = 0; i < 32; ++i) {
			count += 0 == (n & 1) ? 0 : 1;
			n >>= 1;
		}

		return count;
	}

	public static void main(String[] args) {
		e191_NumberOfOneBits solution = new e191_NumberOfOneBits();

		int n = 11;
		System.out.println(n + " : " + solution.hammingWeight(n));

		n = -1;
		System.out.println(n + " : " + solution.hammingWeight(n));
	}

}
