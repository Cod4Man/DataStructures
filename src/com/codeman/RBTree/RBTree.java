package com.codeman.RBTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: zhanghongjie
 * @description: 红黑树
 * <p>
 * 1. 红黑树为空数：
 * 直接插入就好，但是根节点需要插入黑色。
 * 2. 插入节点的key已存在：
 * 更新存在节点的value即可
 * 3. 插入的节点的父节点为黑色：
 * 直接插入即可，因为插入节点为黑色，不影响平衡
 * 4. 插入的节点父节点为红色（主要逻辑）
 * 4.1 叔叔节点(父的兄弟节点)也为红色
 * 那么爷爷节点一定为黑色，因为不可能红红相连，而插入节点为红色，因此会变成黑红红。最简单的做法
 * 就是变色，把爷爷节点变红，把父叔节点变黑，这样可以达到黑色完美平衡。然后再处理爷爷节点(制成
 * 当前节点)，处理爷爷节点主要看爷爷的父节点是什么颜色，如果是黑色不用管，红色破坏了红红补相连
 * 原则，需要按上述步骤(步骤4)重新处理爷爷节点(当前节点)
 * 4.2 叔叔节点不存在或为黑色，并且插入的节点的父节点是爷爷节点的左子节点（即往爷爷左树插入）
 * 4.2.1 插入节点为该节点的父节点的左子节点（LLr左左红）
 * 1）变色：父爷换色
 * 2）旋转：以爷爷节点为支点进行右旋
 * 4.2.2 插入节点为该节点的父节点的右子节点（LRr左右红）
 * 1）左旋：以父节点为支点左旋(设置原父节点为当前节点)
 * 2）然后就成了4.2.1，重复4.2.1即可（变色+右旋）
 * <p>
 * 4.3 叔叔节点不存在或为黑色，并且插入的节点的父节点是爷爷节点的右子节点（即往爷爷右树插入），
 * 步骤4.3其实是4.2反过来，逻辑左右处理相反即可。
 * 4.3.1 插入节点为该节点的父节点的左子节点（RLr左左红）
 * 1）变色：父爷换色
 * 2）左旋：以爷爷节点为指点左旋
 * 4.3.2 插入节点为该节点的父节点的右子节点（RRr左左红）
 * 1）右旋：以父节点为指点右旋
 * 2）同4.3.1一样（换色+左旋）
 * @date: 2021/5/21 9:46
 * @version: 1.0
 */
public class RBTree<K extends Comparable, V> {

    /**
     * 树根
     */
    private BRNode<K, V> root;


