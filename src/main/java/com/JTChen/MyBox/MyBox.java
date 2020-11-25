package com.JTChen.MyBox;

import javax.swing.*;

public class MyBox extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;

    public MyBox() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
    }

    public static void main(String[] args) {
        MyBox dialog = new MyBox();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
