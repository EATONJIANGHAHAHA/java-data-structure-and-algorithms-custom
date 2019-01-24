package questions;

/**
 * 在一个数组中,每一个数左边比当前数小的数累加起来,叫做这个数组的小和。求一个数组
 * 的小和. 时间复杂度不得高于O(N*logN)
 */
public class SmallSum {

    public static int solve(int[] array) {
        if (array.length <= 1) return 0;
        return recSort(array, 0, array.length - 1);
    }

    private static int recSort(int[] array, int left, int right) {
        if (left == right) return 0;
        int mid = left + ((right - left) >> 1);
        int sum = 0;
        sum += recSort(array, left, mid);
        sum += recSort(array, mid + 1, right);
        sum += merge(array, left, mid, right);
        return sum;
    }

    private static int merge(int[] array, int left, int mid, int right) {
        int leftPtr = left, rightPtr = mid + 1, naPtr = 0, sum = 0;
        int[] newArray = new int[right - left + 1];
        while (leftPtr <= mid && rightPtr <= right) {
            sum += (array[leftPtr] < array[rightPtr]) ? array[leftPtr] * (right - rightPtr + 1) : 0; //使用下标计算右边数组中共有多少个比左边leftptr大的数. 因为子数组都是保证有序的了.
            newArray[naPtr++] = (array[leftPtr] < array[rightPtr]) ? array[leftPtr++] : array[rightPtr++];
        }
        while (leftPtr <= mid)
            newArray[naPtr++] = array[leftPtr++];
        while (rightPtr <= right)
            newArray[naPtr++] = array[rightPtr++];
        if (newArray.length >= 0)
            System.arraycopy(newArray, 0, array, left, newArray.length);
        return sum;
    }

    public static void main(String[] args) {
        int result = solve(new int[]{1, 3, 4, 2, 5});
    }
}
