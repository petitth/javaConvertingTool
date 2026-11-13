package edu.spring.gui;

import javax.swing.*;
import java.awt.*;

public class RightPanel extends JPanel {

    private JEditorPane resultPane;

    public RightPanel() {
        this.setLayout(new BorderLayout());

        resultPane = new JEditorPane("text/plain", "");
        resultPane.setEditable(false);
        JScrollPane resultScrollPane = new JScrollPane(resultPane);
        resultScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(resultScrollPane, BorderLayout.CENTER);
    }

    public JEditorPane getResultPane() {
        return resultPane;
    }
}
