package com.allen.algorithm.arry;

import org.junit.Test;

/**
 * Created by xuguocai on 2021/3/2 9:02 股票算法
 */
public class SharesTest {

    /**
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。获取卖出的利润最大的值。不限制买卖次数，每一次上升波段，其实就是一次低价买入高价卖出
     * 条件：
     * 1、不能参与多笔交易。换句话讲，我们只能在手上没有股票的时候买入，也就是必须在再次购买前出售掉之前的股票。像我们平时买股票时的追涨杀跌是不可以的。
     *
     * 2、尽可能地多进行交易。这个非常好理解。像是黄金，一年基本上都有2-3次涨跌。我们只要把握住机会，在每一次涨跌的时候，低价卖入高价卖出，
     * 就可以使利益达到最大化。这个条件也是相当重要的，如果我们把这里变成，最多完成两笔交易，就变成另一道题。
     */
    @Test
    public void getPeakShares() {
//        int[] prices = {15, 7, 3, 6, 8, 10, 12};
        int[] prices = {7, 1, 5, 3, 6, 4};
        // 盈利的基准参数，既是买卖后的盈利总参数
        int profit = 0;

        // 遍历数组
        for (int i = 1; i < prices.length; i++) {
            // 卖出股票，允许多次买卖（后 - 前）
            int diff = prices[i] - prices[i - 1];
            // 找盈利的股票
            if (diff > 0) {
                // 累计每一次盈利的值
                profit += diff;

            }
        }
        System.out.println(profit);
    }

}
