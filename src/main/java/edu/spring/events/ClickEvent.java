package edu.spring.events;

import edu.spring.enumeration.ActionEnum;
import edu.spring.gui.ApplicationUI;
import edu.spring.service.ConvertService;
import edu.spring.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import static edu.spring.utils.Constants.*;

public class ClickEvent implements ActionListener {

    private static final Logger LOG = LoggerFactory.getLogger(ClickEvent.class);
    private final ConvertService convertService;
    private final ApplicationUI applicationUI;

    public ClickEvent(ApplicationUI applicationUI, ConvertService convertService) {
        this.applicationUI = applicationUI;
        this.convertService = convertService;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == applicationUI.getNorthPanel().getButtonConvert()) {
            LOG.trace("convert button was clicked");
            ActionEnum selectedAction = ActionEnum.find(applicationUI.getNorthPanel().getActionBox().getSelectedItem().toString());
            convertService.process(selectedAction);
        }

        if (e.getSource() == applicationUI.getNorthPanel().getButtonOpen()) {
            LOG.trace("open button was clicked");
            applicationUI.getNorthPanel().getFileChooser().setDialogTitle(OPEN_DIALOG_TITLE);
            int returnVal = applicationUI.getNorthPanel().getFileChooser().showDialog(applicationUI.getNorthPanel(), OPEN_DIALOG_MSG);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = applicationUI.getNorthPanel().getFileChooser().getSelectedFile();
                LOG.debug("selected file : {}", file.getAbsolutePath());
                try {
                    String fileContent = FileUtil.getFileContent(file.getAbsolutePath());
                    applicationUI.getLeftPanel().getInputPane().setText(fileContent);
                } catch(IOException ioEx) {
                    applicationUI.processError("cannot read file : ".concat(ioEx.getMessage()));
                }
            }
        }

        if (e.getSource() == applicationUI.getNorthPanel().getButtonSave()) {
            LOG.trace("save button was clicked");
            applicationUI.getNorthPanel().getFileChooser().setDialogTitle(SAVE_DIALOG_TITLE);
            int userSelection = applicationUI.getNorthPanel().getFileChooser().showSaveDialog(applicationUI.getNorthPanel());
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File file = applicationUI.getNorthPanel().getFileChooser().getSelectedFile();
                LOG.debug("file to save : {}", file.getAbsolutePath());
                try {
                    String fileContent = applicationUI.getRightPanel().getResultPane().getText();
                    FileUtil.create(file.getAbsolutePath(), fileContent.getBytes());
                } catch(IOException ioEx) {
                    applicationUI.processError("cannot save to file : ".concat(ioEx.getMessage()));
                }
            }
        }
    }
}
