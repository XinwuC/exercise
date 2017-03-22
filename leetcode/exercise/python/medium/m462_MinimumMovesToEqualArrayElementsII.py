"""
 462. Minimum Moves to Equal Array Elements II

    Total Accepted: 11304
    Total Submissions: 22085
    Difficulty: Medium
    Contributors: andrew56

Given a non-empty integer array, find the minimum number of moves required to make all array elements equal,
where a move is incrementing a selected element by 1 or decrementing a selected element by 1.

You may assume the array's length is at most 10,000.

Example:

Input:
[1,2,3]

Output:
2

Explanation:
Only two moves are needed (remember each move increments or decrements one element):

[1,2,3]  =>  [2,2,3]  =>  [2,2,2]

LeetCode: https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii
"""


class Solution(object):
    def minMoves2(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        # if len(nums) == 0:
        #    return 0
        '''
        simply find the median number as the equal target
        '''
        nums.sort()
        median = nums[len(nums) // 2]

        moves = 0
        for x in nums:
            moves += abs(x - median)

        return moves


if __name__ == '__main__':
    solution = Solution()
    # print("0\t" + solution.minMoves2([]))
    print("2\t" + str(solution.minMoves2([1, 2, 3])))
    print("4\t" + str(solution.minMoves2([1, 2, 3, 4])))
    print("14\t" + str(solution.minMoves2([1, 0, 0, 8, 6])))
