package com.jtchen.typeofdata;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MyBinarySearchTreeTest {

    @Test
    public void insert() throws IllegalAccessException {
        MyBinarySearchTree<Integer> myBinarySearchTree = new MyBinarySearchTree<>();
        myBinarySearchTree.insert(3);
        myBinarySearchTree.insert(1);
        myBinarySearchTree.insert(2);
        myBinarySearchTree.insert(5);
    }

    @Test
    public void randomlyGenerated() {
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

    @SuppressWarnings("unused")
    @Test
    public void search() throws IllegalAccessException {
        Integer[] test = {5, 4, 8, 2, 7};
        MyBinarySearchTree<Integer> tree = new MyBinarySearchTree<>(test);
    }

    @Test
    public void searchMax() {
        MyBinarySearchTree<Integer> tree = new MyBinarySearchTree<>();
        tree.RandomlyGenerated();
        List<Integer> list = tree.InOrderTraversal();
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(tree.searchMax());
        System.out.println(tree.searchMin());

    }

    @Test
    public void searchMin() {
        MyBinarySearchTree<Integer> tree = new MyBinarySearchTree<>();
        tree.RandomlyGenerated();
        List<Integer> list = tree.InOrderTraversal();
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(tree.searchMin());
        System.out.println(tree.searchMax());
    }

    @Test
    public void precursor() throws IllegalAccessException {
        Integer[] test = {15, 6, 23, 4, 7, 71, 5, 50};
        MyBinarySearchTree<Integer> tree = new MyBinarySearchTree<>(test);
        assertEquals(6, (int) tree.precursor(7));
        assertEquals(50, (int) tree.precursor(71));
        assertEquals(23, (int) tree.precursor(50));
        assertEquals(7, (int) tree.precursor(15));
        assertNull(tree.precursor(4));
    }

    @Test
    public void successor() throws IllegalAccessException {
        Integer[] test = {15, 6, 23, 4, 7, 71, 5, 50};
        MyBinarySearchTree<Integer> tree = new MyBinarySearchTree<>(test);
        assertEquals(15, (int) tree.successor(7));
        assertEquals(23, (int) tree.successor(15));
        assertEquals(50, (int) tree.successor(23));
        assertNull(tree.successor(71));
    }

    @Test
    public void remove() throws IllegalAccessException {
        Integer[] test = {41, 65, 50, 91, 56, 72, 99, 73};
        MyBinarySearchTree<Integer> tree = new MyBinarySearchTree<>(test);
        List<Integer> list = tree.InOrderTraversal();
        System.out.println(Arrays.toString(list.toArray()));
        tree.remove(65);
        List<Integer> list2 = tree.InOrderTraversal();
        System.out.println(Arrays.toString(list2.toArray()));
    }

    @Test
    public void levelTraversal() throws IllegalAccessException {
        Integer[] test = {41, 65, 50, 91, 56, 72, 99, 73};
        MyBinarySearchTree<Integer> tree = new MyBinarySearchTree<>(test);
        System.out.println(Arrays.toString(tree.LevelTraversal().toArray()));
    }
}