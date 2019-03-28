package questions;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 */
public class findMidInTwoSortedArray {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int lengthall = nums1.length + nums2.length;
        int l = (lengthall + 1) / 2;
        int r = (lengthall + 2) / 2;

        return (getKMin(nums1, 0, nums2, 0, l) +
                getKMin(nums1, 0, nums2, 0, r)) * 1.0 / 2;
    }

    private static int getKMin(int[] x, int xStart, int[] y, int yStart, int k) {

        if (xStart > x.length - 1) {
            return y[yStart + k - 1];
        }
        if (yStart > y.length - 1) {
            return x[xStart + k - 1];
        }
        if (k == 1) {
            return Math.min(x[xStart], y[yStart]);
        }

        int xMin = Integer.MAX_VALUE, yMin = Integer.MAX_VALUE;
        if (xStart + k / 2 - 1 < x.length) {
            xMin = x[xStart + k / 2 - 1];
        }
        if (yStart + k / 2 - 1 < y.length) {
            yMin = y[yStart + k / 2 - 1];
        }

        if (xMin < yMin)
            return getKMin(x, xStart + k / 2, y, yStart, k - k / 2);
        else
            return getKMin(x, xStart, y, yStart + k / 2, k - k / 2);
    }
}
