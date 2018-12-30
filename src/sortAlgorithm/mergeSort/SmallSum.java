package sortAlgorithm.mergeSort;

public class SmallSum {

    public int smallSum(int[] array) {
        if (array.length <= 1) return 0;
        return recSort(array, 0, array.length - 1);
    }

    private int recSort(int[] array, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        int sum = 0;
        sum += recSort(array, left, mid);
        sum += recSort(array, mid + 1, right);
        sum += merge(array, left, mid, right);
        return sum;
    }

    private int merge(int[] array, int left, int mid, int right) {
        int leftPtr = left;
        int rightPtr = mid + 1;
        int naPtr = 0;
        int sum = 0;
        int[] newArray = new int[right - left + 1];
        while (leftPtr <= mid && rightPtr <= right) {
            sum += (array[leftPtr] < array[rightPtr]) ? array[leftPtr] * (right - rightPtr + 1) : 0; //使用下标计算右边数组中共有多少个比左边leftptr大的数. 因为子数组都是保证有序的了.
            newArray[naPtr++] = (array[leftPtr] < array[rightPtr]) ? array[leftPtr++] : array[rightPtr++];
        }
        while (leftPtr <= mid) newArray[naPtr++] = array[leftPtr++];
        while (rightPtr <= right)  newArray[naPtr++] = array[rightPtr++];
        if (newArray.length >= 0) System.arraycopy(newArray, 0, array, left, newArray.length);
        return sum;
    }

    public static void main(String[] args) {
        SmallSum smallSum = new SmallSum();
        int result = smallSum.smallSum(new int[]{1, 3, 4, 2, 5});
        System.out.println("result = " + result);
    }
}
