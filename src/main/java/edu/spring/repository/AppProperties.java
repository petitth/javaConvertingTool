package edu.spring.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.awt.*;

@Repository
public class AppProperties {

    private static final Logger LOG = LoggerFactory.getLogger(AppProperties.class);

    @Value("${json.identFactor}")
    private Integer jsonIdentFactor;

    @Value("${xml.identFactor}")
    private Integer xmlIdentFactor;

    @Value("${xml.default.header}")
    private String xmlDefaultHeader;

    @Value("${xml.default.root}")
    private String xmlDefaultRootStart;

    @Value("${screen.height}")
    private Integer screenHeight;

    @Value("${screen.width}")
    private Integer screenWidth;

    @Value("${color.error}")
    private String colorError;

    @Value("${color.success}")
    private String colorSuccess;

    @Value("${color.input}")
    private String colorInput;

    @Value("${color.default.result}")
    private String colorDefaultResult;

    @Value("${csv.separator}")
    private String csvSeparator;

    public Integer getJsonIdentFactor() {
        return jsonIdentFactor;
    }

    public Integer getXmlIdentFactor() {
        return xmlIdentFactor;
    }

    public String getXmlDefaultHeader() {
        return xmlDefaultHeader;
    }

    public String getXmlDefaultRootStart() {
        return xmlDefaultRootStart;
    }

    public String getXmlDefaultRootEnd() {
        if (xmlDefaultRootStart != null && !xmlDefaultRootStart.isEmpty()) {
            return xmlDefaultRootStart.replaceAll("<","</");
        } else {
            return null;
        }
    }

    public Integer getScreenHeight() {
        return screenHeight;
    }

    public Integer getScreenWidth() {
        return screenWidth;
    }

    public Color getColorError() {
        return getColor(colorError);
    }

    public Color getColorSuccess() {
        return getColor(colorSuccess);
    }

    public Color getColorInput() {
        return getColor(colorInput);
    }

    public Color getColorDefaultResult() {
        return getColor(colorDefaultResult);
    }

    private Color getColor(String code) {
        try {
            String[] codes = code.split(":");
            return new Color(Integer.parseInt(codes[0].trim()), Integer.parseInt(codes[1].trim()), Integer.parseInt(codes[2].trim()));
        } catch(NumberFormatException | ArrayIndexOutOfBoundsException ex) {
            LOG.error("cannot parse color with code '{}', default white be apllied", code);
            return Color.white;
        }
    }

    public String getCsvSeparator() {
        return csvSeparator;
    }
}
