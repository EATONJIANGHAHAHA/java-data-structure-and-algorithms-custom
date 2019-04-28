package questions.dynamicProgramming;


import java.util.Scanner;

public class CityPath {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] m = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                m[i][j] = scanner.nextInt();
            }
        }
        System.out.println(solveDyna(m));
    }

    public static int solveDyna(int[][] triangle) {
        int sum = 0, min = Integer.MAX_VALUE;
        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j < i; j++) {
                min = triangle[i][j] < min ? triangle[i][j] : min;
            }
            sum += min;
            min = Integer.MAX_VALUE;
        }
        return sum;
    }
}
