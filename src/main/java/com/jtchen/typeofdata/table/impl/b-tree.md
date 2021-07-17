# B-Tree

## 简介
- B树是为磁盘或者其他直接存取的辅助存储设备设计的平衡搜索树
- 和红黑树的区别是B树的节点可以有很多孩子, 就是说一棵树的分支因子可以很大. 
- 因此Btree相较于RBTree的高度很低
- 如果节点内有x.n个关键字, 那么x的孩子数就是x.n + 1
- 节点中的关键字可以看作是分割点, 把x种所处理的关键字的属性分割为x.n+1个域, 每个域都有x的一个孩子处理
- 当在一个B树中查找一个关键字时, 基于对存储在x中的x.n个关键字的比较, 做出一个x.n + 1路的选择

## 和磁盘的关系

- 为什么针对磁盘设计的数据结构不同于针对随机访问主存设计的数据结构?
  - 主存(内存)访问速度很快, 磁盘(辅存)访问的速度很慢(IO)

- 磁盘驱动器: 
  - 由一个或者多个盘片以一定速度绕着主轴旋转, 每个盘的表面覆盖一层可以磁化的物质, 驱动器通过磁臂末尾的磁头来读取/写入. 
  - 磁臂可以绕主轴离近离远, 当磁臂静止的时候, 下面经过的磁盘表面叫做磁道
  - 信息被分为一系列相等大小的在柱面内连续出现的位页面, 并且磁盘一次读取一个页面

- 在B Tree App中, 要处理的数据量非常大, 以至于所有数据无法一次性装入主存, B树算法将所需页面从磁盘恢复到主存, 然后将修改过的页面写回磁盘. 任何时刻主存中要保存一定数量的页面, 主存的大小并不限制被处理的Btree的大小. 

- 从磁盘中加载对象
```
x = a point to some object
DISK-READ(x);
operation that accress and/or modify the attributes of x
DISK-WRITE(x);
operation that accress and/or modify the attributes of x
```

- B-Tree的算法的时间主要由Disk-read(), Disk-write()所决定
- 所谓分支因子就是树的叉有多少. 

## 定义
1. 每个节点x有下面的属性
   1. x.n, 当前存储在x中的关键字个数
   2. x.n 个关键字本身x.key1, x.key2, ..., x.keyn 用非降序方式存放. `(x.key(1) <= x.key(2) <= ... <= x.key(n))` 
   3. x.leaf 是一个bool值, 如果x是叶节点, 就是true, 否则就是false
2. 每个内部节点还包括 x.n + 1个指向其孩子的指针`x.c(1), x.c(2), x.c(3), ...., x.c(n+1)`, 叶节点没有孩子, 因此他们的ci未定义. 
3. 关键字x.keyi对存储在各子树中的关键字范围加以分割, 用ki, 来表示ci为根的子树的关键字那么  
   `k(1) <= x.key(1) <= key(2) <= x.key(2) <= .... <= x.key(n) <= k(x.n + 1)`
4. 每个叶节点有相同的深度: 就是树的高度h
5. 每个关键字个数有上界和下界, 用一个被称为b树的最小度数t(t >= 2) 来表示
   1. 除了根节点, 每个节点至少有t - 1个关键字, 因此除了根节点以外每个内部节点至少有t个孩子
   2. 每个节点至多可以包含2t - 1个关键字, 因此一个内部节点最多可以有2t个孩子. 当一个节点有2t - 1个关键字, 那这个节点是满的(full)

- ⭐ fun-fact: 红黑树对于每个节点: 吸收红色孩子节点, 并且把红色节点的孩子变成自己的孩子, 将会变成一个t = 2 的 b-tree

## b-tree的基本操作

- 两个约定
   1. B-tree的根节点一直在主存中, 这样无需对根节点进行DISK-READ操作. 但是当根节点被改变的时候要做一次DISK-WRITE操作
   2. 任何被当作参数的节点被传递之前, 都要进行DISK-READ操作

### b-tree的搜索
- 线性搜索: 
- B-TREE-SEARCH(x, k) (从key1开始, 下标为1开始)
   ```
   i = 1;
   while (i <= x.n && x.key(i) < k)
      i = i + 1;
   if (i <= x.n && x.key(i) == k) 
      return (x, i);
   else if (x.leaf) 
      return NIL;
   else {
      DISK-READ(x, c(i));
      return B-TREE-SEARCH(x.c(i), k);
   }

   ```
