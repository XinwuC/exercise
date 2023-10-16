package medium;

import basic.TreeNode;

/**
 * 687. Longest Univalue Path
 * https://leetcode.com/problems/longest-univalue-path/description/
 * 
 * Given the root of a binary tree, return the length of the longest path, where
 * each node in the path has the same value. This path may or may not pass
 * through the root.
 * 
 * The length of the path between two nodes is represented by the number of
 * edges between them.
 * 
 * Example 1:
 * 
 * Input: root = [5,4,5,1,1,null,5]
 * Output: 2
 * Explanation: The shown image shows that the longest path of the same value
 * (i.e. 5).
 *
 * Example 2:
 * 
 * Input: root = [1,4,5,4,4,null,5]
 * Output: 2
 * Explanation: The shown image shows that the longest path of the same value
 * (i.e. 4).
 * 
 * Constraints:
 * 
 * The number of nodes in the tree is in the range [0, 104].
 * -1000 <= Node.val <= 1000
 * The depth of the tree will not exceed 1000.
 */
public class m0687_LongestUnivaluePath {
    int max = 0;

    public int longestValuePath(TreeNode root, int parent_val) {
        if (root == null)
            return 0;

        int left_path = longestValuePath(root.left, root.val);
        int right_path = longestValuePath(root.right, root.val);

        max = Math.max(max, left_path + right_path);

        return root.val == parent_val ? 1 + Math.max(left_path, right_path) : 0;
    }

    public int longestUnivaluePath(TreeNode root) {
        if (root == null)
            return 0;
        max = 0;
        longestValuePath(root, 2000);
        return max;
    }

    public static void main(String[] args) {
        m0687_LongestUnivaluePath solution = new m0687_LongestUnivaluePath();

        System.out.println(solution.longestUnivaluePath(TreeNode.deserialize(new Integer[] {})));
        System.out.println(
                solution.longestUnivaluePath(TreeNode.deserialize(new Integer[] { 5, 4, 5, 1, 1, null, 5 })));
        System.out.println(
                solution.longestUnivaluePath(TreeNode.deserialize(new Integer[] { 1, 4, 5, 4, 4, null, 5 })));

    }
}
