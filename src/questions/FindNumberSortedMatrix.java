package questions;

/**
 * 在行列都排好序的矩阵中找数
 * 【题目】 给定一个有N*M的整型矩阵matrix和一个整数K,
 * matrix的每一行和每一 列都是排好序的。实现一个函数,判断K
 * 是否在matrix中。 例如: 0 1 2 5 2 3 4 7 4
 * 4 4 8 5 7 7 9 如果K为7,返回true;如果K为6,返
 * 回false。
 * 【要求】 时间复杂度为O(N+M),额外空间复杂度为O(1)。
 */
public class FindNumberSortedMatrix {

    public static int[] solve(int[][] array, int target) {
        int xIndex = array[0].length - 1;
        int yIndex = 0;
        while (xIndex >= 0 && yIndex <= array.length - 1) {
            if (array[yIndex][xIndex] == target) return new int[] {xIndex, yIndex};
            if (array[yIndex][xIndex] < target) yIndex ++;
            else if (array[yIndex][xIndex] > target) xIndex --;
        }
        if (xIndex < 0 || yIndex > array.length - 1) return null;
        else return new int[] {xIndex, yIndex};
    }

    public static void main(String[] args) {
        int[] result = solve(new int[][]{{0,1,3},{9,10,12},{13,15,19}}, 7);
        System.out.println();
    }
}
