package com.byb;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class MyFlowLayout {
    public static void main(String[] args) {
        var frame = new JFrame("FlowLayout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        var panel = new JPanel(new FlowLayout(
            FlowLayout.LEFT,
            15,
            15
        ));

        panel.add(new JButton("按钮1"));
        panel.add(new JButton("按钮2"));

        frame.add(panel);
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
    }
}
