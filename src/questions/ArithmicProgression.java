package questions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class ArithmicProgression {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> queue = new PriorityQueue<>(Integer::compareTo);
        int number = Integer.parseInt(br.readLine());
        if (number < 3) {
            System.out.println("Possible");
            return;
        }
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < number; i ++){
            queue.add(Integer.parseInt(input[i]));
        }
        int last = queue.poll();
        int lastd = queue.peek() - last;
        last = queue.poll();
        while (!queue.isEmpty()) {
            if (queue.peek() - last != lastd) {
                System.out.print("Impossible");
                return;
            }
            last = queue.poll();
        }
        System.out.print("Possible");
    }
}
