package com.JTChen.TypeOfData;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class MyBinarySearchTreeTest {
//    public static void main(String[] args) throws IllegalAccessException {
//        Integer[] test = {5,4,8,2,7};
//        MyBinarySearchTree<Integer> a = new MyBinarySearchTree<>();
//        MyBinarySearchTree<Integer> b = new MyBinarySearchTree(test);
////        b.insert(2);
//    }

    @Test
    public void insert() throws IllegalAccessException {
        MyBinarySearchTree<Integer> myBinarySearchTree = new MyBinarySearchTree<>();
        myBinarySearchTree.insert(3);
        myBinarySearchTree.insert(1);
        myBinarySearchTree.insert(2);
        myBinarySearchTree.insert(5);
    }

    @Test
    public void randomlyGenerated() throws IllegalAccessException {
        MyBinarySearchTree<Integer> tree = new MyBinarySearchTree<>();
        tree.RandomlyGenerated();

    }

    @Test
    public void inOrderTraversal() throws IllegalAccessException {
        Integer[] test = {5, 4, 8, 2, 7};
        MyBinarySearchTree<Integer> tree = new MyBinarySearchTree<>(test);
        List<Integer> list = tree.InOrderTraversal();
        System.out.println(Arrays.toString(list.toArray()));
    }

    @Test
    public void preorderTraversal() throws IllegalAccessException {
        Integer[] test = {5, 4, 8, 2, 7};
        MyBinarySearchTree<Integer> tree = new MyBinarySearchTree<>(test);
        List<Integer> list = tree.PreorderTraversal();
        System.out.println(Arrays.toString(list.toArray()));
    }

    @Test
    public void postOrderTraversal() throws IllegalAccessException {
        Integer[] test = {5, 4, 8, 2, 7};
        MyBinarySearchTree<Integer> tree = new MyBinarySearchTree<>(test);
        List<Integer> list = tree.PostOrderTraversal();
        System.out.println(Arrays.toString(list.toArray()));
    }

    @Test
    public void search() throws IllegalAccessException {
        Integer[] test = {5, 4, 8, 2, 7};
        MyBinarySearchTree<Integer> tree = new MyBinarySearchTree<>(test);
        MyBinarySearchTree.TreeNode node = tree.search(99);
    }
}