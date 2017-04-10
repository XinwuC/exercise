"""
114. Flatten Binary Tree to Linked List

    Total Accepted: 118973
    Total Submissions: 347823
    Difficulty: Medium
    Contributor: LeetCode

Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6

The flattened tree should look like:

   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6

"""

from utility.TreeNode import TreeNode


class Solution(object):
    def flatten(self, root):
        """
        :type root: TreeNode
        :rtype: void Do not return anything, modify root in-place instead.
        """
        if root is None:
            return

        nodes = []
        self.pre_order_traverse(root, nodes)
        for index in range(len(nodes)):
            nodes[index].left = None
            nodes[index].right = None if index == len(nodes) - 1 else nodes[index + 1]
            index += 1

    def pre_order_traverse(self, root, nodes=[]):
        if root is None:
            return

        nodes.append(root)
        if root.left is not None:
            self.pre_order_traverse(root.left, nodes)
        if root.right is not None:
            self.pre_order_traverse(root.right, nodes)


def run_solution(nodes):
    solution = Solution()
    root = TreeNode.construct_tree(nodes)
    solution.flatten(root)
    TreeNode.print_tree(root)


if __name__ == '__main__':
    run_solution([])
    run_solution([1, 2, 5, 3, 4, None, 6])
    run_solution([1, 3, 2, 5, 3, None, 9, 10, None, 11, 12, None, 15])
