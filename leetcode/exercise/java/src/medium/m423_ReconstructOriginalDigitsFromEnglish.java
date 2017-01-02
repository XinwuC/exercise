package medium;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Created by xinwu on 1/2/17.
 */
public class m423_ReconstructOriginalDigitsFromEnglish {
    public String originalDigits(String s) {
        int[] charCount = new int[26];
        int[] numCount = new int[10];
        for (char c : s.toCharArray()) {
            ++charCount[c - 'a'];
        }
        // zero
        numCount[0] = charCount['z' - 'a'];
        numCount[2] = charCount['w' - 'a'];
        numCount[4] = charCount['u' - 'a'];
        numCount[6] = charCount['x' - 'a'];
        numCount[8] = charCount['g' - 'a'];
        numCount[5] = charCount['f' - 'a'] - numCount[4];
        numCount[3] = charCount['h' - 'a'] - numCount[8];
        numCount[7] = charCount['v' - 'a'] - numCount[5];
        numCount[1] = charCount['o' - 'a'] - numCount[0] - numCount[2] - numCount[4];
        numCount[9] = charCount['i' - 'a'] - numCount[5] - numCount[6] - numCount[8];

        char[] result = new char[IntStream.of(numCount).sum()];
        int startIndex = 0, toIndex = 0;
        for (int i = 0; i < 10 && startIndex < result.length; ++i) {
            if (numCount[i] > 0) {
                toIndex += numCount[i];
                Arrays.fill(result, startIndex, toIndex, (char) ('0' + i));
                startIndex = toIndex;
            }
        }

        return new String(result);
    }

    public static void main(String[] args) {
        m423_ReconstructOriginalDigitsFromEnglish solution = new m423_ReconstructOriginalDigitsFromEnglish();
        System.out.println(solution.originalDigits("zeoneroztwoeonero"));
        System.out.println(solution.originalDigits("owoztneoer"));
        System.out.println(solution.originalDigits("fviefuro"));
        System.out.println(solution.originalDigits("nnei"));
    }
}
