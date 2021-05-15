package com.JTChen.typeofdata;

import java.util.Iterator;

/************************************************
 * @description 下压栈，数组实现，支持迭代~
 * @author jtchen
 * @date 2020/11/25 23:19
 * @version 2.0
 ************************************************/
@Deprecated
public class ResizingArrayStack<Item> implements Iterable<Item> {
    private Item[] items;
    private int i;//栈指针


    /**
     * 创建一个容量为cap的空栈
     *
     * @param cap 容量
     */
    public ResizingArrayStack(int cap) {
        //容量
        this.items = (Item[]) new Object[cap];
        this.i = -1;
    }

    /**
     * 增加一个字符串
     *
     * @param str 字符串
     */
    public void push(Item str) {
        if (++i == items.length) resize(2 * items.length);
        items[i] = str;
    }

    /**
     * 删除最近添加的字符串
     *
     * @return 所删除串
     */
    public Item pop() {
        if (i == -1)
            throw new StackOverflowError("Stack empty");
        else return items[i--];
    }

    /**
     * 判断栈是否为空
     *
     * @return 是否为空
     */
    public boolean isEmpty() {
        return i == -1;
    }

    /**
     * 栈中元素的数量
     *
     * @return 栈中元素的数量
     */
    public int size() {
        return i + 1;
    }

    /**
     * 栈满时，把数组的空间变大
     *
     * @param max 变大后的数组大小
     */
    private void resize(int max) {
        Item[] tmp = (Item[]) new Object[max];
        for (int j = 0; j < i; ++j)
            tmp[j] = items[j];
        items = tmp;
    }

    /**
     * 调用迭代器
     *
     * @return iterator对象
     */
    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    /**
     * 嵌套类实现迭代器
     */
    private class ReverseArrayIterator implements Iterator<Item> {
        private int tmp = i;

        @Override
        public boolean hasNext() {
            return tmp >= 0;
        }

        @Override
        public Item next() {
            return items[tmp--];
        }
    }
}
