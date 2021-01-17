package com.epam.compositeAndChain.comparator;

import com.epam.compositeAndChain.entity.impl.Composite;

import java.util.Comparator;

public enum  CompositeComparator implements Comparator<Composite> {

    COMPONENTS_NUMBER {
        @Override
        public int compare(Composite o1, Composite o2) {
            int list1Size = o1.size();
            int list2Size = o2.size();
            return Integer.compare(list1Size, list2Size);
        }
    }
}
