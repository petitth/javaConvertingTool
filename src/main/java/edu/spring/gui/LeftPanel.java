package edu.spring.gui;

import javax.swing.*;
import java.awt.*;

import static edu.spring.utils.Constants.STATUS_CURSOR_POS;

public class LeftPanel extends JPanel {

    private JEditorPane inputPane;
    private JPanel statusPanel;
    private JLabel statusLabel;

    public LeftPanel() {
        this.setLayout(new BorderLayout());

        inputPane = new JEditorPane("text/plain", "");
        inputPane.setEditable(true);
        JScrollPane inputScrollPane = new JScrollPane(inputPane);
        inputScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        statusLabel = new JLabel(String.format(STATUS_CURSOR_POS, 1, 1));

        statusPanel = new JPanel();
        statusPanel.add(statusLabel);

        this.add(inputScrollPane, BorderLayout.CENTER);
        this.add(statusPanel, BorderLayout.SOUTH);
    }

    public JEditorPane getInputPane() {
        return inputPane;
    }

    public JLabel getStatusLabel() {
        return statusLabel;
    }
}
