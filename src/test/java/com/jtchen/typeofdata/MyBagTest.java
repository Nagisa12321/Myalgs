package com.jtchen.typeofdata;

import org.junit.Test;

public class MyBagTest {

    @Test
    public void add() {
        MyBag<Integer> bag = new MyBag<>();
        bag.add(1);
        bag.add(2);
        bag.add(3);
        bag.add(4);
    }

    @Test
    public void isEmpty() {
        MyBag<Integer> bag = new MyBag<>();
        System.out.println(bag.isEmpty());
        bag.add(1);
        System.out.println(bag.isEmpty());
    }

    @Test
    public void size() {
        MyBag<Integer> bag = new MyBag<>();
        System.out.println(bag.size());
        bag.add(1);
        bag.add(2);
        bag.add(3);
        bag.add(4);
        System.out.println(bag.size());
    }

    @Test
    public void iterator() {
        MyBag<Integer> bag = new MyBag<>();
        bag.add(1);
        bag.add(2);
        bag.add(3);
        bag.add(4);
        bag.add(1);
        bag.add(2);
        bag.add(3);
        bag.add(4);
        for (int i : bag)
            System.out.println(i);
    }
}