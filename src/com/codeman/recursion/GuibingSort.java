package com.codeman.recursion;

/**
 * 归并、分治算法
 *
 *     13456235
 *    1345  6235
 *   13 45  62 35
 *  1 3 4 5 6 2 3 5
 *  ================
 *  1 3 4 5 6 2 3 5
 *   13 45  26  35
 *    1345   2356
 *      12334556
 */
public class GuibingSort {

    public static void main(String[] args) {
        int num = 28888889;
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = (int)(Math.random() * num);
        }
        int[] tempArr = new int[arr.length];
//        comNSort(arr, 0, arr.length, 4, tempArr);
//        System.out.println(Arrays.toString(arr));
//        System.out.println("===================");
        long bef = System.currentTimeMillis();
        sort(arr, tempArr, 0, arr.length-1);
        System.out.println("排序花费时间：" + (System.currentTimeMillis() - bef));


    }

    public static void sort(int[] arr,int[] tempArr, int left, int right) {
        // 按思路分比较难分组，可以使用递归进行分组，就可以倒序(递归会从内往外执行)，每次/2
        // 只有自己一个数就没必要分裂了
        if (left < right) {
            int mid = (left + right) /2; // 以中间为基准进行分裂
            // 向左分裂
            sort(arr, tempArr, left, mid);
            // 向右分裂
            sort(arr, tempArr, mid + 1, right);
            // 合并
            comNSort(arr, left, right+1, mid+1, tempArr);
//            System.out.println(Arrays.toString(arr));
        }



        /*// 分+调用治            left : mid : right
        // 0-1 2-3 4-5 6-73     0   ： 0+1 ： 1+1
        // 01-23 45-678         0   ： 0+2 ： 3+1
        // 0123-45678           0   :  0+4 :  8+1
        //
        // 循环次数计算，得出需要循环arr.length-1次
//        4/2=2  2+1=3; （2+2/2）
//        8/2=4  4+2+1=7; (4+4/2+2/2)
//        9/2=5  5+2+1
//        12/2=6 6+3+1+1=11; (6+6/2+3/2+2/2)
        int beCu = 0;
        if (arr.length % 2 != 0) {
            beCu = 1;
        }
        *//*int tempChuShu = 2;
        while (arr.length / tempChuShu != 0) {
            for (int i = 0; i < arr.length - beCu; i+=tempChuShu) {
                comNSort(arr, i, i+tempChuShu, i+(tempChuShu/2), tempArr);
            }

            tempChuShu *= 2;
        }
        if (beCu == 1) {
            comNSort(arr, 0, arr.length, arr.length - 1, tempArr);
        }

        System.out.println(Arrays.toString(arr));*//*
        // 第一次

        for (int i = 0; i < arr.length-beCu; i+=2) {
            comNSort(arr, i, i+2, i+1, tempArr);
        }
        System.out.println(Arrays.toString(arr));
        // 第二次
        for (int i = 0; i < arr.length-beCu; i+=4) {
            comNSort(arr, i, i+4, i+2, tempArr);
        }
        System.out.println(Arrays.toString(arr));
        // 第三次 1, 2, 7, 8 == 0, 3, 4, 9
        for (int i = 0; i < arr.length-beCu; i+=8) {
            comNSort(arr, i, i+8, i+4, tempArr);
        }
        System.out.println(Arrays.toString(arr));
        //TODO 如果奇数，还要比较最后一个数
        if (1==beCu) {
            comNSort(arr, 0, arr.length, arr.length - 1, tempArr);
        }
        System.out.println(Arrays.toString(arr));*/

    }

    /**
     * l    m   right
     * 0    4   8
     * 1345 23560
     * @param arr
     * @param left
     * @param right
     * @param mid
     */
    public static void comNSort(int[] arr, int left, int right, int mid, int[] tempArr) {
        int l = left;
        int m = mid;
        int tempIndex = left;
        // 循环直至有一侧遍历完成
        while (l < mid && m < right) {
            if (arr[l] < arr[m]) {
                tempArr[tempIndex++] = arr[l++];
            } else {
                tempArr[tempIndex++] = arr[m++];
            }
        }
        // 此时有一方已经遍历完成，而另一方剩下的数直接放入数组末尾即可
        // 左边完成，把右边放入末尾
        if (l == mid) {
            while (m < right) {
                tempArr[tempIndex++] = arr[m++];
            }
        }
        // 右边完成，把左边放入末尾
        else if (m == right) {
            while (l < mid) {
                tempArr[tempIndex++] = arr[l++];
            }
        }
        // 赋值
        for (int i = left; i < right; i ++) {
            arr[i] = tempArr[i];
        }
    }
}
