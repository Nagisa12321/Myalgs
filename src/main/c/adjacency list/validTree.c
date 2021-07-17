#include<stdbool.h>
#include<stdlib.h>
#include<stdio.h>

bool validTree(int n, int* edges[2], int edgesSize);
int _union(int i, int j, int* id, int* count);
int _find(int i, int* id);
// 给定边列表edge[][], 还有节点的多少n, 判断是否为一棵树
bool validTree(int n, int* edges[2], int edgesSize) {
    int* id;
    int i, count;
    
    count = n; // 集合个数
    id = (int *) malloc(sizeof(id) * n);
    for (i = 0; i < n; i++) {
        id[i] = i;
    }

    for (i = 0; i < edgesSize; i++) {
        if (!_union(edges[i][0], edges[i][1], id, &count))
            return false;
    }

    return count == 1;
}

int _union(int i, int j, int* id, int* count) {
    int tmp_i; 
    int tmp_j;

    tmp_j = _find(j, id);
    tmp_i = _find(i, id);

    if (tmp_i == tmp_j) return 0;

    // union
    id[tmp_i] = tmp_j;
    *count -= 1;
    return *count;
}

int _find(int i, int* id) {
    while (id[i] != i) 
        i = id[i];
    return i;
}

int main() {
    int tmp[4][2] = { {0, 1}, {0, 2}, {0, 3}, {1, 4} };
    int** edge;
    int i, j;
    
    edge = (int **) malloc(sizeof(int *) * 4);
    for (i = 0; i < 4; i++) {
        edge[i] = (int *) malloc(sizeof(int) * 2);
        for (j = 0; j < 2; j++) {
            edge[i][j] = tmp[i][j];
        }
    }
    bool res;

    printf("res = %d\n", (res = validTree(5, edge, 4)));
}