package medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 78. Subsets
 * 
 * Total Accepted: 95208 Total Submissions: 302252
 * 
 * Difficulty: Medium
 * 
 * Given a set of distinct integers, nums, return all possible subsets.
 * 
 * Note: Elements in a subset must be in non-descending order. The solution set
 * must not contain duplicate subsets.
 * 
 * For example, If nums = [1,2,3], a solution is:
 * 
 * [ [3], [1], [2], [1,2,3], [1,3], [2,3], [1,2], [] ]
 * 
 * Reference:
 * 
 * 1. Heap's algorithm:
 * http://comjnl.oxfordjournals.org/content/6/3/293.full.pdf
 * 
 * 2. Wiki pseudo code: https://en.wikipedia.org/wiki/Heap%27s_algorithm
 * 
 * 3. algorithm explanation:
 * http://blog.csdn.net/masibuaa/article/details/8171082
 */
public class m078_Subsets {

	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> solution = new ArrayList<List<Integer>>();
		solution.add(new ArrayList<Integer>());

		// empty set
		if (null == nums || 0 == nums.length)
			return solution;

		List<Integer> numList = IntStream.of(nums).boxed().collect(Collectors.toList());
		Collections.sort(numList);

		// number of combinations
		int size = (int) Math.pow(2, numList.size());

		for (int combo = 1; combo < size; ++combo) {
			List<Integer> subset = new ArrayList<Integer>();
			for (int mask = 1; mask <= combo; mask <<= 1) {
				if ((mask & combo) != 0) {
					int idx = (int) (Math.log(mask) / Math.log(2));
					subset.add(numList.get(idx));
				}
			}
			solution.add(subset);
		}

		return solution;
	}

	private void printSolution(List<List<Integer>> solution) {
		for (List<Integer> subset : solution) {
			System.out.print("\t[");
			for (Integer val : subset) {
				System.out.print(val + ",");
			}
			System.out.println("],");
		}
	}

	public static void main(String[] args) {
		m078_Subsets target = new m078_Subsets();

		int[] nums = { 1, 2, 3, 4 };
		List<List<Integer>> solution = target.subsets(nums);
		target.printSolution(solution);

		int[] nums2 = { -1, 2, -3, 4, -100, 12043, -453 };
		target.printSolution(target.subsets(nums2));

	}

	class Permutation {
		public List<List<Integer>> subsets(int[] nums) {
			List<List<Integer>> solution = new ArrayList<List<Integer>>();

			permutation(nums, nums.length, solution);
			printSolution(solution);

			return solution;
		}

		private void permutation(int[] nums, int n, List<List<Integer>> solution) {
			if (1 == n) {
				solution.add(new ArrayList<Integer>(IntStream.of(nums).boxed().collect(Collectors.toList())));
			} else {
				for (int i = 0; i < n - 1; ++i) {
					permutation(nums, n - 1, solution);
					if (0 == n % 2) {
						int tmp = nums[i];
						nums[i] = nums[n - 1];
						nums[n - 1] = tmp;
					} else {
						int tmp = nums[0];
						nums[0] = nums[n - 1];
						nums[n - 1] = tmp;
					}
				}
				permutation(nums, n - 1, solution);
			}
		}
	}
}
