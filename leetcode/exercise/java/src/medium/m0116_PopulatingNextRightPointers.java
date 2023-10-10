package medium;

import java.util.ArrayDeque;
import java.util.Queue;

import basic.TreeNode;

/**
 * m0116_PopulatingNextRightPointers
 */
public class m0116_PopulatingNextRightPointers {
    public TreeNode connect(TreeNode root) {
        if (root == null)
            return root;

        Queue<TreeNode> queue = new ArrayDeque<>();
        TreeNode dummy = new TreeNode();
        queue.add(root);
        queue.add(dummy);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            if (node != dummy) {
                if (queue.peek() != dummy)
                    node.next = queue.peek();
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            } else if (queue.size() > 1)
                queue.add(dummy);
        }
        return root;
    }

    public static void main(String[] args) {
        m0116_PopulatingNextRightPointers solution = new m0116_PopulatingNextRightPointers();
        solution.connect(TreeNode.deserialize(new Integer[] { 1, 2, 3, 4, 5, 6, 7 }));
    }

}