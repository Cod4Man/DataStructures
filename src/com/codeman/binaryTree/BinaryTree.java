package com.codeman.binaryTree;

/**
 * 树
 */
public class BinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, "yi");
        TreeNode node1 = new TreeNode(2, "er");
        TreeNode node2 = new TreeNode(3, "san");
        TreeNode node3 = new TreeNode(4,    "si");
        TreeNode node4 = new TreeNode(5,    "WU");
        TreeNode node5 = new TreeNode(6,    "LIU");
        node2.setLeft(node3);
        root.setLeft(node1);
        root.setRight(node2);
        node1.setLeft(node4);
        node4.setLeft(node5);
        BinaryTree binaryTree = new BinaryTree(root);
//        binaryTree.preSearch(binaryTree.root);
//        System.out.println("==================");
//        System.out.println(binaryTree.findByPre(binaryTree.root, 5));
        binaryTree.sufSearch(binaryTree.root);
        System.out.println("=================");
        System.out.println(binaryTree.findBySuf(binaryTree.root, 4));
//        binaryTree.midSearch(binaryTree.root);
//        System.out.println("=================");
//        System.out.println(binaryTree.findByMid(binaryTree.root, 1));
    }

    private TreeNode root;

    public BinaryTree(TreeNode root) {
        this.root = root;
    }

    /**
     * 后续查找
     * @param node
     * @param no
     * @return
     */
    public TreeNode findBySuf(TreeNode node, int no) {
        System.out.println("查找~");
        if (node == null) {
            System.out.println("这是个空树");
            return null;
        }

        // 左
        if (node.getLeft() != null) {
            TreeNode temp = findBySuf(node.getLeft(), no);
            if (temp != null) {
                return temp;
            }
        }

        // 右
        if (node.getRight() != null) {
            TreeNode temp = findBySuf(node.getRight(), no);
            if (temp != null) {
                return temp;
            }
        }

        // 中
        if (node.getNo() == no) {
            return node;
        }
        return null;
    }

    /**
     * 中序查找
     * @param node
     * @param no
     * @return
     */
    public TreeNode findByMid(TreeNode node, int no) {
        System.out.println("查找~");
        if (node == null) {
            System.out.println("这是个空树");
            return null;
        }
        // 左
        if (node.getLeft() != null) {
            TreeNode temp = findByMid(node.getLeft(), no);
            if (temp != null) {
                return temp;
            }
        }
        // 中
        if (node.getNo() == no) {
            return node;
        }

        // 右
        if (node.getRight() != null) {
            TreeNode temp = findByMid(node.getRight(), no);
            if (temp != null) {
                return temp;
            }
        }

        return null;
    }

    /**
     * 前序方式查找结点
     * @param node
     */
    public TreeNode findByPre(TreeNode node, int no) {
        System.out.println("查找~");
        if (node == null) {
            System.out.println("这是个空树！");
            return null;
        }

        if (node.getNo() == no) {
            return node;
        }

        // 向左
        if (node.getLeft() != null) {
            TreeNode temp = findByPre(node.getLeft(), no);
            if (temp != null) {
                return temp;
            }
        }
        // 向右
        if (node.getRight() != null) {
            TreeNode temp = findByPre(node.getRight(), no);
            if (temp != null) {
                return temp;
            }
        }
        return null;
    }

    /**
     * 前序遍历：先父节点，然后左节点，然后右节点
     */
    public void preSearch(TreeNode root) {
        System.out.println(root);
        if (root.getLeft() != null) {
            preSearch(root.getLeft());
        }
        if (root.getRight() != null) {
            preSearch(root.getRight());
        }

    }

    /**
     * 后续遍历。左-右-父
     * @param root
     */
    public void sufSearch(TreeNode root) {
        if (root.getLeft() != null) {
            sufSearch(root.getLeft());
        }
        if (root.getRight() != null) {
            sufSearch(root.getRight());
        }
        System.out.println(root);
    }

    /**
     * 中序遍历，左-中-右
     * @param root
     */
    public void midSearch(TreeNode root) {
        if (root.getLeft() != null) {
            midSearch(root.getLeft());
//            System.out.println(root.getLeft());
        }
            System.out.println(root);



        if (root.getRight() != null) {
//            System.out.println(root.getRight());
            midSearch(root.getRight());
        }
    }
}
