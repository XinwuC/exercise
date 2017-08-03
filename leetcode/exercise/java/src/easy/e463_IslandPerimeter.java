package easy;

/**
 * You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water. Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells). The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.
 * <p>
 * Example:
 * <p>
 * [[0,1,0,0],
 * [1,1,1,0],
 * [0,1,0,0],
 * [1,1,0,0]]
 * <p>
 * Answer: 16
 */
public class e463_IslandPerimeter {

    public int islandPerimeter(int[][] grid) {
        int perimeter = 0;
        for (int row = 0; row < grid.length; ++row)
            for (int col = 0; col < grid[0].length; ++col) {
                if (grid[row][col] == 1) {
                    if (0 == row)
                        ++perimeter;
                    else if (0 == grid[row - 1][col])
                        ++perimeter;
                    if (0 == col)
                        ++perimeter;
                    else if (0 == grid[row][col - 1])
                        ++perimeter;
                    if (grid.length - 1 == row)
                        ++perimeter;
                    else if (0 == grid[row + 1][col])
                        ++perimeter;
                    if (grid[0].length - 1 == col)
                        ++perimeter;
                    else if (0 == grid[row][col + 1])
                        ++perimeter;
                }
            }
        return perimeter;
    }

    public static void main(String[] args) {
        e463_IslandPerimeter solution = new e463_IslandPerimeter();
        System.out.println(solution.islandPerimeter(new int[][]{
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 0, 0}
        }));
    }
}
