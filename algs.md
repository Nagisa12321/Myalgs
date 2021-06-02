## A* 算法(OK)

### f(n)=g(n)+h(n)

1. 其中n是路径上的下一个节点，g（n）是从起始节点到n的路径的成本，h（n）是一种启发式函数，用于估计从n到目标的最便宜路径的成本。

2. 启发式功能是特定于问题的。如果启发式函数是允许的，
 这意味着它永远不会高估达到目标的实际成本，则可以保证A *返回从起点到目标的最小成本路径。

3. A *的典型实现使用优先级队列来执行重复选择的最小（估计）成本节点以进行扩展。

4. 在算法的每个步骤中，从队列中删除具有最低f（x）值的节点，并相应地更新其邻居的f和g值，并将这些邻居添加到队列中。

## 有向图的环检测(not OK)

- [LINK](https://blog.csdn.net/anlian523/article/details/81806384)

- [leetcode](https://leetcode-cn.com/problems/course-schedule/)
	- [tip](https://www.jianshu.com/p/d6042b659f70)

- 什么是拓补排序? :)