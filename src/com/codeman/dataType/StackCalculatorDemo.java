package com.codeman.dataType;

public class StackCalculatorDemo {
    public static void main(String[] args) {
        // 88*((22-2)-5*6)/7+2
        // 88*22-2-5*6/7+2
        System.out.println(new Calculator("88*22-2-5*6/7+2*35.15").result());




    }
}

class Calculator {
    private String str;
    private MyStack stackNum = new MyStack();
    private MyStack stackOper = new MyStack();

    public Calculator(String str) {
        this.str = str;
        oper();
    }

    public Object result() {
        while (!stackOper.empty()) {
            popOper();
        }
        return stackNum.pop();
    }

    private void oper() {
        int temp = -2;
        int tempIndex = 0;
        for (int i = 0; i < str.length(); i ++) {
            if (compOper(str.charAt(i)) == -1) {
                continue;
            }

            stackNum.push(new Node(Double.valueOf(str.substring(tempIndex,i))));
            // 符号等级小，可以先进行运算部分
            while (compOper(str.charAt(i)) <= temp) {
                popOper();
                temp = stackOper.empty() ? -2 : compOper((Character) stackOper.peek().getNo());
            }
            stackOper.push(new Node(str.charAt(i)));
            temp = compOper((Character) stackOper.peek().getNo());
            tempIndex = i + 1;

        }
        stackNum.push(new Node(Double.valueOf(str.substring(tempIndex))));
    }

    private void popOper() {
        Object tempOper = stackOper.pop().getNo();
        Object tempNum1 = stackNum.pop().getNo();
        Object tempNum2 = stackNum.pop().getNo();
        stackNum.push(new Node(oper(tempNum1, tempNum2, tempOper)));
    }

    private double oper(Object tempNum1, Object tempNum2, Object tempOper) {
        double result = 0.0;
        switch ((char)tempOper) {
            case '/' : result = (double)tempNum2 / (double)tempNum1; break;
            case '*' : result = (double)tempNum2 * (double)tempNum1; break;
            case '+' : result = (double)tempNum2 + (double)tempNum1; break;
            case '-' : result = (double)tempNum2 - (double)tempNum1; break;
            default:  result = -1; break;
        }
        System.out.printf("【%s】%s【%s】=【%s】",tempNum2,tempOper,tempNum1,result);
        System.out.println();
        return result;
    }

    private int compOper(char str) {
        int result = -1;
        switch (str) {
            case '/' :
            case '*' : result = 2; break;
            case '+' :
            case '-' : result = 1; break;
            default: result = -1; break;
        }

        return result;
    }
}

class MyStack {
    private Node first;

    public MyStack() {
        first = new Node(-1);
    }

    public void push(Node node) {
        if (first.getNext() == null) {
            first.setNext(node);
            return ;
        }
        node.setNext(first.getNext());
        first.setNext(node);
    }

    public Node pop() {
        if (empty()) {
            throw new RuntimeException("stack为空");
        }
        Node temp = first;
        Node popNode = temp.getNext();
        first.setNext(first.getNext().getNext());
        popNode.setNext(null);
        return popNode;
    }

    public Node peek() {
        if (empty()) {
            throw new RuntimeException("stack为空");
        }
        Node clone = (Node) first.getNext().clone();
        clone.setNext(null);
        return clone;
    }

    public boolean empty() {
        return first.getNext() == null;
    }

    public void showStacks() {
        Node temp = first;
        while (temp.getNext() != null) {
            System.out.println(temp.getNext());
            temp = temp.getNext();
        }
    }
}


class Node implements Cloneable{
    private Object no;
    private Node next;

    public Node(Object no) {
        this.no = no;
    }


    public Object getNo() {
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
                "}";
    }

    @Override
    protected Object clone(){
        Node node = null;
        try {
            node = (Node) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return node;
    }
}

