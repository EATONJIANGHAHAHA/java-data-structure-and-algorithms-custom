package questions;

/**
 * “之”字形打印矩阵
 * 【题目】 给定一个矩阵matrix,按照“之”字形的方式打印这
 * 个矩阵,例如: 1 2 3 4 5 6 7 8 9 10 11 12
 * “之”字形打印的结果为:1,2,5,9,6,3,4,7,10,11,
 * 8,12
 * 【要求】 额外空间复杂度为O(1)。
 */
public class ZLikePrintMatrix {

    public static void solve(int[][] array) {
        if (array == null) return;
        int[] a = {0, 0};
        int[] b = {0, 0};
        boolean revert = false;
        while (a[0] <= array[0].length - 1 && a[1] <= array.length - 1) {
            printSlash(array, a, b, revert);
            if (a[0] < array[0].length - 1) a[0]++;
            else a[1]++;
            if (b[1] < array.length - 1) b[1]++;
            else b[0]++;
            revert = !revert;
        }
    }

    private static void printSlash(int[][] array, int[] a, int[] b, boolean revert) {
        int xIndex, yIndex;
        if (! revert) {
            xIndex = b[0];
            yIndex = b[1];
            while (yIndex >= 0 && xIndex <= array[0].length - 1)
                System.out.println(array[yIndex --][xIndex ++]);
        } else {
            xIndex = a[0];
            yIndex = a[1];
            while (xIndex >= 0 && yIndex <= array.length - 1)
                System.out.println(array[yIndex ++][xIndex --]);
        }
    }

    public static void main(String[] args) {
        int[][] array = new int[3][3];
        int outer, inner, count = 1;
        for (outer = 0; outer < array.length; outer++) {
            for (inner = 0; inner < array[outer].length; inner++) {
                array[outer][inner] = count++;
            }
        }
        solve(array);
    }
}
