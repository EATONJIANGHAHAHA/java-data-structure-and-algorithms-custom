package questions.greedyAlgorithm;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 一块金条切成两半,是需要花费和长度数值一样的铜板的。比如
 * 长度为20的 金条,不管切成长度多大的两半,都要花费20个铜
 * 板。一群人想整分整块金 条,怎么分最省铜板?
 * 例如,给定数组{10,20,30},代表一共三个人,整块金条长度为
 * 10+20+30=60. 金条要分成10,20,30三个部分。 如果, 先把长
 * 度60的金条分成10和50,花费60 再把长度50的金条分成20和30,
 * 花费50 一共花费110铜板。
 * 但是如果, 先把长度60的金条分成30和30,花费60 再把长度30
 * 金条分成10和20,花费30 一共花费90铜板。
 * 输入一个数组,返回分割的最小代价。
 */
public class Halfman {

    public static void solve(int[] array) {
        if (array == null) System.out.println(0);
        Queue<Integer> queue = new PriorityQueue<>((Integer::compareTo));
        int result = 0, num1, num2;
        for (int number : array)
            queue.add(number);
        while (queue.size() > 1) {
            num1 = queue.poll();
            num2 = queue.poll();
            result += num1 + num2;
            queue.add(result);
        }
        if (array.length > 0) System.out.println(queue.poll());
        else System.out.println(0);
    }

    public static void main(String[] args) {
        solve(null);
    }
}
