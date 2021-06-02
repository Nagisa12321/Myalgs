# 红黑树

## 为什么会出现红黑树
- 二叉搜索树有时候会变成高度很高的样子, 而各个操作的时间复杂度都是O(h)
- 红黑树是平衡树的一种, 能很好的控制树的高度. 

## 红黑树的性质
- 是二叉搜索树, 但是新增了一个属性 boolean color; 
    ```java
    class TreeNode {
        K key;
        V value;
        TreeNode left;
        TreeNode right;
        boolean color;
    }
    ```
- 红黑性质: 
    1. 每个节点要么是红色的, 要么是黑色的
    2. 根节点是黑色的
    3. 每个叶节点(NIL)是黑色的
    4. 如果一个节点是红色的, 那么它的两个子节点是黑色的
    5. 对每个节点. 从该节点到其所有后代的叶节点的简单路径, 包含相同数目的黑色节点

- 使用哨兵来表示所有叶子节点NIL, 即只要是叶子节点都指向它. 
    - why do that: 处理红黑树的边界条件
    - 哨兵T.nil的color属性是black, 其他属性并不重要. 

- black-height(黑高): 从某个节点出发, 到达一个叶节点的任意一条简单路径上的黑色节点个数. 就是该节点的黑高, 记作bh(x)
    - 因为红黑树对每个节点从该节点到后代的叶节点的简单路径都包含相同个数的黑色节点, 因此红黑树的黑高就是根节点和黑高 .
    - NIL的黑高为0, 根节点的父节点是NIL

- 高度证明: 见书中. 

## 红黑树的旋转. O(1)
- 旋转分为左旋和右旋. 
- 图见书中. 
- LEFT-RORATE(T, x)
    ```
    y = x.right;

    x.right = y.left;
    if (y.left != nil) y.left.p = x;

    y.p = x.p;
    if (x.p == nil) T.root = y;
    else if (x.p.left = x) x.p.left = y;
    else x.p.right = y;

    y.left = x;
    x.p = y;
    ```

- RIGHT-RORATE(T, x) 
    ```
    y = x.left;

    x.left = y.right;
    if (x.left != nil) x.left.p = y;

    y.p = x.p;
    if (x.p = nil) T.root = y;
    else if (x.p.left = x) x.p.left = y;
    else x.p.right = y;

    y.right = x;
    x.p = y;

    ```

## 红黑树的插入. O(lgN)