'''
 508. Most Frequent Subtree Sum
DescriptionSubmissionsSolutions

    Total Accepted: 6910
    Total Submissions: 13351
    Difficulty: Medium
    Contributors: Cyber233

Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself). So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.

Examples 1
Input:

  5
 /  \
2   -3

return [2, -3, 4], since all the values happen only once, return all of them in any order.

Examples 2
Input:

  5
 /  \
2   -5

return [2], since 2 happens twice, however -5 only occur once.

Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.

Leetcode: https://leetcode.com/problems/most-frequent-subtree-sum/#/description
'''

# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    def findFrequentTreeSum(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        if root is None:
            return []

        sum_table = {}
        self._fillTreeSum(root, sum_table)

        # find max arrays
        max_sum_count = 0
        result = []
        for sum, count in sum_table.items():
            if count > max_sum_count:
                result.clear()
                result.append(sum)
                max_sum_count = count
            elif count == max_sum_count:
                result.append(sum)

        return result

    def _fillTreeSum(self, root = None, sum_table = {}):
        """
        fill the sum for each node and update sumTable with sum count

        :param sumTable:
        :param root:
        :return: sum of root node
        """
        if root is None:
            return 0

        left_sub_tree_sum = 0
        right_sub_tree_Sum = 0
        if root.left is not None:
            left_sub_tree_sum = self._fillTreeSum(root.left, sum_table)
        if root.right is not None:
            right_sub_tree_Sum = self._fillTreeSum(root.right, sum_table)

        root_sum = root.val + left_sub_tree_sum + right_sub_tree_Sum
        sum_table[root_sum] = sum_table.get(root_sum, 0) + 1
        return root_sum

