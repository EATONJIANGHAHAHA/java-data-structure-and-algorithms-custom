package questions;

import java.util.Scanner;

/**
 * 假设你现在正在爬楼梯，楼梯有 n 级。每次你只能爬 1级或者 2级，那么你有多少种方法爬到楼梯的顶部？
 */
public class ClimbingStaires {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().replace("[", "").replace("]", "").split(",");
        int k = scanner.nextInt(), start = 0, end = 0, left = 0, right = 0;
        while (end < input.length) {
            if ((end + 1) % k == 0) {
                left = start;
                right = end;
                while (left <= right) {
                    input = swap(input, left++, right--);
                }
                start = end + 1;
            }
            end ++;
        }
        System.out.print("[");
        for (int i = 0; i < input.length; i ++) {
            System.out.print(input[i]);
            if (i != input.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }

    public static String[] swap(String[] input, int i, int j) {
        String temp = input[i];
        input[i] = input[j];
        input[j] = temp;
        return input;
    }
}
