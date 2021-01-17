package com.epam.compositeAndChain.parser.impl;

import com.epam.compositeAndChain.entity.Component;
import com.epam.compositeAndChain.entity.impl.Composite;
import com.epam.compositeAndChain.entity.impl.CompositeType;
import com.epam.compositeAndChain.entity.impl.SymbolLeaf;
import com.epam.compositeAndChain.parser.BaseParse;
import com.epam.compositeAndChain.parser.DataParse;

import java.util.List;

public class SymbolParse implements BaseParse {
    public SymbolParse() {
    }

    @Override
    public Composite parse(String data) {
        Composite symbolComposite = new Composite(CompositeType.SYMBOL);
        List<Character> symbols = DataParse.parseBySymbols(data);
        for (char symbol : symbols) {
            Component component = new SymbolLeaf(symbol);
            symbolComposite.add(component);
        }
        return symbolComposite;
    }
}
