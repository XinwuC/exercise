package medium;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 347. Top K Frequent Elements
 * https://leetcode.com/problems/top-k-frequent-elements/
 * 
 * Given an integer array nums and an integer k, return the k most frequent
 * elements. You may return the answer in any order.
 * 
 * Example 1:
 * 
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 * 
 * Input: nums = [1], k = 1
 * Output: [1]
 * 
 * 
 * Constraints:
 * 
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * k is in the range [1, the number of unique elements in the array].
 * It is guaranteed that the answer is unique.
 * 
 * Follow up: Your algorithm's time complexity must be better than O(n log n),
 * where n is the array's size.
 * 
 */
public class m347_TopKFrequentElements {
    static m347_TopKFrequentElements solution = new m347_TopKFrequentElements();

    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> mapCounter = new HashMap<>();
        for (int n : nums) {
            mapCounter.merge(n, 1, Integer::sum);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(mapCounter.size(),
                new Comparator<Map.Entry<Integer, Integer>>() {
                    public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                        if (o1.getValue() == o2.getValue())
                            return 0;
                        else if (o1.getValue() < o2.getValue())
                            return 1;
                        else
                            return -1;
                    }
                });
        for (Map.Entry<Integer, Integer> entry : mapCounter.entrySet()) {
            queue.add(entry);
        }
        int[] result = new int[k];
        for (int i = 0; i < k; ++i) {
            Map.Entry<Integer, Integer> entry = queue.remove();
            result[i] = entry.getKey();
        }
        return result;
    }

    public static void main(String[] args) {
        solution.topKFrequent(new int[] { 1, 1, 1, 2, 2, 3 }, 2);
        solution.topKFrequent(new int[] { 1 }, 1);
    }
}
