package sortAlgorithm;

import sortAlgorithm.bubbleSort.BubbleSort;
import sortAlgorithm.insertionSort.InsertionSort;
import sortAlgorithm.quickSort.QuickSort;
import sortAlgorithm.selectionSort.SelectionSort;
import sortAlgorithm.shellSort.ShellSort;

import java.util.Random;

public class SortTest {

    public static void main(String[] args) {
        int[] array = new int[99999999];
        Random random = new Random(7);
        System.out.println("Generating numbers for calculations");
        for (int i = array.length - 1; i > 0; i--) {
            //array[i] = i;
            array[i] = random.nextInt(9999999);
        }
        System.out.println("Done generating numbers");
        BubbleSort bubbleSort = new BubbleSort();
        SelectionSort selectionSort = new SelectionSort();
        InsertionSort insertionSort = new InsertionSort();
        ShellSort shellSort = new ShellSort();
        QuickSort quickSort = new QuickSort();

        Runnable bubble = new Runnable() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                bubbleSort.sort(array);
                long endTime = System.currentTimeMillis();
                System.out.println("Bubble:" + (endTime - startTime));
            }
        };
        Runnable selection = new Runnable() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                selectionSort.sort(array);
                long endTime = System.currentTimeMillis();
                System.out.println("selection:" + (endTime - startTime));
            }
        };
        Runnable insertion = new Runnable() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                insertionSort.sort(array);
                long endTime = System.currentTimeMillis();
                System.out.println("insertion:" + (endTime - startTime));
            }
        };
        Runnable shell = new Runnable() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                shellSort.sort1(array);
                long endTime = System.currentTimeMillis();
                System.out.println("shell:" + (endTime - startTime));
            }
        };
        Runnable quick = new Runnable() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                //quickSort.sort(array);
                long endTime = System.currentTimeMillis();
                System.out.println("quick = " + (endTime - startTime));
            }
        };

        Thread thread = new Thread(bubble);
        Thread thread1 = new Thread(selection);
        Thread thread2 = new Thread(insertion);
        Thread thread3 = new Thread(shell);
        Thread thread4 = new Thread(quick);

        System.out.println("Start sorting");
        thread.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
