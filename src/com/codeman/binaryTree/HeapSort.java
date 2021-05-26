package com.codeman.binaryTree;

import java.util.Arrays;

/**
 * 堆排序：
 * 大顶堆==》从小到大
 * 小顶堆==》从大到小
 * 规律：非叶子节点的左子节点下标为：2*该节点下标+1；右子节点下标为2*该节点下标+2
 * 规律：长度/2 - 1 = 最后一个非叶子节点的下标
 */
public class HeapSort {
    public static void main(String[] args) {
//        int[] arr = {14, 5, 14, 4, 14, 12, 11, 10, 13, 10, 7, 1, 5, 9, 3};
        int num = 8888888;
        int[] arr = new int[num];
        for (int i = 0; i < num; i ++) {
            arr[i] = (int) (Math.random() * num);
        }
//        System.out.println(Arrays.toString(arr));
        long bef = System.currentTimeMillis();
        changeArrToTopHeap0(arr);
        System.out.println("选择排序花费时间：" + (System.currentTimeMillis() - bef));
//        System.out.println(Arrays.toString(arr));
    }

    public static void changeArrToTopHeap0(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("搞毛呢/?");
            return;
        }
        // 先堆一个大顶堆出来
        for (int i = arr.length / 2 - 1; i >= 0; i --) {
            changeArrToTopHeap(arr, i, arr.length);
        }
        // 排序
        for (int i = arr.length; i > 1; i --) {
            changeArrToTopHeap(arr, 0, i);
            // 首尾互换
            int temp = arr[0];
            arr[0] = arr[i - 1];
            arr[i - 1] = temp;
        }
    }

    public static void changeArrToTopHeap(int[] arr,int currIndex, int length) {

       /*思路：
       * 1.把最大值往上推
       *    1.1第一个需要从最后一个非叶子节点开始推，推出一个大顶堆
       *    1.2推出一个大顶堆后，之后的重复操作就无需从最后开是堆，只需要从root节点开始即可
       * 2.推到顶部后，首尾互换，则最后一个节点为最大值
       * 3.重复上诉步骤，获取剩下的最大值
       * */
        int temp = arr[currIndex]; // 当前节点值
        for (int c = currIndex * 2 + 1; c < length; c = c * 2 + 1) { // 如果是最后一个非叶子节点实际是无需循环的，但是考虑到全部情况复用
            if (c + 1 < length && arr[c] < arr[c + 1]) { // 先左右节点对比
                // 右边大则下标位移值较大的值处
                c ++;
            }
            //比较父子大小
            if (temp < arr[c]) { // 子节点大则复制给父
                arr[currIndex] = arr[c];
                currIndex = c; //如果还有下面的话，则可以继续往下探寻
            } else {
                break; // 如果是最后的叶子节点，不会循环，而如果是上层的节点，由于底层实际上已经堆过，堆成了大顶堆，因此肯定下面不会更小
            }
        }
        arr[currIndex] = temp;


       // 最后一个非叶子节点
        /*for (int i = length / 2 - 1; i >= 0 ; i--) {
            // 当前左子节点：
            int iLeft = 2 * i + 1;
            if (iLeft != length - 1) {
                if (arr[iLeft] <= arr[iLeft + 1]) {
                    if (arr[iLeft + 1] > arr[i]) {
                        // 对换
                        int temp = arr[i];
                        arr[i] = arr[iLeft + 1];
                        arr[iLeft + 1] = temp;
                    }
                } else {
                    if (arr[iLeft] > arr[i]) {
                        // 对换
                        int temp = arr[i];
                        arr[i] = arr[iLeft];
                        arr[iLeft] = temp;
                    }
                }
            } else { // 右边没值
                if (arr[iLeft] > arr[i]) {
                    // 对换
                    int temp = arr[i];
                    arr[i] = arr[iLeft];
                    arr[iLeft] = temp;
                }
            }
        }*/
    }
}




