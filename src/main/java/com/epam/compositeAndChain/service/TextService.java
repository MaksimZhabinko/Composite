package com.epam.compositeAndChain.service;

import com.epam.compositeAndChain.comparator.CompositeComparator;
import com.epam.compositeAndChain.entity.Component;
import com.epam.compositeAndChain.entity.impl.Composite;
import com.epam.compositeAndChain.entity.impl.CompositeType;
import com.epam.compositeAndChain.parser.DataParse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextService {
    private static final Logger logger = LogManager.getLogger(TextService.class);

    public List<Component> sort(Component component) {
        List<Component> components = component.getList();
        if (component.getType().equals(CompositeType.PARAGRAPH) || component.getType().equals(CompositeType.SENTENCE) || component.getType().equals(CompositeType.LEXEME)) {
            List<Composite> textCompositeList = new ArrayList<>();
            components.stream().forEach(x -> textCompositeList.add((Composite) x));
            textCompositeList.sort(CompositeComparator.COMPONENTS_NUMBER);
            components.clear();
            components.addAll(textCompositeList);
        } else {
            logger.error("CompositeComparator doesn't work on this level of composite.");
        }
        return components;
    }


    public StringBuilder findLongestWordSentences(Composite composite) {
        if (composite != null && composite.getType() == CompositeType.PARAGRAPH) {
            String restoredText = composite.toString();
            List<String> words = DataParse.parseByWords(restoredText);
            Comparator<String> comparator = Comparator.comparingInt(String::length);
            words.sort(comparator);
            String longestWord = words.get(words.size() - 1);
            StringBuilder longestWordSentences = new StringBuilder();
            List<Component> sentences = composite.getList();
            for (Component sentence : sentences) {
                Pattern pattern = Pattern.compile(longestWord);
                Matcher matcher = pattern.matcher(sentence.toString());
                if (matcher.find()) {
                    longestWordSentences.append(sentence.toString());
                }
            }
            return longestWordSentences;
        } else {
            logger.error("");
            throw new RuntimeException();
        }
    }

    public List<String> removeSentences(Composite composite, int restrictionNumber) {
        if (composite != null && composite.getType() == CompositeType.SENTENCE) {
            List<Component> sentences = composite.getList();
            List<String> restedSentences = new ArrayList<>();
            for (Component sentence : sentences) {
                if (sentence.getList().size() > restrictionNumber) {
                    restedSentences.add(sentence.toString());
                }
            }
            return restedSentences;
        } else {
            logger.error("");
            throw new RuntimeException();
        }
    }

    public Map<String, Integer> countEqualWords(Composite composite) {
        if (composite != null && composite.getType() == CompositeType.PARAGRAPH) {
            Map<String, Integer> result = new HashMap<>();
            String restoredText = composite.toString();
            List<String> words = DataParse.parseByWords(restoredText);
            for (String word : words) {
                String wordLowerCase = word.toLowerCase();
                if (result.get(wordLowerCase) != null) {
                    int count = result.get(wordLowerCase);
                    result.put(wordLowerCase, ++count);
                } else {
                    result.put(wordLowerCase, 1);
                }
            }
            Map<String, Integer> collect = new HashMap<>();
            for (Map.Entry<String, Integer> e : result.entrySet()) {
                if (e.getValue() != 1) {
                    collect.put(e.getKey(), e.getValue());
                }
            }
//            Map<String, Integer> collect = result.entrySet().stream().filter(e -> e.getValue() != 1).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            return collect;
        } else {
            logger.error("");
            throw new RuntimeException();
        }
    }

    public int countVowel(Composite composite) {
        int result = 0;
        if (composite != null && composite.getType() == CompositeType.PARAGRAPH) {
            List<Character> symbols = DataParse.parseBySymbols(composite.toString().toLowerCase());
            for (Character symbol : symbols) {
                result += isVowel(symbol.toString());
            }
        }
        return result;
    }

    private int isVowel(String symbol) {
        String vowels = "aeqyuioj";
        if (vowels.contains(symbol)) {
            return 1;
        }
        return 0;
    }

    public int countConsonant(Composite composite) {
        int result = 0;
        if (composite != null && composite.getType() == CompositeType.PARAGRAPH) {
            List<Character> symbols = DataParse.parseBySymbols(composite.toString().toLowerCase());
            for (Character symbol : symbols) {
                result += isConsonant(symbol.toString());
            }
        }
        return result;
    }

    private int isConsonant(String s) {
        String consonant = "bcdfghjklmnpqrstvwxz";
        if (consonant.contains(s)) {
            return 1;
        }
        return 0;
    }
}
