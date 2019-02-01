package list;

public class Stack<T> {

    protected LinkedList<T> list;

    public Stack() {
        list = new LinkedList<>();
    }

    private boolean isEmpty(){
        return list.isEmpty();
    }

    private Integer size() {
        return list.size();
    }

    public T peek() {
        if (isEmpty()) throw new IndexOutOfBoundsException("This stack is empty.");
        return list.head.data;
    }

    public void push(T data) {
        list.add(data);
    }

    public T pop() {
        if (isEmpty()) throw new IndexOutOfBoundsException("This stack is empty.");
        return list.remove();
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(20);
        stack.push(1);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
