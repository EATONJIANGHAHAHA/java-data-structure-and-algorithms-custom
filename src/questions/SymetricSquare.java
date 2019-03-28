package questions;

/**
 * 打印所有不超过n（n<256）的，其平方具有对称性质的数。如11*11=121。
 */
public class SymetricSquare {

    public static void main(String[] args) {
        solve();
    }

    private static void solve() {
        int result = 0;
        for (int i = 1; i <= 256; i ++) {
            result = (int) Math.pow(i, 2);
            if (isSymetric(result)) System.out.println(i);
        }
    }

    private static boolean isSymetric(int result) {
        char[] chars = String.valueOf(result).toCharArray();
        int left = 0, right = chars.length - 1;
        while (left < right && chars[left] == chars[right]) {
            left ++;
            right --;
        }
        return left >= right;
    }
}
