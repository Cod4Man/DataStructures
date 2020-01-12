package com.codeman.hashtab;

/**
 * Hash存储
 */
public class MyHashTable {
    private int size = 5;
    private static final int MAX_SIZE= 50;
    private EmpLinkedList[] empLinkedLists;

    public MyHashTable() {
        empLinkedLists = new EmpLinkedList[size];
        for (int i = 0; i < empLinkedLists.length; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    public MyHashTable(int size) {
        if (size > MAX_SIZE) {
            throw new RuntimeException("超过最大长度！");
        }
        if (size < 0) {
            throw new RuntimeException("长度至少为1");
        }
        this.size = size;
        empLinkedLists = new EmpLinkedList[size];
        for (int i = 0; i < empLinkedLists.length; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    /**
     * 查找雇员
     * @param no
     * @return
     */
    public Emp findById(int no) {
        return empLinkedLists[calculateHash(no)].findById(no);
    }

    /**
     * 添加雇员至Hash表中
     * @param emp
     */
    public void addEmp(Emp emp) {
        if (emp == null) {
            throw new RuntimeException("不可添加空成员");
        }
        // 计算Hash
        empLinkedLists[calculateHash(emp.getNo())].addEmp(emp);
    }

    /**
     * 查出该编号所存的hash值
     * @param no
     * @return
     */
    private int calculateHash(int no) {
        return no % size;
    }
}
