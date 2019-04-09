package questions;


import java.util.*;

/**
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * <p>
 * 例如给定三角形
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * <p>
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 */
public class TrianglePath {

    public static void main(String[] args) {
        solveDyna(getRamdomTriangle());
        solveRecMemo(getRamdomTriangle());
    }

    private static List<List<Integer>> getMyTriangle() {
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> row = new ArrayList<>();
        row.add(2);
        triangle.add(row);
        row = new ArrayList<>();
        row.add(3);
        row.add(4);
        triangle.add(row);
        row = new ArrayList<>();
        row.add(6);
        row.add(5);
        row.add(7);
        triangle.add(row);
        row = new ArrayList<>();
        row.add(4);
        row.add(1);
        row.add(8);
        row.add(3);
        triangle.add(row);
        return triangle;
    }

    private static List<List<Integer>> getRamdomTriangle() {
        List<List<Integer>> triangle = new ArrayList<>(1001);
        int depth = 4000;
        List<Integer> row;
        for (int i = 1; i < depth; i++) {
            row = new ArrayList<>(i + 1);
            for (int j = 0; j < i; j++) {
                row.add(new Random().nextInt(Integer.MAX_VALUE));
            }
            triangle.add(row);
        }
        return triangle;
    }

    /**
     * 暴力递归法
     * 时间复杂度O(2^n)
     * 空间复杂度O(1)
     *
     * @param triangle
     * @return
     */
    private static int solveRec(List<List<Integer>> triangle) {
        long start = System.currentTimeMillis();
        int result = solveRec(triangle, 0, 0);
        long end = System.currentTimeMillis();
        System.out.println("" + (end - start));
        return result;
    }

    /**
     * 递归体
     *
     * @param triangle 三角形阵列
     * @param i        深度坐标
     * @param j        广度坐标
     */
    private static int solveRec(List<List<Integer>> triangle, int i, int j) {
        if (i == triangle.size() - 1) return triangle.get(i).get(j);
        return Math.min(solveRec(triangle, i + 1, j),
                solveRec(triangle, i + 1, j + 1))
                + triangle.get(i).get(j);
    }

    /**
     * 记忆化搜索递归，基础动态规划
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     *
     * @param triangle 三角形阵列
     * @return 最小和
     */
    private static int solveRecMemo(List<List<Integer>> triangle) {
        long start = System.currentTimeMillis();
        List<List<Integer>> maxSum = new ArrayList<>(1001);
        List<Integer> row;
        for (int i = 1; i < triangle.size(); i++) {
            row = new ArrayList<>(i + 1);
            for (int j = 0; j < i; j++) {
                row.add(-1);
            }
            maxSum.add(row);
        }
        int result = solveRecMemo(triangle, maxSum, 0, 0);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        return result;
    }

    /**
     * 递归体
     *
     * @param triangle 三角形阵列
     * @param maxSum   记忆体
     * @param i        深度
     * @param j        广度
     * @return 最小和
     */
    private static int solveRecMemo(List<List<Integer>> triangle, List<List<Integer>> maxSum, int i, int j) {
        if (i == triangle.size() - 1) return triangle.get(i).get(j);
        if (maxSum.get(i).get(j) != -1) return maxSum.get(i).get(j);
        List<Integer> temp = maxSum.get(i);
        int min = Math.min(solveRecMemo(triangle, maxSum, i + 1, j),
                solveRecMemo(triangle, maxSum, i + 1, j + 1))
                + triangle.get(i).get(j);
        temp.set(j, min);
        maxSum.set(i, temp);
        return min;
    }

    /**
     * 最优动态规划，自底向上求解
     *
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     * @param triangle 三角形矩阵
     * @return 最小和
     */
    public static int solveDyna(List<List<Integer>> triangle) {
        long start = System.currentTimeMillis();
        List<List<Integer>> maxSum = new ArrayList<>(1001);
        List<Integer> row;
        for (int i = triangle.size() - 2; i >= 0; i--) {
            row = triangle.get(i);
            for (int j = 0; j < triangle.get(i).size(); j++) {
                row.set(j, Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1)) + row.get(j));
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        return triangle.get(0).get(0);
    }
}
