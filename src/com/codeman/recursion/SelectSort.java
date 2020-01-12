package com.codeman.recursion;

public class SelectSort {
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i ++) {
            for (int j = i + 1; j < arr.length; j ++) {
                if (arr[i] > arr[j]) {
                    arr[j] = arr[i] + arr[j];
                    arr[i] = arr[j] - arr[i];
                    arr[j] = arr[j] - arr[i];
                }
            }
        }
    }
}
