package medium;

/**
 * 731. My Calendar II
 * https://leetcode.com/problems/my-calendar-ii/
 * 
 * You are implementing a program to use as your calendar. We can add a new
 * event if adding the event will not cause a triple booking.
 * 
 * A triple booking happens when three events have some non-empty intersection
 * (i.e., some moment is common to all the three events.).
 * 
 * The event can be represented as a pair of integers start and end that
 * represents a booking on the half-open interval {start, end), the range of
 * real numbers x such that start <= x < end.
 * 
 * Implement the MyCalendarTwo class:
 * 
 * MyCalendarTwo() Initializes the calendar object.
 * boolean book(int start, int end) Returns true if the event can be added to
 * the calendar successfully without causing a triple booking. Otherwise, return
 * false and do not add the event to the calendar.
 * 
 */
public class m731 {
    static class MyCalendarTwo {
        class Interval {
            int start = -1;
            int end = -1;
            int count = 0;
            Interval left = null;
            Interval right = null;

            public Interval(int start, int end) {
                this.start = start;
                this.end = end;
                this.count = 1;
            }
        }

        Interval root = null;

        public MyCalendarTwo() {

        }

        public boolean book(int start, int end) {
            if (root == null) {
                root = new Interval(start, end);
                return true;
            }
            return book(root, start, end, true);
        }

        private boolean try_book(Interval current, int start, int end) {
            if (start == end)
                return true;

            boolean bookable = false;
            if (end <= current.start) {
                // non-overlap with current node and it's on the left
                if (current.left == null) {
                    bookable = true;
                } else {
                    bookable = try_book(current.left, start, end);
                }
            } else if (start >= current.end) {
                // non-overlap and on the right
                if (current.right == null) {
                    bookable = true;
                } else {
                    bookable = try_book(current.right, start, end);
                }
            } else {
                // overlap with current, check if triple booked
                if (current.count == 1) {
                    int left_start = Math.min(start, current.start);
                    int left_end = Math.max(start, current.start);
                    int right_start = Math.min(end, current.end);
                    int right_end = Math.max(end, current.end);
                    bookable = (current.left == null ? true : try_book(current.left, left_start, left_end))
                            && (current.right == null ? true : try_book(current.right, right_start, right_end));
                }
            }
            return bookable;
        }

        private boolean book(Interval current, int start, int end, boolean trying) {
            if (trying && !try_book(current, start, end))
                return false;

            if (end <= current.start) {
                // non-overlap with current node and it's on the left
                if (current.left == null) {
                    current.left = new Interval(start, end);
                } else {
                    book(current.left, start, end, false);
                }
            } else if (start >= current.end) {
                // non-overlap and on the right
                if (current.right == null) {
                    current.right = new Interval(start, end);
                } else {
                    book(current.right, start, end, false);
                }
            } else {
                // overlap with current, check if triple booked
                if (current.count == 1) {
                    int left_start = Math.min(start, current.start);
                    int left_end = Math.max(start, current.start);
                    int right_start = Math.min(end, current.end);
                    int right_end = Math.max(end, current.end);
                    if (current.left == null)
                        current.left = new Interval(left_start, left_end);
                    else
                        book(current.left, left_start, left_end, false);
                    if (current.right == null)
                        current.right = new Interval(right_start, right_end);
                    else
                        book(current.right, right_start, right_end, false);
                    current.start = left_end;
                    current.end = right_start;
                    current.count++;
                }
            }
            return true;
        }
    }

    static void test(int[][] intervals) {
        MyCalendarTwo cal = new MyCalendarTwo();
        for (int[] i : intervals) {
            System.out.format("[%d,%d] => %s\n", i[0], i[1], cal.book(i[0], i[1]));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        test(new int[][] { { 10, 20 }, { 50, 60 }, { 10, 40 }, { 5, 15 }, { 5, 10 }, { 25, 55 } });

        test(new int[][] { { 47, 50 }, { 1, 10 }, { 27, 36 }, { 40, 47 }, { 20, 27 }, { 15, 23 }, { 10, 18 },
                { 27, 36 }, { 17, 25 }, { 8, 17 }, { 24, 33 }, { 23, 28 }, { 21, 27 }, { 47, 50 }, { 14, 21 },
                { 26, 32 }, { 16, 21 }, { 2, 7 }, { 24, 33 }, { 6, 13 }, { 44, 50 }, { 33, 39 }, { 30, 36 }, { 6, 15 },
                { 21, 27 }, { 49, 50 }, { 38, 45 }, { 4, 12 }, { 46, 50 }, { 13, 21 } });
    }
}
