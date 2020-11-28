package com.JTChen.TypeOfData;

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
    private TreeNode head;

    /**
     * 构建空二叉搜索树
     */
    public MyBinarySearchTree() {

    }

    /**
     * 通过数组构建二叉搜索树
     *
     * @param items 与生成的二叉搜索树的节点顺序
     */
    public MyBinarySearchTree(Item[] items) {

    }

    /**
     * 随机生成二叉搜索树
     */
    public void RandomlyGenerated() {

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
        if (isEmpty()) head = new TreeNode(item);
        else {
            TreeNode tmp = head;
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
     * 递归中序遍历方法
     *
     * @param root 下一个节点值
     */
    private void InOrderTraversal(TreeNode root) {

    }

    /**
     * 找寻节点为item的元素
     *
     * @param item 节点值
     * @return 返回该节点
     */
//    public MyBinarySearchTree<Item> search(Item item) {
//
//    }

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
     * 中序遍历
     *
     * @return 遍历后以链表形式输出
     */
//    public LinkedList<Item> InOrderTraversal() {
//
//    }

    /**
     * 递归前序遍历方法
     *
     * @param root 下一个节点值
     */
    public void PreorderTraversal(TreeNode root) {

    }

    /**
     * 前序遍历
     *
     * @return 遍历后以链表的形式输出
     */
//    public LinkedList<Item> PreorderTraversal() {
//
//    }

    /**
     * 递归后序遍历方法
     *
     * @param root 下一个节点值
     */
    public void PostOrderTraversal(TreeNode root) {

    }

    /**
     * 后序遍历
     *
     * @return 遍历后以链表的形式输出
     */
//    public LinkedList<Item> PostOrderTraversal() {
//
//    }

    /**
     * 判断是否为空,若head为null，则该树木为空
     *
     * @return 是否为空
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * 层次遍历
     *
     * @return 遍历后以链表的形式输出
     */
//    public LinkedList<Item> LevelTraversal() {
//
//    }

    private class TreeNode {
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
