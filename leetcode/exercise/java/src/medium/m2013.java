package medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 2013. Detect Squares: https://leetcode.com/problems/detect-squares/
 * 
 * You are given a stream of points on the X-Y plane. Design an algorithm that:
 * 
 * Adds new points from the stream into a data structure. Duplicate points are
 * allowed and should be treated as different points.
 * Given a query point, counts the number of ways to choose three points from
 * the data structure such that the three points and the query point form an
 * axis-aligned square with positive area.
 * An axis-aligned square is a square whose edges are all the same length and
 * are either parallel or perpendicular to the x-axis and y-axis.
 * 
 * Implement the DetectSquares class:
 * 
 * DetectSquares() Initializes the object with an empty data structure.
 * void add(int[] point) Adds a new point point = [x, y] to the data structure.
 * int count(int[] point) Counts the number of ways to form axis-aligned squares
 * with point point = [x, y] as described above.
 * 
 */
public class m2013 {
    static class DetectSquares {
        // index of points
        // <x, y, count>
        // x: x coordinator
        // y: y coordinator
        // count: # of points on <x, y>
        HashMap<Integer, HashMap<Integer, Integer>> pointMap = new HashMap<>();

        public DetectSquares() {

        }

        public void add(int[] point) {
            HashMap<Integer, Integer> y_coordindator = pointMap.get(point[0]);
            if (y_coordindator != null) {
                Integer count = y_coordindator.get(point[1]);
                y_coordindator.put(point[1], count == null ? 1 : count + 1);
            } else {
                y_coordindator = new HashMap<>();
                y_coordindator.put(point[1], 1);
                pointMap.put(point[0], y_coordindator);
            }
        }

        public int count(int[] point) {
            HashMap<Integer, Integer> y_maps = pointMap.get(point[0]);
            if (y_maps == null)
                return 0;

            int count = 0;
            for (Map.Entry<Integer, Integer> y : y_maps.entrySet()) {
                int distance = point[1] - y.getKey();
                if (distance == 0)
                    continue;
                // find the other two points on the left bottom
                count += count_squares(y.getValue(), new int[] { point[0] - distance, point[1] },
                        new int[] { point[0] - distance, y.getKey() })
                        + count_squares(y.getValue(), new int[] { point[0] + distance, point[1] },
                                new int[] { point[0] + distance, y.getKey() });
            }
            return count;
        }

        private int count_squares(int base, int[] point1, int[] point2) {
            return base * check(point1) * check(point2);
        }

        private int check(int[] point) {
            HashMap<Integer, Integer> y_coordindator = pointMap.get(point[0]);
            if (y_coordindator != null) {
                return y_coordindator.getOrDefault(point[1], 0);
            } else {
                return 0;
            }
        }
    }

    static void test(String[] ops, int[][] points, Integer[] expected) {
        DetectSquares ds = new DetectSquares();
        for (int i = 0; i < ops.length; ++i) {
            switch (ops[i]) {
                case "add":
                    ds.add(points[i]);
                    break;
                case "count":
                    Integer count = ds.count(points[i]);
                    System.out.format("[%d,%d] => %s, %s\n", points[i][0], points[i][1], count, count == expected[i]);
                    break;
            }
        }
    }

