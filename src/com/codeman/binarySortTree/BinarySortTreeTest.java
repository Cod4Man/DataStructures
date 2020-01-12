package com.codeman.binarySortTree;

/**
 * BST测试
 */
public class BinarySortTreeTest {
    public static void main(String[] args) {
        BinartSortTree binartSortTree = new BinartSortTree();
        binartSortTree.addNode(new Node(5));
        binartSortTree.addNode(new Node(1));
        binartSortTree.addNode(new Node(6));
        binartSortTree.addNode(new Node(0));
        binartSortTree.addNode(new Node(7));
        binartSortTree.addNode(new Node(4));
        binartSortTree.addNode(new Node(2));
        binartSortTree.addNode(new Node(3));
        binartSortTree.midOrder();
        System.out.println("=============");
        binartSortTree.delNode(new Node(4));
        binartSortTree.midOrder();
        System.out.println("=============");
//        System.out.println(binartSortTree.findParentNode(new Node(2)));
    }
}
