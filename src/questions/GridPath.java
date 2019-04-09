package questions;

/**
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 *
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 */
public class GridPath {

    public static void main(String[] args) {
        System.out.println(solve(getMyGrid()));
    }

    private static int solve(int[][] grid) {
        for (int i = grid.length - 1; i >= 0; i --) {
            for (int j = grid[i].length - 1; j >= 0; j --) {
                grid[i][j] = Math.min(
                        (i + 1 < grid.length) ? grid[i + 1][j] : (j + 1 < grid[i].length) ? grid[i][j + 1] : 0,
                        (j + 1 < grid[i].length) ? grid[i][j + 1] : (i + 1 < grid.length) ? grid[i + 1][j] : 0
                ) + grid[i][j];
            }
        }
        return grid[0][0];
    }

    private static int[][] getMyGrid() {
        return new int[][] {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
    }
}
