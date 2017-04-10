"""
 210. Course Schedule II

    Total Accepted: 55070
    Total Submissions: 207871
    Difficulty: Medium
    Contributors: Admin

There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed 
as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to 
finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, 
return an empty array.

For example:

2, [[1,0]]

There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course 
order is [0,1]

4, [[1,0],[2,0],[3,1],[3,2]]

There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 
1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering 
is[0,2,1,3].

Note:

    The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a 
    graph is represented.
    You may assume that there are no duplicate edges in the input prerequisites.

LeetCode: https://leetcode.com/problems/course-schedule-ii
"""


class Solution(object):
    def findOrder(self, numCourses, prerequisites):
        """
        :type numCourses: int
        :type prerequisites: List[List[int]]
        :rtype: List[int]
        """
        if not prerequisites:
            return [i for i in range(numCourses)]

        class Course(object):
            def __init__(self, course):
                self.course = course
                self.prerequisites = 0
                self.following_courses = []

        courses = {}
        ready_courses = []
        for course_num in range(numCourses):
            courses[course_num] = Course(course_num)
            ready_courses.append(courses[course_num])

        for [course_num, prerequisite_num] in prerequisites:
            courses[course_num].prerequisites += 1
            courses[prerequisite_num].following_courses.append(courses[course_num])
            if courses[course_num].prerequisites == 1:
                ready_courses.remove(courses[course_num])

        result = []
        while ready_courses:
            course = ready_courses.pop(0)
            result.append(course.course)
            for following_course in course.following_courses:
                following_course.prerequisites -= 1
                if following_course.prerequisites == 0:
                    ready_courses.append(following_course)

        if len(result) == numCourses:
            return result
        else:
            return []


if __name__ == '__main__':
    solution = Solution()

    print(solution.findOrder(0, []))
    print(solution.findOrder(1, []))
    print(solution.findOrder(1, [[0, 0]]))
    print(solution.findOrder(2, [[1, 0]]))
    print(solution.findOrder(4, [[1, 0], [2, 0], [3, 1], [3, 2]]))
    print(solution.findOrder(4, [[1, 0], [2, 0], [3, 1], [3, 2], [2, 3]]))
