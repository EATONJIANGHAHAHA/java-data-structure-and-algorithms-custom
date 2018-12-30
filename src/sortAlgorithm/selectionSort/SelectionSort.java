package sortAlgorithm.selectionSort;

/**
 * 选择排序就是在每一遍递归的时候选择出除了已经排好序的最小的索引, 并把它放到指定的位置.
 * 这个指定的位置之所以可以确定就是因为要保证每一遍递归找到的都是当前可以找到的最小的.
 * 然后将这些找到的东西一次放到指定的位置就可以了.
 */
public class SelectionSort {

    public void sort(int[] array) {
        int smallestIndex;
        for (int i = 0; i < array.length; i++) {
            smallestIndex = i;
            for (int j = i; j < array.length; j++)
                smallestIndex = (array[j] < array[smallestIndex]) ? j : smallestIndex;
            swap(array, i, smallestIndex);
        }
    }

    private void swap(int[] result, int j, int i) {
        int temp = result[j];
        result[j] = result[i];
        result[i] = temp;
    }

    public static void main(String[] args) {
        int[] array = new int[5];
        array[0] = 5;
        array[1] = 55;
        array[2] = 1;
        array[3] = 23;
        array[4] = 2;
        SelectionSort selectionSort = new SelectionSort();
        System.out.println("array = " + array);
        selectionSort.sort(array);
    }
}
