package com.codeman.dataType;


/**
 * 环形队列
 */
public class AroundQueue {
    public static void main(String[] args) {
        Queue queue = new Queue(5);
//        queue.getAll();
        try {

            int nextVal = queue.get();
            System.out.printf("nextVal = %d " ,nextVal);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        queue.add(5);
        queue.getAll();
        queue.add(7);
        queue.add(8);
        queue.add(9);
        queue.add(10);
        queue.add(11);
        queue.getAll();
        int nextVal2 = queue.get();
        System.out.printf("nextVal2 = %d " ,nextVal2);
        int nextVal33 = queue.get();
        System.out.printf("nextVal33 = %d " ,nextVal33);
        queue.getAll();
        queue.add(10);
        queue.add(11);
        queue.add(12);
        queue.getAll();
        int nextVal4 = queue.get();
        System.out.printf("nextVal44 = %d " ,nextVal4);
        queue.getAll();
    }

}

class Queue {
    private int maxSize; // 最大长度
    private int front; // 前指针
    private int end; // 尾指针
    private int[] arr; // 数据

    public Queue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    private boolean isFull() {
        return lengthReal() == maxSize - 1;
    }

    private boolean isEmpty() {
        return front == end;
    }

    private int lengthReal() {
        return (maxSize + end - front) % maxSize;
    }

    /**
     * 增加元素
     * @return
     */
    public void add(int element) {
        if (isFull()) {
            System.out.println("队列已满！");
            return ;
        }
        // 增加元素
        end = (end + 1) % maxSize;
        arr[end] = element;
    }

    /**
     * 取下一个值
     * @return
     */
    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        front = (front + 1) % maxSize;
        return arr[front];
    }

    public void getAll() {
        for (int i = front + 1; i < front + 1 + lengthReal() ; i ++) {
            System.out.printf("arr[%d]=%d\t", i % maxSize , arr[i % maxSize]);
        }
        System.out.println();
    }
}
