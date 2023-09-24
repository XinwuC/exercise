package medium;

import basic.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 95. Unique Binary Search Trees II
 * https://leetcode.com/problems/unique-binary-search-trees-ii/description/
 * 
 * Given an integer n, return all the structurally unique BST's (binary search
 * trees), which has exactly n nodes of unique values from 1 to n. Return the
 * answer in any order.
 * 
 * Example 1:
 * 
 * Input: n = 3
 * Output:
 * [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
 * Example 2:
 * 
 * Input: n = 1
 * Output: [[1]]
 * 
 * Constraints: 1 <= n <= 8
 */
public class m0095_UnqiueBinarySearchTreeII {

    private List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> results = new ArrayList<>();
        if (start > end) {
            results.add(null);
            return results;
        }
        if (start == end) {
            results.add(new TreeNode(start));
            return results;
        }

        for (int i = start; i <= end; ++i) {
            // generate all left sub-trees
            List<TreeNode> lefts = generateTrees(start, i - 1);
            // generate all right sub-trees
            List<TreeNode> rights = generateTrees(i + 1, end);
            // generate all trees
            for (TreeNode l : lefts) {
                for (TreeNode r : rights) {
                    results.add(new TreeNode(i, l, r));
                }
            }
        }

        return results;
    }

    public List<TreeNode> generateTrees(int n) {
        return generateTrees(1, n);
    }

    private static void test(int n,int  expect) {
        m0095_UnqiueBinarySearchTreeII solution = new m0095_UnqiueBinarySearchTreeII();
        List<TreeNode> result = solution.generateTrees(n);
        System.out.format("%s, %d => %d vs %d\n", result.size() == expect, n, result.size(), expect);
    }

    public static void main(String[] args) {
        test(1, 1);
        test(3, 5);
    }
}
