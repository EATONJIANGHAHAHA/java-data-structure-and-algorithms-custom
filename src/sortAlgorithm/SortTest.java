package sortAlgorithm;

import sortAlgorithm.bubbleSort.BubbleSort;
import sortAlgorithm.bucketSort.CounterSort;
import sortAlgorithm.heapSort.HeapSort;
import sortAlgorithm.insertionSort.InsertionSort;
import sortAlgorithm.mergeSort.MergeSort;
import sortAlgorithm.quickSort.QuickSort;
import sortAlgorithm.selectionSort.SelectionSort;
import sortAlgorithm.shellSort.ShellSort;

import java.util.Random;

public class SortTest {

    public static void main(String[] args) {

        int testLength = 999999999;

        int[] sample = new int[testLength];
        Random random = new Random(3);
        System.out.println("Generating numbers for calculations");
        for (int i = sample.length - 1; i > 0; i--) {
            //array[i] = i;
            sample[i] = random.nextInt(999);
        }
        System.out.println("Done generate numbers");

        Runnable bubble = new Runnable() {
            @Override
            public void run() {
                int[] array = getNewArray(sample);
                long startTime = System.currentTimeMillis();
                BubbleSort.sort(array);
                long endTime = System.currentTimeMillis();
                System.out.println((endTime - startTime));
            }
        };
        Runnable selection = new Runnable() {
            @Override
            public void run() {
                int[] array = getNewArray(sample);
                long startTime = System.currentTimeMillis();
                SelectionSort.sort(array);
                long endTime = System.currentTimeMillis();
                System.out.println((endTime - startTime));
            }
        };
        Runnable insertion = new Runnable() {
            @Override
            public void run() {
                int[] array = getNewArray(sample);
                long startTime = System.currentTimeMillis();
                InsertionSort.sort(array);
                long endTime = System.currentTimeMillis();
                System.out.println((endTime - startTime));
            }
        };
        Runnable shell = new Runnable() {
            @Override
            public void run() {
                int[] array = getNewArray(sample);
                long startTime = System.currentTimeMillis();
                ShellSort.sort(array);
                long endTime = System.currentTimeMillis();
                System.out.println((endTime - startTime));
            }
        };
        Runnable quick = new Runnable() {
            @Override
            public void run() {
                int[] array = getNewArray(sample);
                long startTime = System.currentTimeMillis();
                QuickSort.sort(array);
                long endTime = System.currentTimeMillis();
                System.out.println((endTime - startTime));
            }
        };
        Runnable merge = new Runnable() {
            @Override
            public void run() {
                int[] array = getNewArray(sample);
                long startTime = System.currentTimeMillis();
                MergeSort.sort(array);
                long endTime = System.currentTimeMillis();
                System.out.println((endTime - startTime));
            }
        };
        Runnable heap = new Runnable() {
            @Override
            public void run() {
                int[] array = getNewArray(sample);
                long startTime = System.currentTimeMillis();
                HeapSort.sort(array);
                long endTime = System.currentTimeMillis();
                System.out.println((endTime - startTime));
            }
        };
        Runnable bucket = new Runnable() {
            @Override
            public void run() {
                int[] array = getNewArray(sample);
                long startTime = System.currentTimeMillis();
                CounterSort.sort(array);
                long endTime = System.currentTimeMillis();
                System.out.println((endTime - startTime));
            }
        };

        Thread threadBubble = new Thread(bubble);
        Thread threadSelection = new Thread(selection);
        Thread threadInsertion = new Thread(insertion);
        Thread threadShell = new Thread(shell);
        Thread threadQuick = new Thread(quick);
        Thread threadMerge = new Thread(merge);
        Thread threadHeap = new Thread(heap);
        Thread threadBucket = new Thread(bucket);

        System.out.println("Start sorting");
        //threadBubble.start();
        //threadSelection.start();
        //threadInsertion.start();
        //threadShell.start();
        threadQuick.start();
        threadMerge.start();
        //threadHeap.start();
        threadBucket.start();
    }

    private static int[] getNewArray(int[] sample) {
        int[] array = new int[sample.length];
        for (int i = 0; i < sample.length; i++)
            array[i] = sample[i];
        return array;
    }
}
