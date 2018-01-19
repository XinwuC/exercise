package easy;

/**
 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check.
 * Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 * <p>
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
 * <p>
 * You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should
 * minimize the number of calls to the API.
 */
public class e278_FirstBadVersion {
    private int _firstBadVersion;
    public int count = 0;

    public e278_FirstBadVersion(int firstBadVersion) {
        _firstBadVersion = firstBadVersion;
    }

    boolean isBadVersion(int version) {
        ++count;
        return version >= _firstBadVersion;
    }

    public void reset() {
        count = 0;
    }

    public int firstBadVersion(int n) {
        int start = 1;
        int end = n;
        while (start <= end) {
            int mid = (end + start) / 2;
            if (isBadVersion(mid)) {
                // mid is bad
                if (mid == start)
                    return start; // start of the current range is bad
                if (isBadVersion(mid - 1)) {
                    // pre is also bad
                    end = mid - 2;
                    continue;
                } else {
                    // pre is good
                    return mid;
                }
            } else {
                // mid is good
                if (mid + 1 <= end && isBadVersion(mid + 1)) {
                    // next is bad
                    return mid + 1;
                } else {
                    start = mid;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        e278_FirstBadVersion solution = new e278_FirstBadVersion(5);
        System.out.println(solution.firstBadVersion(100) + "\t" + solution._firstBadVersion + "\t" + solution.count);
    }
}

