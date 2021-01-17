package com.epam.compositeAndChain.parser.impl;

import com.epam.compositeAndChain.entity.impl.Composite;
import com.epam.compositeAndChain.entity.impl.CompositeType;
import com.epam.compositeAndChain.parser.BaseParse;
import com.epam.compositeAndChain.parser.DataParse;

import java.util.List;

public class ParagraphParse implements BaseParse {
    private BaseParse parse;

    public ParagraphParse(BaseParse parse){
        this.parse = parse;
    }

    @Override
    public Composite parse(String data){
        Composite paragraphComposite = new Composite(CompositeType.PARAGRAPH);
        List<String> paragraphParse = DataParse.parseByParagraphs(data);
        for (String paragraph: paragraphParse) {
            Composite nextComposite = parse.parse(paragraph);
            paragraphComposite.add(nextComposite);
        }
        return paragraphComposite;
    }
}

