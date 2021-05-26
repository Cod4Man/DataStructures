package com.codeman.RBTree;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2021/5/21 12:27
 * @version: 1.0
 */
public class RBTreeTest {

    public static void main(String[] args) {
        // 添加+中序打印测试
//        assert false;
        System.out.println("12312");
        RBTree<Integer, String> rbTree = new RBTree<>();
        Scanner scanner = new Scanner(System.in);
        /*while (true) {
            System.out.print("请添加元素：");
            String str = scanner.nextLine();
            rbTree.put(Integer.parseInt(str), null);
            System.out.println();
            System.out.println("红黑树中序遍历：" + Arrays.toString(rbTree.trans2MidEntiryArr()));
        }*/
        rbTree.put(1, null);
        rbTree.put(55, null);
        rbTree.put(66, null);
        rbTree.put(-1, null);
        rbTree.put(77, null);
        rbTree.put(88, null);
        rbTree.put(99, null);
        rbTree.put(100, null);
        rbTree.put(68, null);
    }
}
