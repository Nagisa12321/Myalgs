#include <stdlib.h>
#include <stdbool.h>

// 表示无向图的数据类型
struct graph {
    int v; // 顶点数
    int e; // 边数
    struct node** nodes;
};

// 表示一个邻接表
struct node {
    int size;
    int id; // 是第几个节点
    struct node* next; // 下一项
};

// 创建一个含有V个节点但是不含有边的图
struct graph* creat_graph(int V);

struct graph* creat_with_file(char* fname);

// 向无向图中添加一条边 v-w
void addEdge(struct graph* g, int v, int w);

// 和v相邻的所有顶点
int* adj(struct graph* g, int v);


/* ---------------用的图处理代码 ---------------*/
// 计算v的度数
int degree(struct graph* g, int v);

// 计算所有顶点的最大度数
int maxDegree(struct graph* g);

// 计算所有顶点的平均度数
double avgDegree(struct graph* g);

// 计算自环的个数
int numberOfSelfLoops(struct graph* g);

// 对象的字符串表示
char* show(struct graph* g);


/* ------------ 图处理算法代码 --------------*/

// s和v是联通的吗?
bool marked(struct graph* g, int s, int v);

bool marked_bfs(struct graph* g, int s, int v);

// 与s联通的定点数
int count(struct graph* g, int s);

int* path_to(struct graph* g, int s, int w);

int* closed_path_to(struct graph* g, int s, int w);