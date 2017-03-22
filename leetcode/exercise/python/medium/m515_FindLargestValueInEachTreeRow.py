"""
 515. Find Largest Value in Each Tree Row
DescriptionSubmissionsSolutions

    Total Accepted: 8641
    Total Submissions: 16258
    Difficulty: Medium
    Contributors: µsic_forever

You need to find the largest value in each row of a binary tree.

Example:

Input:

          1
         / \
        3   2
       / \   \
      5   3   9

Output: [1, 3, 9]

LeetCode: https://leetcode.com/problems/find-largest-value-in-each-tree-row
"""

from utility.TreeNode import TreeNode


# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def largestValues(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        if root is None:
            return []

        largest_values_list = [root.val]
        self.largest_values(root.left, 1, largest_values_list)
        self.largest_values(root.right, 1, largest_values_list)

        return largest_values_list

    def largest_values(self, node, level=0, largest_values_list=[]):
        if node is None:
            return

        length = len(largest_values_list)
        if length <= level:
            largest_values_list.append(node.val)
        elif node.val > largest_values_list[level]:
            largest_values_list[level] = node.val

        self.largest_values(node.left, level + 1, largest_values_list)
        self.largest_values(node.right, level + 1, largest_values_list)


if __name__ == '__main__':
    solution = Solution()

    print(solution.largestValues(TreeNode.construct_tree([])))
    print(solution.largestValues(TreeNode.construct_tree([1, 3, 2, 5, 3, None, 9])))
