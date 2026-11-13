package edu.spring.events;

import edu.spring.gui.ApplicationUI;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class TextCursorEvent implements CaretListener {

    private final ApplicationUI applicationUI;

    public TextCursorEvent(ApplicationUI applicationUI) {
        this.applicationUI = applicationUI;
    }

    @Override
    public void caretUpdate(CaretEvent e) {
        if (e.getSource() == applicationUI.getLeftPanel().getInputPane()) {
            applicationUI.updateStatusLeftPanel();
        }
    }
}