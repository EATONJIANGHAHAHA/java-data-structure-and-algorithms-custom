package questions;

import java.util.Stack;

public class StackImplementedQueue<T> {

    private Stack<T> push = new Stack<>();
    private Stack<T> pop = new Stack<>();

    public T peek() {
        if (pop.size() == 0) revert();
        return pop.peek();
    }

    public void push(T item) {
        push.push(item);
    }

    public T pop() {
        if (pop.size() == 0) revert();
        return pop.pop();
    }

    private void revert() {

    }
}
