package com.epam.compositeAndChain;

import com.epam.compositeAndChain.entity.Component;
import com.epam.compositeAndChain.entity.impl.Composite;
import com.epam.compositeAndChain.exception.ReadException;
import com.epam.compositeAndChain.parser.impl.*;
import com.epam.compositeAndChain.reader.DataReader;
import com.epam.compositeAndChain.service.TextService;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws ReadException {
        DataReader dataReader = new DataReader();
        String data = dataReader.readFromFile();
//        System.out.println(data);
        Composite composite = new ParagraphParse(new SentenceParse(
                new LexemeParse(new WordParse(new SymbolParse())))).parse(data);
//        System.out.println(composite.toString());

        TextService service = new TextService();


        int i = service.countVowel(composite);
        int i1 = service.countConsonant(composite);
        System.out.println(i);
        System.out.println(i1);

//        // sort
//        System.out.println("SORT");
//        List<Component> sort = service.sort(composite);
//        sort.stream().forEach(x -> System.out.println(x.toString()));
//        // longestWordSentences
//        System.out.println("LONGEST WORD SENTENCE");
//        StringBuilder longestWordSentences = service.findLongestWordSentences(composite);
//        System.out.println(longestWordSentences);
//        // removeSentences
//        System.out.println("REMOVE");
//        Composite compositeRemove = new SentenceParse(
//                new LexemeParse(new WordParse(new SymbolParse()))).parse(data);
//        List<String> removeSentences = service.removeSentences(compositeRemove, 20);
//        removeSentences.stream().forEach(x -> System.out.println(x));
//        // countEqualWords
//        System.out.println("countEqualWords");
//        Map<String, Integer> map = service.countEqualWords(composite);
//        map.entrySet().stream().forEach(x -> System.out.println(x.getKey() +" " +  x.getValue()));
    }
}