    /**
     * 添加操作
     *
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        if (root == null) {
            root = new BRNode<>(key, value);
            root.setColor(BRNodeColor.BLACK);
        } else {
            // 比较添加
            BRNode<K, V> newNode = onlyPut(key, value);
            // 自平衡
            autoBalance(newNode);

        }
    }

    /**
     * 自平衡
     * 1. 红黑树为空数：
     * 直接插入就好，但是根节点需要插入黑色。
     * 2. 插入节点的key已存在：
     * 更新存在节点的value即可
     * 3. 插入的节点的父节点为黑色：
     * 直接插入即可，因为插入节点为黑色，不影响平衡
     * 4. 插入的节点父节点为红色（主要逻辑）
     * 4.1 叔叔节点(父的兄弟节点)也为红色
     * 那么爷爷节点一定为黑色，因为不可能红红相连，而插入节点为红色，因此会变成黑红红。最简单的做法
     * 就是变色，把爷爷节点变红，把父叔节点变黑，这样可以达到黑色完美平衡。然后再处理爷爷节点(制成
     * 当前节点)，处理爷爷节点主要看爷爷的父节点是什么颜色，如果是黑色不用管，红色破坏了红红补相连
     * 原则，需要按上述步骤(步骤4)重新处理爷爷节点(当前节点)
     * 4.2 叔叔节点不存在或为黑色，并且插入的节点的父节点是爷爷节点的左子节点（即往爷爷左树插入）
     * 4.2.1 插入节点为该节点的父节点的左子节点（LLr左左红）
     * 1）变色：父爷换色
     * 2）旋转：以爷爷节点为支点进行右旋
     * 4.2.2 插入节点为该节点的父节点的右子节点（LRr左右红）
     * 1）左旋：以父节点为支点左旋(设置原父节点为当前节点)
     * 2）然后就成了4.2.1，重复4.2.1即可（变色+右旋）
     * <p>
     * 4.3 叔叔节点不存在或为黑色，并且插入的节点的父节点是爷爷节点的右子节点（即往爷爷右树插入），
     * 步骤4.3其实是4.2反过来，逻辑左右处理相反即可。
     * 4.3.1 插入节点为该节点的父节点的左子节点（RLr左左红）
     * 1）变色：父爷换色
     * 2）左旋：以爷爷节点为指点左旋
     * 4.3.2 插入节点为该节点的父节点的右子节点（RRr左左红）
     * 1）右旋：以父节点为指点右旋
     * 2）同4.3.1一样（换色+左旋）
     * @param newNode
     */
    private void autoBalance(BRNode<K, V> newNode) {
        // 1 和 2在onlyPut已经做了
        if (newNode != null) {
            // 3. 插入的节点的父节点为黑色：
            //  直接插入即可，因为插入节点为黑色，不影响平衡
            BRNode<K, V> parent = newNode.parent;
            if (parent != null) {
                // 红色才需要自平衡，因为不能双红
                if (parent.color == BRNodeColor.RED) {
                    // 4. 插入的节点父节点为红色（主要逻辑）
                    // 4.1 叔叔节点(父的兄弟节点)也为红色
                    // 那么爷爷节点一定为黑色，因为不可能红红相连，而插入节点为红色，因此会变成黑红红。最简单的做法
                    // 就是变色，把爷爷节点变红，把父叔节点变黑，这样可以达到黑色完美平衡。然后再处理爷爷节点(制成
                    // 当前节点)，处理爷爷节点主要看爷爷的父节点是什么颜色，如果是黑色不用管，红色破坏了红红补相连
                    // 原则，需要按上述步骤(步骤4)重新处理爷爷节点(当前节点)
                    BRNode<K, V> gPar = parent.parent;
                    if (gPar == null) {
                        if (parent.left == newNode) {
                            // 右旋
                            rightRotate(parent);
                        } else {
                            leftRotate(parent);
                        }
                        return;
                    }
                    if ((gPar.left != null && gPar.right != null) && gPar.left.color == gPar.right.color) {
                        // 父/叔节点都为红色
                        // 变色
                        gPar.left.color = gPar.right.color = BRNodeColor.BLACK;
                        gPar.setColor(BRNodeColor.RED);
                    } else {
                        if (gPar.left == parent) {
                            // 4.2 叔叔节点不存在或为黑色，并且插入的节点的父节点是爷爷节点的左子节点（即往爷爷左树插入）
                            if (parent.right == newNode) {
                                // 4.2.2 插入节点为该节点的父节点的右子节点（LRr左右红）
                                // 1）左旋：以父节点为支点左旋(设置原父节点为当前节点)
                                leftRotate(parent);
                                // newNode = parent;
                            }
                            // 2）然后就成了4.2.1，重复4.2.1即可（变色+右旋）

                            // 4.2.1 插入节点为该节点的父节点的左子节点（LLr左左红）
                            // 1）变色：父爷换色
                            gPar.setColor(BRNodeColor.RED);
                            // 不能用parent，因为parent已经变色
                            gPar.left.setColor(BRNodeColor.BLACK);
                            // 2）旋转：以爷爷节点为支点进行右旋
                            rightRotate(gPar);
                        } else {
                            // 4.3 叔叔节点不存在或为黑色，并且插入的节点的父节点是爷爷节点的右子节点（即往爷爷右树插入），

                            if (parent.left == newNode) {
                                // 4.3.2 插入节点为该节点的父节点的左子节点（RLr右左红）
                                // 1）右旋：以父节点为指点右旋
                                rightRotate(parent);
                            }

                            // 2）同4.3.1一样（换色+左旋）

                            // 步骤4.3其实是4.2反过来，逻辑左右处理相反即可。
                            // 4.3.1 插入节点为该节点的父节点的右子节点（RRr右右红）
                            // 1）变色：父爷换色
                            gPar.setColor(BRNodeColor.RED);
                            // 不能用parent，因为parent已经变色
                            gPar.right.setColor(BRNodeColor.BLACK);
                            // 2）左旋：以爷爷节点为指点左旋
                            leftRotate(gPar);
                        }

                    }
                    // 以爷爷节点为当前节点，继续重复（递归）
                    if (gPar.parent != null) {
                        autoBalance(gPar);
                    } else {
                        if (gPar.left == parent) {
                            rightRotate(gPar);
                        } else {
                            leftRotate(gPar);
                        }
                    }

                }
            } else {
                // 重置root
                root = newNode;
            }
        }

    }

