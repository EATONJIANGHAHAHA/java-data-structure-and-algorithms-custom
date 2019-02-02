package sortAlgorithm.bucketSort;

import list.LinkedList;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class BucketSort {

    public static class MaxMinLinkedList {
        LinkedList list = new LinkedList();
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

        public void add() {}
    }

    public static int[] sort(int[] array) {
        LinkedList[] buckets = getBuckets(getBucketNumber(array.length));
        int[] range = getRange(array);
        for (int number : array) {
            int index = getBucketIndex(number, range, buckets.length);
            buckets[index].add(number);
        }
        for (LinkedList bucket : buckets) sort(bucket);
        throw new NotImplementedException();
    }

    private static void sort(LinkedList bucket) {

    }

    private static int getBucketIndex(int number, int[] range, int length) {
        return (number - range[1]) * length / (range[0]- range[1]);
    }

    private static int[] getRange(int[] array) {
        int[] result = {Integer.MIN_VALUE, Integer.MAX_VALUE};
        for (int value: array) {
            result[0] = result[0] < value ? value : result[0];
            result[1] = result[1] > value ? value : result[1];
        }
        return result; //result{max, min}
    }

    private static LinkedList[] getBuckets(int bucketNumber) {
        return new LinkedList[10];
    }

    private static int getBucketNumber(int length) {
        return 10;
    }
}
