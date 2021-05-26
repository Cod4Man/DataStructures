package com.codeman.recursion;

/**
 * 基数排序：原理，从个位数开始比较，比到最高位则有序
 */
public class JishuSort {
    public static void main(String[] args) {
        int num = 4988888;
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = (int)(Math.random() * num);
        }
//        System.out.println(Arrays.toString(arr));
        long bef = System.currentTimeMillis();
        sort(arr);
        System.out.println("排序花费时间：" + (System.currentTimeMillis() - bef));
//        System.out.println(Arrays.toString(arr));

    }

    public static void sort(int[] arr) {
        // 找到最大数，几位数则
        int maxVal = arr[0];
        for (int i = 1; i < arr.length ; i ++) {
            if (arr[i] > maxVal) {
                maxVal = arr[i];
            }
        }
        int maxLength = (maxVal + "").length();
        // 创建10个桶，0-9
        int[][] tongs = new int[10][arr.length];
        // 创建数组记录每个通的有效位数
        int[] countPerTong = new int[10];

        int weishu = 0;
        while (maxLength > 0) {
            weishu ++;
            // 入桶
            for (int j = 0; j < arr.length; j ++) {
                //获取当前比较位数
                int currNum = arr[j] / (int)(Math.pow(10,weishu - 1)) % 10;
                tongs[currNum][countPerTong[currNum]] = arr[j];
                countPerTong[currNum] += 1;
            }

            // 出桶，并重置桶
            int tempIndex = 0;
            for (int i = 0; i < countPerTong.length; i++) {

                for (int k = 0; k < countPerTong[i]; k ++) {
                    arr[tempIndex ++] = tongs[i][k]; // 这里有个隐藏点，就是桶先进先出，这样才有一个排序

                }
                countPerTong[i] = 0;
            }

            maxLength --;
        }
    }

}
