package com.github.bael.cache.impl;

import com.github.bael.cache.CacheLoadable;
import com.github.bael.cache.CacheLoadingException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileLoader implements CacheLoadable<String, String> {

    private final String directory;

    public FileLoader(String directory) {
        File directoryFile = new File(directory);
        if (!directoryFile.exists()) {
            throw new CacheLoadingException("Directory does not exist: " + directory);
        }
        if (!directoryFile.isDirectory()) {
            throw new CacheLoadingException("Specified directory is a file! " + directory);
        }

        this.directory = directory;

    }

    @Override
    public String load(String filename) {
        try {
            return Files.readString(Paths.get(directory, filename), Charset.defaultCharset());
        } catch (IOException e) {
            throw new CacheLoadingException("Ошибка при чтении файла!", e);
        }
    }

}
