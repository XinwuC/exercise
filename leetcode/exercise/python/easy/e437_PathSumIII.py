"""
437. Path Sum III

    Total Accepted: 20441
    Total Submissions: 52428
    Difficulty: Easy
    Contributors: Stomach_ache

You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent
nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11

Leetcode: https://leetcode.com/problems/path-sum-iii
"""

import queue
import copy

# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

    @staticmethod
    def constructTree(nodes=[]):
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


class Solution(object):
    def pathSum(self, root, sum):
        """
        :type root: TreeNode
        :type sum: int
        :rtype: int
        """
        self.count = 0
        self.degrade_tree(root, sum, [])
        return self.count

    def degrade_tree(self, node, sum, sum_path):
        if node is None:
            return

        sum_path_new = copy.copy(sum_path)
        sum_path_new.append(sum)

        for i in range(len(sum_path_new)):
            sum_path_new[i] -= node.val
            if 0 == sum_path_new[i]:
                self.count += 1

        self.degrade_tree(node.left, sum, sum_path_new)
        self.degrade_tree(node.right, sum, sum_path_new)


if __name__ == '__main__':
    solution = Solution()
    print(solution.pathSum(TreeNode.constructTree([]), 0))
    print(solution.pathSum(TreeNode.constructTree([10, 5, -3, 3, 2, None, 11, 3, -2, None, 1]), 8))
