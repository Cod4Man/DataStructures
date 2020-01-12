package com.codeman.recursion;

/**
 * 使用递归解决迷宫问题
 */
public class MiGong {
    private static int[][] map = {
            {1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,0,0,0,1},
            {1,0,0,1,1,1,0,0,0,1},
            {1,0,0,1,0,1,0,1,0,1},
            {1,0,1,1,0,1,0,1,0,1},
            {1,0,0,0,0,1,1,1,0,1},
            {1,1,1,1,0,0,0,1,0,1},
            {1,0,0,0,0,1,0,0,1,1},
            {1,0,0,0,0,0,1,0,0,1},
            {1,1,1,1,1,1,1,1,6,1}
    };
    public static void main(String[] args) {
        System.out.println("地图初始化：");
        printMap();
        if (move(3, 2)) {
            System.out.println("找到出口后的地图：");
            printMap();
        } else {
            System.out.println("没有出路！");
        }
    }

    /**
     * 寻找出口
     * @param x 当前的x
     * @param y 当前的y
     * @return 是否找到出口
     */
    private static boolean move(int x, int y) {
        if (map[x][y] == 6) {
            return true;
        } else {
            if (map[x][y] == 0) {
                // 留下行走路线
                map[x][y] = 9;
                // 按 下-右-上-左的方式行走
                if (move(x + 1, y)) {
                    return true;
                }
                if (move(x, y + 1)) {
                    return true;
                }
                if (move(x - 1, y)) {
                    return true;
                }
                if (move(x, y - 1)) {
                    return true;
                }
                // 清楚错误的行走路线
                map[x][y] = 4;
                return false; // 出不去了
            } else if (map[x][y] == 9) {

                return false;
            } else {
                return false;
            }
        }
    }

    /**
     * 打印地图
     */
    private static void printMap() {
        for (int i = 0; i < map.length; i++) {
            for (int i1 = 0; i1 < map[i].length; i1++) {
                String temp = "";
                switch (map[i][i1]) {
                    case 1: temp = "■";break;
                    case 6: temp = "□";break;
                    case 9: temp = "·";break;
                    case 0: temp = " ";break;
                    case 4: temp = "X";break;
                    default:break;
                }
                System.out.print(temp + " ");
            }
            System.out.println();
        }
    }
}
