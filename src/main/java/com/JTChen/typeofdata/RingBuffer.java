package com.JTChen.typeofdata;

/************************************************
 * 环形缓冲区, 具有队列的功能, 迭代功能待完善, 但是用数组
 * 完成，操作均为O(1);
 * @author jtchen
 * @date 2020/12/12 15:42
 * @version 1.0
 ************************************************/
public class RingBuffer<Item> {

    private final Object[] items;
    private final int BUFFER_SIZE;
    private int head;
    private int tail;

    /**
     * 创建一个大小为N的环形缓冲区
     *
     * @param N 缓冲区大小
     */
    public RingBuffer(int N) {
        this.BUFFER_SIZE = N + 1;
        this.items = new Object[BUFFER_SIZE];
        this.head = 0;
        this.tail = 0;
    }

    /**
     * 判断环形缓冲区是否为空
     *
     * @return 是否为空
     */
    public boolean isEmpty() {
        return head == tail;
    }

    /**
     * 如果环形缓冲区不为满, 则进入缓冲区并且返回{@code true};
     * 如果环形缓冲区为满, 则直接返回{@code false};
     *
     * @param item 待入队元素
     * @return 是否如对成功(为满则不成功)
     */
    public boolean enqueue(Item item) {
        if (isFull()) return false;
        else {
            items[head] = item;
            head = (head + 1) % BUFFER_SIZE;
            return true;
        }
    }

    /**
     * 如果环形缓冲区为空, 则返回{@code null}；
     *
     * @return 出队元素
     */
    @SuppressWarnings("unchecked")
    public Item dequeue() {
        if (isEmpty()) return null;
        else {
            Object item = items[tail];
            tail = (tail + 1) % BUFFER_SIZE;
            return (Item) item;
        }
    }

    /**
     * 回归初态
     */
    public void free() {
        tail = head;
    }

    /**
     * 环形缓冲区是否为满
     *
     * @return 是否为满
     */
    private boolean isFull() {
        return (head + 1) % BUFFER_SIZE == tail;
    }
}
