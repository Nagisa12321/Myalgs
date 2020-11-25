package com.JTChen.TypeOfData;

import java.util.EmptyStackException;

/**
 * @author JT Chen
 * @version 1.0
 * @date 2020/11/13
 */
public class MyStack {
    //栈空间大小
    private final int[] data = new int[10000];
    //栈指针
    private int i = -1;

    public int pop() {
        //栈空
        if(isEmpty())
            throw new EmptyStackException();
        int temp = data[i];
        --i;
        return temp;
    }

    public void push(int item) {
        ++i;
        data[i] = item;
    }

    public int peek() {
        if(isEmpty())
            throw new EmptyStackException();
        return data[i];
    }

    public boolean isEmpty() {
        return i == -1;
    }

    public void Empty(){
//        data = new int[10000];
        i = -1;
    }
}

