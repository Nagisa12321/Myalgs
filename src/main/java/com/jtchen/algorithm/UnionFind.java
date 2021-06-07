package com.jtchen.algorithm;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.Scanner;

/************************************************
 *
 * @author jtchen
 * @date 2020/12/18 18:29
 * @version 1.0
 ************************************************/
@SuppressWarnings("CommentedOutCode")
public class UnionFind {

    private final int[] id; //分量id
    private final int[] sz;   //分量大小
    private int count;  //分量数量


    /**
     * 以整数标识初始化0 - N-1 个触点
     *
     * @param N 触点总个数
     */
    public UnionFind(int N) {
        count = N;
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    public static void main(String[] args) {
        Scanner c = new Scanner(System.in);
        int N = c.nextInt();

        UnionFind unionFind = new UnionFind(N);
        while (c.hasNext()) {
            int p = c.nextInt();
            int q = c.nextInt();
            if (unionFind.connected(p, q)) continue;
            unionFind.union(p, q);
            System.out.println(/*Arrays.toString(unionFind.id) +
                    " " + */unionFind.count() + " components");
        }


        /*
        double prev = timeTrial(250, c, N);
        for (int n = 250; true; n += n) {
            double time = timeTrial(n, c, N);
            StdOut.printf("%7d %7.1f %5.1f\n", n, time, time / prev);
            prev = time;
        }

         */
    }

    @SuppressWarnings("unused")
    public static double timeTrial(int n, Scanner c, int N) {
        Stopwatch timer = new Stopwatch();
        for (int i = 0; i < n; i++) {
            UnionFind unionFind = new UnionFind(N);
            while (c.hasNext()) {
                int p = c.nextInt();
                int q = c.nextInt();
                if (unionFind.connected(p, q)) continue;
                unionFind.union(p, q);
            }
        }
        return timer.elapsedTime();
    }

    /**
     * 在p和q之间添加一条链接
     *
     * @param p 触点p
     * @param q 触点q
     */
    /* quick-find
    public void union(int p, int q) {
        //如果p, q在同一个集合中, 则什么都不做
        int pID = find(p);
        int qID = find(q);
        if (pID == qID) return;

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID) id[i] = qID;
        }

        //分量数量减一
        count--;
    }
     */

    /* quick-union
    public void union(int p, int q) {

        int pID = find(p);
        int qID = find(q);
        //如果p, q在同一个集合中, 则什么都不做
        if (pID == qID) return;

        //把pid归并到qid
        id[pID] = qID;

        //分量数量减一
        count--;
    }

     */

    /* 加权 quick-union */
    public void union(int p, int q) {
        //如果p, q在同一个集合中, 则什么都不做
        int pID = find(p);
        int qID = find(q);
        if (pID == qID) return;


        //若p分量小于q分量, 将p分量归并到q分量
        if (sz[pID] < sz[qID]) {
            id[pID] = qID;
            sz[qID] += sz[pID];
        } else {
            id[qID] = pID;
            sz[pID] += sz[qID];
        }
        //分量数量减一
        count--;
    }

    /**
     * 找到p(0 - N-1) 所在分量的标识符
     *
     * @param p 触点p
     * @return 触点p的标识符
     */
    /* quick-find
    public int find(int p) {
        return id[p];
    }
     */
    public int find(int p) {
        while (p != id[p]) p = id[p];
        return p;
    }

    /**
     * 如果p和q之间存在一个连接分量， 则返回{@code true}
     * 否则返回{@code false}
     *
     * @param p 触点p
     * @param q 触点q
     * @return 是否连通？
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 连通分量的数量
     *
     * @return 连通分量的数量
     */
    public int count() {
        return count;
    }
}
