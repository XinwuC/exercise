package basic;


import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    public static List<Integer> serialize(TreeNode root) {
        return null;
    }
}
