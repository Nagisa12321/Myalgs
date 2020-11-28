package com.JTChen.TypeOfData;

import org.junit.Test;

public class MyBinarySearchTreeTest {
    public static void main(String[] args) {

    }

    @Test
    public void insert() throws IllegalAccessException {
        MyBinarySearchTree<Integer> myBinarySearchTree = new MyBinarySearchTree<>();
        myBinarySearchTree.insert(3);
        myBinarySearchTree.insert(1);
        myBinarySearchTree.insert(2);
        myBinarySearchTree.insert(5);
    }
}