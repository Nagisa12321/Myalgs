package com.jtchen.typeofdata.graph;

/**
 * @author jtchen
 * @version 1.0
 * @date 2021/7/5 22:16
 */
public interface Dgraph {

	// 顶点总数
	int V();

	// 边的总是
	int E();

	// 向有向图中添加一条边 v->w
	void addEdge(int v, int w);

	// 由v指出的边所连接的所有顶点
	Iterable<Integer> adj(int v);

	// 该图的反向图
	Dgraph reverse();

	// 对象的字符串表示
	String toString();

}

