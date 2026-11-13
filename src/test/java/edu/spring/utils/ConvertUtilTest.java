package edu.spring.utils;

import edu.spring.exception.ConvertException;
import edu.spring.repository.AppProperties;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = {AppProperties.class})
@ActiveProfiles("test")
public class ConvertUtilTest {

    private static final Logger LOG = LoggerFactory.getLogger(ConvertUtilTest.class);

    @Autowired
    private AppProperties properties;

    @Test
    public void trimJson() throws ConvertException, IOException {
        String json = FileUtil.getFileContent("./src/test/resources/test-data/suppliers.json");
        String expectedResult = FileUtil.getFileContent("./src/test/resources/test-data/trimmed-suppliers.json");
        String result = ConvertUtil.trimJson(json);
        assertNotNull(result);
        assertEquals(expectedResult, result);
    }

    @Test
    public void trimJsonWithError() {
        try {
            String result = ConvertUtil.trimJson("foo");
            fail("it cannot work");
        } catch(ConvertException ex) {
            assertTrue(true, "exception confirmed");
        }
    }

    @Test
    public void displayJson() throws ConvertException, IOException {
        String json = FileUtil.getFileContent("./src/test/resources/test-data/trimmed-suppliers.json");
        String expectedResult = FileUtil.getFileContent("./src/test/resources/test-data/suppliers-displayed.json");
        String result = ConvertUtil.displayJson(json, 5);
        LOG.debug("displayed json : \n {}", result);
        assertNotNull(result);
        assertEquals(expectedResult, result);
    }

    @Test
    public void displayJsonWithError() {
        try {
            String result = ConvertUtil.displayJson("foo", 4);
            fail("it cannot work");
        } catch(ConvertException ex) {
            assertTrue(true, "exception confirmed");
        }
    }

    @Test
    public void trimXmlAsFile() throws ConvertException, IOException {
        String xml = FileUtil.getFileContent("./src/test/resources/test-data/menu.xml");
        String expectedResult = FileUtil.getFileContent("./src/test/resources/test-data/trimmed-menu.xml");
        String resul = ConvertUtil.trimFile(xml);
        assertEquals(expectedResult, resul);
    }

    @Test
    public void trimXml() throws ConvertException, IOException {
        String xml = FileUtil.getFileContent("./src/test/resources/test-data/menu.xml");
        String expectedResult = FileUtil.getFileContent("./src/test/resources/test-data/trimmed-menu.xml");
        String resul = ConvertUtil.trimXml(xml);
        assertEquals(expectedResult, resul);
    }

    @Test
    public void trimXmlWithError() {
        try {
            String result = ConvertUtil.trimXml("foo");
            fail("it cannot work");
        } catch(ConvertException ex) {
            assertTrue(true, "exception confirmed");
        }
    }

    @Test
    public void displayXml() throws ConvertException, IOException {
        String xml = FileUtil.getFileContent("./src/test/resources/test-data/trimmed-menu.xml");
        String expectedResult = FileUtil.getFileContent("./src/test/resources/test-data/menu.xml");
        String resul = ConvertUtil.displayXml(xml, 2);
        assertEquals(expectedResult, resul);
    }

    @Test
    public void displayXmlWithError() {
        try {
            String result = ConvertUtil.displayXml("foo", 4);
            fail("it cannot work");
        } catch(ConvertException ex) {
            assertTrue(true, "exception confirmed");
        }
    }

    @Test
    public void trimFile() throws ConvertException {
        String input = "hello world\n" +
                "hello again\n" +
                "it is me";
        String expected = "hello worldhello againit is me";
        String result = ConvertUtil.trimFile(input);
        assertEquals(expected, result);
    }

    @Test
    public void xmlToJson() throws ConvertException, IOException {
        String xml = FileUtil.getFileContent("./src/test/resources/test-data/menu.xml");
        String expectedResult = FileUtil.getFileContent("./src/test/resources/test-data/menu-xml-to-json.json");
        String result = ConvertUtil.xmlToJson(xml, 5);
        LOG.debug("json : \n{}", result);
        assertNotNull(result);
        assertEquals(expectedResult, result);
    }

    @Test
    public void xmlToJsonWithError() {
        try {
            String result = ConvertUtil.xmlToJson("<foo>", 4);
            fail("it cannot work");
        } catch(ConvertException ex) {
            assertTrue(true, "exception confirmed");
        }
    }

    @Test
    public void jsonToXml() throws ConvertException, IOException {
        String json = FileUtil.getFileContent("./src/test/resources/test-data/suppliers.json");
        String expectedResult = FileUtil.getFileContent("./src/test/resources/test-data/suppliers-json-to-xml.xml");
        String result = ConvertUtil.jsonToXml(json,4, properties);
        assertNotNull(result);
        assertEquals(expectedResult, result);
        LOG.debug("xml : \n{}", result);
    }

    @Test
    public void jsonToXmlWithError() {
        try {
            String result = ConvertUtil.jsonToXml("foo", 4, null);
            fail("it cannot work");
        } catch(ConvertException ex) {
            assertTrue(true, "exception confirmed");
        }
    }

    @Test
    public void csvToJson() throws ConvertException {
        StringBuilder csv = new StringBuilder();
        csv.append("day;color;message\n");
        csv.append("monday;red;hello world\n");
        csv.append("tuesday;blue;it is sunny\n");
        csv.append("\n \n ");
        csv.append("wednesday;yellow;it is raining\n");
        csv.append("  \n   \n  ");

        String result = ConvertUtil.csvToJson(csv.toString(), ";", 5);
        LOG.debug("json : \n{}", result);
        assertNotNull(result);

        JSONObject jsonResult = new JSONObject(result);
        assertNotNull(jsonResult.getJSONArray("records"));
        assertEquals(3, jsonResult.getJSONArray("records").length());

        JSONObject jsonOne = jsonResult.getJSONArray("records").getJSONObject(0);
        assertNotNull(jsonOne);
        assertEquals("red", jsonOne.getString("color"));
        assertEquals("hello world", jsonOne.getString("message"));
        assertEquals("monday", jsonOne.getString("day"));

        JSONObject jsonTwo = jsonResult.getJSONArray("records").getJSONObject(1);
        assertNotNull(jsonTwo);
        assertEquals("blue", jsonTwo.getString("color"));
        assertEquals("it is sunny", jsonTwo.getString("message"));
        assertEquals("tuesday", jsonTwo.getString("day"));

        JSONObject jsonThree = jsonResult.getJSONArray("records").getJSONObject(2);
        assertNotNull(jsonThree);
        assertEquals("yellow", jsonThree.getString("color"));
        assertEquals("it is raining", jsonThree.getString("message"));
        assertEquals("wednesday", jsonThree.getString("day"));
    }
}