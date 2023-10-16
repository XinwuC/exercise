package medium;

import basic.TreeNode;
import basic.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 2476. Closest Nodes Queries in a Binary Search Tree
 * https://leetcode.com/problems/closest-nodes-queries-in-a-binary-search-tree/description/
 * 
 * You are given the root of a binary search tree and an array queries of size n
 * consisting of positive integers.
 * 
 * Find a 2D array answer of size n where answer[i] = [mini, maxi]:
 * 
 * mini is the largest value in the tree that is smaller than or equal to
 * queries[i]. If a such value does not exist, add -1 instead.
 * maxi is the smallest value in the tree that is greater than or equal to
 * queries[i]. If a such value does not exist, add -1 instead.
 * Return the array answer.
 * 
 * Example 1:
 * 
 * Input: root = [6,2,13,1,4,9,15,null,null,null,null,null,null,14], queries =
 * [2,5,16]
 * Output: [[2,2],[4,6],[15,-1]]
 * Explanation: We answer the queries in the following way:
 * - The largest number that is smaller or equal than 2 in the tree is 2, and
 * the smallest number that is greater or equal than 2 is still 2. So the answer
 * for the first query is [2,2].
 * - The largest number that is smaller or equal than 5 in the tree is 4, and
 * the smallest number that is greater or equal than 5 is 6. So the answer for
 * the second query is [4,6].
 * - The largest number that is smaller or equal than 16 in the tree is 15, and
 * the smallest number that is greater or equal than 16 does not exist. So the
 * answer for the third query is [15,-1].
 * 
 * Example 2:
 * 
 * Input: root = [4,null,9], queries = [3]
 * Output: [[-1,4]]
 * Explanation: The largest number that is smaller or equal to 3 in the tree
 * does not exist, and the smallest number that is greater or equal to 3 is 4.
 * So the answer for the query is [-1,4].
 * 
 * Constraints:
 * 
 * The number of nodes in the tree is in the range [2, 105].
 * 1 <= Node.val <= 106
 * n == queries.length
 * 1 <= n <= 105
 * 1 <= queries[i] <= 106
 */
public class m2467_ClosestNodesQueries {
    // private List<Integer> closestNodes(TreeNode root, int query, List<Integer>
    // segment) {
    // if (root == null)
    // return segment;

    // if (root.val == query) {
    // segment.set(0, root.val);
    // segment.set(1, root.val);
    // return segment;
    // }
    // if (query > root.val && root.val > segment.get(0))
    // segment.set(0, root.val);
    // if (query < root.val && (root.val < segment.get(1) || segment.get(1) == -1))
    // segment.set(1, root.val);
    // if (segment.get(0) == query && segment.get(1) == query)
    // return segment;

    // if (root.val > segment.get(0))
    // segment = closestNodes(root.left, query, segment);
    // if (root.val < segment.get(1) || segment.get(1) == -1)
    // segment = closestNodes(root.right, query, segment);
    // return segment;
    // }

    // public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries)
    // {
    // List<List<Integer>> result = new ArrayList<>();
    // for (Integer q : queries) {
    // result.add(closestNodes(root, q, new ArrayList<Integer>(List.of(-1, -1))));
    // }
    // return result;
    // }

    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tree = new ArrayList<>();
        inorder(root, tree);
        // binary search the first elem that > query
        for (int q : queries) {
            int s = 0, e = tree.size() - 1;
            while (s <= e) {
                int mid = (s + e) / 2;
                int a = tree.get(mid);
                if (a == q) {
                    result.add(List.of(a, a));
                    break;
                } else if (a < q) {
                    if (mid + 1 < tree.size()) {
                        if (q < tree.get(mid + 1)) {
                            result.add(List.of(a, tree.get(mid + 1)));
                            break;
                        }
                    } else {
                        result.add(List.of(a, -1));
                        break;
                    }
                    s = mid + 1;
                } else { // a > q
                    if (mid - 1 >= 0) {
                        if (q > tree.get(mid - 1)) {
                            result.add(List.of(tree.get(mid - 1), a));
                            break;
                        }
                    } else {
                        result.add(List.of(-1, a));
                        break;
                    }
                    e = mid - 1;
                }
            }
        }
        return result;
    }

    private static void test(TreeNode root, List<Integer> queries) {
        m2467_ClosestNodesQueries solution = new m2467_ClosestNodesQueries();
        System.out.println(Arrays.deepToString(Utils.twoDListToArray(solution.closestNodes(root, queries))));
    }

    public static void main(String[] args) {
        test(TreeNode.deserialize(new Integer[] { 6, 2, 13, 1, 4, 9, 15, null, null, null, null, null, null, 14 }),
                List.of(2, 5, 16));
        test(TreeNode.deserialize(new Integer[] { 4, null, 9 }), List.of(3));

    }
}
