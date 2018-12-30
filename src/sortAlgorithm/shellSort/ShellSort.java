package sortAlgorithm.shellSort;

import java.util.Random;

public class ShellSort {

    public void sort(int[] theArray) {
        int inner, outer;
        int temp;
        int nElems = theArray.length;

        int knuth = 1;                     // find initial value of knuth
        while(knuth <= nElems/3)
            knuth = knuth*3 + 1;                // (1, 4, 13, 40, 121, ...)

        while(knuth>0)                     // decreasing knuth, until knuth=1
        {
            // knuth-sortOrigin the file
            for(outer=knuth; outer<nElems; outer++)
            {
                temp = theArray[outer];
                inner = outer;
                // one subpass
                while(inner > knuth-1 && theArray[inner-knuth] >=  temp)
                {
                    theArray[inner] = theArray[inner-knuth];
                    inner -= knuth;
                }
                theArray[inner] = temp;
            }
            knuth = (knuth-1) / 3;              // decrease knuth
        }
    }

    public void sort1(int[] array) {
        int knuth = 1, length = array.length, outer, inner = 0, mark = 0;
        boolean flage = false;

        while (knuth <= length / 3)
            knuth = knuth * 3 + 1;

        while (knuth > 0) {
            for (outer = 0; outer < length - knuth; outer++) {
                mark = array[outer + knuth];
                if (mark > array[outer]) continue;
                for (inner = outer; inner >= 0; inner -= knuth) {
                    if (array[inner] > mark && (inner + knuth) < length) {
                        array[inner + knuth] = array[inner];
                        flage = true;
                    }
                    else if (array[inner] <= mark && array[inner + knuth] != mark && flage) {
                        break;
                    }
                }
                if (flage) {
                    if (inner < 0) inner += knuth;
                    array[inner] = mark;
                    flage = false;
                }
            }
            knuth = (knuth - 1) / 3;
        }
        System.out.println();
    }

    public static void main(String[] args) {
/*        ShellSort shellSort = new ShellSort();
        int[] array = new int[999999];
        Random random = new Random(7);
        System.out.println("Generating random numbers for calculations");
        for (int i = array.length - 1; i > 0; i--) {
            array[i] = i;
        }
        System.out.println("Done generating random numbers");
        System.out.println("Time count starts");
        long startTime = System.currentTimeMillis();
        shellSort.sort(array);
        long endTime = System.currentTimeMillis();
        System.out.println("Calculation done, takes" + (endTime - startTime) + " milli seconds to complete");*/
        ShellSort shellsort = new ShellSort();
        int[] array = {29,1,34,666,758,2,34,89,24,71};
        shellsort.sort(array);
        System.out.println();
    }
}
