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
        // 情况1：删除的是叶子节点，直接删除即可

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
        return null;
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
