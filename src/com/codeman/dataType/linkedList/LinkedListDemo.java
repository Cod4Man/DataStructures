package com.codeman.dataType.linkedList;

public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedListt listt = new LinkedListt();
        LinkedListt listt2 = new LinkedListt();
//        listt.addNode(new Node(2,"二"));
//        listt.addNode(new Node(3,"三"));
//        listt.addNode(new Node(1,"一"));
//        listt.addNode(new Node(4,"四"));
        listt.addNodeWithSort(new Node(2,"二"));
        listt.addNodeWithSort(new Node(8,"八"));
        listt.addNodeWithSort(new Node(6,"六"));
        listt.addNodeWithSort(new Node(4,"四"));
        listt2.addNodeWithSort(new Node(7,"七"));
        listt2.addNodeWithSort(new Node(5,"五"));
        listt2.addNodeWithSort(new Node(9,"九"));
        listt2.addNodeWithSort(new Node(3,"三"));
        listt.showNodes();
        listt2.showNodes();



    }
}

class LinkedListt {
    private Node first = new Node(-1,"");

    public void addNode(Node node) {
        Node temp = first;
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        temp.setNext(node);
    }

    public void addNodeWithSort(Node node) {
        Node temp = first;
        while (temp.getNext() != null && temp.getNext().getNo() < node.getNo()) {
            temp = temp.getNext();
        }
        if (temp.getNext() == null) {
            temp.setNext(node);
            return ;
        }
        node.setNext(temp.getNext());
        temp.setNext(node);
    }

    public void showNodes() {
        System.out.println("===============打印Start=============");
        Node temp = first;
        while (temp.getNext() != null) {
            temp = temp.getNext();
            System.out.println(temp);
        }
        System.out.println("===============打印End===============");
    }

    public Node getFirst() {
        return first;
    }

    public void setFirst(Node first) {
        this.first = first;
    }
}

class Node {
    private int no;
    private String name;
    private Node next;

    public Node(int no, String name) {
        this.no = no;
        this.name = name;
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

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
