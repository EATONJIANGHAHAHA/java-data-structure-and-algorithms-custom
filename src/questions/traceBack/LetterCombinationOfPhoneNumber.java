package questions.traceBack;

/**
 * 17. 对于老式手机来说，每一个字母都代表三到四个特定的字母：
 * 2:abc
 * 3:def
 * 4:ghi
 * 5:jkl
 * 6:mno
 * 7:pqrs
 * 8:tuv
 * 9:wxyz
 *
 * 给定一个只包含以上数字的数字字符串，返回所有可能出现的字母字符串数组，数组元素可以不固定位置。
 */
public class LetterCombinationOfPhoneNumber {

    public static void main(String[] args) {
        solve("235");
    }

    private static void solve(String number) {
        char[] numbers = number.toCharArray();
        char[][] letter = {
                {},{},
                {'a','b','c'},
                {'d','e','f'},
                {'g','h','i'},
                {'j','k','l'},
                {'m','n','o'},
                {'p','q','r', 's'},
                {'t','u','v'},
                {'w','x','v', 'z'},
        };
        solve(letter, numbers, 0, "");
    }

    /**
     * 递归体
     * @param letter 字母数组
     * @param numbers 数字数组
     * @param numberi 数组、字母数组父下标
     */
    private static void solve(char[][] letter, char[] numbers, int numberi, String s) {
        if (numberi == numbers.length) {
            System.out.print(s + ", ");
            return;
        }
        for (int i = 0; i < letter[Integer.parseInt(String.valueOf(numbers[numberi]))].length; i++) {
            solve(
                    letter,
                    numbers,
                    numberi + 1,
                    s + letter[Integer.parseInt(String.valueOf(numbers[numberi]))][i]
            );
        }
    }
}
