package com.JTChen.PracticalOperation;

import com.JTChen.typeofdata.MyQueue;

import java.io.File;

/************************************************
 * 文件夹就是一列文件和文件夹的列表。该程序从命令行接收一个文
 * 件夹的名称作为参数，打印出该文件夹下的所有文件并用递归的方式
 * 在子文件夹的名下(缩进)列出其下所有的文件
 * @author jtchen
 * @date 2020/12/15 15:32
 * @version 1.0
 ************************************************/
public class FileList {

    public static void printList(MyQueue<File> files, int level) {
        if (files.isEmpty()) return;
        int size = files.size();
        for (int j = 0; j < size; j++) {
            File file = files.dequeue();
            if (file.isFile()) {
                for (int i = 0; i < level; i++)
                    System.out.print("\t");
//                Thread.sleep(50);
                System.out.println(file.getName());
//                Thread.sleep(50);
            } else if (file.isDirectory()) {
                for (int i = 0; i < level; i++)
                    System.out.print("\t");
//                Thread.sleep(50);
                System.out.println(file.getName());
//                Thread.sleep(50);
                File[] tmp = file.listFiles();
                assert tmp != null;
                for (File i : tmp) {
                    files.enqueue(i);
                    printList(files, level + 1);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyQueue<File> test = new MyQueue<>();
        test.enqueue(new File("./"));
//        test.enqueue(new File("D://theCode/"));
        printList(test, 0);
    }
}
