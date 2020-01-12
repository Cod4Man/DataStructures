package com.codeman.hashtab;

/**
 * 雇员链表
 */
public class EmpLinkedList {
    private Emp root;

    public void addEmp(Emp emp) {
        if (root == null) {
            root = emp;
        } else {
            Emp temp = root;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(emp);
        }
    }

    public Emp findById(int no) {
        if (root == null) {
            return null;
        } else {
            if (root.getNo() == no) {
                return root;
            }
            Emp temp = root;
            while (temp.getNext() != null) {
                temp = temp.getNext();
                if (temp.getNo() == no) {
                    return temp;
                }
            }
            return null;
        }
    }

    public void print() {
        if (root == null) {
            System.out.println("链表为空");
        } else {
            System.out.println(root);
            Emp temp = root;
            while (temp.getNext() != null) {
                temp = temp.getNext();
                System.out.println(temp);
            }
        }
    }
}
