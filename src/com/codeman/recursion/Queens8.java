package com.codeman.recursion;

/**
 * 8皇后
 */
public class Queens8 {
    private static final int QUEEN_COUNT = 8;
    private static int[] queenQueue = new int[QUEEN_COUNT];
    private static int result = 0;
    private static int cost = 0;

    public static void main(String[] args) {
        check(0);
        System.out.printf("共有%s个解：", result);
        System.out.printf("共运行%s次：", cost);
    }

    private static void check(int row) {
        for (int col = 0; col < QUEEN_COUNT; col ++) {
            cost++;
            if (row == 8) {
                result ++;
                printArr();
                return;
            }
            if (jump(row, col)) { // 当前row符合规则则保存并继续下一行 0 4 7 5 ?2 (4,1) (4,2)
                queenQueue[row] = col;
                check(row + 1);
            }
        }

    }

    /**
     * 检查是否符合游戏规则
     * @param index 当前行
     * @param col 当前列
     * @return true or false
     */
    private static boolean jump(int index, int col) {
        for (int i = 0; i < index; i++) {
            if (queenQueue[i] == col ||
                    Math.abs((index - i)) == Math.abs((col - queenQueue[i]))
            ) {
                return false;
            }
        }
        return true;
    }

    private static void printArr() {
        for (int i : queenQueue) {
            System.out.print(i);
        }
        System.out.println();
    }
}
