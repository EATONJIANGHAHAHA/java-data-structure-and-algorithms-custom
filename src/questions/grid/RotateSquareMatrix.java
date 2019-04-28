package questions.grid;

/**
 * 旋转正方形矩阵
 * 【题目】 给定一个整型正方形矩阵matrix,请把该矩阵调整成
 * 顺时针旋转90度的样子。
 * 【要求】 额外空间复杂度为O(1)。
 */
public class RotateSquareMatrix {
    public static void solve(int[][] array) {
        if (array.length == 0) return;
        int a = 0, b = 0, c = array[b].length - 1, d = array.length - 1;
        while (a <= c && b <= d)
            rotateEdges(array, a++, b++, c--, d--);
    }

    private static void rotateEdges(int[][] array, int a, int b, int c, int d) {
        int increment, temp;
        for (increment = 0; increment < c; increment++) {
            temp = array[b][a + increment];
            array[b][a + increment] = array[d - increment][a];
            array[d - increment][a] = array[d][c - increment];
            array[d][c - increment] = array[b + increment][c];
            array[b + increment][c] = temp;
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
        RotatePrintMatrix.solve(array);
    }
}
