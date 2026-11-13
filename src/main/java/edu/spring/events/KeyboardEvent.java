package edu.spring.events;

import edu.spring.gui.ApplicationUI;
import edu.spring.service.ConvertService;
import edu.spring.enumeration.ActionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardEvent implements KeyListener {

    private static final Logger LOG = LoggerFactory.getLogger(KeyboardEvent.class);
    private final ConvertService convertService;
    private final ApplicationUI applicationUI;

    public KeyboardEvent(ApplicationUI applicationUI, ConvertService convertService) {
        this.applicationUI = applicationUI;
        this.convertService = convertService;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // do nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        LOG.trace("[keyPressed] Key character: {} - Key code: {} ( {} ) ", e.getKeyChar(), e.getKeyCode(), KeyEvent.getKeyText(e.getKeyCode()));

        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_ENTER) {
            LOG.trace("CTRL + ENTER pressed");
            ActionEnum selectedAction = ActionEnum.find(applicationUI.getNorthPanel().getActionBox().getSelectedItem().toString());
            convertService.process(selectedAction);
        }

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            LOG.trace("ESCAPE pressed");
            // close the application
            applicationUI.setVisible(false);
            applicationUI.dispose();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        LOG.trace("[keyReleased] Key character: {} - Key code: {} ( {} ) ", e.getKeyChar(), e.getKeyCode(), KeyEvent.getKeyText(e.getKeyCode()));
    }
}
