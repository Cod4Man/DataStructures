package com.codeman.hashtab;

public class HashTableTest {
    public static void main(String[] args) {
//        testEmpLinkedList();
        testMyHashTable();
    }

    private static void testMyHashTable() {
        Emp emp1 = new Emp("yi", 1);
        Emp emp2 = new Emp("er", 2);
        Emp emp3 = new Emp("san", 3);
        Emp emp4 = new Emp("si", 4);
        Emp emp6 = new Emp("liu", 6);
        Emp emp8 = new Emp("ba", 8);
        MyHashTable hashTable = new MyHashTable();
        hashTable.addEmp(emp1);
        hashTable.addEmp(emp2);
        hashTable.addEmp(emp3);
        hashTable.addEmp(emp4);
        hashTable.addEmp(emp6);
        hashTable.addEmp(emp8);
        System.out.println(hashTable.findById(9));

    }

    private static void testEmpLinkedList() {
        EmpLinkedList linkedList = new EmpLinkedList();
        Emp emp1 = new Emp("yi", 1);
        Emp emp2 = new Emp("er", 2);
        Emp emp3 = new Emp("san", 3);
        Emp emp4 = new Emp("si", 4);
        linkedList.addEmp(emp1);
        linkedList.addEmp(emp3);
        linkedList.addEmp(emp2);
        linkedList.addEmp(emp4);
        linkedList.print();
        System.out.println("===============");
        System.out.println(linkedList.findById(6));
    }
}
