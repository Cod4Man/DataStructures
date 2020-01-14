package com.codeman.binaryTreeThreaded;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 二叉树
 */
public class BinaryTreeThreaded {
    private TreeNode root;
    private int[] preArray;
    private List<Integer> tempPreList = new ArrayList<>();
    private TreeNode tempPreNode; // 临时前继节点

    public BinaryTreeThreaded(TreeNode root) {
        this.root = root;
    }

    /**
     * 遍历中序线索二叉树
     */
    public void prinMidThreaded() {
        if (this.root == null) {
            return;
        }
        prinMidThreaded(this.root);
    }

    private void prinMidThreaded(TreeNode node) {
        // 中序： 左-中-右
        // 先找到第一个节点，即前继节点为null的节点
        TreeNode temp = node;
        while (!temp.isLeftThr()) {
            temp = temp.getLeftN();
        }

        // 开始遍历
        while (temp != null) {
            System.out.println(temp);
            if (temp.isRightThr()) {
                temp = temp.getRightN();
            } else { // 非叶子节点需要找到最左边的值，即以该节点为root的树的第一个前序元素
                temp = temp.getRightN();
                while (temp != null && !temp.isLeftThr()) {
                    temp = temp.getLeftN();
                }
            }

        }
    }

    /**
     * 中序线索化二叉树
     */
    public void midThreaded() {
        if (this.root == null) {
            System.out.println("搞毛？？空树");
            return;
        }
        midThreaded(this.root);
    }

    private void midThreaded(TreeNode node) {
        if (node == null) {
            return;
        }
        // 因为是中序线索： 左-中-右
        // 先处理左边
        midThreaded(node.getLeftN());
        // 实际处理
        if (node.getLeftN() == null) { // 为空说明是无指针的叶子节点
            node.setLeftThr(true);
            node.setLeftN(tempPreNode);
        }

        // 那么前继节点也需要指向新节点
        if (tempPreNode != null && tempPreNode.getRightN() == null) {
            tempPreNode.setRightThr(true);
            tempPreNode.setRightN(node);
        }

        // 两个节点相互指完就 需要移动tempPreNode节点，使其指向下一个节点
        tempPreNode = node;

        // 处理右边
        midThreaded(node.getRightN());
    }

    /**
     * 前序遍历: 中-左-右
     */
    public void prePrint() {
        prePrint(this.root);
        // 转换为前序数组
        preArray = new int[tempPreList.size()];
        for (int i = 0; i < tempPreList.size(); i++) {
            preArray[i] = tempPreList.get(i);
        }
        System.out.println("前序数组：" + Arrays.toString(preArray));
    }

    private void prePrint(TreeNode node) {
        if (node == null) {
            System.out.println("空树");
            return ;
        }
        // 中
        System.out.println(node);
        tempPreList.add(node.getNo());
        // 左
        if (node.getLeftN() != null) {
            prePrint(node.getLeftN());
        }
        // 右
        if (node.getRightN() != null) {
            prePrint(node.getRightN());
        }

    }


    public int[] getPreArray() {
        return preArray;
    }

    public void setPreArray(int[] preArray) {
        this.preArray = preArray;
    }
}

/**
 * 二叉树节点
 */
class TreeNode {
    private String name;
    private int no;
    private TreeNode leftN;
    private TreeNode rightN;
    private boolean isLeftThr;
    private boolean isRightThr;

    public TreeNode(String name, int no) {
        this.name = name;
        this.no = no;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "name='" + name + '\'' +
                ", no=" + no +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public TreeNode getLeftN() {
        return leftN;
    }

    public void setLeftN(TreeNode leftN) {
        this.leftN = leftN;
    }

    public TreeNode getRightN() {
        return rightN;
    }

    public void setRightN(TreeNode rightN) {
        this.rightN = rightN;
    }

    public boolean isLeftThr() {
        return isLeftThr;
    }

    public void setLeftThr(boolean leftThr) {
        isLeftThr = leftThr;
    }

    public boolean isRightThr() {
        return isRightThr;
    }

    public void setRightThr(boolean rightThr) {
        isRightThr = rightThr;
    }
}
