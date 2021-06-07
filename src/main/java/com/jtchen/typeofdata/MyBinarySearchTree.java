package com.jtchen.typeofdata;

import edu.princeton.cs.algs4.StdRandom;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
@Deprecated
public class MyBinarySearchTree<Item extends Comparable<Item>> {
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
    public void RandomlyGenerated() {
        int number = 10;//随机数个数
        Set<Item> set = new HashSet<>();
        while (set.size() < number)
            set.add((Item) (Integer) StdRandom.uniform(0, 100));
        for (Item i : set)
            try {
                insert(i);
            } catch (IllegalAccessException ignored) {
            }

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
     * 如果他是叶子节点(左右儿子均为空),直接删除
     * 如果他只有一个孩子, 那么用他的孩子代替他
     * 如果他有两个孩子，则用他的后继节点代替他
     *
     * @param item 所要删除的元素
     */
    public void remove(Item item) {
        TreeNode node = search(item);
        TreeNode parent = node.parent;
        //if node is a leaf, delete node
        if (node.left == null && node.right == null) {
            if ((Integer) parent.value > (Integer) node.value)
                parent.left = null;
            else parent.right = null;
        } else if (node.left != null && node.right == null) {
            //if have a children, replace it;
            node = node.left;
            node.parent = parent;
        } else if (node.left == null) {
            node = node.right;
            node.parent = parent;
        } else {
            //if have two children, replace with successor;
            TreeNode left = node.left;
            TreeNode right = node.right;
            TreeNode successor = searchMin(node.right);
            if (successor.value == right.value) {
                node = right;
                node.left = left;
            } else {
                successor.parent.left = successor.right;
                node = successor;
                node.left = left;
                node.right = right;
            }
            node.parent = parent;
            if ((Integer) parent.value > (Integer) node.value) parent.left = node;
            else parent.right = node;
        }
    }

    /**
     * 找寻节点为item的元素:先定义tmp节点作为遍历节点，如果tmp之
     * value值等于所求value，则直接返回tmp，若tmp之value值小于
     * 所要求value值则说明tmp偏左，应该向右移动，然后观察tmp是否有
     * 右节点，若没有右节点，则说明item值在本二叉树中不存在，返回null
     * 如此往复。
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
     * 找寻最大节点位置：如果root为null，则说明二叉树为空
     * 因此也没有最大节点，若不为空，则一直向右搜索，直到没有右
     * 节点为止，
     *
     * @return 返回该节点值
     */
    public Item searchMax() {
        if (root == null) return null;
        else {
            TreeNode tmp = root;
            while (tmp.right != null)
                tmp = tmp.right;
            return tmp.value;
        }
    }

    /**
     * 找寻最小节点位置
     * 同上~
     *
     * @return 返回该节点
     */
    public Item searchMin() {
        if (root == null) return null;
        else {
            TreeNode tmp = root;
            while (tmp.left != null)
                tmp = tmp.left;
            return tmp.value;
        }
    }

    /**
     * 找寻最小节点，是为找前驱而准备的方法
     *
     * @param root 当前节点
     * @return 返回当前节点的最小子节点
     */
    private TreeNode searchMin(TreeNode root) {
        if (root == null) return null;
        else {
            TreeNode tmp = root;
            while (tmp.left != null)
                tmp = tmp.left;
            return tmp;
        }
    }

    /**
     * 找寻最大节点，是为找前驱而准备的方法
     *
     * @param root 当前节点
     * @return 返回当前节点的最大子节点
     */
    private TreeNode searchMax(TreeNode root) {
        if (root == null) return null;
        else {
            TreeNode tmp = root;
            while (tmp.right != null)
                tmp = tmp.right;
            return tmp;
        }
    }

    /**
     * 寻找前驱
     *
     * @param item 节点值
     * @return 前驱节点
     */
    public Item precursor(Item item) {
        //找到该值所对应的节点
        TreeNode node = search(item);
        if (node == null) return null;
        else if (node.left != null) return searchMax(node.left).value;
        else {
            TreeNode t = node, p = t.parent;
            while (p != null && p.left == t) {
                t = p;
                p = p.parent;
            }
            if (p == null) return null;
            else return p.value;
        }
    }

    /**
     * 寻找后继
     *
     * @return 后继节点
     */
    public Item successor(Item item) {
        //在二叉树中找出该值的相应节点
        TreeNode node = search(item);
        //如果找不到则返回空
        if (node == null) return null;
            //如果该节点存在右儿子节点，则找寻右儿子最大
        else if (node.right != null) return searchMin(node.right).value;
        else {
            //不然则利用parent属性向上遍历
            TreeNode t = node, p = t.parent;
            while (p != null && p.right == t) {
                t = p;
                p = p.parent;
            }
            if (p == null) return null;
            else return p.value;
        }
    }

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
    public List<Item> InOrderTraversal() {
        TreeNode tmp = root;
        var list = new LinkedList<Item>();
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
    public List<Item> PreorderTraversal() {
        TreeNode tmp = root;
        var list = new LinkedList<Item>();
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
    public List<Item> PostOrderTraversal() {
        TreeNode tmp = root;
        var list = new LinkedList<Item>();
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
    public List<LinkedList<Item>> LevelTraversal() {
        var list = new LinkedList<LinkedList<Item>>();
        if (root == null) return list;
        TreeNode tmp = root;
        MyQueue<TreeNode> queue = new MyQueue<>();
        queue.enqueue(tmp);
        while (!queue.isEmpty()) {
            var list1 = new LinkedList<Item>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                tmp = queue.dequeue();
                list1.add(tmp.value);
                if (tmp.left != null) queue.enqueue(tmp.left);
                if (tmp.right != null) queue.enqueue(tmp.right);
            }
            list.add(list1);
        }
        return list;
    }

    public class TreeNode {
        public TreeNode left;
        public TreeNode right;
        public TreeNode parent;
        public Item value;

        public TreeNode(Item value) {
            this.value = value;
        }
    }

}
