package com.jtchen.practicaloperation;

/**
 * 判断两个数是否互质
 * @author JT Chen
 * @version 1.0
 * @date 2020/11/19
 */
public class RelativelyPrime {
    public static boolean relativelyPrime(int[][] i) {
        int a = i.length, b = i[0].length;
        if(a < b) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        int c;
        while((c = a % b) != 0) {
            a = b;
            b = c;
        }
        return b == 1;
    }

    public static void main(String[] args) {
        int[][] test = new int[55][60];
        System.out.println(relativelyPrime(test));
    }
}
