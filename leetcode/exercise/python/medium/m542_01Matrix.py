"""
 542. 01 Matrix
DescriptionHintsSubmissionsSolutions

    Total Accepted: 5116
    Total Submissions: 15716
    Difficulty: Medium
    Contributors: Stomach_ache

Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
The distance between two adjacent cells is 1.

Example 1:
Input:

0 0 0
0 1 0
0 0 0

Output:

0 0 0
0 1 0
0 0 0

Example 2:
Input:

0 0 0
0 1 0
1 1 1

Output:

0 0 0
0 1 0
1 2 1

Note:

    The number of elements of the given matrix will not exceed 10,000.
    There are at least one 0 in the given matrix.
    The cells are adjacent in only four directions: up, down, left and right.

Leetcode: https://leetcode.com/problems/01-matrix/#/description
"""

import sys


class Solution(object):
    def updateMatrix(self, matrix):
        """
        :type matrix: List[List[int]]
        :rtype: List[List[int]]
        """
        row_count = len(matrix)
        col_count = len(matrix[0])

        distance = [[-1 for i in range(col_count)] for j in range(row_count)]
        count = 0
        row = 0
        col = 0
        while count < row_count * col_count:
            if distance[row][col] == -1:
                if matrix[row][col] == 0:
                    distance[row][col] = 0
                    count += 1 + self.propagate(matrix, distance, row, col)
                else:
                    count += self.calculate_distance(matrix, distance, row, col)
            # move to next cell
            if col + 1 < col_count:
                col += 1
            elif row + 1 < row_count:
                row += 1
                col = 0
            else:
                row = 0
                col = 0

        return distance

    def propagate(self, matrix, distance, row, col):
        count = 0
        row_count = len(matrix)
        col_count = len(matrix[0])
        if col + 1 < col_count and distance[row][col + 1] == -1:
            count += self.calculate_distance(matrix, distance, row, col + 1)
        if col - 1 >= 0 and distance[row][col - 1] == -1:
            count += self.calculate_distance(matrix, distance, row, col - 1)
        if row + 1 < row_count and distance[row + 1][col] == -1:
            count += self.calculate_distance(matrix, distance, row + 1, col)
        if row - 1 >= 0 and distance[row - 1][col] == -1:
            count += self.calculate_distance(matrix, distance, row - 1, col)
        return count

    def calculate_distance(self, matrix, distance, row, col):
        if row < 0 or row >= len(matrix) or col < 0 or col >= len(matrix[0]):
            return 0

        if matrix[row][col] == 0:
            distance[row][col] = 0
            return 1 + self.propagate(matrix, distance, row, col)

        left = 1 if col - 1 < 0 else matrix[row][col - 1]
        right = 1 if row + 1 >= len(matrix) else matrix[row + 1][col]
        up = 1 if row - 1 < 0 else matrix[row - 1][col]
        down = 1 if col + 1 >= len(matrix[0]) else matrix[row][col + 1]
        left_distance = sys.maxsize if col - 1 < 0 else distance[row][col - 1]
        right_distance = sys.maxsize if row + 1 >= len(matrix) else distance[row + 1][col]
        up_distance = sys.maxsize if row - 1 < 0 else distance[row - 1][col]
        down_distance = sys.maxsize if col + 1 >= len(matrix[0]) else distance[row][col + 1]

        if left == 0 or right == 0 or up == 0 or down == 0:
            distance[row][col] = 1
            return 1 + self.propagate(matrix, distance, row, col)

        if left == right == up == down == 1 and (
                        left_distance == 1 or right_distance == 1 or up_distance == 1 or down_distance == 1):
            distance[row][col] = 2
            return 1 + self.propagate(matrix, distance, row, col)

        if left_distance != -1 and right_distance != -1 and up_distance != -1 and down_distance != -1:
            distance[row][col] = 1 + min(left, right, up, down)
            return 1 + self.propagate(matrix, distance, row, col)

        return 0


if __name__ == '__main__':
    solution = Solution()
    print(solution.updateMatrix([
        [0, 0, 0],
        [0, 1, 0],
        [0, 0, 0]]))

    print(solution.updateMatrix([
        [0, 0, 0],
        [0, 1, 0],
        [1, 1, 1]]))

    print(solution.updateMatrix([
        [1, 1, 1],
        [1, 1, 1],
        [1, 1, 0]]))

    print(solution.updateMatrix([[1, 0, 1, 1, 0, 0, 1, 0, 0, 1],
                                 [0, 1, 1, 0, 1, 0, 1, 0, 1, 1],
                                 [0, 0, 1, 0, 1, 0, 0, 1, 0, 0],
                                 [1, 0, 1, 0, 1, 1, 1, 1, 1, 1],
                                 [0, 1, 0, 1, 1, 0, 0, 0, 0, 1],
                                 [0, 0, 1, 0, 1, 1, 1, 0, 1, 0],
                                 [0, 1, 0, 1, 0, 1, 0, 0, 1, 1],
                                 [1, 0, 0, 0, 1, 1, 1, 1, 0, 1],
                                 [1, 1, 1, 1, 1, 1, 1, 0, 1, 0],
                                 [1, 1, 1, 1, 0, 1, 0, 0, 1, 1]]))
