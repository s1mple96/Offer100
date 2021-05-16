package dp;
//给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
//
// 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
//
// 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
//
//
//
// 示例 1：
//
//
//输入：[7,1,5,3,6,4]
//输出：5
//解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
//     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
//
//
// 示例 2：
//
//
//输入：prices = [7,6,4,3,1]
//输出：0
//解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
//
//
//
//
// 提示：
//
//
// 1 <= prices.length <= 105
// 0 <= prices[i] <= 104
//
// Related Topics 数组 动态规划
// 👍 1620 👎 0

/**
 * @author s1mple
 * @create 2021/5/13-19:50
 */
public class GetMaxProfit2 {
    /**
     * 定义：
     * dp[i][0]表示第i天交易完之后手里 没有 股票的最大利润
     * dp[i][1]表示第i天交易完之后手里 持有 股票的最大利润
     * 当天交易完成之后手里 没有 股票的可能有两种情况：
     * 1.当天没有进行任何交易，又因为当天手里没有股票，所以当天没有股票的利润只能取前一天手里没有股票的利润
     * 2.当天手里的股票给卖了，既然能卖，说明手里是有股票的，所以这个时候当天没有股票的利润要取前一天手里有股票的利润加上当天股票能卖出去的价格再减去手续费
     * 这两种情况我们取利润最大值即可，所以可以得到公式：
     * dp[i][0]=max(dp[i-1][0],dp[i-1][1]+prices[i]-fee)
     * 当天交易完成之后手里 持有 股票的也有两种情况：
     * 1.当天没有任何交易，又因为当天手里持有股票，所以当天手里持有的股票其实前一天就已经持有了。
     * 2.当天买入了股票，当天能卖股票，说明前一天手里肯定是没有股票的。
     * 这两种情况我们取利润最大值即可，
     * dp[i][1] = max(dp[i-1][1],dp[i-1][0]-prices[i]);
     * 动态规划的递推公式有了，那么初始条件 是什么呢？就是第0天：
     * 如果买入：dp[0][1] = -prices[0];
     * 如果没买：dp[0][0] = 0;
     */
    public int maxProfit2(int[] prices, int fee) {
        //边界条件判断
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int length = prices.length;
        int[][] dp = new int[length][2];
        //初始条件
        dp[0][1] = -prices[0];
        dp[0][0] = 0;
        for (int i = 1; i < length; i++) {
            //递推公式
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        //最后一天肯定是要手里没有股票的时候,利润才会最大,
        //只需要返回dp[length-1][0]即可
        return dp[length][0];
    }
}