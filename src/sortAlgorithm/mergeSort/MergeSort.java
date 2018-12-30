package sortAlgorithm.mergeSort;

public class MergeSort {

    public void sort(int[] array) {
        if (array.length <= 1) return;
        recSort(array, 0, array.length - 1);
        System.out.println();
    }

    private void recSort(int[] array, int left, int right) {
        if (right - left <= 1) return;
        else {
            int mid = (left + right) / 2;
            recSort(array, left, mid);
            recSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    private void merge(int[] array, int left, int mid, int right) {
        int leftPtr = left;
        int rightPtr = mid + 1;
        int naPtr = 0;
        int[] newArray = new int[right - left + 1];
        while (leftPtr <= mid && rightPtr <= right) {
            newArray[naPtr++] = (array[leftPtr] < array[rightPtr])
                    ? array[leftPtr++] : array[rightPtr++];
        }
        while (leftPtr <= mid) {
            newArray[naPtr++] = array[leftPtr++];
        }
        while (rightPtr <= right) {
            newArray[naPtr++] = array[rightPtr++];
        }
        for (int i = 0; i < newArray.length; i++) {
            array[left + i] = newArray[i];
        }
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(new int[] {32,56,1,3,12,5,1,3,5,6,666});
    }
}
