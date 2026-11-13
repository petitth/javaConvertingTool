package edu.spring.gui;

import edu.spring.enumeration.ActionEnum;

import javax.swing.*;
import java.awt.*;

import static edu.spring.utils.Constants.*;

public class NorthPanel extends JPanel {

    private JComboBox actionBox;
    private JLabel labelAction;
    private JButton buttonConvert;
    private JButton buttonOpen;
    private JButton buttonSave;
    private JFileChooser fileChooser;

    private GridBagConstraints cst;

    public NorthPanel() {
        // create components
        buttonOpen = new JButton(BUTTON_OPEN);
        buttonSave = new JButton(BUTTON_SAVE);
        fileChooser = new JFileChooser();
        labelAction = new JLabel(LABEL_ACTION.concat(" : "));
        actionBox = new JComboBox(ActionEnum.getArray());
        actionBox.setEditable(false);
        actionBox.setSelectedIndex(0);
        buttonConvert = new JButton(BUTTON_CONVERT);
        cst = new GridBagConstraints();

        // add to panel
        this.setLayout(new GridBagLayout());

        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridy = 0; 	// line 1
        cst.gridx = 0;	// column 1
        cst.gridwidth = 1;
        cst.insets = new Insets(5,0,5,30); // border (top,left,bottom,right)
        this.add(buttonOpen,cst);

        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridy = 0; 	// line 1
        cst.gridx = 1;	// column 2
        cst.gridwidth = 1;
        cst.insets = new Insets(5,0,5,5); // border (top,left,bottom,right)
        this.add(labelAction,cst);

        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridy = 0; 	// line 1
        cst.gridx = 2;	// column 3
        cst.gridwidth = 1;
        cst.insets = new Insets(5,0,5,5); // border (top,left,bottom,right)
        this.add(actionBox,cst);

        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridy = 0; 	// line 1
        cst.gridx = 3;	// column 4
        cst.gridwidth = 1;
        cst.insets = new Insets(5,0,5,5); // border (top,left,bottom,right)
        this.add(buttonConvert,cst);

        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridy = 0; 	// line 1
        cst.gridx = 4;	// column 5
        cst.gridwidth = 1;
        cst.insets = new Insets(5,0,5,5); // border (top,left,bottom,right)
        this.add(buttonSave,cst);
    }

    public JComboBox getActionBox() {
        return actionBox;
    }

    public JButton getButtonConvert() {
        return buttonConvert;
    }

    public JButton getButtonOpen() {
        return buttonOpen;
    }

    public JButton getButtonSave() {
        return buttonSave;
    }

    public JFileChooser getFileChooser() {
        return fileChooser;
    }
}
