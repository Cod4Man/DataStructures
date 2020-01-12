package com.codeman.binarySortTree;

public class Node {
    private int no;
    private Node left;
    private Node right;

    public Node(int no) {
        this.no = no;
    }

    /**
     * 增加节点：BST
     * @param node
     */
    public void addNode(Node node) {
        if (this.no <= node.no) {
            if (this.right != null) {
                this.right.addNode(node);
            } else {
                this.right = node;
            }
        } else {
            if (this.left != null) {
                this.left.addNode(node);
            } else {
                this.left = node;
            }
        }
    }

    /**
     * 找节点：BST
     * @param node
     */
    public Node findNode(Node node) {
        if (node.no == this.no) {
            return this;
        } else if (this.no < node.no) {
            if (this.right != null) {
                return this.right.findNode(node);
            }
        } else {
            if (this.left != null) {
                return this.left.findNode(node);
            }
        }
        return null;
    }

    /**
     * 中序遍历
     */
    public void midOrder() {
        if (this.left != null) {
            this.left.midOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.midOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                '}';
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
