package 最小的k个数;

import java.util.Arrays;

/**
 * 快排
 * @author cwq
 * @since 2021/03/24
 */

public class Solution1 {
    public int[] getLeastNumbers(int[] arr, int k) {
        if(k == 0 || arr.length == 0) {
            return new int[0];
        }
        return quickSort(arr, 0, arr.length - 1, k - 1);
    }

    private int[] quickSort(int[] arr, int start, int end, int k) {
        int pivot = quickSort(arr, start, end);
        if(pivot == k) {
            return Arrays.copyOf(arr, k + 1);
        }
        return pivot > k ? quickSort(arr, start, pivot - 1, k) : quickSort(arr, pivot + 1, end, k);
    }

    private int quickSort(int[] arr, int start, int end) {
        int left = start, right = end, pivot = arr[start];
        while (left < right) {
            while (left < right && arr[right] >= pivot) {
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = pivot;
        return left;
    }
}
