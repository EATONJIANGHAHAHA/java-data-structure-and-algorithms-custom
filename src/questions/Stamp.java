package questions;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Stamp {
    public static void main(String[] args) {
        solve();
    }

    private static void solve() {
        char c = 'c';
        int i = Integer.valueOf(String.valueOf(c));
        int num08, num10, num18, temp;
        Set<Integer> set = new HashSet<>();
        for (num08 = 5; num08 > 0; num08 --) {
            for (num10 = 4; num10 > 0; num10 --) {
                for (num18 = 6; num18 > 0; num18 --) {
                    set.add(8 * num08 + 10 * num10 + num18 * 18);
                }
            }
        }
        System.out.println(set.size());
    }
}
