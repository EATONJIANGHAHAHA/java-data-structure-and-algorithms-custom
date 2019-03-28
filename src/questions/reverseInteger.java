package questions;

import java.util.Stack;

public class reverseInteger {

    public static int reverse(int x) {
        try {
            return wtf(x);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private  static int wtf(int x) throws NumberFormatException{
        String s = Integer.toString(x);
        Stack<Character> stack = new Stack<>();
        for (Character c : s.toCharArray()) {
            stack.push(c);
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (!stack.isEmpty()) {
            Character c = stack.pop();
            if (c == '0' && stringBuilder.capacity() == 0)
                continue;
            stringBuilder.append(c);
        }
        if (x < 0) {
            stringBuilder.insert(0, "-");
            stringBuilder.deleteCharAt(stringBuilder.lastIndexOf("-"));
        }

        return Integer.valueOf(stringBuilder.toString());
    }

    public static void main(String[] args) {
        System.out.println(reverse(964632435));
    }
}
