package com.JTChen.PracticalOperation;

import com.JTChen.typeofdata.MyQueue;

import java.io.File;

/************************************************
 * �ļ��о���һ���ļ����ļ��е��б��ó���������н���һ����
 * ���е�������Ϊ��������ӡ�����ļ����µ������ļ����õݹ�ķ�ʽ
 * �����ļ��е�����(����)�г��������е��ļ�
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
