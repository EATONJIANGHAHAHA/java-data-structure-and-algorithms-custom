package sortAlgorithm.bucketSort;


import java.util.LinkedList;

/**
 * 时间复杂度不达标,目测只达到到n*log n.
 */
public class BucketSort {

    private static class MaxMinLinkedList{

        private LinkedList<Integer> list = new LinkedList<>();
        private Integer max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

        public void add(Integer number) {
            list.add(number);
            max = max < number ? number : max;
            min = min > number ? number : min;
        }

        public MaxMinLinkedList addAll(MaxMinLinkedList list) {
            this.list.addAll(list.list);
            return this;
        }
    }

    public static int[] sort(int[] array) {
        int[] range = getRange(array);
        MaxMinLinkedList[] buckets = getBuckets(getBucketNumber(range[0] - range[1]));
        long startTime = System.currentTimeMillis();
        for (int number : array) {
            int index = getBucketIndex(number, range[0], range[1], buckets.length - 1);
            buckets[index].add(number);
        }
        long endTime = System.currentTimeMillis();
        int index = 0;
        for (MaxMinLinkedList bucket : buckets) {
            if (!bucket.list.isEmpty()) {
                bucket = sort(bucket);
                while (!bucket.list.isEmpty()) {
                    array[index++] = bucket.list.remove();
                }
            }
        }
        System.out.println("BucketSort:");
        System.out.println(endTime - startTime);
        return array;
    }

    private static MaxMinLinkedList sort(MaxMinLinkedList bucket) {
        if (bucket.list.size() == 1) return bucket;
        if (bucket.max - bucket.min == 0) return bucket;
        MaxMinLinkedList[] smallBuckets = getBuckets(getBucketNumber(bucket.max - bucket.min));
        for (Integer number : bucket.list) {
            int index = getBucketIndex(number, bucket.max, bucket.min, smallBuckets.length - 1);
            smallBuckets[index].add(number);
        }
        int index = 0;
        MaxMinLinkedList result = new MaxMinLinkedList();
        for (MaxMinLinkedList smallBucket: smallBuckets) {
            if (!smallBucket.list.isEmpty()) {
                result.addAll(sort(smallBucket));
            }
        }
        bucket = result;
        return bucket;
    }

    private static int getBucketIndex(int number, int max, int min, int length) {
        if (max - min == 0) return 0;
        return (number - min) * length / (max - min);
    }

    private static int[] getRange(int[] array) {
        int[] result = {Integer.MIN_VALUE, Integer.MAX_VALUE};
        for (int value: array) {
            result[0] = result[0] < value ? value : result[0];
            result[1] = result[1] > value ? value : result[1];
        }
        return result; //result{max, min}
    }

    private static MaxMinLinkedList[] getBuckets(int bucketNumber) {
        MaxMinLinkedList[] lists = new MaxMinLinkedList[bucketNumber];
        for (int i = 0; i < lists.length; i ++) lists[i] = new MaxMinLinkedList();
        return lists;
    }

    private static int getBucketNumber(int range) {
        return 2000 + 1;
    }

    public static void main(String[] args) {
        sort(new int[]{20, -9, 5, 11, 14, 8, 2, 1, 19, 5, 3, 7, 17, 0});
    }
}
