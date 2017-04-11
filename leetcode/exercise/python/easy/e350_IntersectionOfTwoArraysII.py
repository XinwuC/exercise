"""
 350. Intersection of Two Arrays II
DescriptionSubmissionsSolutions

    Total Accepted: 58974
    Total Submissions: 133518
    Difficulty: Easy
    Contributor: LeetCode

Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

Note:

    Each element in the result should appear as many times as it shows in both arrays.
    The result can be in any order.

Follow up:

    What if the given array is already sorted? How would you optimize your algorithm?
    What if nums1's size is small compared to nums2's size? Which algorithm is better?
    What if elements of nums2 are stored on disk, and the memory is limited such that you 
    cannot load all elements into the memory at once?

Leetcode: https://leetcode.com/problems/intersection-of-two-arrays-ii
"""


class Solution(object):
    def intersect(self, nums1, nums2):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :rtype: List[int]
        """
        if len(nums1) == 0 or len(nums2) == 0:
            return []

        nums1.sort()
        nums2.sort()
        index1 = 0
        index2 = 0
        result = []
        while index1 < len(nums1) and index2 < len(nums2):
            if nums1[index1] == nums2[index2]:
                result.append(nums1[index1])
                index1 += 1
                index2 += 1
            elif nums1[index1] < nums2[index2]:
                index1 += 1
            else:
                index2 += 1

        return result


if __name__ == '__main__':
    solution = Solution()
    print(solution.intersect([], []))
    print(solution.intersect([], [2, 2]))
    print(solution.intersect([1, 2, 2, 1], []))
    print(solution.intersect([1, 2, 2, 1], [2, 2]))
