package medium;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 451. Sort Characters By Frequency
 * <p>
 * Total Accepted: 11609
 * Total Submissions: 22857
 * Difficulty: Medium
 * Contributors: stickypens
 * <p>
 * Given a string, sort it in decreasing order based on the frequency of characters.
 * <p>
 * <code>
 * Example 1:
 * <p>
 * Input:
 * "tree"
 * <p>
 * Output:
 * "eert"
 * <p>
 * Explanation:
 * 'e' appears twice while 'r' and 't' both appear once.
 * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 * </code>
 * <p>
 * <code>
 * Example 2:
 * <p>
 * Input:
 * "cccaaa"
 * <p>
 * Output:
 * "cccaaa"
 * <p>
 * Explanation:
 * Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
 * Note that "cacaca" is incorrect, as the same characters must be together.
 * </code>
 * <p>
 * <code>
 * Example 3:
 * <p>
 * Input:
 * "Aabb"
 * <p>
 * Output:
 * "bbAa"
 * <p>
 * Explanation:
 * "bbaA" is also a valid answer, but "Aabb" is incorrect.
 * Note that 'A' and 'a' are treated as two different characters.
 * </code>
 * <p>
 * Leetcode: https://leetcode.com/problems/sort-characters-by-frequency/
 * Created by xinwu on 1/2/17.
 */
public class m451_SortCharactersByFrequency {
    class Char implements Comparable<Char> {
        char value;
        int frequency;

        public Char(char v) {
            value = v;
        }

        public void incremental() {
            ++frequency;
        }


        @Override
        public int compareTo(Char o) {
            return frequency - o.frequency;
        }
    }

    public String frequencySort(String s) {
        HashMap<Character, Char> hash = new HashMap<>();
        for(char c: s.toCharArray()) {
            Character key = Character.valueOf(c);
            Char ch = hash.containsKey(key) ? hash.get(key) : new Char(c);
            ch.incremental();
            hash.putIfAbsent(key, ch);
        }

        Char [] chars = hash.values().toArray(new Char[hash.size()]);
        Arrays.sort(chars);

        char [] result = new char[s.length()];
        int startIndex = 0;
        int toIndex = 0;
        for(int i= chars.length-1; i>=0; --i) {
            toIndex += chars[i].frequency;
            Arrays.fill(result, startIndex, toIndex, chars[i].value);
            startIndex = toIndex;
        }

        return new String(result);
    }

    public static void main(String[] args) {
        m451_SortCharactersByFrequency solution = new m451_SortCharactersByFrequency();

        System.out.println(solution.frequencySort("tree"));
        System.out.println(solution.frequencySort("cccaaa"));
        System.out.println(solution.frequencySort("Aabb"));
    }
}
