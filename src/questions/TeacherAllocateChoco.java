package questions;


import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * 六一儿童节，老师带了很多好吃的巧克力到幼儿园。
 * 每块巧克力j的重量为w[j]，对于每个小朋友i，当他分到的巧克力大小达到h[i] (即w[j]>=h[i])，他才会上去表演节目。
 * 老师的目标是将巧克力分发给孩子们，使得最多的小孩上台表演。
 * 可以保证每个w[i]> 0且不能将多块巧克力分给一个孩子或将一块分给多个孩子。
 *
 * 输入描述
 *  第一行：n，表示h数组元素个数
 *  第二行：n个h数组元素
 *  第三行：m，表示w数组元素个数
 *  第四行：m个w数组元素
 */
public class TeacherAllocateChoco {

    public static void main(String[] args) {
        Queue<Integer> h = new PriorityQueue<>(Comparator.naturalOrder());
        Queue<Integer> w = new PriorityQueue<>(Comparator.naturalOrder());
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (; n > 0; n --) {
            h.add(scanner.nextInt());
        }
        int m = scanner.nextInt();
        for (; m > 0; m --) {
            w.add(scanner.nextInt());
        }
        int result = 0;
        while (!h.isEmpty() && !w.isEmpty()) {
            if (w.peek() >= h.peek()) {
                w.poll();
                h.poll();
                result ++;
            } else {
                w.poll();
            }
        }
        System.out.print(result);
    }
}
