package questions.traceBack;

import java.util.Scanner;

public class SecreatStone {

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt(), sum = 0, count = 0;
//        int[] a = new int[n];
//        for (int i = 0; i < n; i++)
//            a[i] = scanner.nextInt();
//        for (int i = 0; i < n - 1; i++) {
//            if (a[i] > a[i + 1]) {
//                sum -= a[i + 1];
//                count++;
//            } else if (a[i] < a[i + 1]) {
//                sum += a[i + 1];
//                count++;
//            }
//        }
//        System.out.println(sum + " " + count);
//    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), sum = 0, count = 0;
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = scanner.nextInt();
        solve(a, 0, 0, false);
    }

    private static void solve(int[] a, int current, int sum, boolean buy) {
        if (current == a.length - 1) return;
        if (buy) {
            sum -= a[current];
            buy = false;
        }
        else {
            for (int i = 0; i < a.length; i++) {
                sum += a[current];
                buy = true;
            }
        }
        System.out.println(sum);
    }
}
