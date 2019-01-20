package questions;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 怎样使用队列来实现一个栈?
 * @param <T>
 */
public class QueueImplementedStack<T> {

    private Queue<T> data = new LinkedList<>();
    private Queue<T> help = new LinkedList<>();

    public T peek() {
        T object = pop();
        push(object);
        return object;
    }

    public void push(T item) {
        data.add(item);
    }

    public T pop() {
        while (data.size() > 1)
            help.add(data.poll());
        T object = data.poll();
        swap();
        return object;
    }

    private void swap() {
        Queue<T> temp = data;
        data = help;
        help = temp;
    }
}
