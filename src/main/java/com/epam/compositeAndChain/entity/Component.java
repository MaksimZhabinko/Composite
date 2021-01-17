package com.epam.compositeAndChain.entity;

import com.epam.compositeAndChain.entity.impl.CompositeType;

import java.util.List;

public interface Component {
    void add(Component component);
    void remove(Component component);
    int size();
    List<Component> getList();
    Component getChild(int index);
    CompositeType getType();
}
