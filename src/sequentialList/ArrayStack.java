package sequentialList;

/**
 * TODO:改为ArrayList实现
 */
public class ArrayStack {

    protected Integer[] array;
    protected int index;

    public ArrayStack(int size){
        if (size < 0) throw new IllegalArgumentException("The size of the stack must be bigger than 0");
        array = new Integer[size];
        index = 0;
    }

    public Integer peek() {
        if (index == 0) return null;
        return array[index -1];
    }

    public void push(Integer object) {
        if (index == array.length) throw new ArrayIndexOutOfBoundsException("This stack is full.");
        array[index++] = object;
    }

    public Integer pop() {
        if (index == 0) throw new ArrayIndexOutOfBoundsException("This stack is empty.");
        return array[--index];
    }

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);

    }
}
