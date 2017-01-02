package medium;

import java.util.Arrays;

/**
 * 274. H-Index
 * <p>
 * Total Accepted: 59201
 * Total Submissions: 185167
 * Difficulty: Medium
 * Contributors: Admin
 * <p>
 * Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute
 * the researcher's h-index.
 * <p>
 * According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at
 * least h citations each, and the other N − h papers have no more than h citations each."
 * <p>
 * For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them
 * had received 3, 0, 6, 1, 5 citations respectively. Since the researcher has 3 papers with at least 3 citations
 * each and the remaining two with no more than 3 citations each, his h-index is 3.
 * <p>
 * Note: If there are several possible values for h, the maximum one is taken as the h-index.
 * <p>
 * Hint:
 * <p>
 * An easy approach is to sort the array first.
 * What are the possible values of h-index?
 * A faster approach is to use extra space.
 * <p>
 * <p>
 * LeetCode: https://leetcode.com/problems/h-index/
 * <p>
 * Created by xinwu on 1/2/17.
 */
public class m274_HIndex {
    public int hIndex(int[] citations) {
        int[] hArray = new int[citations.length + 2];

        for (int c : citations) {
            int hIndex = c < hArray.length - 1 ? c : hArray.length - 2;
            ++hArray[hIndex];
        }

        for (int i = hArray.length - 2; i >= 0; --i) {
            hArray[i] += hArray[i + 1];
            if (i <= hArray[i])
                return i;
        }

        return 0;
    }

    public int hIndexSorted(int[] citations) {
        Arrays.sort(citations);
        int h = 0;
        for (int i = citations.length - 1; i >= 0; --i) {
            if (citations[i] >= citations.length - i) {
                h = citations.length - i;
            } else {
                break;
            }
        }
        return h;
    }

    public static void main(String[] args) {
        m274_HIndex solution = new m274_HIndex();

        System.out.println(solution.hIndex(new int[]{3, 0, 6, 1, 5}));

        System.out.println(solution.hIndexSorted(new int[]{3, 0, 6, 1, 5}));

        System.out.println(solution.hIndexSorted(new int[]{1}));
        System.out.println(solution.hIndexSorted(new int[]{0}));
        System.out.println(solution.hIndexSorted(new int[]{100}));
    }
}
