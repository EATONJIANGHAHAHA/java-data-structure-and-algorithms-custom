package questions;

import java.util.Scanner;

//todo
public class BigStringMultiply {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num1 = scanner.nextInt(), num2 = scanner.nextInt(),
                num1copy = num1, digitNum1, digitNum2,
                carry = 0, result = 0, innerCount = 1, temp, innerResult = 0, outerCount = 1;
        while(num2 != 0) {
            digitNum2 = num2 % 10;
            while (num1copy != 0) {
                digitNum1 = num1copy % 10;
                num1copy /= 10;
                temp = digitNum2 * digitNum1 + carry;
                carry = temp / 10;
                innerResult += temp % 10 * innerCount;
                innerCount *= 10;
            }
            innerResult += carry * innerCount;
            result += innerResult * outerCount;
            num1copy = num1;
            innerCount = 1;
            innerResult = 0;
            outerCount *= 10;
            num2 /= 10;
        }
        System.out.println(result);
    }
}
