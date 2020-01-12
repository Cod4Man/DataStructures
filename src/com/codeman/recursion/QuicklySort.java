package com.codeman.recursion;

import java.util.Arrays;

/**
 * 快速排序：使用递归,取一个中间值，然后左右对调应为中间值另一侧的两个数直至有序
 */
public class QuicklySort {
    public static void main(String[] args) {
        int num = 8888888;
        int[] arr = new int[num];
        for (int i = 0; i < num; i ++) {
            arr[i] = (int) (Math.random() * num);
        }
//        System.out.println(Arrays.toString(arr));
        long bef = System.currentTimeMillis();
        sort(arr);
        System.out.println("选择排序花费时间：" + (System.currentTimeMillis() - bef));
//        System.out.println(Arrays.toString(arr));

    }

    public static int[] sort(int[] arr) {
        realSort(arr, 0, arr.length - 1);
        return arr;
    }

    private static void realSort(int[] arr, int left, int right) {
        int l = left; // 左指针
        int r = right; // 右指针
        int mid = (left + right) / 2;
        int midValue = arr[mid]; // 这个一定要记录，要记录左右对比的值，就算中间值交换位置了，依然能保证左边的值是<中间值，右边>中间值
        //
        while (l < r) {
            // 遍历左边，直至找到比中间值大的数
            while (arr[l] < midValue) {
                l ++;
            }
            // 遍历右边，直至找到比中间值小的数
            while (arr[r] > midValue) {
                r --;
            }
            if (l >= r) { // 此时已经遍历完所有的数
                break;
            }
            // 1 2 11 6 13 -5 15 -7 4 15
            // 1 2 11 6 4  -5 15 -7 13 15
            // 1 2 11 6 4  -5 -7 15 13 15
            // 找到两个值了，进行交换位置，就算又一端已经是中间值也交换
            int temp = arr[r];
            arr[r] = arr[l];
            arr[l] = temp;
//            System.out.println(Arrays.toString(arr));
            // 继续循环找，交换
            if (arr[l] == midValue) {
                r --;

            }

            if (arr[r] == midValue) {
                l ++;
            }
        }
        if (l == r) {
            l++;
            r--;
        }
        // 此时，左边和右边都分别在中间值两侧，但是左右两边不一定有序，所以需要重复上述操作
        // 右边
        if (right > l) {
            realSort(arr, l, right);
        }
        // 左边
        if (left < r) {
            realSort(arr, left, r);
        }

    }
}
