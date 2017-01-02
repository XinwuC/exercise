package hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 140. Word Break II
 * 
 * Total Accepted: 61204 Total Submissions: 298072 Difficulty: Hard
 * 
 * Given a string s and a dictionary of words dict, add spaces in s to construct
 * a sentence where each word is a valid dictionary word.
 * 
 * Return all such possible sentences.
 * 
 * For example, given s = "catsanddog", dict = ["cat", "cats", "and", "sand",
 * "dog"].
 * 
 * A solution is ["cats and dog", "cat sand dog"].
 *
 * leetcode: https://leetcode.com/problems/word-break-ii/
 * 
 * @author xinwu
 *
 */
public class h140_WordBreakII {

	public List<String> wordBreak(String s, Set<String> wordDict) {
		if (!canBreak(s, wordDict))
			return new ArrayList<String>();

		return wordBreakRecursive(s, wordDict);
	}

	boolean canBreak(String s, Set<String> dict) {
		if (s == null || s.length() == 0)
			return false;

		int n = s.length();

		// dp[i] represents whether s[0...i] can be formed by dict
		boolean[] dp = new boolean[n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= i; j++) {
				String sub = s.substring(j, i + 1);

				if (dict.contains(sub) && (j == 0 || dp[j - 1])) {
					dp[i] = true;
					break;
				}
			}
		}

		return dp[n - 1];
	}

	// recursive solution
	public List<String> wordBreakRecursive(String s, Set<String> wordDict) {
		List<String> output = new ArrayList<String>();

		// argu checking
		if (null == s || s.isEmpty() || null == wordDict || wordDict.isEmpty())
			return output;

		for (String word : wordDict) {
			if (word.length() == s.length() && word.equals(s)) {
				output.add(word);
			} else if (word.length() < s.length() && s.startsWith(word)) {
				List<String> subset = wordBreak(s.substring(word.length()), wordDict);
				for (String substring : subset) {
					output.add(word.concat(" ").concat(substring));
				}
			}
		}

		return output;
	}

	public static void main(String[] args) {
		h140_WordBreakII solution = new h140_WordBreakII();

		String s = "catsanddog";
		Set<String> wordDict = new HashSet<String>(Arrays.asList(new String[] { "cat", "cats", "and", "sand", "dog" }));
		System.out.println("Dict:\t" + Arrays.toString(wordDict.toArray()));
		System.out.println("Input:\t" + s);
		System.out.println("output:\t" + Arrays.toString(solution.wordBreak(s, wordDict).toArray()));
		System.out.println();

		s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		wordDict = new HashSet<String>(Arrays.asList(new String[] { "a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa",
				"aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa" }));
		System.out.println("Dict:\t" + Arrays.toString(wordDict.toArray()));
		System.out.println("Input:\t" + s);
		System.out.println("output:\t" + Arrays.toString(solution.wordBreak(s, wordDict).toArray()));
		System.out.println();

	}

}
