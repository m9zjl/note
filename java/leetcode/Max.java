import java.util.*;

public class Max {
    public static void main(String[] args) {
        Max ladder = new Max();
        int[] prices = { 3, 3, 5, 0, 0, 3, 1, 4 };
        System.out.println(ladder.maxProfit(prices));
    }

    public int maxProfit(int[] prices) {
        int[] lefts = new int[prices.length];
        int[] rights = new int[prices.length];
        int minL = Integer.MAX_VALUE;
        int maxR = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            minL = Math.min(minL, prices[i]);
            lefts[i] = prices[i] - minL;
        }
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            max = Math.max(max, lefts[i] + rights[i]);
        }
        return max;
    }
}