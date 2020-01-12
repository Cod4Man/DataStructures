package com.codeman.recursion;

import java.util.Arrays;

/**
 * Shell排序法
 * 原理，将数据分成若干组，组内先进行比较(可以嵌套各种算法)，多次分组比较后，数据被排成了近似有序状态，然后再进行排序
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {1, -2, 5, -6, 22, 1};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void shellSort(int[] arr) {

        // 分组
        for (int grp = arr.length/2; grp > 0 ; grp /= 2) {
            for (int i = grp; i < arr.length; i ++) {
                int tempI = arr[i];
                int index = i;
                boolean tempBoo = false;
                while (index >= grp && tempI < arr[index -grp] ) {
                    arr[index] = arr[index - grp];
                    index -= grp;
                }
                arr[index] = tempI;
            }
        }
    }
}
