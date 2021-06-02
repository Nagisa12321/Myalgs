# 红黑树 (Red & Black-Tree)

## 为什么会出现红黑树
- 二叉搜索树有时候会变成高度很高的样子, 而各个操作的时间复杂度都是O(h)
- 红黑树是平衡树的一种, 能很好的控制树的高度. 
s
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

- 怎么插入? 
    1. 先把红黑树看成是一个正常的二叉搜索树进行插入. 
    2. 把插入节点着色为红色
    3. 为了保持**红黑性质**我们要调用RB_FIXEDUP(T, z);
- RB_INSERT(T, z);
    ```
    parent = T.nil
    cur = T.root;
    while (cur != T.nil) {
        parent = cur;
        if (z.key < cur.key) cur = cur.left;
        else cur = cur.right;
    }
    z.p = parent;

    if (p == T.nil) T.root = z;
    else if (z.key < parent.key) p.left = z;
    else p.right = z;

    z.left = T.nil;
    z.right = T.nil;
    z.color = RED;

    RB_FIXUP(T, z);
    ```
- RB_INSERT_FIXUP(T, z);
    ```
    while (z.p.color == RED) {
        if (z.p == z.p.p.left) {
            y = z.p.p.right;
            if (y.color == RED) {
                z.p.color = BLACK;
                y.color = BLACK;
                z.p.p.color = RED;
                z = z.p.p;
            } else if (z == z.p.right) {
                z = z.p;
                LEFT_ROTATE(T, z);
            }
            z.p.color = BLACK;
            z.p.p.color = RED;
            RIGHT_RORATE(T, z.p.p);
        } else {
            // same as then clause
            // right and left exchanged
        }
        T.root.color = BLACK;
    }
    ```
- 关于RB_INSERT_FIXUP要回答三个问题
    1. 当节点z被插入并且为红色的时候, 哪些性质不能保持? 
    2. while循环的总目标是什么? 
    3. while循环的三种情况? 

- 当节点z被插入并且为红色的时候, 哪些性质不能保持? 
    - 性质1: 肯定能保持, 因为我们的颜色是布尔值呀, 只有两种颜色
    - 性质3: 能保持, 我们设置了哨兵.  
    - 性质5: 也会成立, 因为我们插入的是红色, 不影响黑色数量. 
    - 因此只有性质2和性质4会被破坏. 
      - 当插入节点z刚好是根节点的话, 性质2会被破坏, 因此有了最后一句话. 
      - 插入节点z的父节点是红色则破坏了性质4.


- while循环的总目标是什么? 
  - while循环在每次开始之前保持了三个不变式子.
    1. z.color == RED
    2. 如果z.p == root, 则z.p.color == BLACK
    3. 如果有任何红黑性质被破坏, 则只有一条被破坏, 要么是性质2要么是性质4. 如果是性质2被破坏, 那么就是因为插入的z是给你节点, 如果性质4被破坏, 就是因为z和z.p的颜色都是红色. 
   
- while循环的三种情况? (其实是六种, 有三种和另外三种对称, 关键看z.p 是 z.p.p的左孩子还是右孩子)
    1. z的叔节点是红色的
        - 这种情况怎么处理: 把z的父亲节点和z的叔节点变成BLACK, Z的祖父节点变成RED, z上升两层, 变成原先的祖父节点. 
        - 这样在下次循环开始前还成立吗? 
            1. z的祖父节点变成BLACK
            2. 因为在上次循环中没有改变到当前的z.p因此如果是root, 还是黑色的. 
            3. 上次循环的修改不会破坏性质5
        - 如果现在z是根节点, 那么只违反了性质2, 把z修改为黑色即可. 
    2. z的叔叔节点是黑色的且z是一个右孩子/3. z的叔节点是黑色的且z是一个左孩子
        - 情况2和情况3可以和为一谈. 情况2可以通过旋转变成情况3. 
        - 如果是情况2, 直接z向上走一层, 然后左转z. 那不就变成情况三了吗? 
        - 而且进行左转的时候, 红黑性质没有发生任何改变. 
        - 情况3中, 为了保证红黑性质, 要把父亲变成黑色, 把祖父变成红色, 然后对祖父进行右旋
        - 之后进入下一次循环便会发现z.p.color == BLACK, 因此会跳出循环. 