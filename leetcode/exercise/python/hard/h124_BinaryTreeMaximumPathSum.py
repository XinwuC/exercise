'''
 124. Binary Tree Maximum Path Sum
DescriptionSubmissionsSolutions

    Total Accepted: 89226
    Total Submissions: 352048
    Difficulty: Hard
    Contributors: Admin

Given a binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

For example:
Given the below binary tree,

       1
      / \
     2   3

Return 6.

Leetcode: https://leetcode.com/problems/binary-tree-maximum-path-sum/
'''

import queue


# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None
        # self.max = x # max sum that follows path from current node to either left or right sub trees
        # self.left_path_max = x # max sum that follows path from current node to left sub trees
        # self.left_path_max = x # max sum that follows path from current node to right sub trees


class Solution(object):
    def maxPathSum(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        if root is None:
            return 0

        self.global_max = root.val
        self._fill_tree_max(root)
        return self.global_max

    def _fill_tree_max(self, root):
        if root is None:
            return 0

        root.connectable_max = root.val
        max_sum = root.val

        if root.left is None:
            root.left_path_max = root.val
        else:
            self._fill_tree_max(root.left)
            root.left_path_max = root.left.connectable_max + root.val
            if root.left_path_max > root.connectable_max:
                root.connectable_max = root.left_path_max
            max_sum += root.left.connectable_max

        if root.right is None:
            root.right_path_max = root.val
        else:
            self._fill_tree_max(root.right)
            root.right_path_max = root.right.connectable_max + root.val
            if root.right_path_max > root.connectable_max:
                root.connectable_max = root.right_path_max
            max_sum += root.right.connectable_max

        if max_sum >= root.connectable_max:
            root.max = max_sum
        else:
            root.max = root.connectable_max

        if root.max > self.global_max:
            self.global_max = root.max
        if root.left is not None and root.left.max > self.global_max:
            self.global_max = root.left.max
        if root.right is not None and root.right.max > self.global_max:
            self.global_max = root.right.max

    def constructTree(self, nodes=[]):
        if not nodes:
            return None

        root = TreeNode(nodes[0])
        q = queue.Queue()
        q.put(root)
        index = 1
        length = len(nodes)
        while q.not_empty:
            current = q.get()
            if index < length:
                if nodes[index] is not None:
                    current.left = TreeNode(nodes[index])
                    q.put(current.left)
                index += 1
                if index < length:
                    if nodes[index] is not None:
                        current.right = TreeNode(nodes[index])
                        q.put(current.right)
                    index += 1
                else:
                    break
            else:
                break

        return root


if __name__ == '__main__':
    solution = Solution()

    print(solution.maxPathSum(solution.constructTree([])))
    print(solution.maxPathSum(solution.constructTree([1, 2, 3])))
    print(solution.maxPathSum(solution.constructTree([-1, -2, -3])))
    print(solution.maxPathSum(solution.constructTree([5, 4, 8, 11, None, 13, 4, 7, 2, None, None, None, 1])))
    print(solution.maxPathSum(solution.constructTree([])))
