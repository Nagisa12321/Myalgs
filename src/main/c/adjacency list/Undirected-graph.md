## 构建方法

- 首先不能用邻接矩阵, 因为空间占用过大
- 边的集合不能用, 因为不支持常用算法
- 用的是邻接表

1. 维护一个nodes数组存图中全部顶点的指针, node的数据结构如下
```c
struct node {
    int val;
    struct node *next;
}
```

2. 用Java写的时候维护Node对象
```java
class Node {
    String name;
    List<Node> neighborhoods;
}

HashMap<String, Node> map;
```