package edu.spring.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileUtil {

    private FileUtil() {}

    /**
     * Get file content as a string.
     * @param filePath file path
     * @return file content in a string
     * @throws IOException any exception
     */
    public static String getFileContent(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return new String(Files.readAllBytes(path));
    }

    /**
     * Create a file from a byte array
     * @param fileContent file content in a byte array
     * @param filePath file path
     * @throws IOException any exception
     */
    public static void create(String filePath, byte[] fileContent) throws IOException {
        Path path = Paths.get(filePath);
        new File(filePath).getParentFile().mkdirs();
        Files.write(path, fileContent);
    }

    /**
     * Create an empty directory.
     * @param directoryPath path of directory to create
     * @throws IOException any exception
     */
    public static void createDirectory(String directoryPath) throws IOException {
        Files.createDirectories(Paths.get(directoryPath));
    }

    /**
     * Clear a directory.
     * @param directoryPath directory path
     * @throws IOException any exception
     */
    public static void clearDirectory(String directoryPath) throws IOException {
        Path path = Paths.get(directoryPath);
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /**
     * Does a file or folder exist ?
     * @param filePath file or folder path
     * @return yes if the file or folder exist, false otherwise
     */
    public static boolean doesPathExist(String filePath) {
        Path path = Paths.get(filePath);
        return path.toFile().exists();
    }
}