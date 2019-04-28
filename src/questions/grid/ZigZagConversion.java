package questions.grid;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.LinkedList;

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * <p>
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 */
public class ZigZagConversion {

    public static String convert(String s, int numRows) {
        LinkedList<Character[]> list = new LinkedList<>();
        int count = 0;
        while (count < s.length()) {
            Character[] characters = new Character[numRows];
            for (int i = 0; i < characters.length && count < s.length(); i ++) {
                 characters[i] = s.charAt(count++);
            }
            list.add(characters);
            for (int temp = numRows - 2; temp > 0 && count < s.length(); temp--) {
                list.add(new Character[numRows]);
                list.get(list.size() - 1)[temp] = s.charAt(count++);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < numRows; i ++) {
            for (Character[] characters : list) {
                if (characters[i] != null)
                    stringBuilder.append(characters[i]);
            }
        }
        return stringBuilder.toString();
    }

    public static String convert2(String s, int numRows) {

        if (numRows == 1) return s;

        StringBuilder ret = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                ret.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n)
                    ret.append(s.charAt(j + cycleLen - i));
            }
        }
        return ret.toString();
    }

        public static void main(String[] args) {
        System.out.println(convert("LEETCODEISHIRING", 3));
    }
}
