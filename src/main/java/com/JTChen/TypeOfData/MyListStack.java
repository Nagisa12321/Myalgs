package com.JTChen.TypeOfData;

/************************************************
 * @description
 * @author jtchen
 * @date 2020/11/27 17:29
 * @version 1.0
 ************************************************/
@Deprecated
public class MyListStack<Item> {
    private MyLinkList<Item> linkList;

    /**
     * 创建一个空栈
     */
    public MyListStack() {
        linkList = new MyLinkList<>();
    }

    /**
     * 删除最近添加的字符串
     *
     * @return 所删除串
     */
    public Item pop() {
        if (linkList.isEmpty()) throw new StackOverflowError("Stack empty");
        Item tmp = peek();
        linkList.removeHead();
        return tmp;
    }

    /**
     * 增加一个字符串
     *
     * @param item 字符串
     */
    public void push(Item item) {
        linkList.insertHead(item);
    }

    public Item peek() {
        if (linkList.isEmpty()) throw new StackOverflowError("Stack empty");
        return linkList.get(0);
    }

    /**
     * 判断栈是否为空
     *
     * @return 是否为空
     */
    public boolean isEmpty() {
        return linkList.isEmpty();
    }

    /**
     * 栈中元素的数量
     *
     * @return 栈中元素的数量
     */
    public int size() {
        return linkList.length();
    }

    /**
     * 清空栈
     */
    public void Empty() {
        linkList = new MyLinkList<>();
    }
}
