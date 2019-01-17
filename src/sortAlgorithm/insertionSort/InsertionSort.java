package sortAlgorithm.insertionSort;

public class InsertionSort {

    public static int[] sort(int[] array) {
        int mark, inner, outer;
        for (outer = 0; outer < array.length - 1; outer++) {
            mark = array[outer + 1];
            if (array[outer] < mark) continue;
            for (inner = outer; inner >= 0 && array[inner] > mark; inner--)
                array[inner + 1] = array[inner];
            array[inner + 1] = array[inner + 1] > mark ? mark : array[inner + 1];
        }
        System.out.println("InsertionSort:");
        return array;
    }

    public static void main(String[] args) {
        sort(new int[]{12, 3, 1, 467, 6, 8, 5, 12, 8675, 7, 462, 23, 55});
    }
}
