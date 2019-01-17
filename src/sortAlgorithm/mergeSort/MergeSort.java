package sortAlgorithm.mergeSort;

/**
 * 归并排序, 递归版本, TODO:更改为迭代版本
 */
public class MergeSort {

    public static int[] sort(int[] array) {
        if (array.length <= 1) return array;
        recSort(array, 0, array.length - 1);
        System.out.println("MergeSort:");
        return array;
    }

    private static void recSort(int[] array, int left, int right) {
        if (right - left <= 1) return;
        int mid = (left + right) / 2;
        recSort(array, left, mid);
        recSort(array, mid + 1, right);
        merge(array, left, mid, right);
    }

    private static void merge(int[] array, int left, int mid, int right) {
        int leftPtr = left, rightPtr = mid + 1, naPtr = 0;
        int[] newArray = new int[right - left + 1];
        while (leftPtr <= mid && rightPtr <= right)
            newArray[naPtr++] = array[leftPtr] < array[rightPtr] ? array[leftPtr++] : array[rightPtr++];
        while (leftPtr <= mid)
            newArray[naPtr++] = array[leftPtr++];
        while (rightPtr <= right)
            newArray[naPtr++] = array[rightPtr++];
        if (newArray.length >= 0)
            System.arraycopy(newArray, 0, array, left, newArray.length);
    }

    public static void main(String[] args) {
        sort(new int[]{32, 56, 1, 3, 12, 5, 1, 3, 5, 6, 666});
    }
}
