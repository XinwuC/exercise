package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import basic.TreeNode;
import basic.Utils;

class BinaryTreeZigzagLevelOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        if (root == null)
            return ans;

        List<TreeNode> queue = new ArrayList<>();
        queue.add(root);
        queue.add(null);
        int index = 0;
        boolean left2right = true;
        // BFS
        while (index < queue.size() && queue.get(index) != null) {
            List<Integer> layer = new ArrayList<>();
            if (left2right) {
                for (TreeNode n = queue.get(index); n != null; n = queue.get(++index)) {
                    layer.add(n.val);
                    if (n.left != null)
                        queue.add(n.left);
                    if (n.right != null)
                        queue.add(n.right);
                }
                queue.add(null);
                ++index; // skip null
                left2right = false; // reverse direction
            } else {
                int layer_end = index;
                for (TreeNode n = queue.get(layer_end); n != null; n = queue.get(++layer_end)) {
                    if (n.left != null)
                        queue.add(n.left);
                    if (n.right != null)
                        queue.add(n.right);
                }
                queue.add(null);
                for (int j = layer_end - 1; j >= index; --j)
                    layer.add(queue.get(j).val);
                index = layer_end + 1;
                left2right = true;
            }
            ans.add(layer);
        }

        return ans;
    }

    private static void test(Integer[] nodes) {
        BinaryTreeZigzagLevelOrder solution = new BinaryTreeZigzagLevelOrder();
        System.out.print(Arrays.toString(nodes));
        List<List<Integer>> result = solution.zigzagLevelOrder(TreeNode.deserialize(nodes));
        System.out.format(" => %s\n", Arrays.deepToString(Utils.twoDListToArray(result)));
    }

    public static void main(String[] args) {
        test(new Integer[] {});
        test(new Integer[] { 1 });
        test(new Integer[] { 3, 9, 20, null, null, 15, 7 });
        test(new Integer[] { 1, 2, 3, 4, 5 });

    }

}
