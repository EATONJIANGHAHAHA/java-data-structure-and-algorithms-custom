package questions;

import java.util.Stack;

/**
 * 怎样使用栈来实现一个队列?
 * @param <T>
 */
public class StackImplementedQueue<T> {

    private Stack<T> push = new Stack<>();
    private Stack<T> pop = new Stack<>();

    public T peek() {
        if (push.isEmpty() && pop.isEmpty()) throw new RuntimeException("ListQueue is empty.");
        if (pop.size() == 0) revert();
        return pop.peek();
    }

    public void push(T item) {
        push.push(item);
    }

    public T pop() {
        if (push.isEmpty() && pop.isEmpty()) throw new RuntimeException("ListQueue is empty.");
        if (pop.size() == 0) revert();
        return pop.pop();
    }

    private void revert() {
        while (!push.isEmpty())
            pop.push(push.pop());
    }
}
