package com.JTChen.TypeOfData;

/************************************************
 * 文本编辑器的缓冲区
 * (使用两个栈)
 * @author jtchen
 * @date 2020/12/15 16:50
 * @version 1.0
 ************************************************/
public class Buffer {
    /*使用两个栈保存Buffer, 约定左栈顶为光标位置*/
    private final MyStack<Character> left;
    private final MyStack<Character> right;

    /**
     * 创建一块空的缓冲区,缓冲区初始大小我定义为10
     */
    public Buffer() {
        left = new MyStack<>();
        right = new MyStack<>();
    }

    /**
     * 在光标位置插入字符c
     *
     * @param c 所插入的字符
     */
    public void insert(char c) {
        left.push(c);
    }

    /**
     * 删除并返回光标位置的字符, 如果光标处于最左边,则抛出异常;
     * 否则则出栈,以更新光标值；
     *
     * @return 所删除元素
     * @throws IllegalArgumentException 光标处于最左边, 仍请求删除
     */
    public char delete() {
        if (left.isEmpty()) throw new IllegalArgumentException
                ("The cursor is at the left end and cannot be deleted");
        return left.pop();
    }

    /**
     * 把光标向左边移动k个位置, 如果请求的移动大于左栈大小, 则打出提示
     * 否则则从左栈依次入k个到右栈
     *
     * @param k 移动的位置
     */
    public void left(int k) {
        if (k > left.size()) System.out.println
                ("Move the string length to the left, please try again");
        else for (int i = 0; i < k; i++) right.push(left.pop());
    }

    /**
     * 把光标向右边移动k个位置 同上
     *
     * @param k 移动的位置
     * @see #left
     */
    public void right(int k) {
        if (k > right.size()) System.out.println
                ("Move the string length to the right, please try again");
        else for (int i = 0; i < k; i++) left.push(right.pop());
    }

    /**
     * 缓冲区中字符的数量
     *
     * @return 缓冲区中字符的数量
     */
    public int size() {
        return left.size() + right.size();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        int size = size();
        int tmp = 0;
        while (!left.isEmpty()) {
            right.push(left.pop());
            tmp++;
        }
        while (!right.isEmpty()) {
            char ch = right.pop();
            builder.append(ch);
            left.push(ch);
        }
        for (int i = 0; i < size - tmp; i++)
            right.push(left.pop());
        return builder.toString();
    }

}
