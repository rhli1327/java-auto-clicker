package com.byb;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AutoClicker {
    private final int startKey = KeyEvent.VK_F1; // 默认启动键：F1
    private final int stopKey = KeyEvent.VK_F2;  // 默认停止键：F2
    private Timer timer;

    private JFrame frame;

    public AutoClicker() {
        initGUI();
        initActions();
    }

    private void initGUI() {
        frame = new JFrame("Auto Clicker");
        frame.setSize(400, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        // Add Start Key Field
        JTextField startKeyField = new JTextField(KeyEvent.getKeyText(startKey), 10);
        frame.add(new JLabel("Start Key: "));
        frame.add(startKeyField);

        // Add Stop Key Field
        JTextField stopKeyField = new JTextField(KeyEvent.getKeyText(stopKey), 10);
        frame.add(new JLabel("Stop Key: "));
        frame.add(stopKeyField);

        // Add Interval Field
        JTextField intervalField = new JTextField("100", 10);
        JButton setIntervalButton = new JButton("Set Interval (Default 100ms)");
        frame.add(new JLabel("Interval (ms): "));
        frame.add(intervalField);
        frame.add(setIntervalButton);

        setIntervalButton.addActionListener(e -> {
            try {
                int newInterval = Integer.parseInt(intervalField.getText());
                timer.setDelay(newInterval);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number.");
            }
        });

        frame.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                timer.stop();
            }

            @Override
            public void windowLostFocus(WindowEvent windowEvent) {

            }
        });
        frame.setVisible(true);
    }

    private void initActions() {
        int DEFAULT_INTERVAL = 100;

        timer = new Timer(DEFAULT_INTERVAL, e-> {
            try {
                Robot robot = new Robot();
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            } catch (AWTException awtException) {
                awtException.printStackTrace();
            }
        });

        // Define and bind actions
        Action startAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.start();
            }
        };

        Action stopAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
            }
        };

        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(
                        KeyStroke.getKeyStroke(startKey, 0),
                        "startAction"
                );
        frame.getRootPane().getActionMap().put(
                "startAction", startAction
        );

        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(
                        KeyStroke.getKeyStroke(stopKey, 0),
                        "stopAction"
                );
        frame.getRootPane().getActionMap().put("stopAction", stopAction);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(AutoClicker::new);
    }
}
