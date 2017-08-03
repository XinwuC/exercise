"""
 154. Find Minimum in Rotated Sorted Array II
DescriptionHintsSubmissionsSolutions

    Total Accepted: 74930
    Total Submissions: 204276
    Difficulty: Hard
    Contributor: LeetCode

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

The array may contain duplicates.

https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii
"""

import random


class Solution(object):
    def findMin(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if len(nums) == 1:
            return nums[0]

        left = 0
        right = len(nums) - 1

        while left < right:
            if nums[left] < nums[right]:
                return nums[left]
            if nums[left] == nums[right]:
                left += 1
            else:
                mid = (left + right) // 2
                while nums[left] <= nums[mid] and left != mid:
                    left = mid
                    mid = (left + right) // 2
                if left == mid:
                    return nums[right]
                if nums[right] >= nums[mid]:
                    right = mid

        return nums[right]


def random_list(value_range=20, duplicate_factor=3):
    result = [0]

    for val in range(value_range):
        duplicates = random.randrange(duplicate_factor)
        result += [val] * duplicates

    pivot = random.randrange(len(result) - 1)
    return result[pivot:len(result) - 1] + result[0:pivot]


if __name__ == '__main__':
    solution = Solution()

    print(solution.findMin([0]))
    print(solution.findMin([0, 0]))
    print(solution.findMin([1, 0]))
    print(solution.findMin([3, 1, 3]))
    print(solution.findMin([1, 2, 1]))
    print(solution.findMin([10, 1, 10, 10, 10]))
    for loop in range(5):
        nums = random_list()
        nums.append(nums[0])
        print(nums)
        print(solution.findMin(nums))
