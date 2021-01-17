package com.epam.compositeAndChain.service;

import com.epam.compositeAndChain.entity.Component;
import com.epam.compositeAndChain.entity.impl.Composite;
import com.epam.compositeAndChain.exception.ReadException;
import com.epam.compositeAndChain.parser.impl.*;
import com.epam.compositeAndChain.reader.DataReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

public class TextServiceTest {
    DataReader dataReader = new DataReader();
    TextService service = new TextService();
    String data;
    Composite composite;

    @BeforeMethod
    public void before() throws ReadException {
        data = dataReader.readFromFile();
        composite = new ParagraphParse(new SentenceParse(
                new LexemeParse(new WordParse(new SymbolParse())))).parse(data);
    }

    @Test
    public void testSort() {
        List<Component> sort = service.sort(composite);
        Assert.assertNotEquals(sort, composite);
    }

    @Test
    public void testFindLongestWordSentences() {
        StringBuilder actual = service.findLongestWordSentences(composite);
        StringBuilder expected = new StringBuilder("It is a long a ! = b established fact that a reader will be distracted by the readable content of a page when looking at its layout . The point of using Ipsum is that it has a more-or-less normal distribution ob . toString ( a ? b : c ) , as opposed to using ( Content here ) , content here's , making it look like readable English ?");
        Assert.assertEquals(actual.toString(), expected.toString());
    }

    @Test
    public void testRemoveSentences() {
        Composite compositeRemove = new SentenceParse(
                new LexemeParse(new WordParse(new SymbolParse()))).parse(data);
        List<String> actual = service.removeSentences(compositeRemove, 20);
        List<String> expected = new ArrayList<>();
        expected.add("It has survived - not only ( five ) centuries , but also the leap into electronic typesetting , remaining essentially unchanged .");
        expected.add("String containing Lorem Ipsum passages , and more recently with desktop publishing software like Aldus PageMaker Faclon9 including versions of Lorem Ipsum !");
        expected.add("It is a long a ! = b established fact that a reader will be distracted by the readable content of a page when looking at its layout .");
        expected.add("The point of using Ipsum is that it has a more-or-less normal distribution ob . toString ( a ? b : c ) , as opposed to using ( Content here ) , content here's , making it look like readable English ?");
        expected.add("It is a established fact that a reader will be of a page when looking at its layout . . .");
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testCountEqualWords() {
        Map<String, Integer> actual = service.countEqualWords(composite);
        Map<String, Integer> expected = new HashMap<>();
        expected.put("fact", 2);
        expected.put("be", 2);
        expected.put("reader", 2);
        expected.put("when", 2);
        expected.put("content", 3);
        expected.put("lorem", 2);
        expected.put("that", 3);
        expected.put("of", 5);
        expected.put("has", 2);
        expected.put("readable", 2);
        expected.put("established", 2);
        expected.put("a", 9);
        expected.put("using", 2);
        expected.put("b", 2);
        expected.put("like", 2);
        expected.put("will", 2);
        expected.put("its", 2);
        expected.put("is", 3);
        expected.put("it", 6);
        expected.put("the", 5);
        expected.put("layout", 2);
        expected.put("with", 2);
        expected.put("at", 2);
        expected.put("ipsum", 3);
        expected.put("looking", 2);
        expected.put("page", 2);
        Assert.assertEquals(actual, expected);
    }

    public void testCountVowel() {
        int actual = service.countVowel(composite);
        int expected = 240;
        Assert.assertEquals(actual, expected);
    }

    public void testCountConsonant() {
        int actual = service.countConsonant(composite);
        int expected = 361;
        Assert.assertEquals(actual, expected);
    }
}
