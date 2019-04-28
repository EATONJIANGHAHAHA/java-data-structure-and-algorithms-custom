package questions.grid;

/**
 * 转圈打印矩阵
 * 【题目】 给定一个整型矩阵matrix,请按照转圈的方式打印它。
 * 例如: 1 2 3 4 5 6 7 8 9 10 11 12 13 14
 * 15 16 打印结果为:1,2,3,4,8,12,16,15,14,13,9,
 * 5,6,7,11, 10
 * 【要求】 额外空间复杂度为O(1)。
 */
public class RotatePrintMatrix {

    public static void solve(int[][] array) {
        if (array.length == 0) return;
        int a = 0, b = 0, c = array[b].length - 1, d = array.length - 1;
        while (a <= c && b <= d)
            printEdges(array, a++, b++, c--, d--);
        if (a < c) printEdges(array,a + 1, b, c - 1, d);
        if (b < d) printEdges(array,a, b + 1, c, d - 1);
    }

    private static void printEdges(int[][] array, int a, int b, int c, int d) {
        int xIndex = a, yIndex = b;
        if (a == c && b == d) System.out.println(array[a][b]);
        while (xIndex < c)
            System.out.println(array[yIndex][xIndex++]);
        while (yIndex < d)
            System.out.println(array[yIndex++][xIndex]);
        while (xIndex > a)
            System.out.println(array[yIndex][xIndex--]);
        while (yIndex > b)
            System.out.println(array[yIndex--][xIndex]);
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
