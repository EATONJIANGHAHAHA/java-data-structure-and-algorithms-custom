package questions;

import java.util.Scanner;

/**
 * 小易有一个长度为n的整数序列,a_1,...,a_n。然后考虑在一个空序列b上进行n次以下操作:
 * 1、将a_i放入b序列的末尾
 * 2、逆置b序列
 * 小易需要你计算输出操作n次之后的b序列。
 */
public class OperationSequence {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt(), rightPtr = number / 2, leftPtr = rightPtr - 1;
        int[] result = new int[number];
        boolean moveRight = number % 2 == 1;
        if (number > 0)
            result[rightPtr++] = scanner.nextInt();
        for (int i = 1; i < number; i ++) {
            if (moveRight) {
                result[rightPtr++] = scanner.nextInt();
            } else {
                result[leftPtr--] = scanner.nextInt();
            }
            moveRight = (! moveRight);
        }

        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}