- 利用线性搜索, 时间复杂度是O(tlog(t)n)/O(th)

### b-tree的创建. 
- B-TREE-CREATE(T)
   ```
   x = ALLOCATE-NODE()
   x.leaf = true;
   x.n = 0;
   DISK-WRITE(x);
   T.root = x;
   ```
### b-tree 的插入
- 不能简单的创建一个新的节点然后插入. 这会破坏b-tree的性质. 
- 也不能将关键词插入到满了的b-tree之中
- 因此在插入之前如果是满的要进行分裂, 把2t - 1分为两个t - 1和一个中间关键字, 中间关键字上升至父节点
- 我们并不是等到找出插入过程中实际要分裂的满节点才做分裂, 相反, 当沿着树往下查找新的关键字所属的位置的时候, 就分裂沿途遇到的满节点
- 因此要分裂一个节点y时能确保它父亲不是满的

- 先执行判断根节点是否需要分裂
- 注意: 对根节点的分裂是b树长高的唯一途径
- B-TREE-INSERT(T, k)
```
   r = T.root;
   if (r,n == 2 * t - 1) {
      s = ALLOCATE-NODE();
      s.n = 1;
      s.leaf = false;
      s.children[0] = r;
      T.root = s;
      B-TREE-SPLIT-CHILD(s, 0); 
   }
   r = T.root;
   B-TREE-INSERT-NONFULL(r, k);
```

- 如果被插的节点是叶节点, 间接说明叶节点不是满的, 因此直接挪位插入即可
- 如果被插的节点不是叶节点, 要搜索到合适的children位置插进去
- 而且在插进去之前如果发现孩子节点是满的, 要先对孩子节点进行分裂. 
- B-TREE-INSERT-NONFULL(x, k) 
   ```
   for (int i = 0; i < x.n; i++) {
      if (x.key(i) == k) {
         x.entries[i].value = v;
         retrun;
      }
   }
   if (x.leaf) {
      int i = x.n;
      for (; i >= 0; i--) {
         if (x.key(i - 1) > k) 
            x.entries[i] = x.entries[i - 1];
         else break;
      }
      x.entries[i] = new Entry(k, v);
      x.n++;
   } else {
      int i;
      for (i = 0; i < x.n; i++) {
         if (x.key(i) > k) break;
      }
      c = x.children(i);
      DISK-READ(c);
      if (c.n == 2 * t - 1) {
         B-TREE-SPLIT-CHILD(x, i)
         if (k > x.key(i)) 
            i = i + 1;
      }
      B-TREE-INSERT-NONFULL(x.c(i), k)
   }
   ```

### b-tree的分裂
- B-TREE-SPLIT-CHILD(x, i)
   ```
   y = x.children(i);
   z = ALLOCATE-NODE()
   z.n = t - 1;
   z.leaf = y.leaf;
   for (int j = 0; j < t - 1; i++) {
      z.entries[j] = y.entries[j + t];
   }
   if (!y.leaf) {
      for (int j = 0; j < t; j++) {
         z.children[j] = y.entries[j + t];
      }
   }
   y.n = t - 1;

   for (int j = x.n; j >= i + 1; j--) {
      x.entries[j] = x.entries[j - 1];
   }
   for (int j = x.n + 1; j >= i + 1; j--) {
      x.children[j] = x.children[j - 1];
   }
   x.n++;
   x.children[i + 1] = z;
   x.entries[i] = y.enrties[t - 1];

   DISK-WRITE(x);
   DISK-WRITE(y);
   DISK-WRITE(z);
   ```

## B-Tree的刪除
- 如果刪除了某個節點, 要重新安排這個節點的孩子.
- 和插入一樣, 要防止因爲刪除導致的`n < t - 1`
- 過程B-TREE-DELETE從以x為根的子樹中刪除關鍵字k. 我們必須保證無論何時x遞歸調用自身時x中關鍵字個數至少爲最小度數t, 這個條件要求比通常B樹的最少關鍵字多一個以上, 使得有時在遞歸下降至子節點之前, 需要把一個關鍵字從樹中刪除, 無需任何向上回溯
- 如果根節點x成爲一個不含任何關鍵字的内部節點. x就要刪除
- x的唯一孩子x.c(0)成爲樹的新根. 從而樹的高度降低1, 同時也位處樹根必須包含至少一個關鍵字的性質(除非樹是空的.)

