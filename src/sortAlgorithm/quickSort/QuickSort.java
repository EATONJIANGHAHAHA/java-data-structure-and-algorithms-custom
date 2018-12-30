    package sortAlgorithm.quickSort;



public class QuickSort {

    private void sort(int[] array) {
        if (array.length <= 1) return;
        recSort(array, 0, array.length - 1);
    }

    private void recSort(int[] array, int left, int right) {

    }

    private void partition(int[] array, int number, int left, int right) {
        int less = left - 1;
        int more = right;
        int current = left;
        while (current < more) {
            if (array[current] < number)
                swap (array, current++, ++less);
            else if (array[current] > number)
                swap(array, current, --more);
            else
                current++;
        }
        System.out.println("current = " + (current - 1));
    }

    private void swap(int[] result, int j, int i) {
        int temp = result[j];
        result[j] = result[i];
        result[i] = temp;
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        quickSort.partition(new int[]{3,2,6,9,7}, 6, 0, 5);
    }
}
