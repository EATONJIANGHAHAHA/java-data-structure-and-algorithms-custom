package sortAlgorithm.bubbleSort;

public class BubbleSort {

    public static int[] sort(int[] array) {
        int outer, inner;
        for (outer = array.length -1; outer > 0; outer--) {
            for (inner = 0; inner < outer; inner++) {
                if (array[inner] > array[inner+1])
                    swap(array, inner, inner+1);
            }
        }
        System.out.println("BubbleSort:");
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
