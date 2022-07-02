package org.trufanoff.task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class TaskWindow extends JFrame {

    private TaskFieldPanel taskFieldPanel;

    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 200;
    public static final int WINDOW_POS_X = 500;
    public static final int WINDOW_POS_Y = 20;

    public TaskWindow() {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocation(WINDOW_POS_X, WINDOW_POS_Y);
        setTitle("Rectangle intersection");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JButton selectButton = createStartButton();
        JButton buttonExit = createExitButton();
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(selectButton);
        buttonPanel.add(buttonExit);
        add(buttonPanel, BorderLayout.SOUTH);

        taskFieldPanel = new TaskFieldPanel();
        taskFieldPanel.setVisible(false);
        add(taskFieldPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private JButton createExitButton() {
        JButton button = new JButton("Exit");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        return button;
    }

    private JButton createStartButton() {
        JButton button = new JButton("Select file");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                taskFieldPanel.reset();
                JFileChooser jFileChooser = new JFileChooser();
                int response = jFileChooser.showOpenDialog(null);
                if (response == JFileChooser.APPROVE_OPTION) {
                    File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                    taskFieldPanel.getController().findRectangleIntersection(file);
                }
                taskFieldPanel.setVisible(true);
            }
        });
        return button;
    }

}
