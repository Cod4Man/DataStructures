package com.codeman.recursion;

/**
 * 二分查找
 */
public class BinaSearch {
    static int count = 0;
    public static void main(String[] args) {
//        int[] arr = {1, 2, 5, 5, 6, 7, 8, 10, 16, 22};
        int[] arr = {1,2,3,4,5,6,7,8};
        System.out.println(find(arr, 1, 0, arr.length - 1));
    }

    /**
     * 二分查找
     * @param arr
     * @param beFindNum
     * @param start
     * @param end
     * @return
     */
    public static int find(int[] arr, int beFindNum, int start, int end) {
        System.out.printf("第%s次",++count);
        System.out.println();
        if (start > end) {
            return -1;
        }
        int mid = (start + end) /2;
        if (arr[mid] > beFindNum) {
            return find(arr, beFindNum, start, mid - 1);
        } else if (arr[mid] < beFindNum) {
            return find(arr, beFindNum, mid + 1, end);
        } else {
            return mid;
        }
    }

    /**
     * 插值查找
     * @return
     */
    public static int find2(int[] arr, int beFindNum, int start, int end) {
        // mid = (start+end)/2 = start+(end-start)/2
        // 斜率趋势 1/2 ===》
        // 根据斜率公式 ((beFindNum-arr[start])/(arr[end]-arr[start])) =(mid-start)/(end-start)
        // 得mid = start + (end-start) * ((beFindNum-arr[start])/(arr[end]-arr[start]))
        System.out.printf("第%s次",++count);
        System.out.println();
        int mid = start + (end-start) * ((beFindNum-arr[start])/(arr[end]-arr[start]));
        if (start > end || mid < start || mid > end) {
            return -1;
        }

        if (arr[mid] > beFindNum) {
            return find(arr, beFindNum, start, mid - 1);
        } else if (arr[mid] < beFindNum) {
            return find(arr, beFindNum, mid + 1, end);
        } else {
            return mid;
        }
    }
}
