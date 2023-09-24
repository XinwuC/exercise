package medium;

import basic.TreeNode;
import basic.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 113. Path Sum II
 * https://leetcode.com/problems/path-sum-ii/description/
 * Given the root of a binary tree and an integer targetSum, return all
 * root-to-leaf paths where the sum of the node values in the path equals
 * targetSum. Each path should be returned as a list of the node values, not
 * node references.
 * 
 * A root-to-leaf path is a path starting from the root and ending at any leaf
 * node. A leaf is a node with no children.
 * 
 * Example 1:
 * 
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: [[5,4,11,2],[5,8,4,5]]
 * Explanation: There are two paths whose sum equals targetSum:
 * 5 + 4 + 11 + 2 = 22
 * 5 + 8 + 4 + 5 = 22
 * 
 * Example 2:
 * 
 * Input: root = [1,2,3], targetSum = 5
 * Output: []
 * 
 * Example 3:
 * 
 * Input: root = [1,2], targetSum = 0
 * Output: []
 * 
 * 
 * Constraints:
 * 
 * The number of nodes in the tree is in the range [0, 5000].
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 * 
 */
public class m0113_PathSumII {
    private void dfsSum(TreeNode root, int targetSum, Stack<Integer> path, List<List<Integer>> paths) {
        if (root.left == null && root.right == null) {
            if (targetSum == root.val) {
                path.push(root.val);
                paths.add(new ArrayList<Integer>(path));
                path.pop();
            }
            return;
        }
        path.push(root.val);
        if (root.left != null)
            dfsSum(root.left, targetSum - root.val, path, paths);
        if (root.right != null)
            dfsSum(root.right, targetSum - root.val, path, paths);
        path.pop();
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            Stack<Integer> path = new Stack<>();
            dfsSum(root, targetSum, path, result);
        }
        return result;
    }

    private static void test(TreeNode root, int targetSum, int expects) {
        m0113_PathSumII solution = new m0113_PathSumII();
        List<List<Integer>> result = solution.pathSum(root, targetSum);
        System.out.format("%s: %d => %s\n", result.size() == expects, targetSum,
                Arrays.deepToString(Utils.twoDListToArray(result)));
    }

    public static void main(String[] args) {
        test(TreeNode.deserialize(new Integer[] { 5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1 }), 22, 2);
        test(TreeNode.deserialize(new Integer[] { 1, 2, 3 }), 5, 0);
        test(TreeNode.deserialize(new Integer[] { 1, 2 }), 0, 0);
    }
}
