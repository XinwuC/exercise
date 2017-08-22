package easy;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * <p>
 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock),
 * design an algorithm to find the maximum profit.
 * <p>
 * Example 1:
 * <p>
 * Input: [7, 1, 5, 3, 6, 4]
 * Output: 5
 * <p>
 * max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
 * <p>
 * Example 2:
 * <p>
 * Input: [7, 6, 4, 3, 1]
 * Output: 0
 * <p>
 * In this case, no transaction is done, i.e. max profit = 0.
 */
public class e121_BestTimetoBuyandSellStock {
    public int maxProfit(int[] prices) {
        int maxPrice = 0;
        int maxProfit = 0;
        for (int i = prices.length - 1; i >= 0; --i) {
            if (prices[i] >= maxPrice)
                maxPrice = prices[i];
            else if (maxPrice - prices[i] > maxProfit)
                maxProfit = maxPrice - prices[i];
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        e121_BestTimetoBuyandSellStock solution = new e121_BestTimetoBuyandSellStock();

        System.out.println(solution.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(solution.maxProfit(new int[]{7, 6, 4, 3, 1}));
    }
}
