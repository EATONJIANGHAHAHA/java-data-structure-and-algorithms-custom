package questions.dynamicProgramming;

/**
 * 给定一个正整数n，可以将其分隔成多个数字的和，若要让这些数字的乘机最大，求分割的方法（至少要分隔成两个数), 算法返回这个最大的乘机。
 */
public class IntegerBreak {

    public static void main(String[] args) {
        System.out.println(solveMemo(10));
    }

    private static int solve(int n) {
        if (n == 1) return 1;
        int res = 0;
        for (int i = 1; i < n; i++) {
            res = max(res, i * (n - i), i * solve(n - i));
        }
        return res;
    }

    private static int solveMemo(int n) {
        return solveMemo(n, new int[n + 1]);
    }

    private static int solveMemo(int n, int[] memo) {
        if (n == 1) return 1;
        if (memo[n] != 0) return memo[n];
        int res = 0;
        for (int i = 1; i < n; i++)
            res = max(res, i * (n - i), i * solveMemo(n - i, memo));
        memo[n] = res;
        return res;
    }

    private static int solveDyna(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                //          dp[i]充当拆分到当前位置的前一个拆分的最大乘机
                //                   j * (i - j)代表仅把i拆分成两个数，即i和j的最大乘积
                //                                   j * dp[i - j]代表如果将j继续拆分的最大乘积。但是因为j一定比i小，所以可知j的最大乘机一定被计算过了
                dp[i] = max(dp[i], j * (i - j), j * dp[i - j]);
            }
        }
        return dp[n];
    }

    private static int max(int i, int j, int k) {
        int max = i > j ? i : j;
        return max > k ? max : k;
    }
}
