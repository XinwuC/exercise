"""
 119. Pascal's Triangle II
DescriptionSubmissionsSolutions

    Total Accepted: 108770
    Total Submissions: 303610
    Difficulty: Easy
    Contributor: LeetCode

Given an index k, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space? 

Leetcode: https://leetcode.com/problems/pascals-triangle-ii
"""


class Solution(object):
    def getRow(self, rowIndex):
        """
        :type rowIndex: int
        :rtype: List[int]
        """
        if rowIndex < 1:
            return [1]
        if rowIndex == 1:
            return [1, 1]

        result = [1, 2, 1]
        for i in range(3, rowIndex + 1):
            result.insert(1, result[0] + result[1])
            for j in range(2, len(result) - 1):
                result[j] = result[j] + result[j + 1]
        return result


if __name__ == '__main__':
    solution = Solution()
    for test in range(10):
        print(solution.getRow(test))
