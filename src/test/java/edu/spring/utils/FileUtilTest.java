package edu.spring.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
public class FileUtilTest {

    @TempDir
    Path tempDir;

    private String tempFolder;

    @BeforeEach
    public void setup() throws IOException {
        tempFolder = tempDir.toString() + File.separator + "convert-tool";
        Files.createDirectories(Paths.get(tempFolder));
    }

    @Test
    public void readFileFromResourcesAlternateWay() throws IOException {
        String content = FileUtil.getFileContent("./src/test/resources/animal.txt");
        assertEquals("dog\ncat\nbird\nhorse\nmouse", content);
    }

    @Test
    public void createFileFromByte() throws Exception {
        byte[] fileContent = "hello world".getBytes();
        String testFilePath = tempFolder + File.separator +  "abc" + File.separator + "test";
        FileUtil.create(testFilePath, fileContent);
        assertTrue(FileUtil.doesPathExist(testFilePath));
        assertEquals("hello world", FileUtil.getFileContent(testFilePath));
    }
}
