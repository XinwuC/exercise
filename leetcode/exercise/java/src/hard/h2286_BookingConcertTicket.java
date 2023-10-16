package hard;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/booking-concert-tickets-in-groups/
 * 
 * A concert hall has n rows numbered from 0 to n - 1, each with m seats,
 * numbered from 0 to m - 1. You need to design a ticketing system that can
 * allocate seats in the following cases:
 * 
 * If a group of k spectators can sit together in a row.
 * If every member of a group of k spectators can get a seat. They may or may
 * not sit together.
 * Note that the spectators are very picky. Hence:
 * 
 * They will book seats only if each member of their group can get a seat with
 * row number less than or equal to maxRow. maxRow can vary from group to group.
 * In case there are multiple rows to choose from, the row with the smallest
 * number is chosen. If there are multiple seats to choose in the same row, the
 * seat with the smallest number is chosen.
 * Implement the BookMyShow class:
 * 
 * BookMyShow(int n, int m) Initializes the object with n as number of rows and
 * m as number of seats per row.
 * int[] gather(int k, int maxRow) Returns an array of length 2 denoting the row
 * and seat number (respectively) of the first seat being allocated to the k
 * members of the group, who must sit together. In other words, it returns the
 * smallest possible r and c such that all [c, c + k - 1] seats are valid and
 * empty in row r, and r <= maxRow. Returns [] in case it is not possible to
 * allocate seats to the group.
 * boolean scatter(int k, int maxRow) Returns true if all k members of the group
 * can be allocated seats in rows 0 to maxRow, who may or may not sit together.
 * If the seats can be allocated, it allocates k seats to the group with the
 * smallest row numbers, and the smallest possible seat numbers in each row.
 * Otherwise, returns false.
 * 
 * 
 * Example 1:
 * 
 * Input
 * ["BookMyShow", "gather", "gather", "scatter", "scatter"]
 * [[2, 5], [4, 0], [2, 0], [5, 1], [5, 1]]
 * Output
 * [null, [0, 0], [], true, false]
 * 
 * Explanation
 * BookMyShow bms = new BookMyShow(2, 5); // There are 2 rows with 5 seats each
 * bms.gather(4, 0); // return [0, 0]
 * // The group books seats [0, 3] of row 0.
 * bms.gather(2, 0); // return []
 * // There is only 1 seat left in row 0,
 * // so it is not possible to book 2 consecutive seats.
 * bms.scatter(5, 1); // return True
 * // The group books seat 4 of row 0 and seats [0, 3] of row 1.
 * bms.scatter(5, 1); // return False
 * // There is only one seat left in the hall.
 * 
 * 
 * Constraints:
 * 
 * 1 <= n <= 5 * 104
 * 1 <= m, k <= 109
 * 0 <= maxRow <= n - 1
 * At most 5 * 104 calls in total will be made to gather and scatter.
 * 
 */

public class h2286_BookingConcertTicket {
    public static class BookMyShow {
        int row;
        int seats;
        // index of next available seat in row i.
        // next[i] == seats => no seat available in row i;
        // availabe seats in row i: seats - next[i]
        int[] next;
        long total_available;

        public BookMyShow(int n, int m) {
            row = n;
            seats = m;
            next = new int[n];
            total_available = (long)row * (long)seats;
        }

        public int[] gather(int k, int maxRow) {
            if (k > total_available)
                return new int[0];

            int[] result = null;
            maxRow = Math.min(maxRow, row - 1);
            for (int row = 0; row <= maxRow; ++row)
                if (seats - next[row] >= k) {
                    result = new int[2];
                    result[0] = row;
                    result[1] = next[row];
                    next[row] += k;
                    total_available -= k;
                    break;
                }
            return (result == null) ? new int[0] : result;
        }

        public boolean scatter(int k, int maxRow) {
            if (k > total_available)
                return false;

            maxRow = Math.min(maxRow, row - 1);
            int row = 0;
            int remaining = k;
            for (; remaining > 0 && row <= maxRow; ++row) {
                int available = seats - next[row];
                if (available >= remaining) {
                    next[row] += remaining;
                    remaining = 0;
                    break;
                } else {
                    remaining -= available;
                }
            }
            if (remaining == 0) {
                for (int r = 0; r < row; ++r)
                    next[r] = seats;
                total_available -= k;
                return true;
            } else
                return false;
        }
    }

    private static void test(String[] ops, int[][] input, Object[] output) {
        BookMyShow test = new h2286_BookingConcertTicket.BookMyShow(input[0][0], input[0][1]);
        for (int i = 1; i < ops.length; ++i) {
            switch (ops[i]) {
                case "gather":
                    int[] r = test.gather(input[i][0], input[i][1]);
                    System.out.println(Arrays.toString(r));
                    break;
                case "scatter":
                    boolean b = test.scatter(input[i][0], input[i][1]);
                    System.out.println(b);
                    break;
            }
        }
    }

    public static void main(String[] args) {
        test(
                new String[] { "BookMyShow", "scatter", "scatter", "scatter", "scatter" },
                new int[][] { { 50000, 1 }, { 1, 49999 }, { 50000, 49999 }, { 50000, 49999 },
                        { 50000, 49999 } },
                new Object[] {
                        null, true, new int[] { 2, 0 }, new int[0], new int[0]
                });


        test(
                new String[] { "BookMyShow", "scatter", "gather", "gather", "gather" },
                new int[][] { { 3, 999999999 }, { 1000000000, 2 }, { 999999999, 2 }, { 999999999, 2 },
                        { 999999999, 2 } },
                new Object[] {
                        null, true, new int[] { 2, 0 }, new int[0], new int[0]
                });

        test(
                new String[] { "BookMyShow", "gather", "scatter", "gather", "gather", "gather" },
                new int[][] { { 5, 9 }, { 10, 1 }, { 3, 3 }, { 9, 1 }, { 10, 2 }, { 2, 0 } },
                new Object[] {
                        null, new int[0], true, new int[] { 1, 0 }, new int[0], new int[] { 0, 3 }
                });
    }
}