package com.codeman.binarySortTree;

/**
 * BST测试
 */
public class BinarySortTreeTest {
    public static void main(String[] args) {
        BinartSortTree binartSortTree = new BinartSortTree();
        binartSortTree.addNode(new Node(3));
        binartSortTree.addNode(new Node(1));
        binartSortTree.addNode(new Node(6));
        binartSortTree.addNode(new Node(0));
        binartSortTree.addNode(new Node(4));
        binartSortTree.addNode(new Node(2));
        binartSortTree.midOrder();
        System.out.println("=============");
        System.out.println(binartSortTree.findNode(new Node(7)));
    }
}
