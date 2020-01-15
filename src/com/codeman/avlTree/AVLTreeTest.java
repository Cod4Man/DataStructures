package com.codeman.avlTree;

/**
 * AVL树测试类
 */
public class AVLTreeTest {
    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        int[] arr = {4, 3, 6, 5, 7, 8};
//        int[] arr = {7, 8, 5, 3, 6, 4};
//        int[] arr = {10, 11, 7, 6, 8, 9};
        for (int i : arr) {
            avlTree.addNode(new Node(i));
        }
        avlTree.midOrder();
    }
}
