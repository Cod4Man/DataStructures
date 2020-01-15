package com.codeman.avlTree;

public class Node {
    private int no;
    private Node left;
    private Node right;

    public Node(int no) {
        this.no = no;
    }

    /**
     * 右旋转：
     * 实际和左旋转同理，只是方向而已
     */
    private void rightRotate() {
        //1. 创建一个临时节点，复制root的值
        Node temp = new Node(this.no);
        //2. 设置temp左==》root.left右；temp右==》root右
        temp.setLeft(this.left.right);
        temp.setRight(this.right);
//        this.left.setRight(null);
        //3. root复制为root.right
        this.no = this.left.no;
        //4. 调整树
        this.setLeft(this.left.left);
        this.setRight(temp);
    }

    /**
     * 左旋转：
     * 实际上两个步骤：1. 根节点的右节点的左子树反向指向根节点；(这样树就矮了)
     *               2. 根节点的右子树指向【原根节点的右节点的左子树】。（因为1）
     *              *3. 其实还有个重要的隐藏条件，就是需要让根节点变成根节点的右节点
     *
     * 要做到上述两点，需要一个中间变量
     */
    private void leftRotate() {
        //1. 创建一个临时节点，复制root的信息
        Node temp = new Node(this.no);
        //2. temp左==》root左；temp右==》root右-左
        temp.setLeft(this.left);
        temp.setRight(this.right.left);
//        this.right.setLeft(null);
        //3. root变成root的右节点
        this.no = this.right.no;
        //4. root左==》temp；root右==》right右
        this.setLeft(temp);
        this.setRight(this.right.right);
    }

    /**
     * 左子树高度
     * @return
     */
    public int leftHeight() {
        return this.left == null ? 0 : this.left.height();
    }

    /**
     * 右子树的高度
     * @return
     */
    public int rightHeight() {
        return this.right == null ? 0 : this.right.height();
    }

    /**
     * 树的高度:左、右树的最高
     */
    private int height() {
        return Math.max(this.left == null ? 0 : this.left.height(),
                        this.right == null ? 0 : this.right.height()) + 1;
    }

    /**
     * 增加节点：BST
     * @param node 被添加节点
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

        // 如果右树高，则左旋转
        if (this.rightHeight() - this.leftHeight() > 1) {
            // 如果right.leftHeight > right.leftHeight ，则先右旋right
            if (this.right != null && this.right.leftHeight() > this.right.rightHeight()) {
                this.right.rightRotate();
            }
            this.leftRotate();
        }

        // 如果左树高，则右旋转
        else if (this.leftHeight() - this.rightHeight() > 1) {
            // 如果left.左<left.右，则先左旋转left
            if (this.left != null && this.left.leftHeight() < this.left.rightHeight()) {
                this.left.leftRotate();
            }
            this.rightRotate();
        }
    }

    /**
     * 找树最小点
     * @return
     */
    public Node findMinNode() {
        if (this.left != null) {
            return this.left.findMinNode();
        } else {
            return this;
        }
    }

    /**
     * 查找该节点的父节点
     * @param node
     * @return
     */
    public Node findParentNode(Node node) {
        if ((this.left != null && this.left.no == node.no)
        || (this.right != null && this.right.no == node.no)) {
            return this;
        } else {
            if (this.right != null && this.no < node.no) { // 向右递归
                return this.right.findParentNode(node);
            } else if (this.left != null && this.no >= node.no) { // 向左递归
                return this.left.findParentNode(node);
            } else {
                return null;
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
