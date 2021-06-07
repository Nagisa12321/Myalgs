package com.jtchen.practicaloperation;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;


/************************************************
 * @description 支持加一和减一的操作。他的构造函数接收
 * 两个参数<N>和<max>其中N指定了操作的最大次数,max指定了
 * 计数器的最大绝对值。作为副作用，用图象显示每次计数变化后的值
 * @author jtchen
 * @date 2020/11/22 18:23
 * @version 1.0
 ************************************************/
public class VisualCounter {
    private final int N;//最大次数
    private final int max;//最大值
    private int value;//当前值
    private int n;//当前次数
    private Point2D[] point2DS;

    /**
     * @param N   操作的最大次数
     * @param max 计数器的最大绝对值
     */
    public VisualCounter(int N, int max) {
        this.n = 0;
        this.value = 0;
        this.N = N;
        this.max = max;
        this.point2DS = new Point2D[N + 1];
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(-max, max);
        StdDraw.setPenRadius(.003);
        point2DS[0] = new Point2D(0, 0);
        point2DS[0].draw();
    }

    /**
     * 自减
     */
    public void decrease() {
        if (Math.abs(value - 1) >= max)
            System.err.println("Exceed the absolute value");
        else if (n + 1 > N) {
            System.err.println("Exceeded the maximum number of times and has been reset");
            clear();
        } else {
            --value;
            ++n;
            drawPoint(n, value);
        }
    }

    /**
     * 自增
     */
    public void increase() {
        if (Math.abs(value + 1) >= max)
            System.err.println("Exceed the absolute value");
        else if (n + 1 > N) {
            System.err.println("Exceeded the maximum number of times and has been reset");
            clear();
        } else {
            ++value;
            ++n;
            drawPoint(n, value);
        }
    }

    /**
     * 清零
     */
    public void clear() {
        value = 0;
        n = 0;
        point2DS[0].draw();
    }

    private void drawPoint(int n, int value) {
        point2DS[n] = new Point2D(n, value);
        point2DS[n].draw();
        StdDraw.setPenColor(Color.red);
        StdDraw.setPenRadius(.001);
        StdDraw.line(point2DS[n].x(), point2DS[n].y(), point2DS[n - 1].x(), point2DS[n - 1].y());
        StdDraw.setPenColor(Color.black);
        StdDraw.setPenRadius(.01);
    }

    public static void main(String[] args) {
        VisualCounter a = new VisualCounter(10, 10);
        a.increase();
        a.decrease();
        a.increase();
        a.increase();
        a.increase();
        a.decrease();
        a.decrease();
        a.decrease();
        a.decrease();
        a.decrease();
        a.increase();
        a.decrease();
        a.increase();
        a.increase();
        a.decrease();
        a.decrease();
        a.decrease();
        a.decrease();
        a.increase();
        a.decrease();
        a.increase();
        a.increase();
        a.decrease();
        a.decrease();
        a.decrease();
        a.decrease();
        a.increase();
        a.decrease();
        a.increase();
        a.increase();

    }
}
