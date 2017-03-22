"""
 60. Permutation Sequence

    Total Accepted: 76979
    Total Submissions: 279405
    Difficulty: Medium
    Contributors: Admin

The set [1,2,3,…,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

    "123"
    "132"
    "213"
    "231"
    "312"
    "321"

Given n and k, return the kth permutation sequence.

Note: Given n will be between 1 and 9 inclusive.

Leetcode: https://leetcode.com/problems/permutation-sequence
"""
import math


class Solution(object):
    def getPermutation(self, n, k):
        """
        :type n: int
        :type k: int
        :rtype: str
        """
        nums = []
        for x in range(n):
            nums.append(x + 1)

        return self.get_permutation(nums, k)

    def get_permutation(self, nums=[], k=0):
        if not nums:
            return ""

        n = len(nums)
        factorial_n_1 = math.factorial(n - 1)
        index = math.ceil(k / factorial_n_1) - 1
        a = nums[index]
        del nums[index]
        return str(a) + self.get_permutation(nums, k - index * factorial_n_1)


if __name__ == '__main__':
    solution = Solution()

    print(solution.getPermutation(0, 0))
    print(solution.getPermutation(1, 1))
    for x in range(math.factorial(3)):
        print(x + 1, solution.getPermutation(3, x + 1))
