package questions;

/**
 * 求一个有序数组的最大差值,时间复杂度不得高于等于O(N*logN),不得使用非基于比较的排序
 */
public class MaxGap {

    public static int solve(int[] array) {
        if (array == null || array.length < 2) return 0;
        boolean[] hasNumbers = new boolean[array.length + 1];
        int[] maxs = new int[array.length + 1];
        int[] mins = new int[array.length + 1];
        int length = array.length, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, maxGap = 0;
        for (int i = 0; i < array.length; i++) {
            min = Math.min(min, array[i]);
            max = Math.max(max, array[i]);
        }
        if (max == min) return 0;
        for (int i = 0; i < length; i++) {
            int bucket = (array[i] - min) * length / (max - min);
            hasNumbers[bucket] = true;
            mins[bucket] = Math.min(mins[bucket], array[i]);
            maxs[bucket] = Math.max(maxs[bucket], array[i]);
        }
        for (int i = 0; i < hasNumbers.length - 1; i++)
            maxGap = hasNumbers[i] && maxs[i] - mins[i + 1] > maxGap ? maxs[i] - mins[i + 1] : maxGap;
        return maxGap;
    }
}
