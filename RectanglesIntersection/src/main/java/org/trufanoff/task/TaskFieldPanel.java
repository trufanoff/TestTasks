package org.trufanoff.task;

import javax.swing.*;
import java.awt.*;

public class TaskFieldPanel extends JPanel {

    private TaskController controller;

    private JTextArea inputArea;
    private JTextArea outputArea;

    public TaskFieldPanel() {
        super(new GridLayout(1, 2));
        setBackground(Color.LIGHT_GRAY);
        controller = new TaskController(this);
        inputArea = new JTextArea();
        outputArea = new JTextArea();
        this.add(inputArea);
        this.add(outputArea);
    }

    public JTextArea getInputArea() {
        return inputArea;
    }

    public JTextArea getOutputArea() {
        return outputArea;
    }

    public TaskController getController() {
        return controller;
    }

    public void reset() {
        inputArea.setText("");
        outputArea.setText("");
        controller.reset();
    }
}
