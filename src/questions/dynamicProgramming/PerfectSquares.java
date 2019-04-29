package questions.dynamicProgramming;


import java.util.Arrays;
import java.util.LinkedList;

/**
 * 279.给出一个正整数n，寻找最少的完全平方数的和，使得这个和为n。你需要让组成和的完全平方数的个数最少。
 * <p>
 * 示例 1:
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 * <p>
 * 示例 2:
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 */
public class PerfectSquares {

    public static int numSquares(int n) {
        if (n <= 3) return n;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = i; //存储当前i的解
            for (int j = 1; i - j * j >= 0; j++)
                //当i - j * j = 0时，代表i是完全平方数，位数为1
                //当j = 1时，寻找的是i前一个数的解，加1便为自己的值，如果无法被任何完全平方数整除的话。
                //如果可以被一个完全平方数整除，也就是当i是一个完全平方数的倍数，比如8可以被4整除，则查找4的解，加1即为本解。
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(numSquares(12));
    }
}
