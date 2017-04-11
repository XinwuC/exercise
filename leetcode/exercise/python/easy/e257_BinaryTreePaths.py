"""
 257. Binary Tree Paths
DescriptionSubmissionsSolutions

    Total Accepted: 100525
    Total Submissions: 273909
    Difficulty: Easy
    Contributor: LeetCode

Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5

All root-to-leaf paths are:

["1->2->5", "1->3"]

Leetcode: https://leetcode.com/problems/binary-tree-paths
"""

from utility.TreeNode import TreeNode


class Solution(object):
    def binaryTreePaths(self, root):
        """
        :type root: TreeNode
        :rtype: List[str]
        """
        if root is None:
            return []

        left_paths = self.binaryTreePaths(root.left)
        right_paths = self.binaryTreePaths(root.right)

        paths = []
        for path in left_paths:
            paths.append('%s->%s' % (root.val, path))
        for path in right_paths:
            paths.append('%s->%s' % (root.val, path))
        if len(paths) == 0:
            paths.append('%s' % root.val)

        return paths


if __name__ == '__main__':
    solution = Solution()
    print(solution.binaryTreePaths(TreeNode.construct_tree([])))
    print(solution.binaryTreePaths(TreeNode.construct_tree([1])))
    print(solution.binaryTreePaths(TreeNode.construct_tree([1, 2, 3, None, 5])))
    print(solution.binaryTreePaths(TreeNode.construct_tree([1, 3, 2, 5, 3, None, 9, 10, None, 11, 12, None, 15])))
