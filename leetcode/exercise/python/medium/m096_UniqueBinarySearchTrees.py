'''
 96. Unique Binary Search Trees
Description Submission Solutions

    Total Accepted: 111775
    Total Submissions: 278734
    Difficulty: Medium
    Contributors: Admin

Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3


Leetcode: https://leetcode.com/problems/unique-binary-search-trees/?tab=Description
'''


class Solution(object):
    def numTrees(self, n):
        """
        :type n: int
        :rtype: int
        """
        if n <= 0:
            return 0
        result = [0 for x in range(n + 1)]
        result[0] = 0
        result[1] = 1
        for i in range(2, n+1):
            count = result[i-1]
            for j in range(2, i+1):
                if i - j <= 0:
                    count += result[j - 1]
                else:
                    count += result[j - 1] * result[i - j]
            result[i] = count

        return result[n]


if __name__ == "__main__":
    solution = Solution()

    print(solution.numTrees(2))
    print(solution.numTrees(3))
    print(solution.numTrees(4))
    print(solution.numTrees(5))
