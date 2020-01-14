package com.codeman.avlTree;

/**
 * AVL树：自平衡二叉树
 */
public class AVLTree {
    private Node root;

    /**
     * 增加节点:BST
     * @param node
     */
    public void addNode(Node node) {
        if (this.root == null) {
            this.root = node;
        } else {
            this.root.addNode(node);
        }
    }

    /**
     * 中序遍历
     */
    public void midOrder() {
        if (this.root == null) {
            System.out.println("空树！");
            return;
        }
        this.root.midOrder();
        System.out.println("左树的高度为：" + this.root.leftHeight());
        System.out.println("右树的高度为：" + this.root.rightHeight());
    }
}
