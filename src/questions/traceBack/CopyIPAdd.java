package questions.traceBack;


import java.util.LinkedList;

import java.util.Scanner;

/**
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 *
 * 示例:
 *
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 */
public class CopyIPAdd {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        LinkedList<String> result = solve(input, "", new LinkedList<>());
        if (input.length() > 12) return;
    }

    private static LinkedList<String> solve(String rest, String valid, LinkedList<String> result) {
        if (valid.matches("([0-9]+\\.){3}")) {
            if (isValidSubIP(rest))
                result.add(valid + rest);
            return result;
        }
        for (int i = 1; i < rest.length(); i++) {
            String sub = rest.substring(0, i);
            if (isValidSubIP(sub))
                solve(rest.substring(i), valid + sub + ".", result);
            else return result;
        }
        return result;
    }

    private static boolean isValidSubIP(String sub) {
        return Long.parseLong(sub) >= 0 && Long.parseLong(sub) < 256;
    }
}
