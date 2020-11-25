package com.JTChen.Boxes;

import com.JTChen.PracticalOperation.MyCalculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoxMyCalculator extends JDialog {
    private JPanel contentPane;
    private JTextArea textArea1;
    private JTextField textField1;
    private JButton button1;
    private JTextField textField2;
    private JButton buttonOK;

    public BoxMyCalculator() {
        textField2.setEditable(false);
        textArea1.setLineWrap(true);
        textArea1.setEditable(false);
        textArea1.setText("这是一个计算机程序~\n" +
                "我采用的是符号栈方法来写，因此\n" +
                "输入的要用以下格式噢 “1 + 3 * ( 2 + 5 )”");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = textField1.getText();
                String result = MyCalculator.Calculate(str);
                textField1.removeAll();
                textField2.setText(result);
            }
        });
    }
}
