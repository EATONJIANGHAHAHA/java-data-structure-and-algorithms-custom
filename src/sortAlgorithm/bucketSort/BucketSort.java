package sortAlgorithm.bucketSort;

public class BucketSort {

    public static int[] sort(int[] array) {
        if (array.length <= 1) return array;
        int arrayPtr = 0, bucketPtr = 0;
        int[] bucket = getBucket(array);
        for (int i = 0; i < array.length; i++)
            bucket[array[i]]++;
        while (bucketPtr < bucket.length) {
            if (bucket[bucketPtr] >= 1) {
                array[arrayPtr++] = bucketPtr;
                bucket[bucketPtr]--;
            }
            else bucketPtr++;
        }
        System.out.println("BucketSort:");
        return array;
    }

    private static int[] getBucket(int[] array) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            max = array[i] > max ? array[i] : max;
            min = array[i] < min ? array[i] : min;
        }
        if (min < 0) return new int[max - min + 1];
        return new int[max + 1];
    }

    public static void main(String[] args) {
        sort(new int[]{12,3,1,467,6,8,5,12,8675,7,462,23,55});
    }
}
