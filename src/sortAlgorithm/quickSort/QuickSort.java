package sortAlgorithm.quickSort;

/**
 * 随机快速排序, 递归版本, TODO:更改为迭代版本
 */
public class QuickSort {

    public static int[] sort(int[] array) {
        if (array.length <= 1) return array;
        recSort(array, 0, array.length - 1);
        System.out.println("QuickSort:");
        return array;
    }

    private static void recSort(int[] array, int left, int right) {
        if (right - left > 1) {
            swap(array, left + (int) (Math.random() * (right - left + 1)), right);
            int[] partition = partition(array, array[right], left, right);
            recSort(array, left, partition[0]);
            recSort(array, partition[1], right);
        }
    }

    private static int[] partition(int[] array, int number, int left, int right) {
        int less = left - 1;
        int more = right + 1;
        int current = left;
        while (current < more) {
            if (array[current] < number)
                swap(array, current++, ++less);
            else if (array[current] > number)
                swap(array, current, --more);
            else
                current++;
        }
        return new int[] {less, more};
    }

    private static void swap(int[] result, int j, int i) {
        int temp = result[j];
        result[j] = result[i];
        result[i] = temp;
    }

    public static void main(String[] args) {
        sort(new int[]{32,56,1,3,3,12,666});
    }
}
