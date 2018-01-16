package easy;

/**
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 * <p>
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has
 * both v and w as descendants (where we allow a node to be a descendant of itself).”
 * <p>
 * _______6______
 * /              \
 * ___2__          ___8__
 * /      \        /      \
 * 0      _4       7       9
 * /  \
 * 3   5
 * For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another example is LCA of nodes 2 and 4 is 2, since a node can be a descendant
 * of itself according to the LCA definition.
 */
public class e235_LowestCommonAncestor {
    /*
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p == q) return p;

        System.out.println(p.val +' ' + q.val);


        TreeNode current = root;
        TreeNode left = p.val < q.val ? p: q;
        TreeNode right = p.val > q.val ? p: q;

        System.out.println(left.val +' ' + right.val);
        while (current != null) {
            if (left.val <= current.val && right.val >= current.val)
                return current;
            else if (left.val <= current.val)
                current = current.left;
            else
                current = current.right;
        }

        return current;
    }
    */
}