    public static void main(String[] args) {
        test(new String[] { "add", "add", "add", "count", "count", "add", "count" },
                new int[][] { { 3, 10 }, { 11, 2 }, { 3, 2 }, { 11, 10 }, { 14, 8 }, { 11, 2 }, { 11, 10 } },
                new Integer[] { null, null, null, 1, 0, null, 2 });

        System.out.println("===============================================================");
        test(new String[] { "add", "add", "add", "count", "add", "add", "add", "count", "add", "add", "add", "count",
                "add", "add", "add", "count", "add", "add", "add", "count", "add", "add", "add", "count", "add", "add",
                "add", "count", "add", "add", "add", "count", "add", "add", "add", "count", "add", "add", "add",
                "count", "add", "add", "add", "count" },
                new int[][] { { 419, 351 }, { 798, 351 }, { 798, 730 }, { 419, 730 }, { 998, 1 }, { 0, 999 },
                        { 998, 999 }, { 0, 1 }, { 226, 561 }, { 269, 561 }, { 226, 604 }, { 269, 604 }, { 200, 274 },
                        { 200, 793 }, { 719, 793 }, { 719, 274 }, { 995, 99 }, { 146, 948 }, { 146, 99 }, { 995, 948 },
                        { 420, 16 }, { 962, 558 }, { 420, 558 }, { 962, 16 }, { 217, 833 }, { 945, 105 }, { 217, 105 },
                        { 945, 833 }, { 26, 977 }, { 26, 7 }, { 996, 7 }, { 996, 977 }, { 96, 38 }, { 96, 483 },
                        { 541, 483 }, { 541, 38 }, { 38, 924 }, { 961, 1 }, { 961, 924 }, { 38, 1 }, { 438, 609 },
                        { 818, 609 }, { 818, 229 }, { 438, 229 } },
                new Integer[] { null, null, null, 1, null, null, null, 1, null, null, null, 1, null, null, null, 1,
                        null, null, null, 1, null, null, null, 1, null, null, null, 1, null, null, null, 1, null, null,
                        null, 1, null, null, null, 1, null, null, null, 1 });

        System.out.println("===============================================================");
        test(new String[] { "add", "add", "add", "count", "add", "add", "add", "count", "add", "add", "add", "count",
                "add", "add", "add", "count", "add", "add", "add", "count", "add", "add", "add", "count", "add", "add",
                "add", "count", "add", "add", "add", "count", "add", "add", "add", "count", "add", "add", "add",
                "count", "add", "add", "add", "count", "add", "add", "add", "count", "add", "add", "add", "count",
                "add", "add", "add", "count", "add", "add", "add", "count", "add", "add", "add", "count", "add", "add",
                "add", "count", "add", "add", "add", "count", "add", "add", "add", "count", "add", "add", "add",
                "count", "add", "add", "add", "count", "add", "add", "add", "count", "add", "add", "add", "count",
                "add", "add", "add", "count", "add", "add", "add", "count", "add", "add", "add", "count", "add", "add",
                "add", "count", "add", "add", "add", "count", "add", "add", "add", "count", "add", "add", "add",
                "count", "add", "add", "add", "count", "add", "add", "add", "count", "add", "add", "add", "count",
                "add", "add", "add", "count", "add", "add", "add", "count", "add", "add", "add", "count", "add", "add",
                "add", "count", "add", "add", "add", "count", "add", "add", "add", "count" },
                new int[][] { { 933, 325 }, { 336, 922 }, { 336, 325 }, { 933, 922 }, { 697, 201 }, { 697, 845 },
                        { 53, 845 }, { 53, 201 }, { 856, 14 }, { 85, 785 }, { 856, 785 }, { 85, 14 }, { 361, 55 },
                        { 361, 678 }, { 984, 678 }, { 984, 55 }, { 343, 689 }, { 587, 445 }, { 343, 445 }, { 587, 689 },
                        { 198, 967 }, { 946, 219 }, { 198, 219 }, { 946, 967 }, { 998, 893 }, { 138, 893 }, { 998, 33 },
                        { 138, 33 }, { 860, 875 }, { 183, 198 }, { 183, 875 }, { 860, 198 }, { 127, 18 }, { 943, 18 },
                        { 127, 834 }, { 943, 834 }, { 703, 347 }, { 636, 280 }, { 703, 280 }, { 636, 347 },
                        { 805, 799 }, { 213, 207 }, { 213, 799 }, { 805, 207 }, { 555, 301 }, { 555, 722 },
                        { 976, 301 }, { 976, 722 }, { 983, 928 }, { 63, 8 }, { 63, 928 }, { 983, 8 }, { 410, 195 },
                        { 369, 236 }, { 369, 195 }, { 410, 236 }, { 665, 249 }, { 665, 848 }, { 66, 848 }, { 66, 249 },
                        { 491, 722 }, { 127, 358 }, { 491, 358 }, { 127, 722 }, { 961, 933 }, { 961, 54 }, { 82, 54 },
                        { 82, 933 }, { 879, 166 }, { 46, 166 }, { 879, 999 }, { 46, 999 }, { 28, 5 }, { 995, 972 },
                        { 995, 5 }, { 28, 972 }, { 973, 452 }, { 562, 863 }, { 973, 863 }, { 562, 452 }, { 916, 135 },
                        { 482, 569 }, { 916, 569 }, { 482, 135 }, { 38, 712 }, { 38, 61 }, { 689, 712 }, { 689, 61 },
                        { 875, 823 }, { 242, 823 }, { 875, 190 }, { 242, 190 }, { 660, 506 }, { 660, 277 },
                        { 889, 506 }, { 889, 277 }, { 294, 129 }, { 294, 755 }, { 920, 755 }, { 920, 129 },
                        { 247, 117 }, { 789, 117 }, { 789, 659 }, { 247, 659 }, { 918, 686 }, { 488, 686 },
                        { 918, 256 }, { 488, 256 }, { 95, 438 }, { 548, 891 }, { 548, 438 }, { 95, 891 }, { 102, 416 },
                        { 459, 416 }, { 102, 773 }, { 459, 773 }, { 245, 999 }, { 863, 381 }, { 863, 999 },
                        { 245, 381 }, { 60, 971 }, { 79, 971 }, { 60, 952 }, { 79, 952 }, { 255, 511 }, { 255, 14 },
                        { 752, 511 }, { 752, 14 }, { 404, 775 }, { 404, 589 }, { 590, 775 }, { 590, 589 }, { 734, 924 },
                        { 165, 924 }, { 165, 355 }, { 734, 355 }, { 613, 71 }, { 613, 444 }, { 240, 444 }, { 240, 71 },
                        { 888, 984 }, { 664, 760 }, { 888, 760 }, { 664, 984 }, { 798, 486 }, { 449, 835 },
                        { 798, 835 }, { 449, 486 }, { 826, 348 }, { 207, 348 }, { 826, 967 }, { 207, 967 },
                        { 830, 993 }, { 830, 495 }, { 332, 993 }, { 332, 495 } },
                new Integer[] { null, null, null, 1, null, null, null, 1, null, null, null, 1, null, null, null,
                        1, null, null, null, 1, null, null, null, 1, null, null, null, 1, null, null, null, 1, null,
                        null, null, 1, null, null, null, 1, null, null, null, 1, null, null, null, 1, null, null, null,
                        1, null, null, null, 1, null, null, null, 1, null, null, null, 1, null, null, null, 1, null,
                        null, null, 1, null, null, null, 1, null, null, null, 1, null, null, null, 1, null, null, null,
                        1, null, null, null, 1, null, null, null, 1, null, null, null, 1, null, null, null, 1, null,
                        null, null, 1, null, null, null, 1, null, null, null, 1, null, null, null, 1, null, null, null,
                        1, null, null, null, 1, null, null, null, 1, null, null, null, 1, null, null, null, 1, null,
                        null, null, 1, null, null, null, 1, null, null, null, 1, null, null, null, 1 });
    }

}
