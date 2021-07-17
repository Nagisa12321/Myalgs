#include<stdlib.h>
/*
 * 题目 
 * 有N件物品和一个容量为V的背包。第i件物品的费用是c[i]，价值是w[i]。求解将哪些物
 * 品装入背包可使价值总和最大。
 */

int findMax(int* c, int *w, int V, int numsSize) {
    int **dp = (int**) malloc(sizeof(int*) * numsSize);
    for (int i = 0; i < numsSize; i++) {
        dp[i] = (int*) malloc(sizeof(int) * (V + 1));
    }

    for (int i = 0; i < numsSize; i++) {
        for (int j = 0; j <= V; j++) {
            dp[i][j] = max(i == 0 ? 0 : dp[i - 1][j], 
                           i >= c[i] ? dp[i - 1][j - c[i]] + w[i] : 0);
        }
    }
    return dp[numsSize - 1][V];
}

int findMax1(int* c, int *w, int V, int numsSize) {
    int *dp = (int*) malloc(sizeof(int) * (V + 1));

    for (int i = 0; i < numsSize; i++) {
        for (int j = V; j >= 0; j++) {
            dp[j] = max(i == 0 ? 0 : dp[j]
                        , i >= c[i] ? dp[j - c[i]] + w[i] : 0);
            // dp[i][j] = max(i == 0 ? 0 : dp[i - 1][j], 
            //                i >= c[i] ? dp[i - 1][j - c[i]] + w[i] : 0);
        }
    }
    return dp[V];
}