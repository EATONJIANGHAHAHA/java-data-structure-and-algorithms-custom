package sortAlgorithm.bubbleSort;

/**
 * 世界上最简单的排序算法, 通过将每一遍递归中最大的值与下一个值以此交换位置从而达到他应该在的位置的这种操作类似于冒牌而得名.
 * 因为每一次排序后被排序的元素都Yui敬佩交换到了他应该在的位置了, 所以下一次遍历是可以忽略之前所有排序过的元素的.
 */
public class BubbleSort {

    public void sort(int[] array) {
        for (int i = array.length -1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j+1])
                    swap(array, j, j+1);
            }
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
        BubbleSort arrayBub = new BubbleSort();
        arrayBub.sort(array);
        System.out.println("array = " + array);
    }
}
