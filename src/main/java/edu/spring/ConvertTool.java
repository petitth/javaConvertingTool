package edu.spring;

import edu.spring.events.ChangeEvent;
import edu.spring.events.ClickEvent;
import edu.spring.events.KeyboardEvent;
import edu.spring.events.TextCursorEvent;
import edu.spring.gui.ApplicationUI;
import edu.spring.repository.AppProperties;
import edu.spring.service.ConvertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ConvertTool {

    private static final Logger LOG = LoggerFactory.getLogger(ConvertTool.class);

    public static void main(String[] args) {
        // get context
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(ConvertTool.class)
                .headless(false)
                .run(args);

        // create components
        BuildProperties buildProperties = ctx.getBean(BuildProperties.class);
        AppProperties properties = ctx.getBean(AppProperties.class);
        ApplicationUI applicationUI = ctx.getBean(ApplicationUI.class, properties, buildProperties);
        applicationUI.reset();
        ConvertService convertService = new ConvertService(properties, applicationUI);
        ClickEvent clickEvent = new ClickEvent(applicationUI, convertService);
        KeyboardEvent keyboardEvent = new KeyboardEvent(applicationUI, convertService);
        ChangeEvent changeEvent = new ChangeEvent(applicationUI);
        TextCursorEvent textCursorEvent = new TextCursorEvent(applicationUI);

        // add events
        applicationUI.getNorthPanel().getButtonConvert().addActionListener(clickEvent);
        applicationUI.getNorthPanel().getButtonOpen().addActionListener(clickEvent);
        applicationUI.getNorthPanel().getButtonSave().addActionListener(clickEvent);
        applicationUI.addKeyListener(keyboardEvent);
        applicationUI.getLeftPanel().getInputPane().addKeyListener(keyboardEvent);
        applicationUI.getLeftPanel().getInputPane().addCaretListener(textCursorEvent);
        applicationUI.getRightPanel().getResultPane().addKeyListener(keyboardEvent);
        applicationUI.getNorthPanel().getButtonConvert().addKeyListener(keyboardEvent);
        applicationUI.getNorthPanel().getActionBox().addKeyListener(keyboardEvent);
        applicationUI.getLeftPanel().getInputPane().getDocument().addDocumentListener(changeEvent);

        // log settings
        LOG.debug("xml ident = {}", properties.getXmlIdentFactor());
        LOG.debug("json ident = {}", properties.getJsonIdentFactor());
    }
}