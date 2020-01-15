package com.codeman.binarysearch;

import java.util.Arrays;

/**
 * 二分查找法，非递归
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 1, 4, 5, 6, 8, 23, 88};
        int result = search(arr, 2);
        if ((result < 0)) {
            System.out.printf("找不到了");
        } else {
            System.out.printf("找到了arr[%s]=%s", result, arr[result]);
        }
    }

    private static int search(int[] arr, int beFind) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == beFind) {
                return mid;
            } else if (arr[mid] < beFind) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

}
