package com.JTChen.TypeOfData;

/************************************************
 * @description 简单的栈类型，只能处理String，他要求
 * 指定一个容量且不支持迭代
 * @author jtchen
 * @date 2020/11/25 23:19
 * @version 1.0
 ************************************************/
public class FixedCapacityStackOfStrings {
    private int cap;//容量
    private String[] strings;
    private int i;//栈指针

    /**
     * 创建一个容量为cap的空栈
     *
     * @param cap 容量
     */
    public FixedCapacityStackOfStrings(int cap) {
        this.cap = cap;
        this.strings = new String[cap];
        this.i = -1;
    }

    /**
     * 增加一个字符串
     *
     * @param str 字符串
     */
    public void push(String str) {
        strings[++i] = str;
    }

    /**
     * 删除最近添加的字符串
     *
     * @return 所删除串
     */
    public String pop() {
        if (i == -1)
            throw new StackOverflowError("Stack empty");
        else return strings[i--];
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
     * 栈中字符串的数量
     *
     * @return 栈中字符串的数量
     */
    public int size() {
        return i + 1;
    }
}
