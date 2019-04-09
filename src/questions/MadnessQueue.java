package questions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MadnessQueue {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        String[] s = reader.readLine().split(" ");
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(s[i]);
        }
        int lptr = 0, rptr = n - 1, mid = (n / 2), result;
        int[] sorted = new int[n];
        boolean leftMove = true;
        Arrays.sort(array);
        sorted[mid] = array[rptr--];
        for (int i = 1; rptr >= lptr; i++) {
            if (leftMove) {
                sorted[mid - i] = array[lptr++];
                if (lptr > rptr) break;
                sorted[mid + i] = array[lptr++];
            } else {
                sorted[mid - i] = array[rptr--];
                if (lptr > rptr) break;
                sorted[mid + i] = array[rptr--];
            }
            leftMove = (!leftMove);
        }
        result = sorted[sorted.length - 1] == sorted[sorted.length - 2]
                && sorted[sorted.length - 1] != sorted[0]
                ? Math.abs(sorted[sorted.length - 1] - sorted[0])
                : 0;
        for (int i = 0; i < sorted.length - 1; i++) {
            result += Math.abs(sorted[i + 1] - sorted[i]);
        }
        System.out.println(result);
    }
}
