package com.JTChen.Algorithm;

/************************************************
 * @description
 * @author jtchen
 * @date 2020/11/23 14:12
 * @version 1.0
 ************************************************/
public class KMP {
    private int dp[][];
    private String pat;

    public KMP(String pat) {
        //通过pat来构建dp数组，需要O(M)的时间
        this.pat = pat;
        //构建bp[][]数组
        BuildBP();
    }

    private void BuildBP() {
        int M = pat.length();
        //dp[状态][字符] = 下个状态
        dp = new int[M][256];
        //base case
        dp[0][pat.charAt(0)] = 1;
        //影子状态初始为0
        int X = 0;
        //当前状态j从1开始
        for (int j = 1; j < M; j++) {
            for (int c = 0; c < 256; c++) {
                if (pat.charAt(j) == c)
                    dp[j][c] = j + 1;
                else dp[j][c] = dp[X][c];
            }
            //更新影子状态
            X = dp[X][pat.charAt(j)];
        }
    }

    public int search(String txt) {
        // 借助 dp 数组去匹配 txt，需要 O(N) 时间
        int M = pat.length();
        int N = txt.length();
        //pat初始状态为0
        int j = 0;
        for (int i = 0; i < N; i++) {
            //j根据现在状态和实时charAt(i)决定值
            j = dp[j][txt.charAt(i)];
            //如果达到终止态，则返回匹配开头的索引
            if (j == M) return i - M + 1;
        }
        //若没有达到终止态，则匹配失败。
        return -1;
    }


}
