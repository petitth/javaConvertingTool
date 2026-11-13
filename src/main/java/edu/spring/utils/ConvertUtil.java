package edu.spring.utils;

import edu.spring.exception.ConvertException;
import edu.spring.repository.AppProperties;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class ConvertUtil {

    private ConvertUtil() { }

    public static String trimFile(String content) throws ConvertException {
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new StringReader(content))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line.trim());
            }
            return result.toString();

        } catch(IOException ioEx) {
            throw new ConvertException(ioEx.getMessage());
        }
    }

    public static String trimJson(String content) throws ConvertException {
        try {
            JSONObject json = new JSONObject(content);
            return json.toString(0);
        } catch(JSONException ex) {
            throw new ConvertException(ex.getMessage());
        }
    }

    public static String trimXml(String content) throws ConvertException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // prevent XML EXternal Entity (XXE) attacks
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(content)));
            document.setXmlStandalone(true);

            //---------- use xpath to identify and remove empty nodes ----------
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xpath = xPathFactory.newXPath();
            NodeList empty = (NodeList)xpath.evaluate("//text()[normalize-space(.) = '']", document, XPathConstants.NODESET);
            for (int i = 0; i < empty.getLength(); i++) {
                Node node = empty.item(i);
                node.getParentNode().removeChild(node);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            // secure this "Transformer" by disabling external DTDs
            transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
            Transformer transformer = transformerFactory.newTransformer();
            StringWriter writer = new StringWriter();
            DOMSource source = new DOMSource(document);
            transformer.transform(source, new StreamResult(writer));
            return writer.toString().trim();

        } catch(SAXException | IOException | ParserConfigurationException | TransformerException | XPathExpressionException ex) {
            throw new ConvertException(ex.getMessage());
        }
    }

    public static String displayJson(String content, int identFactor) throws ConvertException {
        try {
            JSONObject json = new JSONObject(content);
            return json.toString(identFactor);
        } catch(JSONException ex) {
            throw new ConvertException(ex.getMessage());
        }
    }

    public static String xmlToJson(String xmlContent, int identFactor) throws ConvertException {
        try {
            JSONObject json = XML.toJSONObject(xmlContent);
            return json.toString(identFactor);
        } catch(JSONException ex) {
            throw new ConvertException(ex.getMessage());
        }
    }

    public static String jsonToXml(String content, int identFactor, AppProperties properties) throws ConvertException {
        try {
            JSONObject json = new JSONObject(content);
            String xmlBody = XML.toString(json);

            StringBuilder result = new StringBuilder();
            result.append(properties.getXmlDefaultHeader());
            result.append(properties.getXmlDefaultRootStart());
            result.append(xmlBody);
            result.append(properties.getXmlDefaultRootEnd());
            return displayXml(result.toString(), identFactor);

        } catch(JSONException ex) {
            throw new ConvertException(ex.getMessage());
        }
    }

    public static String displayXml(String content, int identFactor) throws ConvertException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // prevent XML EXternal Entity (XXE) attacks
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(content)));
            document.setXmlStandalone(true);
            DOMSource source = new DOMSource(document);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            // secure this "Transformer" by disabling external DTDs
            transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", ""+identFactor);

            StringWriter writer = new StringWriter();
            transformer.transform(source, new StreamResult(writer));
            return writer.toString().trim();

        } catch(SAXException | IOException | ParserConfigurationException | TransformerException ex) {
            throw new ConvertException(ex.getMessage());
        }
    }

    public static String csvToJson(String csvContent, String csvSeparator, int identFactor) throws ConvertException {
        try {
            JSONArray jsonRecords = new JSONArray();
            String[] csvLines = csvContent.trim().split("\n");
            String[] headers = csvLines[0].split(csvSeparator);
            for (int line=1; line<csvLines.length; line++) {
                if (csvLines[line].trim().isEmpty()) {
                    continue;
                }
                JSONObject jsonRecord = new JSONObject();
                String[] csvLine = csvLines[line].split(csvSeparator);
                for (int col=0; col<headers.length; col++) {
                    jsonRecord.put(headers[col].trim(), csvLine[col].trim());
                }
                jsonRecords.put(jsonRecord);
            }

            JSONObject jsonResult = new JSONObject();
            jsonResult.put("records", jsonRecords);
            return displayJson(jsonResult.toString(), identFactor);

        } catch(JSONException ex) {
            throw new ConvertException(ex.getMessage());
        }
    }
}