package com.epam.compositeAndChain.entity.impl;

import com.epam.compositeAndChain.entity.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Composite implements Component {
    private CompositeType compositeType;
    private List<Component> components = new ArrayList<>();

    public Composite(CompositeType compositeType) {
        this.compositeType = compositeType;
    }

    @Override
    public void add(Component component) {
        components.add(component);
    }

    @Override
    public void remove(Component component) {
        components.remove(component);
    }

    @Override
    public int size() {
        return components.size();
    }

    @Override
    public List<Component> getList() {
        return new ArrayList<>(components);
    }

    @Override
    public Component getChild(int index) {
        return components.get(index);
    }

    @Override
    public CompositeType getType() {
        return compositeType;
    }

    @Override
    public String toString() {
        StringJoiner result = new StringJoiner(compositeType.getDelimiter(), compositeType.getPrefix(), "");
        components.forEach( x -> result.add(x.toString()));
        return result.toString();
    }
}



