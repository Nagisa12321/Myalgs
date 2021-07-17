#include<string.h>
#include "graph.h"

int degree(struct graph* g, int v) {
    int degree, i;
    int *neighborhoods;
    
    degree = 0;
    neighborhoods = adj(g, v);
    i = 0;
    while(neighborhoods[i++]) 
        ++degree;   
    
    free(neighborhoods);
    return degree;
}

int maxDegree(struct graph* g) {
    int max, v, d;

    max = 0;
    for (v = 0; v < g->v; ++v) {
        d = degree(g, v);
        if (d > max)
            max = d;
    }

    return max;
}

double avgDegree(struct graph* g) {
    return ((double) g->e + g->v) / 2;
}

int numberOfSelfLoops(struct graph* g) {
    int count, v, w;
    int *neighborhoods;
    count = 0;
    for (v = 0; v < g->v; ++v) {
        neighborhoods = adj(g, v);
        w = 0;
        while (neighborhoods[w]) {
            if (v == neighborhoods[w])
                ++count;
            ++w;
        }
        free(neighborhoods);
    }
    return count;
}

static char* tostr(int num) {
    char* s;
    if (num == 0) {
        s = (char *) malloc(sizeof(char));
        s[0] = '0';
        return s;
    }
    int len, tmp, i;
    tmp = num;
    len = 0;
    while (tmp) {
        len++;
        tmp /= 10;
    }
    s = (char *) malloc(sizeof(char) * len);
    i = len;
    while (num) {
        tmp = num % 10;
        s[--i] = tmp + '0';
        num /= 10;
    }
    return s;
}

char* show(struct graph* g) {
    char* s;
    int v, w, i;
    int* neigh;
    s = (char *) malloc(1000 * sizeof(char));
    strcat(s, tostr(g->v));
    strcat(s, " vertices, ");
    strcat(s, tostr(g->e));
    strcat(s, " edges.\n");

    for (v = 0; v < g->v; ++v) {
        strcat(s, tostr(v));
        strcat(s, " -> ");
        w = 0;
        neigh = adj(g, v);
        i = g->nodes[v] ? g->nodes[v]->size : 0;
        while (i--) {
            strcat(s, tostr(neigh[w++]));
            strcat(s, " ");
        }
        strcat(s, "\n");
        free(neigh);
    } 
    return s;
}