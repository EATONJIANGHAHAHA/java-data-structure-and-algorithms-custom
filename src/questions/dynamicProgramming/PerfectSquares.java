package questions.dynamicProgramming;


import java.util.Arrays;
import java.util.LinkedList;

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
        LinkedList<Integer> list = getSquares(n);
        return 0;
    }

    private static LinkedList<Integer> getSquares(int n) {
        LinkedList<Integer> list = new LinkedList<>();
        int square = 1;
        for (int i = 1; square < n; i++) {
            list.add(square);
            square = i * i;
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(numSquares(30));
    }
}
