package medium;

import basic.TreeNode;

public class m0687_LongestUnivaluePath {
    public int longestValuePath(TreeNode root, int val) {
        if (root == null || root.val != val)
            return 0;

        int left_path = 0, right_path = 0;
        if (root.left != null && root.left.val == val)
            left_path = 1 + longestValuePath(root.left, val);
        if (root.right != null && root.right.val == val)
            right_path = 1 + longestValuePath(root.right, val);

        return Math.max(left_path, right_path);
    }

    public int longestUnivaluePath(TreeNode root) {
        if (root == null)
            return 0;
        int self_path = 0;
        if (root.left != null && root.left.val == root.val)
            self_path += 1 + longestValuePath(root.left, root.val);
        if (root.right != null && root.right.val == root.val)
            self_path += 1 + longestValuePath(root.right, root.val);
        return Math.max(self_path,
                Math.max(longestUnivaluePath(root.left), longestUnivaluePath(root.right)));
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
