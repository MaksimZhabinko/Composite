package com.epam.compositeAndChain.entity.impl;

import com.epam.compositeAndChain.entity.Component;

import java.util.List;

public class SymbolLeaf implements Component {
    private char symbol;

    public SymbolLeaf(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public void add(Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public List<Component> getList() {
        throw new UnsupportedOperationException();

    }

    @Override
    public Component getChild(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }

    @Override
    public CompositeType getType() {
        throw new UnsupportedOperationException();
    }
}
