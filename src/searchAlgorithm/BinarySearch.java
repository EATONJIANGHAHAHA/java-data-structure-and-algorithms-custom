/*
 * created by yichen jiang
 */
package searchAlgorithm;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.ArrayList;

/**
 * This is a Binary Search class containing both iteration and
 * recursion implementation of binary search. The array has to be a sorted
 * one otherwise the result will not correct.
 */
public class BinarySearch {

    /**
     * Make sure your array is in ascending order.
     * @param aNumber
     * @param numbers
     * @return the index of the target number, return null if array does not contain such element.
     */
    public static Integer searchAscending(@NotNull Integer aNumber, @NotNull ArrayList<Integer> numbers) {
        if (!numbers.contains(aNumber)) return null;
        int lowerBound = 0;
        int upperBound = numbers.size()- 1;
        int current;
        while (true) {
            current = (lowerBound + upperBound) / 2;
            if (lowerBound == current) current = upperBound;
            else if (upperBound == current) current = lowerBound;
            if (lowerBound > upperBound) return null;
            else if (numbers.get(current).equals(aNumber)) return current;
            else {
                if (numbers.get(current) > aNumber)
                {
                    upperBound = current;
                }
                else lowerBound = current;
            }
        }
    }

    /**
     * make sure your array is in descending order
     * @param aNumber
     * @param numbers
     * @return the index of the target number, return null if array does not contain such element.
     */
    public static Integer searchDscending(@NotNull Integer aNumber, @NotNull ArrayList<Integer> numbers) {
        if (!numbers.contains(aNumber)) return null;
        int lowerBound = 0;
        int upperBound = numbers.size()- 1;
        int current;
        while (true) {
            current = (lowerBound + upperBound) / 2;
            if (lowerBound == current) current = upperBound;
            else if (upperBound == current) current = lowerBound;
            if (lowerBound > upperBound) return null;
            else if (numbers.get(current).equals(aNumber)) return current;
            else {
                if (numbers.get(current) < aNumber)
                {
                    upperBound = current;
                }
                else lowerBound = current;
            }
        }
    }
}