1. 如果關鍵字k在節點x中, 并且x是葉節點, 直接刪除
2. 如果關鍵字k在節點x中, 并且x不是葉節點
   1. 如果節點x中前于k的子節點y至少包含t個關鍵字, 則找出k在以y為根的子樹中的前驅k', 遞歸的刪除k', 并在x中用k'代替k
   2. 如果y中有少於t個關鍵字, 則華北差節點x中後于k的子節點z, 如果z至少有t個關鍵字, 則找出k在以z為根的子樹中的後繼k', 遞歸的刪除k', 并在x中用k'代替k
   3. 否則就是k的左右孩子(y,z)都只有t-1的大小, 那麽直接把z合并到y, 然後递归刪除k即可
3. 如果關鍵字k當前不在節點x之中, 則確定必包含k的子樹的根x.children(i), 必須執行步驟3a或3b來保證降至一個至少包含t個關鍵字的節點, 然後通過對x的某個合適的子節點進行遞歸
   1. 如果x.children(i)只含有t - 1個關鍵字, 但是他的相鄰兄弟至少包含t個關鍵字, 則將x中的某一個關鍵字降至x.c(i)中, 將x.c(i)的相鄰左兄弟或者右兄弟的一個關鍵字升至x. 將該兄弟中相應的孩子指針移到x.c(i)之中, 這樣就使x.c(i)增加一個額外的關鍵字
   2. 如果x.children(i)以及x.children(i)的相鄰節點只包含t - 1個關鍵字, 則將x.c(i)與一個兄弟合并, 即將x的一個關鍵字移至新合并的節點, 使之成爲該節點的中間關鍵字

- B-TREE-DELETE(x, k)
```
int i;
for (i = 0;  i < x.n; i++) {
   if (x.key(i) >= k)
      break;
}
if (x.key(i) == k) {
   if (x.leaf) {
      for (int j = i; j < n - 1; j++) {
         x.enrties[j] = x.entries[j - 1];
      }
      x.n--;
      return;
   } else {
      y = x.children(i);
      if (y.n >= t) {
         cur = y;
         while (!cur.leaf) {
            cur = cur.children(cur.n);
         }
         x.entries[i] = cur.entries[cur.n - 1];
         k' = x.entries[i].key;
         B-TREE-DELETE(y, k')
      } else {
         z = x.children(i + 1)
         if (z.n >= t) {
            cur = z;
            while (!cur.leaf) {
               cur = cur.children(0);
            }
            x.entries[i] = cur.entries[0];
            k' = x.entries[i].key;
            B-TREE-DELETE(z, k')
         } else {
            // copy k, z to y
            // and recurse
         }
      }
   }
} else {
   child = x.children(i);
   if (i != 0 && x.children(i - 1).n >= t) {
      leftChild = x.children(i - 1);
      tmpEntry = x.entries[i];
      tmpChild = leftChild.children(leftChild.n);
      for (int i = n; i > 0; i++) {
         child.entries[i] = child.entries[i - 1]; 
      }
      for (int i = n + 1; i > 0; i++) {
         child.children[i] = child.children[i - 1]; 
      }
      leftChild.n--;
      child.n++;
      child.entries[0] = tmpEntry;
      child.children[0] = tmpChild;
   } else if (i != n && x.children(i - 1).n >= t) {
      // same of the left
   } else {
      // pick a tmp child and 
   }
}
```

## B-tree 和磁盘存储
- 利用以下网址给出的结构
1. put
   - 如果存在还要修改标志位
   - 写入文件末尾. 
2. delete
   - 更改标志位(flag)
3. get
   - 通过b树算法, 找到所在的pos, 然后读取相应位置

- 删除/标记的节点怎么处理???
- 每隔一段时间通过"文件压缩"算法进行删除, 也可以跟踪用户行为. 
## 磁盘管理/CS模型参考
- https://medium.com/@pthtantai97/implement-key-value-store-by-btree-5a100a03da3a

## 课后答案参考
- https://www.kancloud.cn/windmissing/algorithms-my-answer/115241