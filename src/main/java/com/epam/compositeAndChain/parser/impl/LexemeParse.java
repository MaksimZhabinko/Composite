package com.epam.compositeAndChain.parser.impl;

import com.epam.compositeAndChain.entity.Component;
import com.epam.compositeAndChain.entity.impl.Composite;
import com.epam.compositeAndChain.entity.impl.CompositeType;
import com.epam.compositeAndChain.entity.impl.PunctuationLeaf;
import com.epam.compositeAndChain.parser.BaseParse;
import com.epam.compositeAndChain.parser.DataParse;

import java.util.List;

public class LexemeParse  implements BaseParse {
    private BaseParse parse;

    public LexemeParse(BaseParse parse) {
        this.parse = parse;
    }

    @Override
    public Composite parse(String data){
        Composite lexemeComposite = new Composite(CompositeType.LEXEME);
        List<String> lexemeParse = DataParse.parseByLexemes(data);
        for (String lexeme: lexemeParse) {
            List<Character> punctuations = DataParse.parseByPunctuation(lexeme);
            for (Character punctuation : punctuations) {
                if (punctuation == '(' || punctuation == '=') {
                    Component component = new PunctuationLeaf(punctuation);
                    lexemeComposite.add(component);
                }
            }
            Composite nextComposite = parse.parse(lexeme);
            lexemeComposite.add(nextComposite);
            for (Character punctuation : punctuations) {                           //добавляем пунктуацию в конце лексемы
                if (punctuation == '(' || punctuation == '=') {
                    continue;
                }
                Component component = new PunctuationLeaf(punctuation);
                lexemeComposite.add(component);
            }
        }
        return lexemeComposite;
    }

}
