package sortAlgorithm.selectionSort;

public class SelectionSort {

    public static int[] sort(int[] array) {
        int smallest, outer, inner;
        for (outer = 0; outer < array.length; outer++) {
            smallest = outer;
            for (inner = outer; inner < array.length; inner++)
                smallest = (array[inner] < array[smallest]) ? inner : smallest;
            swap(array, outer, smallest);
        }
        System.out.println("SelectionSort:");
        return array;
    }

    private static void swap(int[] result, int j, int i) {
        int temp = result[j];
        result[j] = result[i];
        result[i] = temp;
    }

    public static void main(String[] args) {
        sort(new int[] {12,3,1,467,6,8,5,12,8675,7,462,23,55});
    }
}
