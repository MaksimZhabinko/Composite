package com.epam.compositeAndChain.reader;

import com.epam.compositeAndChain.exception.ReadException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataReader {

    private static final String PATH = "src/main/resources/data.txt";

    public String readFromFile() throws ReadException {
        String text;
        try {
            text = Files.readString(Paths.get(PATH));
        } catch (IOException e) {
            throw new ReadException("error by reading in the file " + PATH, e);
        }
        return text;
    }
}
