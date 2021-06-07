package com.jtchen.practicaloperation;

import java.util.Arrays;

/**
 * 矩阵的运算
 * @author JT Chen
 * @version 1.0
 * @date 2020/11/19
 */
public class Matrix {
    /**
     * 向量点乘
     * 向量是由n个实数组成的一个n行1列（n*1）或一个1行n列（1*n）的有序数组；
     * 向量的点乘,也叫向量的内积、数量积，对两个向量执行点乘运算，就是对这两个
     * 向量对应位一一相乘之后求和的操作，点乘的结果是一个标量。
     *
     * @param x 向量x
     * @param y 向量y
     * @return 点乘结果
     */
    public static double dot(double[] x, double[] y) {
        double sum = 0;
        for (int i = 0; i < x.length; ++i)
            sum += x[i] * y[i];
        return sum;
    }

    /**
     * 矩阵和矩阵之积
     * 1、当矩阵A的列数（column）等于矩阵B的行数（row）时，A与B可以相乘。
     * 2、矩阵C的行数等于矩阵A的行数，C的列数等于B的列数。
     * 3、乘积C的第m行第n列的元素等于矩阵A的第m行的元素与矩阵B的第n列对应元素乘积之和。
     *
     * @param a 矩阵a
     * @param b 矩阵b
     * @return 两矩阵相乘后的新的矩阵
     */
    public static double[][] multi(double[][] a, double[][] b) {
        double[][] s = new double[a.length][b[0].length];
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[i].length; j++) {
                for (int k = 0; k < b.length; k++) {
                    s[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return s;
    }


    /**
     * 转置矩阵
     * 将矩阵的行列互换得到的新矩阵称为转置矩阵，转置矩阵的行列式不变。
     *
     * @param a 待转置矩阵
     * @return 转置矩阵
     */
    public static double[][] transpose(double[][] a) {
        double[][] b = new double[a[0].length][a.length];
        for (int i = 0; i < a[0].length; ++i)
            for (int j = 0; j < a.length; ++j)
                b[i][j] = a[j][i];
        return b;
    }

    /**
     * 向量和矩阵之积
     * ------------[1, 2]
     * [1, 2, 3] * [3, 4] = 1 * [1, 2] + 2 * [3, 4] + 3 * [5, 6]
     * ------------[5, 6]
     *
     * @param x 向量
     * @param a 矩阵
     * @return 乘积，是个向量
     */
    public static double[] multi(double[] x, double[][] a) {
        double[] y = new double[a[0].length];
        for (int i = 0; i < a[0].length; ++i)
            for (int j = 0; j < x.length; ++j)
                y[i] += a[j][i] * x[j];
        return y;
    }

    public static String toString(double[][] a) {
        int x = a.length;
        int y = a[0].length;
        StringBuilder theString = new StringBuilder();
        for (double[] doubles : a) {
            for (int j = 0; j < y; ++j)
                theString.append(doubles[j]).append(" ");
            theString.append("\n");
        }
        return theString.toString();
    }

    public static void main(String[] args) {
        double[][] a = {{1, 2, 3}, {4, 5, 6}};
        double[][] b = {{7, 8}, {9, 10}, {11, 12}};
        System.out.println(toString(multi(a, b)));
        System.out.println(toString(b));
        System.out.println(toString(transpose(b)));
        double[] y = {1, 2};
        double[][] c = {{1, 2}, {3, 4}};
        System.out.println(Arrays.toString(multi(y,c)));
    }
}
