package com.codeman.recursion;

import java.util.Arrays;

/**
 * 插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
//        int[] arr = {1, -2, 5, -6, 22, 1, 0};
//        System.out.println(Arrays.toString(arr));
        int num = 88888888;
        int[] arr = new int[num];
        for (int i = 0; i < num; i ++) {
            arr[i] = (int) (Math.random() * num);
        }
        System.out.println("开始arr[num]=" + arr[num-1]);
        long bef = System.currentTimeMillis();
//        SelectSort.selectSort(arr);
        System.out.println("选择排序花费时间：" + (System.currentTimeMillis() - bef));
        bef = System.currentTimeMillis();
        ShellSort.shellSort(arr);
        System.out.println("Shell排序花费时间：" + (System.currentTimeMillis() - bef));
        bef = System.currentTimeMillis();
//        insertSort(arr);
        System.out.println("插入排序花费时间：" + (System.currentTimeMillis() - bef));
//        System.out.println(Arrays.toString(arr));
    }

    public static void insertSort(int[] arr) {

        for (int i = 1; i< arr.length; i ++) {
            int tempI = arr[i];
            boolean tempBoo = false;
            for (int j = i - 1; j >= 0; j --) { // 从i的前面一个开始对比
                if (tempI < arr[j]) { // 后面的小于前面的则需要移动
                    tempBoo = true;
                    arr[j + 1] = arr[j];
                    if (j == 0) {
                        arr[0] = tempI;
                    }
                } else {
                    if (tempBoo) {
                        arr[j + 1] = tempI;
                    }
                    tempBoo = false;
                    break; // 找到数了要跳出
                }
            }
        }
    }
}
