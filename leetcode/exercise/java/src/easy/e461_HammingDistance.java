package easy;

/**
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 * <p>
 * Given two integers x and y, calculate the Hamming distance.
 * <p>
 * Note:
 * 0 ≤ x, y < 2^31.
 * <p>
 * Example:
 * <p>
 * Input: x = 1, y = 4
 * <p>
 * Output: 2
 * <p>
 * Explanation:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 * -      ?   ?
 * <p>
 * The above arrows point to positions where the corresponding bits are different.
 */
public class e461_HammingDistance {
    public int hammingDistance(int x, int y) {
        int distance = 0;

        int r = x ^ y;
        long bitmask = 1;
        while (bitmask <= x || bitmask <= y) {
            if ((bitmask & r) > 0)
                ++distance;
            bitmask <<= 1;
        }

        return distance;
    }

    public static void main(String[] args) {
        e461_HammingDistance solution = new e461_HammingDistance();

        System.out.println(solution.hammingDistance(1, 4));
        System.out.println(solution.hammingDistance(0, 0));
        System.out.println(solution.hammingDistance(0, 1));
        System.out.println(solution.hammingDistance(0, Integer.MAX_VALUE - 1));
        System.out.println(solution.hammingDistance(1, Integer.MAX_VALUE - 1));
        System.out.println(solution.hammingDistance(Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 1));
        System.out.println(solution.hammingDistance(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }
}
