package com.frank.hound.core.codec.protocol;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@AllArgsConstructor
public class OriginalContent {


    /**
     * 协议组成名->body
     */
    private final Map<String,Object> elementMap = new HashMap<>();

    public void addElement(String elementKey,Object element) {
        elementMap.put(elementKey,element);
    }

    public Iterator<Map.Entry<String,Object>> elementIterator() {
        return elementMap.entrySet().iterator();
    }

}
