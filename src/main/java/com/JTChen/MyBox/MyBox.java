package com.JTChen.MyBox;

import com.JTChen.Boxes.BoxMyCalculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyBox extends JDialog {
    private JPanel contentPane;
    private JButton btnMyCalculator;
    private JButton button2;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button3;
    private JButton buttonOK;

    public MyBox() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        btnMyCalculator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BoxMyCalculator boxMyCalculator = new BoxMyCalculator();
                boxMyCalculator.pack();
                boxMyCalculator.setResizable(false);
                boxMyCalculator.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        MyBox dialog = new MyBox();
        dialog.pack();
        dialog.setResizable(false);
        dialog.setVisible(true);
        System.exit(0);
    }
}
