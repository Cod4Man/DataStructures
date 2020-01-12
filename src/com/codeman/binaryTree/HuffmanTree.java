package com.codeman.binaryTree;

import java.util.*;

/**
 * 利用赫夫曼树的特性，实现数据压缩/解压
 * 赫夫曼树特性：是一种带权长度最短的二叉树。可以把权值大的放在顶端，从而权长度最短。
 *              可以利用再数据压缩，将最频繁的数据放在顶端，这样此数据路径就短，整体压缩量就下降了。
 */
public class HuffmanTree {
    private HufTreeNode root;
    private Map<Byte, String> huffmanCode = new HashMap<>();

    public HuffmanTree(HufTreeNode[] hufTreeNodes) {
        if (hufTreeNodes == null || hufTreeNodes.length < 1) {
            throw new RuntimeException("认真点，空数组？");
        }
        createHuffmanTree(hufTreeNodes);
        getHufmanCode(this.root, new StringBuffer());
    }

    /**
     * 拿到赫夫曼编码
     * @return
     */
    private void getHufmanCode(HufTreeNode node, StringBuffer sb) {
        // 前序遍历，左边为0，右边为1，为所有字符编码

        if (node.getLetter() != null) {
            huffmanCode.put(node.getLetter(),sb.toString());
        }
        if (node.getLeftNode() != null) {
            StringBuffer tempCode = new StringBuffer(sb);
            tempCode.append("0");
            getHufmanCode(node.getLeftNode(), tempCode);
        }
        if (node.getRightNode() != null) {
            StringBuffer tempCode = new StringBuffer(sb);
            tempCode.append("1");
            getHufmanCode(node.getRightNode(), tempCode);
        }

    }

    /**
     * 构造赫夫曼树
     * @param hufTreeNodes
     */
    private void createHuffmanTree(HufTreeNode[] hufTreeNodes) {
        List<HufTreeNode> hufTreeNodeList = new ArrayList<>(Arrays.asList(hufTreeNodes));
        while (hufTreeNodeList.size() > 1) { // 集合只剩下一个说明已经生成树，该值为root
            // 先排序，才能从小到大生成WPL即赫夫曼树
            Collections.sort(hufTreeNodeList);
            // 取前两个对象进行合并
            HufTreeNode hufTreeNode0 = hufTreeNodeList.get(0);
            HufTreeNode hufTreeNode1 = hufTreeNodeList.get(1);
            HufTreeNode temp = new HufTreeNode(hufTreeNode0.getTimes() + hufTreeNode1.getTimes(), null);
            temp.setLeftNode(hufTreeNode0);
            temp.setRightNode(hufTreeNode1);
            // 生成新的树集合
            hufTreeNodeList.remove(hufTreeNode0);
            hufTreeNodeList.remove(hufTreeNode1);
            hufTreeNodeList.add(temp);
        }
        this.root = hufTreeNodeList.get(0);
//        this.root.preOrder();
    }

    public Map<Byte, String> getHuffmanCode() {
        return huffmanCode;
    }

    public void setHuffmanCode(Map<Byte, String> huffmanCode) {
        this.huffmanCode = huffmanCode;
    }
}

/**
 * 树节点
 */
class HufTreeNode implements Comparable<HufTreeNode> {
    private int times;
    private Byte letter;
    private HufTreeNode leftNode;
    private HufTreeNode rightNode;

    public HufTreeNode(int times, Byte letter) {
        this.times = times;
        this.letter = letter;
    }

    public void preOrder() {
        System.out.println(this);
        if (this.leftNode != null) {
            this.leftNode.preOrder();
        }
        if (this.rightNode != null) {
            this.rightNode.preOrder();
        }
    }

    @Override
    public String toString() {
        return "HufTreeNode{" +
                "times=" + times +
                ", letter=" + letter +
                '}';
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public Byte getLetter() {
        return letter;
    }

    public void setLetter(Byte letter) {
        this.letter = letter;
    }

    public HufTreeNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(HufTreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public HufTreeNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(HufTreeNode rightNode) {
        this.rightNode = rightNode;
    }

    @Override
    public int compareTo(HufTreeNode o) {
        return this.times - o.times;
    }
}

