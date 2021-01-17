package com.epam.compositeAndChain.parser.impl;

import com.epam.compositeAndChain.entity.impl.Composite;
import com.epam.compositeAndChain.entity.impl.CompositeType;
import com.epam.compositeAndChain.parser.BaseParse;
import com.epam.compositeAndChain.parser.DataParse;

import java.util.List;

public class SentenceParse implements BaseParse {
    private BaseParse parse;

    public SentenceParse(BaseParse parse) {
        this.parse = parse;
    }

    @Override
    public Composite parse(String data) {
        Composite sentenceComposite = new Composite(CompositeType.SENTENCE);
        List<String> sentenceParse = DataParse.parseBySentences(data);
        for (String sentence: sentenceParse) {
            Composite nextComposite = parse.parse(sentence);
            sentenceComposite.add(nextComposite);
        }
        return sentenceComposite;
    }
}
