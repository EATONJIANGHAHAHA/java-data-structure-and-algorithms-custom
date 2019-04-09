package questions;


import java.util.Arrays;

/**
 * 给出一个正整数n，寻找最少的完全平方数的和，使得这个和为n。你需要让组成和的完全平方数的个数最少。
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
        int result[] = new int[n + 1];
        Arrays.fill(result, Integer.MAX_VALUE);
        for (int i = 1; i * i <= n; i++)
            result[i * i] = 1;
        for (int i = 1; i <= n; i++)
            //小于i的平方的所有可能的和
            for (int j = 1; j * j < i; j++) {
                //i - j * j 表示计算过的j的平方的最小和的下标，
                int a = i - j * j;
                int b = i;
                result[i] = Math.min(result[i], result[i - j * j] + 1);
            }
        return result[n];
    }

    public static void main(String[] args) {
        System.out.println(numSquares(30));
    }
}
