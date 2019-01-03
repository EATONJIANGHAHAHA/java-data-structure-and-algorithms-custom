package sortAlgorithm.quickSort;

public class QuickSort {

    public void sort(int[] array) {
        if (array.length <= 1) return;
        recSort(array, 0, array.length);
    }

    private void recSort(int[] array, int left, int right) {
        if (right - left < 1) return;
        int partition = partition(array, array[right - 1], left, right);
        recSort(array, left, partition);
        recSort(array, partition + 1, right);
    }

    private int partition(int[] array, int number, int left, int right) {
        int less = left - 1;
        int more = right;
        int current = left;
        while (current < more) {
            if (array[current] < number)
                swap(array, current++, ++less);
            else if (array[current] > number)
                swap(array, current, --more);
            else
                current++;
        }
        return more - 1;
    }

    private void swap(int[] result, int j, int i) {
        int temp = result[j];
        result[j] = result[i];
        result[i] = temp;
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        quickSort.sort(new int[]{32,56,1,3,12,666,5,1,3,5});
    }
}
