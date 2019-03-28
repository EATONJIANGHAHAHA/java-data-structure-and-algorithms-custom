package questions;

import java.util.Scanner;
import java.util.Stack;

//todo
public class BigStringMultiply {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] numbers = scanner.nextLine().split(" ");
        char[] num1 = numbers[0].toCharArray(), num2 = numbers[1].toCharArray();
        int num1I = num1.length - 1, num2I = num2.length - 1, num1c, num2c, temp = 0, mult;
        Stack<Integer> stack = new Stack<>();
        while (num1I >= 0 && num2I >= 0) {
            num1c = Integer.parseInt(String.valueOf(num1[num1I--]));
            num2c = Integer.parseInt(String.valueOf(num2[num2I--]));
            mult = num1c * num2c + temp;
            temp = mult / 10;
            stack.push(mult % 10);
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
        }
    }
}
