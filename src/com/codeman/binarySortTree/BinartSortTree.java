package com.codeman.binarySortTree;

/**
 * 二叉排序树：BST
 */
public class BinartSortTree {
    private Node root;

    /**
     * 删除节点
     * @param node
     */
    public void delNode(Node node) {
        if (this.root == null) {
            return;
        }
        Node beDeleN = this.root.findNode(node);
        Node beDeleParN = this.root.findParentNode(node);
        if (beDeleParN != null) {
            if (beDeleN.getLeft() == null && beDeleN.getRight() == null) { // 叶子节点
                if (beDeleParN.getLeft() == beDeleN) {
                    beDeleParN.setLeft(null);
                } else {
                    beDeleParN.setRight(null);
                }
            } else if(beDeleN.getLeft() != null && beDeleN.getRight() != null) { // 非叶子节点
                // 有两种替换方法：
                // 1. 左支树的最大点
                // 2. 右支树的最小点 （明显这个简单，因为最小点肯定是叶子节点，最大则不一定[可能右左节点]）
                Node minNode = beDeleN.getRight().findMinNode();
                int tempNo = minNode.getNo();
                this.delNode(minNode);
                beDeleN.setNo(tempNo);
            } else { // 单子节点
                if (beDeleParN.getRight() == beDeleN) {
                    beDeleParN.setRight(beDeleN.getRight() == null ? beDeleN.getLeft() : beDeleN.getRight());
                } else {
                    beDeleParN.setLeft(beDeleN.getRight() == null ? beDeleN.getLeft() : beDeleN.getRight());
                }
            }
        } else {
            if (this.root == beDeleN) {
                if (this.root.getRight() == null) {
                    this.root = this.root.getLeft();
                } else {
                    Node minNode = this.root.getRight().findMinNode();
                    int tempNo = minNode.getNo();
                    this.delNode(minNode);
                    this.root.setNo(tempNo);
                }
            }
        }

    }

    /**
     * 找节点
     * @param node
     * @return
     */
    public Node findNode(Node node) {
        if (this.root == null) {
            return null;
        } else {
            return this.root.findNode(node);
        }
    }

    /**
     * 找父节点
     * @param node
     * @return
     */
    public Node findParentNode(Node node) {
        if (this.root == null) {
            return null;
        } else {
            return this.root.findParentNode(node);
        }
    }



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
    }

}
