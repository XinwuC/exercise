package basic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.naming.spi.DirStateFactory.Result;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    public static TreeNode deserialize(Integer[] nodes) {

        if (null == nodes || 0 == nodes.length)
            return null;

        TreeNode root = new TreeNode(nodes[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        for (int index = 0; index < nodes.length; index += 2) {
            TreeNode current = queue.poll();
            if (index + 1 < nodes.length) {
                Integer val = nodes[index + 1];
                if (null != val) {
                    current.left = new TreeNode(val);
                    queue.add(current.left);
                }
            }
            if (index + 2 < nodes.length) {
                Integer val = nodes[index + 2];
                if (null != val) {
                    current.right = new TreeNode(val);
                    queue.add(current.right);
                }
            }
        }

        return root;
    }

    /**
     * serialize a tree with layers
     * 
     * @param root
     * @return
     */
    public static List<Integer> serialize(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            result.add(null);
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        result.add(root.val);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            if (node.left == null)
                result.add(null);
            else {
                result.add(node.left.val);
                queue.add(node.left);
            }
            if (node.right == null)
                result.add(null);
            else {
                result.add(node.right.val);
                queue.add(node.right);
            }

        }
        for (int i = result.size() - 1; i > 0 && result.get(i) == null; --i) {
            result.remove(i);
        }
        return result;
    }
}
