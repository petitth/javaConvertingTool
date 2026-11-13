package edu.spring.events;

import edu.spring.gui.ApplicationUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ChangeEvent implements DocumentListener {

    private static final Logger LOG = LoggerFactory.getLogger(ChangeEvent.class);
    private final ApplicationUI applicationUI;

    public ChangeEvent(ApplicationUI applicationUI) {
        this.applicationUI = applicationUI;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        if (e.getDocument() == applicationUI.getLeftPanel().getInputPane().getDocument()) {
            LOG.trace("input panel changed with insertUpdate");
            applicationUI.reset();
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        if (e.getDocument() == applicationUI.getLeftPanel().getInputPane().getDocument()) {
            LOG.trace("input panel changed with removeUpdate");
            applicationUI.reset();
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        if (e.getDocument() == applicationUI.getLeftPanel().getInputPane().getDocument()) {
            LOG.trace("input panel changed with changeUpdate");
            applicationUI.reset();
        }
    }
}