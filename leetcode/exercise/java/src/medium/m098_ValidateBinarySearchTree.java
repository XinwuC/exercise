package medium;

/**
 * 98. Validate Binary Search Tree
 * <p>
 * Total Accepted: 132475
 * Total Submissions: 594460
 * Difficulty: Medium
 * Contributors: Admin
 * <p>
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * <p>
 * Assume a BST is defined as follows:
 * <p>
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * <p>
 * Example 1:
 * <p>
 * 2
 * / \
 * 1   3
 * <p>
 * Binary tree [2,1,3], return true.
 * <p>
 * Example 2:
 * <p>
 * 1
 * / \
 * 2   3
 * <p>
 * Binary tree [1,2,3], return false.
 * <p>
 * <p>
 * Leetcode: https://leetcode.com/problems/validate-binary-search-tree/
 * <p>
 * Created by xinwu on 1/2/17.
 */
public class m098_ValidateBinarySearchTree {

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isValidBST(TreeNode root) {
        return null == root ? true : validate(root).isValid;
    }

    class SubTree {
        boolean isValid = false;
        TreeNode min;
        TreeNode max;

        public SubTree(TreeNode root) {
            min = root;
            max = root;
            isValid = false;
        }
    }

    // return null if not valid;
    // return the maximal value node if valid left tree
    // return the minimal value node if valid right tree
    private SubTree validate(TreeNode root) {
        SubTree result = new SubTree(root);

        if (null != root.left) {
            SubTree leftTree = validate(root.left);
            // left tree is not validate
            if (!leftTree.isValid) return result;
            // max value of left tree is bigger than left
            if (leftTree.max.val >= root.val) return result;
            result.min = leftTree.min;
        }

        if (null != root.right) {
            SubTree rightTree = validate(root.right);
            // left tree is not validate
            if (!rightTree.isValid) return result;
            // min value of right tree is bigger than left
            if (rightTree.min.val <= root.val) return result;
            result.max = rightTree.max;
        }

        result.isValid = true;

        return result;
    }
}
