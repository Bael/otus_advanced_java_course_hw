package com.github.bael.cache.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.github.bael.cache.CacheLoadingException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileLoaderTest {

    public static final String FILE_NAME = "file.txt";
    private FileLoader fileLoader;

    @BeforeEach
    void setUp() throws IOException {
        Path tempDir = Files.createTempDirectory("load");
        Files.writeString(tempDir.resolve(FILE_NAME), "Happy path!");
        fileLoader = new FileLoader(tempDir.toString());
    }
    @Test
    void loadExistingFileCompletedSuccessfully() throws IOException {
        String load = fileLoader.load(FILE_NAME);
        assertEquals("Happy path!", load);
    }

    @Test
    void loadNonExistingFileThrowsException() throws IOException {
        assertThrows(
            CacheLoadingException.class,
            () -> fileLoader.load("wrong_filename")
        );
    }

    @Test
    void loaderCreatedOnWrongDirectoryThrowsException() throws IOException {
        assertThrows(
            CacheLoadingException.class,
            () -> new FileLoader(UUID.randomUUID().toString()).load("wrong_filename")
        );
    }

}
