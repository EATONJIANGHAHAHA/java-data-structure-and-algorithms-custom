package sortAlgorithm.heapSort;

/**
 * 堆排序
 */
public class HeapSort {

    public static int[] sort(int[] array) {
        if (array.length < 2) return array;
        for (int i = 0; i < array.length; i++)
            heapInsert(array, i);
        int heapSize = array.length;
        while (heapSize > 0) {
            swap(array, 0, --heapSize);
            heapify(array, 0, heapSize);
        }
        System.out.println("HeapSort:");
        return array;
    }

    private static void heapInsert(int[] array, int index) {
        while (array[index] > array[(index - 1) / 2]) {
            swap(array, index, (index - 1) /2);
            index = (index - 1) / 2;
        }
    }

    private static void heapify(int[] array, int index, int heapSize) {
        int left = 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize & array[left] < array[left + 1] ? left + 1: left;
            if (array[index] >= array[largest]) break;
            swap(array, index, largest);
            index = largest;
            left = 2 * index + 1;
        }
    }

    private static void swap(int[] result, int j, int i) {
        int temp = result[j];
        result[j] = result[i];
        result[i] = temp;
    }

    public static void main(String[] args) {
        sort(new int[]{32,56,1,3,12,666,5,1,3,5});
    }
}
