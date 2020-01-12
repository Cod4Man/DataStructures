package com.codeman.hashtab;

/**
 * Hash�洢
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
            throw new RuntimeException("������󳤶ȣ�");
        }
        if (size < 0) {
            throw new RuntimeException("��������Ϊ1");
        }
        this.size = size;
        empLinkedLists = new EmpLinkedList[size];
        for (int i = 0; i < empLinkedLists.length; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    /**
     * ���ҹ�Ա
     * @param no
     * @return
     */
    public Emp findById(int no) {
        return empLinkedLists[calculateHash(no)].findById(no);
    }

    /**
     * ��ӹ�Ա��Hash����
     * @param emp
     */
    public void addEmp(Emp emp) {
        if (emp == null) {
            throw new RuntimeException("������ӿճ�Ա");
        }
        // ����Hash
        empLinkedLists[calculateHash(emp.getNo())].addEmp(emp);
    }

    /**
     * ����ñ�������hashֵ
     * @param no
     * @return
     */
    private int calculateHash(int no) {
        return no % size;
    }
}
