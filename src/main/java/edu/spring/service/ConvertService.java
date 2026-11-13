package edu.spring.service;

import edu.spring.enumeration.ActionEnum;
import edu.spring.exception.ConvertException;
import edu.spring.gui.ApplicationUI;
import edu.spring.repository.AppProperties;
import edu.spring.utils.ConvertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static edu.spring.utils.Constants.*;

@Service
public class ConvertService {

    private static final Logger LOG = LoggerFactory.getLogger(ConvertService.class);
    private static final String SELECTED_ACTION = "'{}' action selected";
    private final ApplicationUI applicationUI;
    private final AppProperties properties;

    public ConvertService(AppProperties properties, ApplicationUI applicationUI) {
        this.applicationUI = applicationUI;
        this.properties = properties;
    }

    public void process(ActionEnum selectedAction) {
        switch(selectedAction) {
            case TRIM_JSON:
                LOG.trace(SELECTED_ACTION, ActionEnum.TRIM_JSON.getValue());
                trimJson();
                break;
            case TRIM_XML:
                LOG.trace(SELECTED_ACTION, ActionEnum.TRIM_XML.getValue());
                trimXml();
                break;
            case TRIM_FILE:
                LOG.trace(SELECTED_ACTION, ActionEnum.TRIM_FILE.getValue());
                trimFile();
                break;
            case DISPLAY_XML:
                LOG.trace(SELECTED_ACTION, ActionEnum.DISPLAY_XML.getValue());
                displayXml();
                break;
            case DISPLAY_JSON:
                LOG.trace(SELECTED_ACTION, ActionEnum.DISPLAY_JSON.getValue());
                displayJson();
                break;
            case JSON_TO_XML:
                LOG.trace(SELECTED_ACTION, ActionEnum.JSON_TO_XML.getValue());
                jsonToXml();
                break;
            case XML_TO_JSON:
                LOG.trace(SELECTED_ACTION, ActionEnum.XML_TO_JSON.getValue());
                xmlToJson();
                break;
            case CSV_TO_JSON:
                LOG.trace(SELECTED_ACTION, ActionEnum.CSV_TO_JSON.getValue());
                csvToJson();
                break;
            case LOWER:
                LOG.trace(SELECTED_ACTION, ActionEnum.LOWER.getValue());
                lowerCases();
                break;
            case UPPER:
                LOG.trace(SELECTED_ACTION, ActionEnum.UPPER.getValue());
                upperCases();
                break;
            default:
        }
    }

    private void trimJson() {
        try {
            String input = applicationUI.getLeftPanel().getInputPane().getText();
            String result = ConvertUtil.trimJson(input);
            applicationUI.processSuccess(result);
        } catch(ConvertException ex) {
            applicationUI.processError(String.format(ERROR_JSON, ex.getMessage()));
        }
    }

    private void displayJson() {
        try {
            String input = applicationUI.getLeftPanel().getInputPane().getText();
            String result = ConvertUtil.displayJson(input, properties.getJsonIdentFactor());
            applicationUI.processSuccess(result);
        } catch(ConvertException ex) {
            applicationUI.processError(String.format(ERROR_JSON, ex.getMessage()));
        }
    }

    private void trimXml() {
        try {
            String input = applicationUI.getLeftPanel().getInputPane().getText();
            String result = ConvertUtil.trimXml(input);
            applicationUI.processSuccess(result);
        } catch(ConvertException ex) {
            applicationUI.processError(String.format(ERROR_XML, ex.getMessage()));
        }
    }

    private void trimFile() {
        try {
            String input = applicationUI.getLeftPanel().getInputPane().getText();
            String result = ConvertUtil.trimFile(input);
            applicationUI.processSuccess(result);
        } catch(ConvertException ex) {
            applicationUI.processError(String.format(ERROR_FILE, ex.getMessage()));
        }
    }

    private void displayXml() {
        try {
            String input = applicationUI.getLeftPanel().getInputPane().getText();
            String result = ConvertUtil.displayXml(input, properties.getXmlIdentFactor());
            applicationUI.processSuccess(result);
        } catch(ConvertException ex) {
            applicationUI.processError(String.format(ERROR_XML, ex.getMessage()));
        }
    }

    private void jsonToXml() {
        try {
            String input = applicationUI.getLeftPanel().getInputPane().getText();
            String result = ConvertUtil.jsonToXml(input, properties.getJsonIdentFactor(), properties);
            applicationUI.processSuccess(result);
        } catch(ConvertException ex) {
            applicationUI.processError(String.format(ERROR_JSON, ex.getMessage()));
        }
    }

    private void xmlToJson() {
        try {
            String input = applicationUI.getLeftPanel().getInputPane().getText();
            String result = ConvertUtil.xmlToJson(input, properties.getJsonIdentFactor());
            applicationUI.processSuccess(result);
        } catch(ConvertException ex) {
            applicationUI.processError(String.format(ERROR_XML, ex.getMessage()));
        }
    }

    private void csvToJson() {
        try {
            String input = applicationUI.getLeftPanel().getInputPane().getText();
            String result = ConvertUtil.csvToJson(input, properties.getCsvSeparator(), properties.getJsonIdentFactor());
            applicationUI.processSuccess(result);
        } catch(ConvertException ex) {
            applicationUI.processError(String.format(ERROR_CSV, ex.getMessage()));
        }
    }

    private void lowerCases() {
        String input = applicationUI.getLeftPanel().getInputPane().getText();
        String result = input.toLowerCase();
        applicationUI.processSuccess(result);
    }

    private void upperCases() {
        String input = applicationUI.getLeftPanel().getInputPane().getText();
        String result = input.toUpperCase();
        applicationUI.processSuccess(result);
    }

}