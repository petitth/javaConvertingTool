package edu.spring.gui;

import edu.spring.repository.AppProperties;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.Element;
import java.awt.*;

import static edu.spring.utils.Constants.*;

@Component
public class ApplicationUI extends JFrame {

    private static ApplicationUI instance = null;
    private AppProperties properties;
    private NorthPanel northPanel;
    private LeftPanel leftPanel;
    private RightPanel rightPanel;

    public static ApplicationUI getInstance(AppProperties properties, BuildProperties buildProperties) {
        if (instance == null) {
            instance = new ApplicationUI(properties, buildProperties);
        }
        return instance;
    }

    private ApplicationUI(AppProperties properties, BuildProperties buildProperties) {
        this.properties = properties;
        // frame properties
        this.setTitle(String.format("%s :: %s", APP_TITLE, buildProperties.getVersion()));
        this.setSize(properties.getScreenWidth(), properties.getScreenHeight());
        this.setResizable(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width-this.getWidth())/2,(screenSize.height-this.getHeight())/2);
        this.setDefaultCloseOperation(ApplicationUI.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // borders
        Border borderSidePanels = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        TitledBorder tbLeft = BorderFactory.createTitledBorder(borderSidePanels, LEFT_PANEL_TITLE);
        TitledBorder tbRight = BorderFactory.createTitledBorder(borderSidePanels, RIGHT_PANEL_TITLE);
        TitledBorder tbNorth = BorderFactory.createTitledBorder(borderSidePanels, NORTH_PANEL_TITLE);

        // frame content
        northPanel = new NorthPanel();
        northPanel.setBorder(tbNorth);
        leftPanel = new LeftPanel();
        leftPanel.setBorder(tbLeft);
        rightPanel = new RightPanel();
        rightPanel.setBorder(tbRight);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1,2));
        centerPanel.add(leftPanel);
        centerPanel.add(rightPanel);

        this.add(northPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);

        // display frame
        this.setVisible(true);
    }

    public void reset() {
        // clear result
        rightPanel.getResultPane().setText("");
        // change result background color
        rightPanel.getResultPane().setBackground(properties.getColorDefaultResult());
        // set color for input text area
        leftPanel.getInputPane().setBackground(properties.getColorInput());
        // disable save button
        northPanel.getButtonSave().setEnabled(false);
    }

    public void processSuccess(String message) {
        // display result
        rightPanel.getResultPane().setText(message);
        // change result background color
        rightPanel.getResultPane().setBackground(properties.getColorSuccess());
        // enable save button
        northPanel.getButtonSave().setEnabled(true);
    }

    public void processError(String message) {
        // display error
        rightPanel.getResultPane().setText(message);
        // change result background color
        rightPanel.getResultPane().setBackground(properties.getColorError());
        // disable save button
        northPanel.getButtonSave().setEnabled(false);
    }

    public void updateStatusLeftPanel() {
        int caretPosition = leftPanel.getInputPane().getCaretPosition();
        Element root = leftPanel.getInputPane().getDocument().getDefaultRootElement();
        int line = root.getElementIndex(caretPosition) + 1;
        int startLineOffset = root.getElement( line - 1 ).getStartOffset();
        int col = caretPosition - startLineOffset + 1;
        leftPanel.getStatusLabel().setText(String.format(STATUS_CURSOR_POS, line, col));
    }

    public void clearStatusLeftPanel() {
        leftPanel.getStatusLabel().setText("");
    }

    public NorthPanel getNorthPanel() {
        return northPanel;
    }

    public LeftPanel getLeftPanel() {
        return leftPanel;
    }

    public RightPanel getRightPanel() {
        return rightPanel;
    }
}
