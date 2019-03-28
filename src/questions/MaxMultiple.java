package questions;

import java.util.Scanner;

public class MaxMultiple {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] array = new long[n];
        for (int i = 0; i <n ; i++) {
            array[i] = sc.nextLong();
        }
        getLargestMultiple(array,n);
    }

    private static void getLargestMultiple(long[] array, int length) {
        long max1 = Long.MIN_VALUE, max2 = max1, max3 = max1, min1 = Long.MAX_VALUE, min2 = min1;
        for (long num : array) {
            if (num == 0) continue;
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3)
                max3 = num;
            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2)
                min2 = num;
        }
        System.out.println(Math.max(max1 * max2 * max3, max1 * min1 * min2));
    }
}
