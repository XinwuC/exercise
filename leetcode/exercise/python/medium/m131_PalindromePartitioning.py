"""
131. Palindrome Partitioning

    Total Accepted: 89496
    Total Submissions: 282365
    Difficulty: Medium
    Contributors: Admin

Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

[
  ["aa","b"],
  ["a","a","b"]
]

Leetcode: https://leetcode.com/problems/palindrome-partitioning
"""


class Solution(object):
    def partition(self, s):
        """
        :type s: str
        :rtype: List[List[str]]
        """
        if not s:
            return [[]]

        n = len(s)
        is_palindrome = [[0 for x in range(n)] for y in range(n)]
        for j in range(n):
            for i in range(j + 1):
                if i == j:
                    is_palindrome[i][j] = True
                elif s[i] == s[j]:
                    if i + 1 > j - 1 or is_palindrome[i + 1][j - 1]:
                        is_palindrome[i][j] = True
                    else:
                        is_palindrome[i][j] = False
                else:
                    is_palindrome[i][j] = False

        palindromes = []
        for i in range(n):
            if is_palindrome[0][i]:
                for result in self.find_path(s, is_palindrome, 0, i, n):
                    palindromes.append(result)
        return palindromes

    def find_path(self, s, is_palindrome, i, j, n):
        result = []
        if not is_palindrome[i][j]:
            return result
        if j == n - 1:
            return [[s[i:j + 1]]]

        for k in range(j + 1, n):
            if is_palindrome[j + 1][k]:
                rest_result = self.find_path(s, is_palindrome, j + 1, k, n)
                for rr in rest_result:
                    rr.insert(0, s[i:j + 1])
                    result.append(rr)

        return result


if __name__ == '__main__':
    solution = Solution()

    print(solution.partition(""))
    print(solution.partition("a"))
    print(solution.partition("aa"))
    print(solution.partition("aab"))
    print(solution.partition("neveroddoreven"))
    print(solution.partition("mayamoodybabydoomayam"))
    print(solution.partition("docnoteidissentafastneverpreventsafatnessidietoncod"))
