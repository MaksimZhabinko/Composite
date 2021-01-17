package com.epam.compositeAndChain.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataParse {
    private final static String PARAGRAPH_PATTERN = "[^\\n\\t]+";
    private final static String SENTENCE_PATTERN = "([А-ЯA-Z]((!=|ob.)|[^?!.(]|\\([^)]*\\))*[.?!]{1,3})";
    private final static String LEXEME_PATTERN = "[(=]?[-\"\\wА-я']+[)]?([.,!?:]?){1,3}";
    private final static String WORD_PATTERN = "[-\"\\wА-я']+";
    private final static String SYMBOL_PATTERN = "[-\"\\wА-я']";
    private final static String PUNCTUATION_PATTERN = "[.=,!?:;)(\\t\\n]";

    public static List<String> parseByParagraphs(String data) {
        Pattern pattern = Pattern.compile(PARAGRAPH_PATTERN);
        Matcher matcher = pattern.matcher(data);
        List<String> paragraphs = new ArrayList<>();
        while (matcher.find()){
            paragraphs.add(matcher.group());
        }
        return paragraphs;
    }

    public static List<String> parseBySentences(String data) {
        Pattern pattern = Pattern.compile(SENTENCE_PATTERN);
        Matcher matcher = pattern.matcher(data);
        List<String> sentences = new ArrayList<>();
        while (matcher.find()){
            sentences.add(matcher.group());
        }
        return sentences;
    }

    public static List<String> parseByLexemes(String data) {
        Pattern pattern = Pattern.compile(LEXEME_PATTERN);
        Matcher matcher = pattern.matcher(data);
        List<String> lexemes = new ArrayList<>();
        while (matcher.find()){
            lexemes.add(matcher.group());
        }
        return lexemes;
    }

    public static List<String> parseByWords(String data) {
        Pattern pattern = Pattern.compile(WORD_PATTERN);
        Matcher matcher = pattern.matcher(data);
        List<String> words = new ArrayList<>();
        while (matcher.find()){
            words.add(matcher.group());
        }
        return words;
    }

    public static List<Character> parseBySymbols(String data) {
        List<Character> symbols = new ArrayList<>();
        char[] array = data.toCharArray();
        for (char c : array) {
            String v = String.valueOf(c);
            if (v.matches(SYMBOL_PATTERN)) {
                char symbol = v.charAt(0);
                symbols.add(symbol);
            }
        }
        return symbols;
    }

    public static List<Character> parseByPunctuation(String data) {
        List<Character> punctuation = new ArrayList<>();
        char[] array = data.toCharArray();
        for (char c : array) {
            String v = String.valueOf(c);
            if (v.matches(PUNCTUATION_PATTERN)) {
                char symbol = v.charAt(0);
                punctuation.add(symbol);
            }
        }
        return punctuation;
    }
}
