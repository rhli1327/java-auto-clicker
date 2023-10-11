package com.byb;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class AutoClicker {

    public static void main(String[] args) {
        // Default interval 100ms
        int DEFAULT_INTERVAL = 100;
        JFrame frame = new JFrame("Auto Clicker");
        frame.setSize(400, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JTextField startKeyField = new JTextField(KeyE)
        // 默认启动键：F1
        int startKey = KeyEvent.VK_F1;
        JTextField startKeyField = new JTextField(
                KeyEvent.getKeyText(startKey), 
                10
        );
        // 默认停止键：F2
        int stopKey = KeyEvent.VK_F2;
        JTextField stopKeyField = new JTextField(
                KeyEvent.getKeyText(stopKey), 
                10
        );

        JButton startButton = new JButton("Start");
        JButton stopButton = new JButton("Stop");
        JTextField intervalField = new JTextField(10);
        JButton setIntervalButton = new JButton(
                "Set Interval (Default 100ms)"
        );

        frame.add(startButton);
        frame.add(stopButton);
        frame.add(new JLabel("Interval (ms): "));
        frame.add(intervalField);
        frame.add(setIntervalButton);

        frame.setVisible(true);

        Timer timer = new Timer(DEFAULT_INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Robot robot = new Robot();
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                } catch (AWTException awtException) {
                    awtException.printStackTrace();
                }
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.start();
            }
        });

        setIntervalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int newInterval = Integer.parseInt(intervalField.getText());
                    timer.setDelay(newInterval);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid number.");
                }
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
            }
        });
    }
}
