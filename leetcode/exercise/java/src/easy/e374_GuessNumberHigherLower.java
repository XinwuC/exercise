package easy;

/**
 * We are playing the Guess Game. The game is as follows:
 * <p>
 * I pick a number from 1 to n. You have to guess which number I picked.
 * <p>
 * Every time you guess wrong, I'll tell you whether the number is higher or lower.
 * <p>
 * You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
 * <p>
 * -1 : My number is lower
 * 1 : My number is higher
 * 0 : Congrats! You got it!
 * Example:
 * n = 10, I pick 6.
 * <p>
 * Return 6.
 * <p>
 * https://leetcode.com/problems/guess-number-higher-or-lower/description/
 */
public class e374_GuessNumberHigherLower {
    private int _mynum;

    e374_GuessNumberHigherLower(int num) {
        _mynum = num;
    }

    /**
     * The guess API is defined in the parent class GuessGame.
     *
     * @param num, your guess
     * @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
     * int guess(int num);
     */
    private int guess(int num) {
        if (num == _mynum) return 0;
        else if (num > _mynum) return -1;
        else return 1;
    }

    public int guessNumber(int n) {
        int start = 1, end = n;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            switch (guess(mid)) {
                case 0:
                    return mid;
                case -1:
                    end = mid - 1;
                    break;
                case 1:
                    start = mid + 1;
                    break;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new e374_GuessNumberHigherLower(5).guessNumber(9));
        System.out.println(new e374_GuessNumberHigherLower(10).guessNumber(9));
        System.out.println(new e374_GuessNumberHigherLower(5).guessNumber(10));
        System.out.println(new e374_GuessNumberHigherLower(6).guessNumber(10));
        System.out.println(new e374_GuessNumberHigherLower(1).guessNumber(10));
        System.out.println(new e374_GuessNumberHigherLower(10).guessNumber(10));
        System.out.println(new e374_GuessNumberHigherLower(11).guessNumber(10));

        System.out.println(new e374_GuessNumberHigherLower(1702766719).guessNumber(2126753390));
    }
}
