#include <fcntl.h>
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

#include "graph.h"
int main() {
    int i, j, k, len, *path;
    struct graph *g;
    
    if (!(g = creat_with_file("grapg_in"))) {
        printf("no such file\n");
        return -1;
    }
    char* s = show(g);
    printf("%s\n", s);

    // checked mark
    for (i = 0; i < g->v; ++i) {
        for (j = 0; j < g->v; ++j) {
            printf("mark: %d-%d: %d\n", i, j, marked_bfs(g, i, j));
        }
    }

    // path to
    for (i = 0; i < g->v; ++i) {
        for (j = 0; j < g->v; ++j) {
            printf("%d to %d: ", i, j);
            if (!(path = path_to(g, i, j))) {
                printf("%d can't get to %d\n", i ,j);
                continue;
            }
            for(k = 0; k < g->v; k++) {
                if (path[k] == -1)
                    break;
                printf("%d ", path[k]);
            }
            printf("\n");
        }
    }

    // closed path to
    for (i = 0; i < g->v; ++i) {
        for (j = 0; j < g->v; ++j) {
            // i = 0, j = 4;
            printf("%d to %d: ", i, j);
            if (!(path = closed_path_to(g, i, j))) {
                printf("%d can't get to %d\n", i ,j);
                continue;
            }
            for(k = 0; k < g->v; k++) {
                if (path[k] == -1)
                    break;
                printf("%d ", path[k]);
            }
            printf("\n");
            // return -1;
        }
    }
    
}