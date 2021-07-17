#include<stdlib.h>
/*
有N种物品和一个容量为V的背包，每种物品都有无限件可用。第i种物品的费用是c[i]，
价值是w[i]。求解将哪些物品装入背包可使这些物品的费用总和不超过背包容量，且价值总和最
大。
*/
int findMax(int V, int* c, int* w, int numSize) {
    int** dp = (int**) malloc(sizeof(int*) * (numSize + 1));
    for (int i = 0; i <= numSize; i++) {
        dp[i] = (int*) malloc(sizeof(int) * (V + 1));
    }
    // dp[i][j]: 前i件物品装到容量为j的背包中最多能装多少
    for (int i = 1; i <= numSize; i++) {
        for (int j = 0; j <= V; j++) {
            dp[i][j] = dp[i - 1][j];
            for (int k = 1; k <= numSize; k++) {
                if (j >= k * c[i - 1])
                    dp[i][j] = max(dp[i][j], 
                                    dp[i - 1][j - k * c[i - 1]] + k * w[i - 1]);
                    else break;
            } 
        }
    }
    return dp[numSize][V];
}