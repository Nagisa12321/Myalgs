package com.JTChen.TypeOfData;

import edu.princeton.cs.algs4.StdRandom;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/************************************************
 * @description 用内部数据结构TreeNode实现的二叉搜索树
 * ------查询------
 * 1.搜索
 * - 搜索最大 - 搜索最小
 * 2.
 * - 前身 - 后继
 * 3.遍历
 * - 中序遍历 - 后续遍历 - 前序遍历 - 层次遍历
 * ------更新------
 * 1.插入
 * 2.删除
 * 3.创建BST
 * - 随即创建 - 创建空 - 根据数组创建
 * @author jtchen
 * @date 2020/11/28 11:38
 * @version 1.0
 ************************************************/
public class MyBinarySearchTree<Item> {
    private TreeNode root;

    /**
     * 构建空二叉搜索树
     */
    public MyBinarySearchTree() {

    }

    /**
     * 通过数组构建二叉搜索树.遍历一遍数组，然后依次通过insert方法添加
     *
     * @param items 与生成的二叉搜索树的节点顺序
     */
    public MyBinarySearchTree(Item[] items) throws IllegalAccessException {
        for (Item i : items) insert(i);
    }

    /**
     * 随机生成二叉搜索树。<number>为二叉树节点个数，
     * 借助了ALGS4的Random.uniform方法，生成0-100的随机数
     * 而且每次都存入HashSet中，达到去重复的功能
     */
    @SuppressWarnings("unchecked")
    public void RandomlyGenerated() throws IllegalAccessException {
        int number = 10;//随机数个数
        Set<Item> set = new HashSet<>();
        while (set.size() < 10)
            set.add((Item) (Integer) StdRandom.uniform(0, 100));
        for (Item i : set) insert(i);
    }

    /**
     * 向二叉搜索树添加元素。如果该二叉树为空，则直接把head节点new并赋值即可
     * 然后就进行<二进制搜索>，对比大小，如果小的话则更新节点到左边，大的话
     * 更新节点到右边，若相等，因为这是二叉搜索树，不允许相等节点的存在，则
     * 抛出一个<IllegalAccessException>
     *
     * @param item 所添加的元素
     */
    public void insert(Item item) throws IllegalAccessException {
        if (isEmpty()) root = new TreeNode(item);
        else {
            TreeNode tmp = root;
            while (true) {
                if ((Integer) item < (Integer) tmp.value) {
                    if (tmp.left == null) {
                        TreeNode treeNode = new TreeNode(item);
                        tmp.left = treeNode;
                        treeNode.parent = tmp;
                        return;
                    } else tmp = tmp.left;
                } else if ((Integer) item > (Integer) tmp.value) {
                    if (tmp.right == null) {
                        TreeNode treeNode = new TreeNode(item);
                        tmp.right = treeNode;
                        treeNode.parent = tmp;
                        return;
                    } else tmp = tmp.right;
                } else throw new IllegalAccessException("The element already exists!");
            }
        }
    }

    /**
     * 向二叉搜索树中删除元素
     *
     * @param item 所要删除的元素
     */
    public void remove(Item item) {

    }

    /**
     * 找寻节点为item的元素
     *
     * @param item 节点值
     * @return 返回该节点
     */
    public TreeNode search(Item item) {
        TreeNode tmp = root;
        while (true) {
            if (tmp.value == item) return tmp;
            else if ((Integer) tmp.value > (Integer) item) {
                if (tmp.left == null) return null;
                else tmp = tmp.left;
            } else {
                if (tmp.right == null) return null;
                else tmp = tmp.right;
            }
        }
    }

    /**
     * 找寻最大节点位置
     *
     * @return 返回该节点
     */
//    public MyBinarySearchTree<Item> searchMax() {
//
//    }

    /**
     * 找寻最小节点位置
     *
     * @return 返回该节点
     */
//    public MyBinarySearchTree<Item> searchMin() {
//
//    }

    /**
     * 寻找前驱动
     *
     * @return 前驱节点
     */
//    public MyBinarySearchTree<Item> precursor() {
//
//    }

    /**
     * 寻找后继
     *
     * @return 后继节点
     */
//    public MyBinarySearchTree<Item> successor() {
//
//    }

    /**
     * 递归中序遍历方法
     *
     * @param root 下一个节点值
     */
    private void InOrderTraversal(TreeNode root, LinkedList<Item> list) {
        if (root != null) {
            InOrderTraversal(root.left, list);
            list.add(root.value);
            InOrderTraversal(root.right, list);
        }
    }

    /**
     * 中序遍历
     *
     * @return 遍历后以链表形式输出
     */
    public LinkedList<Item> InOrderTraversal() {
        TreeNode tmp = root;
        LinkedList<Item> list = new LinkedList<>();
        InOrderTraversal(tmp, list);
        return list;
    }

    /**
     * 递归前序遍历方法
     *
     * @param root 下一个节点值
     */
    private void PreorderTraversal(TreeNode root, LinkedList<Item> list) {
        if (root != null) {
            list.add(root.value);
            InOrderTraversal(root.left, list);
            InOrderTraversal(root.right, list);
        }
    }

    /**
     * 前序遍历
     *
     * @return 遍历后以链表的形式输出
     */
    public LinkedList<Item> PreorderTraversal() {
        TreeNode tmp = root;
        LinkedList<Item> list = new LinkedList<>();
        PreorderTraversal(tmp, list);
        return list;
    }

    /**
     * 递归后序遍历方法
     *
     * @param root 下一个节点值
     */
    private void PostOrderTraversal(TreeNode root, LinkedList<Item> list) {
        if (root != null) {
            InOrderTraversal(root.left, list);
            InOrderTraversal(root.right, list);
            list.add(root.value);
        }
    }

    /**
     * 后序遍历
     *
     * @return 遍历后以链表的形式输出
     */
    public LinkedList<Item> PostOrderTraversal() {
        TreeNode tmp = root;
        LinkedList<Item> list = new LinkedList<>();
        PostOrderTraversal(tmp, list);
        return list;
    }

    /**
     * 判断是否为空,若head为null，则该树木为空
     *
     * @return 是否为空
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * 层次遍历
     *
     * @return 遍历后以链表的形式输出
     */
//    public LinkedList<Item> LevelTraversal() {
//
//    }

    public class TreeNode {
        public TreeNode left;
        public TreeNode right;
        public TreeNode parent;
        public Item value;

        public TreeNode() {

        }

        public TreeNode(Item value) {
            this.value = value;
        }
    }

}
