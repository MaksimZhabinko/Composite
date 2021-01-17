package com.epam.compositeAndChain.entity.impl;

public enum CompositeType {
    PARAGRAPH ("\n\t", "\t"),
    SENTENCE (" ", ""),
    LEXEME (" ", ""),
    WORD ("", ""),
    SYMBOL ("", "");


    private final String delimiter;
    private final String prefix;

    CompositeType(String delimiter, String prefix) {
        this.delimiter = delimiter;
        this.prefix = prefix;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public String getPrefix() {
        return prefix;
    }
}
