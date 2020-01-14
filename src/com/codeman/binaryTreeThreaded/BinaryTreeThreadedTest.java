package com.codeman.binaryTreeThreaded;

public class BinaryTreeThreadedTest {
    public static void main(String[] args) {

        TreeNode root = new TreeNode("zero", 0);
        TreeNode node1 = new TreeNode("one", 1);
        TreeNode node2 = new TreeNode("two", 2);
        TreeNode node3 = new TreeNode("three", 3);
        TreeNode node4 = new TreeNode("four", 4);
        TreeNode node5 = new TreeNode("five", 5);
        TreeNode node6 = new TreeNode("six", 6);
        TreeNode node7 = new TreeNode("seven", 7);
        TreeNode node8 = new TreeNode("eight", 8);
//        TreeNode node9 = new TreeNode("nine", 9);
//        TreeNode node10 = new TreeNode("ten", 10);

//        node6.setRightN(node10);
//        node5.setLeftN(node9);
        node2.setLeftN(node5);
        node2.setRightN(node6);
        node3.setLeftN(node7);
        node3.setRightN(node8);
        node1.setLeftN(node3);
        node1.setRightN(node4);
        root.setLeftN(node1);
        root.setRightN(node2);

        BinaryTreeThreaded binaryTreeThreaded = new BinaryTreeThreaded(root);
        binaryTreeThreaded.prePrint();
        System.out.println("===========");
        binaryTreeThreaded.midThreaded();
        binaryTreeThreaded.prinMidThreaded();
    }
}
