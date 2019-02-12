package sortAlgorithm;

import sortAlgorithm.bubbleSort.BubbleSort;
import sortAlgorithm.bucketSort.BucketSort;
import sortAlgorithm.counterSort.CounterSort;
import sortAlgorithm.heapSort.HeapSort;
import sortAlgorithm.insertionSort.InsertionSort;
import sortAlgorithm.mergeSort.MergeSort;
import sortAlgorithm.quickSort.QuickSort;
import sortAlgorithm.selectionSort.SelectionSort;
import sortAlgorithm.shellSort.ShellSort;

import java.util.Random;

public class SortTest {

    public static void main(String[] args) {

        int testLength = 9999999;

        int[] sample = new int[testLength];
        Random random = new Random(3);
        System.out.println("Generating numbers for calculations");
        for (int i = sample.length - 1; i > 0; i--) {
            //array[i] = i;
            sample[i] = random.nextInt(999999);
        }
        System.out.println("Done generate numbers");

        Thread threadBubble = new Thread(() -> {
            int[] array = getNewArray(sample);
            long startTime = System.currentTimeMillis();
            BubbleSort.sort(array);
            long endTime = System.currentTimeMillis();
            System.out.println((endTime - startTime));
        });
        Thread threadSelection = new Thread(() -> {
            int[] array = getNewArray(sample);
            long startTime = System.currentTimeMillis();
            SelectionSort.sort(array);
            long endTime = System.currentTimeMillis();
            System.out.println((endTime - startTime));
        });
        Thread threadInsertion = new Thread(() -> {
            int[] array = getNewArray(sample);
            long startTime = System.currentTimeMillis();
            InsertionSort.sort(array);
            long endTime = System.currentTimeMillis();
            System.out.println((endTime - startTime));
        });
        Thread threadShell = new Thread(() -> {
            int[] array = getNewArray(sample);
            long startTime = System.currentTimeMillis();
            ShellSort.sort(array);
            long endTime = System.currentTimeMillis();
            System.out.println((endTime - startTime));
        });
        Thread threadQuick = new Thread(() -> {
            int[] array = getNewArray(sample);
            long startTime = System.currentTimeMillis();
            QuickSort.sort(array);
            long endTime = System.currentTimeMillis();
            System.out.println((endTime - startTime));
        });
        Thread threadMerge = new Thread(() -> {
            int[] array = getNewArray(sample);
            long startTime = System.currentTimeMillis();
            MergeSort.sort(array);
            long endTime = System.currentTimeMillis();
            System.out.println((endTime - startTime));
        });
        Thread threadHeap = new Thread(() -> {
            int[] array = getNewArray(sample);
            long startTime = System.currentTimeMillis();
            HeapSort.sort(array);
            long endTime = System.currentTimeMillis();
            System.out.println((endTime - startTime));
        });
        Thread threadCounter = new Thread(() -> {
            int[] array = getNewArray(sample);
            long startTime = System.currentTimeMillis();
            CounterSort.sort(array);
            long endTime = System.currentTimeMillis();
            System.out.println((endTime - startTime));
        });
        Thread threadBucket = new Thread(() -> {
            int[] array = getNewArray(sample);
            long startTime = System.currentTimeMillis();
            BucketSort.sort(array);
            long endTime = System.currentTimeMillis();
            System.out.println((endTime - startTime));
        });

        System.out.println("Start sorting");
//        threadBubble.start();
//        threadSelection.start();
//        threadInsertion.start();
        threadShell.start();
        threadQuick.start();
//        threadMerge.start();
//        threadHeap.start();
        threadCounter.start();
        threadBucket.start();
    }

    private static int[] getNewArray(int[] sample) {
        int[] array = new int[sample.length];
        for (int i = 0; i < sample.length; i++)
            array[i] = sample[i];
        return array;
    }
}
