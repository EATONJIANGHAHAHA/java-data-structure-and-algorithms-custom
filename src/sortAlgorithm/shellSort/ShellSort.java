package sortAlgorithm.shellSort;

public class ShellSort {

    public static int[] sort(int[] array) {
        int increment = 1, length = array.length, outer, middle, inner, mark;
        increment = getIncrement(increment, length);
        while (increment > 0) {
            for (outer = 0; outer < increment; outer++) {
                for (middle = outer; middle + increment < length; middle += increment) {
                    mark = array[middle + increment];
                    if (array[middle] < mark) continue;
                    for (inner = middle; inner >= outer && array[inner] > mark; inner -= increment)
                        array[inner + increment] = array[inner];
                    array[inner + increment] = array[inner + increment] > mark ? mark : array[inner + increment];
                }
            }
            increment = (increment - 1) / 3;
        }
        System.out.println("ShellSort:");
        return array;
    }

    private static int getIncrement(int increment, int length) {
        while (increment <= length / 3)
            increment = increment * 3 + 1;
        return increment;
    }

    public static void main(String[] args) {
        sort(new int[]{12, 3, 1, 467, 6, 8, 5, 12, 8675, 7, 462, 23, 55});
    }
}
