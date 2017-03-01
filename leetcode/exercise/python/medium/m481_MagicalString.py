'''
 481. Magical String
Description Submission Solutions

    Total Accepted: 4905
    Total Submissions: 10644
    Difficulty: Medium
    Contributors: DonaldTrump

A magical string S consists of only '1' and '2' and obeys the following rules:

The string S is magical because concatenating the number of contiguous occurrences of characters '1' and '2' generates the string S itself.

The first few elements of string S is the following: S = "1221121221221121122……"

If we group the consecutive '1's and '2's in S, it will be:

1 22 11 2 1 22 1 22 11 2 11 22 ......

and the occurrences of '1's or '2's in each group are:

1 2 2 1 1 2 1 2 2 1 2 2 ......

You can see that the occurrence sequence above is the S itself.

Given an integer N as input, return the number of '1's in the first N number in the magical string S.

Note: N will not exceed 100,000.

Example 1:

Input: 6
Output: 3
Explanation: The first 6 elements of magical string S is "12211" and it contains three 1's, so return 3.
'''


class Solution(object):
    def magicalString(self, n):
        """
        :type n: int
        :rtype: int
        """
        if n <= 3:
            return 1

        magicalString = "122"
        ret = 1
        index = 2
        char = "1"
        length = len(magicalString)
        while length < n:
            length_to_add = int(magicalString[index])
            if length + length_to_add > n:
                length_to_add = n - length
            magicalString += char * length_to_add
            length += length_to_add
            if "1" == char:
                char = "2"
                ret += length_to_add
            else:
                char = "1"
            index += 1

        if length != len(magicalString):
            raise "length calculation is wrong: " + len(magicalString) + " " + str(length)

        # print(magicalString)
        return " ".join((str(n), magicalString, str(ret)))
        # return ret

if __name__ == "__main__":
    solution = Solution()
    print(solution.magicalString(1))
    print(solution.magicalString(2))
    print(solution.magicalString(3))
    print(solution.magicalString(4))
    print(solution.magicalString(6))
    print(solution.magicalString(7))
    print(solution.magicalString(10))
    print(solution.magicalString(24))
    print(solution.magicalString(30))
