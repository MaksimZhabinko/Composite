package com.epam.compositeAndChain.parser;

import com.epam.compositeAndChain.entity.impl.Composite;


public interface BaseParse {
    Composite parse(String data);
}
