package sortAlgorithm.insertionSort;

public class InsertionSort {

    public void sort(int[] array) {
        int mark;
        int inner;
        int outer;
        for (outer = 0; outer < array.length - 1; outer++) {
            mark = array[outer + 1];
            boolean flag = false;
            if (array[outer] < mark) continue;
            for (inner = outer; inner >= 0; inner--) {
                if (array[inner] > mark) {
                    array[inner + 1] = array[inner];
                    flag = true;
                }
                else break;
            }
            array[inner + 1] = (flag) ? mark : array[inner + 1];
        }
        System.out.println();
    }

    public static void main(String[] args) {
        InsertionSort insertionSort = new InsertionSort();
        insertionSort.sort(new int[] {12,3,1,467,6,8,5,12,8675,7,462,23,55});
    }
}
