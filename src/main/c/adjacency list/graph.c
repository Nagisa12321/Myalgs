#include <fcntl.h>
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <memory.h>

#include "graph.h"


struct graph* creat_graph(int V) {
    struct graph* g = (struct graph *) malloc(sizeof(struct graph));
    g->e = 0;
    g->v = V;
    g->nodes = (struct node **) malloc(sizeof(struct node *) * V);
    return g;
}

struct graph* creat_with_file(char* fname) {
    int fd;
    char* buf;
    int v, e, i;
    int p1, p2;
    
    // open file
    if (!(fd = open("graph_in", O_RDONLY))) {
        printf("file not exist!\n");
        return NULL;
    }

    v = 0;
    e = 0;
    buf = (char *) malloc(sizeof(char));
    // read file
    while (read(fd, buf, 1) && *buf != '\n') {
        v *= 10;
        v += *buf - '0';
    }
    printf("v = %d\n", v);
    struct graph* g = creat_graph(v);

    while (read(fd, buf, 1) && *buf != '\n') {
        e *= 10;
        e += *buf - '0';
    }
    printf("e = %d\n", e);

    for (i = 0; i < e; ++i) {
        p1 = 0;
        p2 = 0;
        while (read(fd, buf, 1) && *buf != ' ') {
            p1 *= 10;
            p1 += *buf - '0';
        }
        while (read(fd, buf, 1) && *buf != '\n') {
            p2 *= 10;
            p2 += *buf - '0';
        }
        printf("connect [%d] to [%d]\n", p1, p2);
        addEdge(g, p1, p2);
    }

    return g;
}

void addEdge(struct graph* g, int v, int w) {
    struct node* newV = (struct node *) malloc(sizeof(struct node));
    newV->id = v;
    newV->next = g->nodes[w];
    newV->size = g->nodes[w] ? g->nodes[w]->size + 1 : 1;

    struct node* newW = (struct node *) malloc(sizeof(struct node));
    newW->id = w;
    newW->next = g->nodes[v];
    newW->size = g->nodes[v] ? g->nodes[v]->size + 1 : 1;

    g->nodes[v] = newW;
    g->nodes[w] = newV;
    g->e += 1;
}

// 和v相邻的所有顶点
int* adj(struct graph* g, int v) {
    struct node* node = g->nodes[v];

    if (!node) return NULL;
    int size = node->size;
    int* res = (int *) malloc(sizeof(int) * size);
    for (int i = 0; i < size; i++) {
        res[i] = node->id;
        node = node->next;
    }

    return res;
}

static void dfs(struct graph* g, int node, int* visit) {
    int* nei;
    struct node* n;
    int i;

    if (visit[node])
        return;
    visit[node] = 1;
    nei = adj(g, node);
    n = g->nodes[node];
    for (i = 0; i < n->size; ++i) {
        dfs(g, nei[i], visit);
    }
    free(nei);
}

// s和v是联通的吗?
bool marked(struct graph* g, int s, int v) {
    bool res;
    int* visit;
    
    visit = (int *) malloc(sizeof(int) * g->v);
    memset(visit, 0, sizeof(int) * g->v);
    dfs(g, s, visit);
    res = visit[v];
    free(visit);
    return res;
}

// s和v是联通的吗?
bool marked_bfs(struct graph* g, int s, int v) {
    struct node {
        int val;
        struct node* next;
    };

    struct node *head, *tail, *tmpNode;
    int *visit, *nei;
    bool res;
    int size, tmp, i, j, len;

    head = (struct node *) malloc(sizeof(struct node *));
    tail = head;
    head->val = s;
    tail->val = s;
    size = 1;
    visit = (int *) malloc(sizeof(int) * g->v);
    memset(visit, 0, sizeof(int) * g->v);
    while (head) {
        tmp = size;
        for (i = 0; i < tmp; ++i) {
            int cur = head->val;

            if (cur == v) {
                tmpNode = head;
                while (head) {
                    tmpNode = head;
                    head = head->next;
                    free(tmpNode);
                }
                free(visit);
                return true;
            }
            
            if (head == tail) {
                free(head);
                head = NULL;
                tail = NULL;
            } else {
                tmpNode = head;
                head = head->next;
                free(tmpNode);
            }
            size--;

            if (visit[cur])
                continue;
            visit[cur] = 1;

            nei = adj(g, cur);
            len = g->nodes[cur]->size;
            for (j = 0; j < len; ++j) {
                tmpNode = malloc(sizeof(struct node *));
                tmpNode->val = nei[j];
                if (!head) {
                    head = tmpNode;
                    tail = tmpNode;
                } else {
                    tail->next = tmpNode;
                    tail = tail->next;
                }
                size++;
            }

            free(nei);
        }
    }
    free(visit);
    return res;
}