    /**
     * 右旋
     *        A
     *
     *     B     E
     *
     * C      (D)
     *
     *
     * @param node
     */
    private void rightRotate(BRNode<K, V> node) {
        if (node != null) {
            BRNode<K, V> oldLeft = node.left;
            BRNode<K, V> oldParent = node.parent;
            if (oldLeft != null) {
                if (oldLeft.right != null) {
                    oldLeft.right.parent = node;
                }
                node.left = oldLeft.right;
                oldLeft.right = node;
                node.parent = oldLeft;
                if (oldParent != null) {
                    oldLeft.parent = oldParent;
                    if (oldParent.left == node) {
                        oldParent.left = oldLeft;
                    } else {
                        oldParent.right = oldLeft;
                    }
                } else {
                    // 改root
                    oldLeft.parent = null;
                    root = oldLeft;
                }
            }
        }
    }

    /**
     * 左旋
     *        A
     *
     *     B      E
     *
     *        (D)      F
     * @param node
     */
    private void leftRotate(BRNode<K, V> node) {
        if (node != null) {
            BRNode<K, V> oldRight = node.right;
            BRNode<K, V> oldParent = node.parent;
            if (oldRight != null) {
                node.right = oldRight.left;
                if (oldRight.left != null) {
                    oldRight.left.parent = node;
                }
                oldRight.left = node;
                node.parent = oldRight;

                if (oldParent != null) {
                    oldRight.parent = oldParent;
                    if (oldParent.left == node) {
                        oldParent.left = oldRight;
                    } else {
                        oldParent.right = oldRight;
                    }
                } else {
                    // 改root
                    oldRight.parent = null;
                    root = oldRight;
                }
            }
        }
    }

    /**
     * 只做比较插入
     * @param key
     * @param value
     */
    private BRNode<K, V> onlyPut(K key, V value) {
        BRNode<K, V> temp = root;
        while (true) {
            if (temp.key.compareTo(key) == 0) {
                // 相同则替换值
                temp.setValue(value);
                return temp;
            } else if (temp.key.compareTo(key) > 0) {
                if (temp.left == null) {
                    temp.left = new BRNode<>(key, value);
                    temp.left.setColor(BRNodeColor.RED);
                    temp.left.setParent(temp);
                    return temp.left;
                }
                // 小于则往左比较
                temp = temp.left;

            } else {
                if (temp.right == null) {
                    temp.right = new BRNode<>(key, value);
                    temp.right.setColor(BRNodeColor.RED);
                    temp.right.setParent(temp);
                    return temp.right;
                }
                // 大于则往右比较
                temp = temp.right;
            }
        }
    }


    /**
     * 获取最大值
     *
     * @return
     */
    public BREntity<K, V> getMax() {
        if (root != null) {
            BRNode<K, V> last = root;
            while (last.right != null) {
                last = last.right;
            }
            return new BREntity<>(last.key, last.value);
        }
        return null;
    }

    /**
     * 获取最小值
     *
     * @return
     */
    public BREntity<K, V> getMin() {
        if (root != null) {
            BRNode<K, V> first = root;
            while (first.left != null) {
                first = first.left;
            }
            return new BREntity<>(first.key, first.value);
        }
        return null;
    }

    /**
     * 中序遍历方法
     */
    public BREntity[] trans2MidEntiryArr() {
        List<BREntity> brEntityList = new ArrayList<>();
        doTrans2MidEntiryArr(root, brEntityList);
        return brEntityList.toArray(new BREntity[]{});
    }

    /**
     * 中序遍历：前 -> 中 -> 后
     * 要找到最前，可以用遍历
     *
     * @param node
     * @param brEntityList
     */
    private void doTrans2MidEntiryArr(BRNode<K, V> node, List<BREntity> brEntityList) {
        if (node != null) {
            // 左边递归
            doTrans2MidEntiryArr(node.left, brEntityList);
            // 左边递归直至node.left为空，然后开始封装对象
            brEntityList.add(transNode2Entity(node));
            // 封装完，递归右树
            doTrans2MidEntiryArr(node.right, brEntityList);
        }
    }

    private BREntity<K, V> transNode2Entity(BRNode<K, V> node) {
        return new BREntity<>(node.key, node.value);
    }

    /**
     * 对外暴露的key-value映射
     */
    public class BREntity<K, V> {
        K key;
        V value;

        public BREntity(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "[" +key + "]";
        }
    }


    /**
     * 树需要一个节点
     * 节点需要属性：
     * key/value/color/left/right/parent
     */
    private class BRNode<K, V> {
        K key;
        V value;
        BRNodeColor color;
        BRNode<K, V> parent, left, right;

        public BRNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public void setColor(BRNodeColor color) {
            this.color = color;
        }

        public void setParent(BRNode<K, V> parent) {
            this.parent = parent;
        }

        public void setLeft(BRNode<K, V> left) {
            this.left = left;
        }

        public void setRight(BRNode<K, V> right) {
            this.right = right;
        }
    }

    /**
     * 节点颜色
     */
    private enum BRNodeColor {
        RED, BLACK
    }

}
