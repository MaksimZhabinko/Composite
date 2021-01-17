package com.epam.compositeAndChain.parser.impl;

import com.epam.compositeAndChain.entity.impl.Composite;
import com.epam.compositeAndChain.entity.impl.CompositeType;
import com.epam.compositeAndChain.parser.BaseParse;
import com.epam.compositeAndChain.parser.DataParse;

import java.util.List;

public class WordParse implements BaseParse {
    private BaseParse parse;

    public WordParse(BaseParse parse) {
        this.parse = parse;
    }

    @Override
    public Composite parse(String data) {
        Composite wordComposite = new Composite(CompositeType.WORD);
        List<String> words = DataParse.parseByWords(data);
        for (String word: words) {
            Composite nextComposite = parse.parse(word);
            wordComposite.add(nextComposite);
        }
        return wordComposite;
    }
}