void path_to_dfs(struct graph* g, int node, int res, int *visit, int *path, int *idx, int* finded) {
    int *nei, i, size;
    if (*finded || visit[node])
        return;
    visit[node] = 1;
    path[*idx] = node;
    *idx += 1;
    if (node == res) {
        *finded = 1;
        return;
    }

    nei = adj(g, node);
    size = g->nodes[node]->size;
    for (i = 0; i < size; ++i) {
        path_to_dfs(g, nei[i], res, visit, path, idx, finded);
    }
    free(nei);
}

int* path_to(struct graph* g, int s, int w) {
    int *visit, *path, len, cur, i, idx;
    
    if (!marked(g, s, w))
        return NULL;
    visit = (int *) malloc(sizeof(int) * g->v);
    path = (int *) malloc(sizeof(int) * g->v);
    memset(visit, 0, sizeof(int) * g->v);
    memset(path, -1, sizeof(int) * g->v);
    i = 0;
    idx = 0;
    path_to_dfs(g, s, w, visit, path, &idx, &i);

    free(visit);
    return path;
}


int* closed_path_to(struct graph* g, int s, int v) {
    struct node {
        int val;
        struct node* next;
        struct node* parent;
    };
    int* path, idx;
    struct node *head, *tail, *tmpNode, *parent;
    int *visit, *nei;
    bool res;
    int size, tmp, i, j, len, tmp2;

    if (!marked(g, s, v))
        return NULL;
    
    path = (int *) malloc(sizeof(int) * g->v);
    memset(path, -1, sizeof(int) * g->v);
    idx = 0;

    head = (struct node *) malloc(sizeof(struct node *));
    tail = head;
    head->val = s;
    tail->val = s;
    size = 1;
    visit = (int *) malloc(sizeof(int) * g->v);
    memset(visit, 0, sizeof(int) * g->v);
    while (head) {
        tmp = size;
        for (i = 0; i < tmp; ++i) {
            int cur = head->val;
            parent = head;

            // poll
            if (cur == v) {
                // deal with path
                tmpNode = head;
                while (tmpNode) {
                    path[idx++] = tmpNode->val;
                    tmpNode = tmpNode->parent;
                }

                // reserve path
                for (j = 0; j < idx / 2; ++j) {
                    tmp2 = path[j];
                    path[j] = path[idx - j - 1];
                    path[idx - j - 1] = tmp2;
                }

                tmpNode = head;
                while (head) {
                    tmpNode = head;
                    head = head->next;
                    // free(tmpNode);
                }
                free(visit);
                
                return path;
            }
            
            if (head == tail) {
                // free(head);
                head = NULL;
                tail = NULL;
            } else {
                tmpNode = head;
                head = head->next;
                // free(tmpNode);
            }
            size--;

            if (visit[cur])
                continue;
            visit[cur] = 1;

            nei = adj(g, cur);
            len = g->nodes[cur]->size;
            for (j = 0; j < len; ++j) {
                // offer the neighborhood
                tmpNode = malloc(sizeof(struct node *));
                tmpNode->val = nei[j];
                tmpNode->parent = parent;
                if (!head) {
                    head = tmpNode;
                    tail = tmpNode;
                } else {
                    tail->next = tmpNode;
                    tail = tail->next;
                }
                size++;
            }

            // free(nei);
        }
    }
    free(visit);
    
    return NULL;
}

// 与s联通的定点数
int count(struct graph* g, int s) {
    
}