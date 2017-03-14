'''
 384. Shuffle an Array
Description Submission Solutions

    Total Accepted: 19865
    Total Submissions: 43799
    Difficulty: Medium
    Contributors: Admin

Shuffle a set of numbers without duplicates.

Example:

// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
solution.reset();

// Returns the random shuffling of array [1,2,3].
solution.shuffle();

Leetcode: https://leetcode.com/problems/shuffle-an-array/
'''

import copy as copy
import random as random


class Solution(object):
    '''
    This is actually a permutation problem. We can refer more on wiki:
    https://en.wikipedia.org/wiki/Permutation#Algorithms_to_generate_permutations

    More specifically, this solution is using Fisher-Yates_shuffle algorithm:
    https://en.wikipedia.org/wiki/Fisher-Yates_shuffle
    '''

    def __init__(self, nums):
        """
        :type nums: List[int]
        """
        self.nums = nums

    def reset(self):
        """
        Resets the array to its original configuration and return it.
        :rtype: List[int]
        """
        return self.nums

    def shuffle(self):
        """
        Returns a random shuffling of the array.
        :rtype: List[int]
        """
        result = copy.copy(self.nums)
        length = len(self.nums)
        for i in range(length - 1):
            j = random.randint(i, length - 1)
            if i != j:
                result[i] = result[i] ^ result[j]
                result[j] = result[i] ^ result[j]
                result[i] = result[i] ^ result[j]
        return result


if __name__ == "__main__":
    solution = Solution([1, 2, 3])

    print(solution.reset())
    print(solution.shuffle())
    print(solution.shuffle())
    print(solution.shuffle())
    print(solution.shuffle())
    print(solution.shuffle())
    print(solution.shuffle())
    print(solution.reset())
