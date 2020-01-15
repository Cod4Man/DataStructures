package com.codeman.dac;

/**
 * 使用分治算法解决汉诺塔问题
 */
public class HanoiTower {
    private static long count;
    public static void main(String[] args) {
        doIt(20, 'A', 'B', 'C');
        System.out.println("执行次数：" + count);
    }

    /**
     * 解题
     * @param num 盘的个数
     * @param a 柱子Ａ
     * @param b　柱子Ｂ
     * @param c　柱子Ｃ
     */
    public static void doIt(int num, char a, char b, char c) {
        count++;
        /*思路：
        * 1. 因为大盘必须在下面，因此只有一种解法，就是把非最后一个盘移动到B柱上，然后移动最后一个盘到C柱上。
        * 2. 可以明显看出这是在重复移动最后一个盘，因此可以使用递归实现
        * 分步骤：
        * 1) 非最后一块的部分从A移动到B
        * 2) 最后一块的部分从A移动到C
        * 3) 非最后一块的部分从B移动到C
        * */
        if (num == 1) {
            System.out.printf("把第1个盘 %s移动到%s", a, c);
            System.out.println();
        } else {
            // 拆分成非最后一个与最后一个，两个部分
            // 1) 非最后一块的部分从A移动到B
            doIt(num - 1, a, c, b); // 是把A-B进行过度，然后移动最后一个盘

            // 2) 最后一块的部分从A移动到C
            // 移动最后一个盘
            System.out.printf("把第%s个盘 %s移动到%s", num, a, c);
            System.out.println();

            // 3) 非最后一块的部分从B移动到C
            // 移动B->C
            doIt(num - 1, b, a, c);

        }

    }
}
