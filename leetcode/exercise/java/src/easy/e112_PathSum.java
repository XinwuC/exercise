package easy;

/**
 * 112. Path Sum
 * 
 * Total Accepted: 112988 Total Submissions: 354746 Difficulty: Easy
 * 
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path
 * such that adding up all the values along the path equals the given sum.
 * 
 * For example: Given the below binary tree and sum = 22,
 * 
 * <code>
 *             5
 *            / \
 *           4   8
 *          /   / \
 *         11  13  4
 *        /  \      \
 *       7    2      1
 * </code>
 * 
 * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 * 
 * Leetcode: https://leetcode.com/problems/path-sum/
 * 
 * @author xinwu
 *
 */
public class e112_PathSum {
	// Definition for a binary tree node.
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public boolean hasPathSum(TreeNode root, int sum) {
		if (null == root)
			return false;

		if (null == root.left && null == root.right)
			return sum == root.val;

		return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
